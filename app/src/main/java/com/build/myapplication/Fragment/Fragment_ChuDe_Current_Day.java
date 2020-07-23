package com.build.myapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.build.myapplication.Activity.ChudeActivity;
import com.build.myapplication.Activity.ListBaiHatActivity;
import com.build.myapplication.Activity.ListPlaylistByChuDe_Activity;
import com.build.myapplication.Model.Chude;
import com.build.myapplication.Model.ChudeAll;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Fragment_ChuDe_Current_Day extends Fragment {
    HorizontalScrollView horizontalScrollView;
    TextView txtMoreChuDe;
    ChudeAll chudeAll;
    ArrayList<Chude> ChuDeCurrentDay;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_current_day, container, false);
        AnhXa();
        GetData();
        return view;
    }

    private void AnhXa() {
        horizontalScrollView = view.findViewById(R.id.scrollViewChuDe);
        //   horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_LEFT);
        txtMoreChuDe = view.findViewById(R.id.moreChuDe);
        txtMoreChuDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChudeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<ChudeAll> chudeAllCall = dataService.GetChuDeAllAndCurrentDay();
        chudeAllCall.enqueue(new Callback<ChudeAll>() {
            @Override
            public void onResponse(Call<ChudeAll> call, Response<ChudeAll> response) {
                chudeAll = response.body();
                ChuDeCurrentDay = (ArrayList<Chude>) chudeAll.getChudeCurrentDay();

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20, 10, 30);
                for (int i = 0; i < ChuDeCurrentDay.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (ChuDeCurrentDay.get(i).getHinhChuDe() != null) {
                        Picasso.get().load(ChuDeCurrentDay.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListPlaylistByChuDe_Activity.class);
                            intent.putExtra("idchude", ChuDeCurrentDay.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChudeAll> call, Throwable t) {

            }
        });
    }
}
