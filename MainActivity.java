package com.example.testvideo_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.ui.PlayerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

import com.example.testvideo_1.Api.ApiService;
import com.example.testvideo_1.Api.Service;
import com.example.testvideo_1.Data.VideoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Uri> listUrl = new ArrayList<>();
    ArrayList<String> listUser = new ArrayList<>();
    Button bt_add;



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        bt_add = (Button) findViewById(R.id.bt_add);

        bt_add.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            someActivityResultLauncher.launch(i);
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri test_uri = data.getData();
                        Intent i = new Intent(MainActivity.this,EnterUser.class);
                        getDataResult.launch(i);
                        Log.d("asdashgdkas",listUser.toString());
//                        doSomeOperations(test_uri);
                    }
                }
            });

    private void doSomeOperations(Uri uri) {
        listUrl.add(uri);
        RecycleView_Adapter recycleViewAdapter = new RecycleView_Adapter(listUrl,this,listUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recycleViewAdapter);
    }

    ActivityResultLauncher<Intent> getDataResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == Activity.RESULT_OK){
                String temp = o.getData().getData().toString();
                listUser.add(temp);
            }
        }
    });

//    private void save_VideotoServer(){
//        VideoItem videoItem = new VideoItem("testUri","testName");
//
//        ApiService apiService = new ApiService();
//        Service service = apiService.getRetorfit().create(Service.class);
//        service.savevideo(videoItem)
//                .enqueue(new Callback<VideoItem>() {
//                    @Override
//                    public void onResponse(Call<VideoItem> call, Response<VideoItem> response) {
//                        Toast.makeText(MainActivity.this, "Succeed!!", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onFailure(Call<VideoItem> call, Throwable t) {
//                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Get Errorrrr",t);
//                    }
//                });
//    }
}