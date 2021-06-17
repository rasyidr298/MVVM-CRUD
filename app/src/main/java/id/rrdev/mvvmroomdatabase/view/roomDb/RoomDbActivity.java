package id.rrdev.mvvmroomdatabase.view.roomDb;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.data.database.Note;


public class RoomDbActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fabAddNote, fabDellete;
    private RoomDbViewModel roomDbViewModel;
    private RecyclerView rvNotes;
    private Button btnAdd, btnUpdate;
    private EditText etNoteTitle, etNoteDesc;
    private LinearLayout llAddOrUpdate;
    private NoteAdapter noteAdapter;
    private Integer noteId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_db);

        init();
        setupView();
    }

    private void init(){
        roomDbViewModel = ViewModelProviders.of(this).get(RoomDbViewModel.class);

        fabAddNote = findViewById(R.id.fab_add_note);
        rvNotes = findViewById(R.id.rv_notes);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        etNoteTitle = findViewById(R.id.et_note_title);
        etNoteDesc = findViewById(R.id.et_note_desc);
        llAddOrUpdate = findViewById(R.id.ll_add_or_update);
        fabDellete = findViewById(R.id.fabDellete);

        fabAddNote.setOnClickListener(this);
        fabDellete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        noteAdapter = new NoteAdapter();
        rvNotes.setAdapter(noteAdapter);
    }

    private void setupView(){
        noteAdapter.setOnItemClickListener((view, note, position) -> {
            resetFields();
            llAddOrUpdate.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.VISIBLE);
            fabAddNote.setVisibility(View.GONE);
            fabDellete.setVisibility(View.GONE);
            etNoteTitle.setText(note.getTitle());
            etNoteDesc.setText(note.getDescription());
            noteId = note.getId();
        });

        noteAdapter.setOnItemLongClickListener((view, note, position) -> {
            new MaterialAlertDialogBuilder(RoomDbActivity.this)
                    .setTitle("Hapus "+note.getTitle())
                    .setMessage("Yakin akan menghapus "+ note.getTitle())
                    .setPositiveButton("YA", ((dialog, which) -> {
                        roomDbViewModel.delete(note);
                    }))
                    .setNegativeButton("TIDAK", ((dialog, which) -> { }))
                    .show();
            return true;
        });


        roomDbViewModel.getAllNotes().observe(this, notes -> {
            Log.d("test", notes.toString() );
            noteAdapter.submitList(notes);
        });
    }

    private void resetFields(){
        llAddOrUpdate.setVisibility(View.GONE);
        btnAdd.setVisibility(View.GONE);
        btnUpdate.setVisibility(View.GONE);
        fabAddNote.setVisibility(View.VISIBLE);
        fabDellete.setVisibility(View.VISIBLE);
        noteId = null;
        etNoteTitle.setText(null);
        etNoteDesc.setText(null);
    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    public void onBackPressed() {
        if (llAddOrUpdate.getVisibility() == View.VISIBLE) resetFields();
        else super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        fabAddNote.setOnClickListener(v1 -> {
            resetFields();
            llAddOrUpdate.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.VISIBLE);
            fabAddNote.setVisibility(View.GONE);
            fabDellete.setVisibility(View.GONE);
        });

        fabDellete.setOnClickListener(v2 ->
                roomDbViewModel.deleteAllNotes()
        ); // delete

        btnAdd.setOnClickListener(v3 -> {
            Note note = new Note(
                    0,
                    etNoteTitle.getText().toString().trim(),
                    etNoteDesc.getText().toString()
            );
            roomDbViewModel.insert(note); //insert
            resetFields();
            hideKeyboard();
        });

        btnUpdate.setOnClickListener(v3 -> {
            Note note = new Note(
                    noteId,
                    etNoteTitle.getText().toString().trim(),
                    etNoteDesc.getText().toString()
            );
            roomDbViewModel.update(note); //update
            resetFields();
            hideKeyboard();
        });
    }
}