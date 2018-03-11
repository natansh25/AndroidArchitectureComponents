package com.example.natan.my_room;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.natan.my_room.Database.Word;
import com.example.natan.my_room.ViewModel.WordViewModel;
import com.facebook.stetho.Stetho;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;
    private EditText edt_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edt_word = findViewById(R.id.edt_word);
        Stetho.initializeWithDefaults(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        final WordListAdapter adapter = new WordListAdapter(this, new WordListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(Word word) {
                Toast.makeText(MainActivity.this, String.valueOf("Hurray !!"), Toast.LENGTH_SHORT).show();
                mWordViewModel.deleteobj(word);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;


            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int id = (int) viewHolder.itemView.getTag();
                //mWordViewModel.deleteobj(id);


            }
        }).attachToRecyclerView(recyclerView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void save(View view) {

        String nmae = edt_word.getText().toString();
        Word word = new Word(nmae);
        mWordViewModel.insert(word);
        edt_word.setText(" ");


    }
}
