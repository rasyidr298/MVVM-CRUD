package id.rrdev.mvvmroomdatabase.view.webService;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    @Override
    protected void onResume() {
        super.onResume();
        getAllRoom();
        setupView();
    }

    private void setupView() {
        roomAdapter.setOnItemClickListener((view, note, position) -> {
            Toast.makeText(this, "click ruang" + note.getNamaRoom(), Toast.LENGTH_SHORT).show();
        });

        roomAdapter.setOnItemLongClickListener((view, note, position) -> {
            new MaterialAlertDialogBuilder(WebServiceActivity.this)
                    .setTitle("Menghapus Ruang "+note.getNamaRoom())
                    .setMessage("Yakin akan menghapus Ruang"+ note.getNamaRoom())
                    .setPositiveButton("YA", ((dialog, which) -> {
                        webServiceViewModel.deleteRoom(note.getIdRoom());
                    }))
                    .setNegativeButton("TIDAK", ((dialog, which) -> { }))
                    .show();
            return true;
        });

        btnAdd.setOnClickListener(v -> {
            Toast.makeText(this, "tambah", Toast.LENGTH_SHORT).show();
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