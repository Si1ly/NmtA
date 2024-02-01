package com.example.nmta.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nmta.R;
import com.example.nmta.adapter.RecycleView_Adapter;

import java.util.ArrayList;

public class TestFragment extends FragmentActivity {

    RecyclerView recyclerView;
    ArrayList<Uri> listUrl = new ArrayList<>();
    ArrayList<String> listUser = new ArrayList<>();
    Button bt_add;
    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        bt_add = (Button) findViewById(R.id.bt_add);

        bt_add.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            someActivityResultLauncher.launch(i);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_clear:
                listUrl.clear();
            case R.id.item_save:

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri test_uri = data.getData();
                        Intent i = new Intent(TestFragment.this,EnterUser.class);
                        getDataResult.launch(i);
                        Log.d("asdashgdkas",listUser.size()+"");
                        doSomeOperations(test_uri);
                    }
                }
            });

    private void doSomeOperations(Uri uri) {
        listUrl.add(uri);
        RecycleView_Adapter recycleViewAdapter = new RecycleView_Adapter(listUrl,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
