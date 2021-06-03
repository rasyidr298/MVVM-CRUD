package id.rrdev.mvvmroomdatabase.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.data.database.Note;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fabAddNote;
    private RecyclerView rvNotes;
    private Button btnAdd, btnUpdate;
    private EditText etNoteTitle, etNoteDesc;
    private LinearLayout llAddOrUpdate;

    private NoteAdapter noteAdapter;

    private Integer noteId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddNote = findViewById(R.id.fab_add_note);
        rvNotes = findViewById(R.id.rv_notes);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        etNoteTitle = findViewById(R.id.et_note_title);
        etNoteDesc = findViewById(R.id.et_note_desc);
        llAddOrUpdate = findViewById(R.id.ll_add_or_update);

        noteAdapter = new NoteAdapter();
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        noteAdapter.setOnItemClickListener(new NoteAdapter.Callback.ItemClick() {
            @Override
            public void onItemClick(View view, Note note, int position) {
                resetFields();
                llAddOrUpdate.setVisibility(View.VISIBLE);
                btnUpdate.setVisibility(View.VISIBLE);
                etNoteTitle.setText(note.getTitle());
                etNoteDesc.setText(note.getDescription());
                noteId = note.getId();
            }
        });

        rvNotes.setAdapter(noteAdapter);
//        mainViewModel.delete(item); // delete

        mainViewModel.getAllNotes().observe(this, notes -> {
            Log.d("test", notes.toString() );
            noteAdapter.submitList(notes);
        });

//        mainViewModel.deleteAllNotes(); //delete all data

        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                llAddOrUpdate.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.VISIBLE);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(
                        0,
                        etNoteTitle.getText().toString().trim(),
                        etNoteDesc.getText().toString()
                );
                mainViewModel.insert(note); //insert
                resetFields();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(
                        noteId,
                        etNoteTitle.getText().toString().trim(),
                        etNoteDesc.getText().toString()
                );
                mainViewModel.update(note); //update
                resetFields();
            }
        });
    }

    private void resetFields(){
        llAddOrUpdate.setVisibility(View.GONE);
        btnAdd.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.GONE);
        noteId = null;
        etNoteTitle.setText(null);
        etNoteDesc.setText(null);
    }

    @Override
    public void onBackPressed() {
        if (llAddOrUpdate.getVisibility() == View.VISIBLE) resetFields();
        else super.onBackPressed();
    }
}