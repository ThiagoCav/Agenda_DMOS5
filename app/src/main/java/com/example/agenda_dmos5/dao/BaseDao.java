package com.example.agenda_dmos5.dao;

import android.provider.BaseColumns;

public class BaseDao {
    private BaseDao() {
    }

    public static class addTable implements BaseColumns {
        public static final String NOME_TABELA = "contato";
        public static final String COLUNA_NOME = "nome";
        public static final String COLUNA_TELEFONE = "telefone_fixo";
        public static final String COLUNA_CELULAR = "telefone_celular";
        public static final String COLUNA_EMAIL = "email";

    }

    public static final String CREATE_TABLE =
            "CREATE TABLE " + addTable.NOME_TABELA + "("
                    + BaseColumns._ID + " INTEGER PRIMARY KEY,"
                    + addTable.COLUNA_NOME + " TEXT NOT NULL,"
                    + addTable.COLUNA_TELEFONE + " TEXT NOT NULL,"
                    + addTable.COLUNA_CELULAR + " TEXT NOT NULL,"
                    + addTable.COLUNA_EMAIL + " TEXT );";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + addTable.NOME_TABELA;

}
