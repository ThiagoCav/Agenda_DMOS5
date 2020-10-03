package com.example.agenda_dmos5.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.agenda_dmos5.dao.BaseDao.addTable.COLUNA_EMAIL;
import static com.example.agenda_dmos5.dao.BaseDao.addTable.NOME_TABELA;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "agenda.db";

    private Context context;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BaseDao.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql;
        switch (oldVersion){
            case 1:
                sql = "ALTER TABLE " + NOME_TABELA + " ADD COLUMN " + COLUNA_EMAIL + " TEXT";
                sqLiteDatabase.execSQL(sql);
        }

    }
}
