package com.example.natan.my_room.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.natan.my_room.Database.Word;
import com.example.natan.my_room.Database.WordRepository;

import java.util.List;

/**
 * Created by natan on 3/9/2018.
 */

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }


    public void insert(Word word) {
        mRepository.insert(word);
    }


   /* public void deleteobj(Word word) {
        mRepository.delete(word);
    }*/


    public void vDeleteByID(int id) {
        mRepository.deleteByID(id);
    }

    public void update(Word word) {
        mRepository.update(word);
    }


}
