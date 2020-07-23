//package com.build.myapplication.Fragment;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.build.myapplication.Adapter.ChudeAdapter;
//import com.build.myapplication.Model.Chude;
//import com.build.myapplication.Model.ChudeAll;
//import com.build.myapplication.R;
//import com.build.myapplication.Service.APIService;
//import com.build.myapplication.Service.DataService;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class Fragment_Chude extends Fragment {
//    ChudeAdapter chudeAdapter;
//    ArrayList<Chude> ChuDe,chudeCurrentDays;
//    ChudeAll ChudeAll;
//    ListView listView;
//    View  view;
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_chude,container,false);
//        GetData();
//        AnhXa();
//        return view;
//    }
//
//    private void GetData() {
//        DataService dataService = APIService.getService();
//        Call<ChudeAll> callback = dataService.GetChuDeAllAndCurrentDay();
//        callback.enqueue(new Callback<ChudeAll>() {
//            @Override
//            public void onResponse(Call<ChudeAll> call, Response<ChudeAll> response) {
//                ChudeAll = response.body();
//                //chudeCurrentDays = (ArrayList<Chude>) ChudeAll.getChudeCurrentDay();
//                ChuDe = (ArrayList<Chude>) ChudeAll.getChude();
//                chudeAdapter = new ChudeAdapter(getContext(),android.R.layout.simple_expandable_list_item_1,ChuDe);
//                listView.setAdapter(chudeAdapter);
//            }
//            @Override
//            public void onFailure(Call<ChudeAll> call, Throwable t) {
//                Toast.makeText(getContext(),"404",Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    private void AnhXa() {
//        listView = view.findViewById(R.id.ListChuDe);
//    }
//}
