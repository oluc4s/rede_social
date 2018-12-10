package com.s2start.matheus.fiveconn.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.s2start.matheus.fiveconn.R;
import com.s2start.matheus.fiveconn.app.FiveConnApplication;
import com.s2start.matheus.fiveconn.model.Post;
import com.s2start.matheus.fiveconn.model.Usuario;
import com.s2start.matheus.fiveconn.services.ServicePost;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostActivity extends AppCompatActivity {
    @BindView(R.id.titulo)
    EditText titulo;

    @BindView(R.id.descricao)
    EditText descricao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //colocando a pagina fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        //butther knife importanto
        ButterKnife.bind(this);

    }

    @OnClick(R.id.compartilhar)
    public void compartilhar(){

        //pegando o usuario no SharedPreference
        SharedPreferences sharedPreferences =  getApplicationContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);

        //chamando o gson
        Gson gson = new Gson();

        //colocando o gson em uma string
        String json =  sharedPreferences.getString("user", null);

        //colocando o usuario do gson em um objeto
        Usuario user = gson.fromJson(json, Usuario.class);

        //criando o objeto de post
        Post p = new Post();

        p.setId_usuario(user.getId_usuario());
        p.setTitulo(titulo.getText().toString());
        p.setDescricao(descricao.getText().toString());
        p.setYoutube("");
        p.setData_post("2018-10-11 16:00:40");
        p.setTipo_post(1);
        p.setStatus(1);

        //chamando a api
        ServicePost s = FiveConnApplication.getInstance().getServicePost();
        Call<Post> call = s.postar(p);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.code() == 204){

                    Intent i = new Intent(NewPostActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                    Toast.makeText(NewPostActivity.this, "Postado com sucesso", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(NewPostActivity.this, "Não foi possivel postar", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(NewPostActivity.this, "Error ao postar", Toast.LENGTH_LONG).show();

            }
        });


    }


    //menu inferior
    @OnClick(R.id.gotimeline)
    public void goTimeline(){
        Intent i = new Intent(NewPostActivity.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);
    }

    @OnClick(R.id.criarpost)
    public void goCriarPost(){
        Intent i = new Intent(NewPostActivity.this, NewPostActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
    @OnClick(R.id.perfil)
    public void goPerfil(){
        Intent i = new Intent(NewPostActivity.this, PerfilActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
    @OnClick(R.id.configuracao)
    public void goConfiguracao(){
        Intent i = new Intent(NewPostActivity.this, ConfiguracaoActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);


    }
}
