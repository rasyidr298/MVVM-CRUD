package id.rrdev.mvvmroomdatabase.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import id.rrdev.mvvmroomdatabase.R;
import id.rrdev.mvvmroomdatabase.data.database.Note;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        
        Note note = new Note(0,"test","yuhuhuu");

        mainViewModel.insert(note); //insert

//        mainViewModel.delete(item); // delete

        mainViewModel.getAllNotes().observe(this, notes -> {
            Log.d("test", notes.toString() );
        });

//        mainViewModel.update(item); //update

//        mainViewModel.deleteAllNotes(); //delete all data
    }
}