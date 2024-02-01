package com.example.nmta.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nmta.R;
import com.example.nmta.adapter.RecycleView_Adapter;
import com.example.nmta.api.API_Service;
import com.example.nmta.api.Service;
import com.example.nmta.data.VideoItem.VideoItem;

import java.util.List;


public class homeFragment extends Fragment {

   List<VideoItem> projectList;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycleView_Home_Fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        RecycleView_Adapter recycleViewAdapter = new RecycleView_Adapter();

        recycleViewAdapter.notifyDataSetChanged();
    }

    public void initData(List<VideoItem> videoList){
        API_Service apiService = new API_Service();
        Service service = apiService.getRetrofit().create(Service.class);


    }

    private void addData(List<VideoItem> body) {
       RecycleView_Adapter recycleViewAdapter = new RecycleView_Adapter();
        recyclerView.setAdapter(recycleViewAdapter);
    }

}