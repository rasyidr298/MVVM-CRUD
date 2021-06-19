package id.rrdev.mvvmroomdatabase.view.webService;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.data.network.response.Room;

import static id.rrdev.mvvmroomdatabase.util.Constant.KEY_INTENT_ROOM;

public class FormWebServiceActivity extends AppCompatActivity {

    private EditText etNama, etKapasitas, etFasilitas, etDeskripsi;
    private TextView tvNama;
    private Button btnAdd;
    private ProgressBar progressBar;
    private WebServiceViewModel webServiceViewModel;
    private  Integer idoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_web_service);

        init();
        setupView();
    }

    private void init(){
        webServiceViewModel = ViewModelProviders.of(this).get(WebServiceViewModel.class);

        etNama = findViewById(R.id.etNama);
        etFasilitas = findViewById(R.id.etFasilitas);
        etKapasitas = findViewById(R.id.etKapasitas);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        btnAdd = findViewById(R.id.btnAddRoom);
        progressBar = findViewById(R.id.progress);
        tvNama = findViewById(R.id.tvNama);
    }

    private void setupView(){
        if (getIntent().hasExtra(KEY_INTENT_ROOM)){
            Room room = (Room)getIntent().getSerializableExtra(KEY_INTENT_ROOM);

            etNama.setVisibility(View.GONE);
            btnAdd.setText("Update Room");
            idoom = room.getIdRoom();
            etKapasitas.setText(""+room.getKapasitas());
            etFasilitas.setText(room.getFasilitas1());
            etDeskripsi.setText(room.getDeskripsi());
            tvNama.setText("Ruangan "+room.getNamaRoom());

            addRoom(false);
        }else {
            etNama.setText("4.4.4");
            etKapasitas.setText("40");
            etFasilitas.setText("Komputer");
            etDeskripsi.setText(getText(R.string.dumy_desc));

            addRoom(true);
        }
    }

    private void addRoom(Boolean addRoom){
        btnAdd.setOnClickListener(v -> {
            String namaRoom = etNama.getText().toString();
            int kapasitas = Integer.decode(etKapasitas.getText().toString());
            String fasilitas = etFasilitas.getText().toString();
            String deskripsi = etDeskripsi.getText().toString();

            progressBar.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);

            if (addRoom){
                webServiceViewModel.addRoom(namaRoom, kapasitas, fasilitas, fasilitas, fasilitas, fasilitas, deskripsi).observe(this, addRoomResponse -> {
                    if (addRoomResponse != null){
                        Toast.makeText(this, addRoomResponse.message, Toast.LENGTH_SHORT).show();

                        goneProgres();
                    }
                });
            }else {
                webServiceViewModel.updateRoom(idoom, idoom, kapasitas, fasilitas, fasilitas, fasilitas, fasilitas, deskripsi);
                Toast.makeText(this, "update berhasil", Toast.LENGTH_SHORT).show();

                goneProgres();
            }

        });
    }
    private void goneProgres(){
        progressBar.setVisibility(View.GONE);
        btnAdd.setVisibility(View.VISIBLE);
    }
}