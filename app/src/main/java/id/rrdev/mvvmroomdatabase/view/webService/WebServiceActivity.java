package id.rrdev.mvvmroomdatabase.view.webService;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.data.network.response.Room;
import static id.rrdev.mvvmroomdatabase.util.Constant.KEY_INTENT_ROOM;

public class WebServiceActivity extends AppCompatActivity {
    private WebServiceViewModel webServiceViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RoomAdapter roomAdapter;
    private RecyclerView rvRoom;
    private ProgressBar progressBar;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        init();
        getAllRoom();
        setupView();
    }

    private void init(){
        webServiceViewModel = ViewModelProviders.of(this).get(WebServiceViewModel.class);

        rvRoom = findViewById(R.id.rvWebService);
        progressBar = findViewById(R.id.pbWebservice);
        btnAdd =findViewById(R.id.fabAdd);
        swipeRefreshLayout = findViewById(R.id.swipe);

        roomAdapter = new RoomAdapter();
        rvRoom.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupView() {
        roomAdapter.setOnItemClickListener((view, room, position) -> {
            Intent i = new Intent(this, FormWebServiceActivity.class);
            i.putExtra(KEY_INTENT_ROOM, room);
            startActivity(i);
        });

        roomAdapter.setOnItemLongClickListener((view, room, position) -> {
            new MaterialAlertDialogBuilder(WebServiceActivity.this)
                    .setTitle("Menghapus Ruang "+room.getNamaRoom())
                    .setMessage("Yakin akan menghapus Ruang "+ room.getNamaRoom())
                    .setPositiveButton("YA", ((dialog, which) -> {
                        webServiceViewModel.deleteRoom(room.getIdRoom());
                        getAllRoom();
                    }))
                    .setNegativeButton("TIDAK", ((dialog, which) -> { }))
                    .show();
            return true;
        });

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, FormWebServiceActivity.class));
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
           getAllRoom();
           swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void getAllRoom(){
        progressBar.setVisibility(View.VISIBLE);
        rvRoom.setAdapter(roomAdapter);
        webServiceViewModel.getRoomLiveData().observe(this, roomResponse -> {
            if (roomResponse != null) {
                List<Room> roomResponses = roomResponse.getRoom();
                roomAdapter.submitList(roomResponses);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}