package com.example.natan.my_room.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by natan on 3/9/2018.
 */

@Entity(tableName = "word_table")
public class Word {

    /*@PrimaryKey(autoGenerate = true)
    private int id=0;
*/

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;


    public Word(@NonNull String word) {
        this.word = word;
    }

    /*public int getId() {
        return id;
    }*/

    public String getWord() {
        return word;
    }
}
