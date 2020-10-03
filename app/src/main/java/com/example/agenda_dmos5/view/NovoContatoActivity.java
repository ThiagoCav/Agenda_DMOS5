package com.example.agenda_dmos5.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.snackbar.Snackbar;

import com.example.agenda_dmos5.dao.ContatoDao;
import com.example.agenda_dmos5.model.Contato;
import com.example.agenda_dmos5.R;

public class NovoContatoActivity extends AppCompatActivity {

    private EditText nomeEditText;
    private EditText telefoneEditText;
    private EditText celularEditText;
    private EditText emailEditText;
    private Button salvarButton;

    private ContatoDao contatoDao;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_contato);

        nomeEditText = findViewById(R.id.edittext_nome);
        telefoneEditText = findViewById(R.id.edittext_telefone);
        celularEditText = findViewById(R.id.edittext_celular);
        emailEditText = findViewById(R.id.edittext_email);
        salvarButton = findViewById(R.id.button_salvar_contato);
        salvarButton.setOnClickListener(this::salvarContato);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finalizar(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarContato(View view){
        String nome, telefone, celular, email;
        nome = nomeEditText.getText().toString();
        telefone = telefoneEditText.getText().toString();
        celular = celularEditText.getText().toString();
        email = emailEditText.getText().toString();

        if(nome.isEmpty() || telefone.isEmpty() || celular.isEmpty()){
            showSnackbar(getString(R.string.erro_dados_vazio));
        }else{
            contatoDao = new ContatoDao(getApplicationContext());
            try {
                contatoDao.add(new Contato(nome, telefone, celular, email));
                finalizar(true);
            } catch (NullPointerException e){
                showSnackbar(getString(R.string.erro_contato));
            } catch (Exception e) {
                showSnackbar("Erro ao inserir contato!");
            }
        }
    }

    private void showSnackbar(String mensagem){
        Snackbar snackbar;
        ConstraintLayout constraintLayout = findViewById(R.id.layout_novo_contato);
        snackbar = Snackbar.make(constraintLayout, mensagem, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void finalizar(boolean sucesso){
        if(sucesso){
            setResult(Activity.RESULT_OK);
        }else{
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }
}