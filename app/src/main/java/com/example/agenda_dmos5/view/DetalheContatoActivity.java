package com.example.agenda_dmos5.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda_dmos5.model.Contato;
import com.example.agenda_dmos5.R;

public class DetalheContatoActivity extends AppCompatActivity {

    private TextView nomeTextView;
    private TextView telefoneTextView;
    private TextView celularTextView;
    private TextView emailTextView;

    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_contato);

        nomeTextView = findViewById(R.id.textview_nome);
        telefoneTextView = findViewById(R.id.textview_telefone);
        celularTextView = findViewById(R.id.textview_celular);
        emailTextView = findViewById(R.id.textview_email);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extrairArgumentos();
        exibeDados();
    }

    @Override
    public void finish() {
        super.finish();
    }

    private void extrairArgumentos(){
        Intent intent = getIntent();
        Bundle embrulho = intent.getExtras();

        if(embrulho != null){
            String nome     = embrulho.getString(MainActivity.KEY_NOME);
            String telefone = embrulho.getString(MainActivity.KEY_TELEFONE);
            String celular  = embrulho.getString(MainActivity.KEY_CELULAR);
            String email = embrulho.getString(MainActivity.KEY_EMAIL);

            contato = new Contato(nome,telefone,celular,email);
        }
    }

    private void exibeDados(){
        nomeTextView.setText(contato.getNome());
        telefoneTextView.setText(contato.getTelefone());
        celularTextView.setText(contato.getCelular());
        emailTextView.setText(contato.getEmail());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}