package com.example.agenda_dmos5.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_dmos5.dao.ContatoDao;
import com.example.agenda_dmos5.model.Contato;
import com.example.agenda_dmos5.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contatosListView;
    private ImageView mImageView;
    private List<Contato> contatos;

    private ArrayAdapter<Contato> contatoArrayAdapter;

    protected static final String KEY_NOME = "nome";
    protected static final String KEY_TELEFONE = "telefone";
    protected static final String KEY_CELULAR = "celular";
    protected static final String KEY_EMAIL = "email";

    protected static final int DETALHES_ITEM_CONTATO = 200;
    protected static final int REQUESTCODE_NOVO_CONTATO = 200;

    private FloatingActionButton adicionarContatoButton;
    private ContatoDao contatoDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        adicionarContatoButton = findViewById(R.id.fab_adicionar_contato);
        adicionarContatoButton.setOnClickListener(this::adicionarContato);

        contatosListView = findViewById(R.id.list_contatos);
        contatoDao = new ContatoDao(this);
        contatos = contatoDao.recuperateAll();

        contatoArrayAdapter = new ItemContatoAdapter(this, contatos);
        contatosListView.setAdapter(contatoArrayAdapter);

        contatosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle args = new Bundle();
                args.putString(KEY_NOME, contatos.get(i).getNome());
                args.putString(KEY_TELEFONE, contatos.get(i).getTelefone());
                args.putString(KEY_CELULAR, contatos.get(i).getCelular());
                args.putString(KEY_EMAIL, contatos.get(i).getEmail());
                Intent intent = new Intent(getApplicationContext(), DetalheContatoActivity.class);
                intent.putExtras(args);
                startActivityForResult(intent, DETALHES_ITEM_CONTATO);
            }
        });

    }

    public void adicionarContato(View view) {
        Intent in = new Intent(this, NovoContatoActivity.class);
        startActivityForResult(in, REQUESTCODE_NOVO_CONTATO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUESTCODE_NOVO_CONTATO:
                if (resultCode == RESULT_OK) {
                    refreshListView(contatoDao.recuperateAll());
                }
                break;
        }
    }

    private void refreshListView(List<Contato> contactList){
        contatoArrayAdapter.clear();
        contatoArrayAdapter.addAll(contactList);
        contatoArrayAdapter.notifyDataSetChanged();
    }
}
