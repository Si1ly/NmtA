package com.example.nmta.fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.nmta.R;
import com.example.nmta.activity.EnterUser;
import com.example.nmta.adapter.RecycleView_Adapter;

import java.util.ArrayList;


public class addFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Uri> listUrl = new ArrayList<>();
    ArrayList<String> listUser = new ArrayList<>();
    Button bt_add;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_clear:
                listUrl.clear();
            case R.id.item_save:
                listUrl.clear();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycleView);
        bt_add  = view.findViewById(R.id.bt_add);

        bt_add.setOnClickListener(view1 -> {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            someActivityResultLauncher.launch(i);
        });
    }



    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Uri test_uri = data.getData();
                        Intent i = new Intent(getContext(), EnterUser.class);
                        getDataResult.launch(i);
                        Log.d("asdashgdkas",listUser.size()+"");

                        doSomeOperations(test_uri);
                    }
                }
            });

    private void doSomeOperations(Uri uri) {
        listUrl.add(uri);
        RecycleView_Adapter recycleViewAdapter = new RecycleView_Adapter(listUrl,getContext(),listUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleViewAdapter);
    }

    ActivityResultLauncher<Intent> getDataResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == RESULT_OK && o != null ){
                if(o.getData().getStringExtra("USER") != null){
                    String temp = o.getData().getStringExtra("USER");
                    listUser.add(temp);
                }
            }
        }
    });

}