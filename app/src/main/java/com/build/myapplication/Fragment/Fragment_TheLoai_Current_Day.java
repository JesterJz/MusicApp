package com.build.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.build.myapplication.Activity.ListBaiHatActivity;
import com.build.myapplication.Activity.TheLoaiActivity;
import com.build.myapplication.Model.QuangCao;
import com.build.myapplication.Model.TheLoai;
import com.build.myapplication.R;
import com.build.myapplication.Service.APIService;
import com.build.myapplication.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TheLoai_Current_Day extends Fragment {

    HorizontalScrollView horizontalScrollView;
    TextView txtmoreTheLoai;
    ArrayList<TheLoai> theLoaiArrayList;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_theloai_current_day, container, false);
        AnhXa();
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> listCall = dataService.GetTheLoaiCurrentDay();
        listCall.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20, 10, 30);
                for (int i = 0; i < theLoaiArrayList.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(i).getHinhTheLoai() != null) {
                        Picasso.get().load(theLoaiArrayList.get(i).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), ListBaiHatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                Log.d("BBB", "don't data return");
            }
        });
    }

    private void AnhXa() {
        horizontalScrollView = view.findViewById(R.id.scrollViewTheLoai);
        txtmoreTheLoai = view.findViewById(R.id.moreTheLoai);
        txtmoreTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TheLoaiActivity.class);
                startActivity(intent);
            }
        });
    }

}
