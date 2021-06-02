package id.rrdev.mvvmroomdatabase.view.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import id.rrdev.mvvmroomdatabase.data.database.Note;
import id.rrdev.mvvmroomdatabase.data.repository.NoteRepo;

public class MainViewModel extends AndroidViewModel {
    private final NoteRepo repository;
    private final LiveData<List<Note>> allNotes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepo(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}