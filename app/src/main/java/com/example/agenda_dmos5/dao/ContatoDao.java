package com.example.agenda_dmos5.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.agenda_dmos5.model.Contato;

import java.util.ArrayList;
import java.util.List;


public class ContatoDao {
    private Context context;

    public ContatoDao(Context context) {
        this.context = context;
    }

    public void add(Contato contato) {
        if (contato == null) throw new NullPointerException();

        SQLiteHelper dbHelper = new SQLiteHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseDao.addTable.COLUNA_NOME, contato.getNome());
        values.put(BaseDao.addTable.COLUNA_TELEFONE, contato.getTelefone());
        values.put(BaseDao.addTable.COLUNA_CELULAR, contato.getCelular());
        values.put(BaseDao.addTable.COLUNA_EMAIL, contato.getEmail());

        db.insert(BaseDao.addTable.NOME_TABELA, null, values);

        db.close();
    }
    public void alterarContato(Contato contato){

        if (contato == null) throw new NullPointerException();

        SQLiteHelper dbHelper = new SQLiteHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BaseDao.addTable.COLUNA_NOME, contato.getNome());
        values.put(BaseDao.addTable.COLUNA_TELEFONE, contato.getTelefone());
        values.put(BaseDao.addTable.COLUNA_CELULAR, contato.getCelular());
        values.put(BaseDao.addTable.COLUNA_EMAIL, contato.getEmail());

        String[]args = {contato.getId().toString()};

        db.update(BaseDao.addTable.NOME_TABELA, values, "id=?",args);

        db.close();
    }

    public String deletarContato(String nome){

        SQLiteHelper dbHelper = new SQLiteHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "DELETE FROM contato where nome = " + "'" + nome + "'";
        try {
            db.execSQL(sql);
        }catch(SQLException e){
            return String.valueOf(e);

        }
            return "Sucesso ao deletar";

        /*SQLiteHelper dbHelper = new SQLiteHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String [] args = {contato.getId().toString()};

        //db.delete(BaseDao.addTable.NOME_TABELA, Contato, "id=?",args);
        db.close();
*/
    }

    public List<Contato> recuperateAll(){

        String sql = "SELECT * FROM contato order by nome asc";

        List<Contato> contatos;
        Contato contato;
        Cursor cursor;

        contatos = new ArrayList<Contato>();
        SQLiteHelper dbHelper = new SQLiteHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String colunas[] = new String[]{
                BaseColumns._ID,
                BaseDao.addTable.COLUNA_NOME,
                BaseDao.addTable.COLUNA_TELEFONE,
                BaseDao.addTable.COLUNA_CELULAR,
                BaseDao.addTable.COLUNA_EMAIL
        };
        String orderBy = BaseDao.addTable.COLUNA_NOME + " ASC";
        cursor = db.query(
                BaseDao.addTable.NOME_TABELA,
                colunas,
                null,
                null,
                null,
                null,
                orderBy
        );

        while (cursor.moveToNext()){
            contato = new Contato(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
            contatos.add(contato);
        }
        cursor.close();
        db.close();
        return contatos;
    }
}
