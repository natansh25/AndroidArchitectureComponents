package com.example.natan.my_room.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by natan on 3/9/2018.
 */

public class WordRepository {

    private WordDao mWordDao;

    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void delete(Word word) {
        //mWordDao.delete(id);
        new deleteAsyncTask(mWordDao).execute(word);
        //mWordDao.deleteFromObj(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    private static class deleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;


        public deleteAsyncTask(WordDao wordDao) {
            mAsyncTaskDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.deleteFromObj(words[0]);
            return null;
        }
    }


}
