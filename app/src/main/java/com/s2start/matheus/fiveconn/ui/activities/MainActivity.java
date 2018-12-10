package com.s2start.matheus.fiveconn.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.s2start.matheus.fiveconn.R;
import com.s2start.matheus.fiveconn.app.FiveConnApplication;
import com.s2start.matheus.fiveconn.model.Post;
import com.s2start.matheus.fiveconn.model.Usuario;
import com.s2start.matheus.fiveconn.services.ServicePost;
import com.s2start.matheus.fiveconn.ui.adapters.AdapterTimeline;
import com.s2start.matheus.fiveconn.ui.fragments.MenuFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.posts)
    ListView listView;

    List<Post> post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //colocando a pagina fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //butther knife importanto
        ButterKnife.bind(this);

        //pegando o usuario no SharedPreference
        SharedPreferences sharedPreferences =  getApplicationContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);

        //chamando o gson
        Gson gson = new Gson();

        //colocando o gson em uma string
        String json =  sharedPreferences.getString("user", null);

        //colocando o usuario do gson em um objeto
        Usuario user = gson.fromJson(json, Usuario.class);

        //chamando a api
        ServicePost s = FiveConnApplication.getInstance().getServicePost();
        Call<List<Post>> call = s.listarPostSeguindo(user.getId_usuario());

        //callback dos posts
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                post = response.body();

                AdapterTimeline adapter = new AdapterTimeline(MainActivity.this, post);
                listView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"erro ", Toast.LENGTH_LONG).show();

            }
        });
    }

    @OnItemClick(R.id.posts)
    public void listaClick(int p) {

        Intent i = new Intent(this, DetalhePostActivity.class);
        Post teste = post.get(p);
        Log.i("#########", "listaClick: "+teste);
        i.putExtra("post",post.get(p));
        startActivity(i);

    }

    //menu inferior
    @OnClick(R.id.gotimeline)
    public void goTimeline(){
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }

    @OnClick(R.id.criarpost)
    public void goCriarPost(){
        Intent i = new Intent(MainActivity.this, NewPostActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
    @OnClick(R.id.perfil)
    public void goPerfil(){
        Intent i = new Intent(MainActivity.this, PerfilActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
    @OnClick(R.id.configuracao)
    public void goConfiguracao(){
        Intent i = new Intent(MainActivity.this, ConfiguracaoActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);


    }


}
