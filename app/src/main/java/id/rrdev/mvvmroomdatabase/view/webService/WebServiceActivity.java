package id.rrdev.mvvmroomdatabase.view.webService;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.data.network.response.Room;
import id.rrdev.mvvmroomdatabase.data.network.response.RoomResponse;
import id.rrdev.mvvmroomdatabase.view.roomDb.NoteAdapter;

public class WebServiceActivity extends AppCompatActivity {

    private WebServiceViewModel webServiceViewModel;
    private RoomAdapter roomAdapter;
    private RecyclerView rvRoom;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        init();
    }

    private void init(){
        rvRoom = findViewById(R.id.rvWebService);

        rvRoom.setAdapter(roomAdapter);

        webServiceViewModel = ViewModelProviders.of(this).get(WebServiceViewModel.class);
        textView = findViewById(R.id.test);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllRoom();
    }

    private void getAllRoom(){
        rvRoom.setAdapter(roomAdapter);
        webServiceViewModel.getRoomLiveData().observe(this, roomResponse -> {
            if (roomResponse != null) {
                List<Room> roomResponses = roomResponse.getRoom();
//                roomAdapter.submitList(roomResponses);

                textView.setText(roomResponses.get(1).getNamaRoom());
            }
        });
    }
}