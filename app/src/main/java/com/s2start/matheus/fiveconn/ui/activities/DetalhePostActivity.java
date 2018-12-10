package com.s2start.matheus.fiveconn.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.s2start.matheus.fiveconn.R;
import com.s2start.matheus.fiveconn.model.Post;
import com.s2start.matheus.fiveconn.model.Usuario;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalhePostActivity extends AppCompatActivity {
    @BindView(R.id.nomecompleto)
    TextView nomecompleto;

    @BindView(R.id.titulo)
    TextView titulo;

    @BindView(R.id.descricao)
    TextView descricao;

    @BindView(R.id.horario)
    TextView horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //colocando a pagina fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_post);


        //butther knife importanto
        ButterKnife.bind(this);

        Post p = (Post) getIntent().getSerializableExtra("post");
        nomecompleto.setText(p.getUser().getNome() + "  " + p.getUser().getSobrenome());
        titulo.setText(p.getTitulo());
        horario.setText(p.getData_post());
        descricao.setText(p.getDescricao());
    }


    //menu inferior
    @OnClick(R.id.gotimeline)
    public void goTimeline(){
        Intent i = new Intent(DetalhePostActivity.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);
    }

    @OnClick(R.id.criarpost)
    public void goCriarPost(){
        Intent i = new Intent(DetalhePostActivity.this, NewPostActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);
    }
    @OnClick(R.id.perfil)
    public void goPerfil(){
        Intent i = new Intent(DetalhePostActivity.this, PerfilActivity.class);
        DetalhePostActivity.this.overridePendingTransition(0,0);
        startActivity(i);
        finish();
       overridePendingTransition(0,0);

    }
    @OnClick(R.id.configuracao)
    public void goConfiguracao(){
        Intent i = new Intent(DetalhePostActivity.this, ConfiguracaoActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
}
