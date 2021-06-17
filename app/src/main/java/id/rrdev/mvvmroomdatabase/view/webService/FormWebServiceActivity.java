package id.rrdev.mvvmroomdatabase.view.webService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.data.network.response.Room;

public class FormWebServiceActivity extends AppCompatActivity {

    private EditText etNama, etKapasitas, etFasilitas, etDeskripsi;
    private Button btnAdd;
    private ProgressBar progressBar;
    private WebServiceViewModel webServiceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_web_service);

        init();
        setupView();
        addRoom();
    }

    private void init(){
        webServiceViewModel = ViewModelProviders.of(this).get(WebServiceViewModel.class);

        etNama = findViewById(R.id.etNama);
        etFasilitas = findViewById(R.id.etFasilitas);
        etKapasitas = findViewById(R.id.etKapasitas);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        btnAdd = findViewById(R.id.btnAddRoom);
        progressBar = findViewById(R.id.progress);
    }

    private void setupView(){
    }

    private void addRoom() {
        btnAdd.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            String namaRoom = etNama.getText().toString();
            int kapasitas = Integer.decode(etKapasitas.getText().toString());
            String fasilitas1 = etFasilitas.getText().toString();
            String deskirpsi = etDeskripsi.getText().toString();

            webServiceViewModel.addRoom(namaRoom, kapasitas, fasilitas1, fasilitas1, fasilitas1, fasilitas1, deskirpsi).observe(this, addRoomResponse -> {
                if (addRoomResponse != null){
                    Toast.makeText(this, addRoomResponse.message, Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    btnAdd.setVisibility(View.VISIBLE);
                }
            });
        });
    }
}