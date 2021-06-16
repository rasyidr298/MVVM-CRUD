package id.rrdev.mvvmroomdatabase.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.view.roomDb.RoomDbActivity;
import id.rrdev.mvvmroomdatabase.view.webService.WebServiceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRoomDb, btnWebService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        btnRoomDb = findViewById(R.id.btnRoomDb);
        btnWebService = findViewById(R.id.btnWebService);

        btnRoomDb.setOnClickListener(this);
        btnWebService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        btnRoomDb.setOnClickListener(v1 -> {
            startActivity(new Intent(this, RoomDbActivity.class));
        });

        btnWebService.setOnClickListener(v2 ->{
            startActivity(new Intent(this, WebServiceActivity.class));
        });
    }
}