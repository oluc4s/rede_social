package com.s2start.matheus.fiveconn.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.s2start.matheus.fiveconn.R;
import com.s2start.matheus.fiveconn.app.FiveConnApplication;
import com.s2start.matheus.fiveconn.model.Post;
import com.s2start.matheus.fiveconn.model.Usuario;
import com.s2start.matheus.fiveconn.services.ServicePost;
import com.s2start.matheus.fiveconn.services.ServiceUsuario;
import com.s2start.matheus.fiveconn.ui.adapters.AdapterTimeline;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilActivity extends AppCompatActivity {
    @BindView(R.id.nome)
    TextView nome;

    @BindView(R.id.tipo)
    TextView tipo;

    @BindView(R.id.seguidores)
    TextView tvSeguidores;

    @BindView(R.id.seguindo)
    TextView tvSeguindo;

    @BindView(R.id.posts)
    ListView listView;


    List<Post> post;

    List<Usuario> meusSeguidores;
    List<Usuario> meusSeguindo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ButterKnife.bind(this);


        SharedPreferences sharedPreferences =  getApplicationContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json =  sharedPreferences.getString("user", null);
        //colocando o usuario do gson em um objeto
        Usuario user = gson.fromJson(json, Usuario.class);

        String nomecompleto = user.getNome() + " " + user.getSobrenome();

        if(user.getTipo_usuario() == 2){
            tipo.setText("Administrador");
        }
        nome.setText(nomecompleto);

        //chamando a api
        ServicePost s = FiveConnApplication.getInstance().getServicePost();
        Call<List<Post>> call = s.meusposts(user.getId_usuario());

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                post = response.body();

                AdapterTimeline adapter = new AdapterTimeline(PerfilActivity.this, post);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(PerfilActivity.this,"erro ", Toast.LENGTH_LONG).show();

            }
        });


        //contar a quantidade de seguidores
        ServiceUsuario seg = FiveConnApplication.getInstance().getServiceUsuario();
        Call<List<Usuario>> call1  = seg.listSeguidores(user.getId_usuario());
        call1.enqueue(new Callback<List<Usuario>>() {
          @Override
          public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
              meusSeguidores = response.body();
              int tamanholista = meusSeguidores.size();
              tvSeguidores.setText(tamanholista + " Seguidores");
          }

          @Override
          public void onFailure(Call<List<Usuario>> call, Throwable t) {
              Toast.makeText(PerfilActivity.this,"erro ", Toast.LENGTH_LONG).show();

          }
      });

        //contar a quantidade de pessoas que sigo
        ServiceUsuario resSeguidores = FiveConnApplication.getInstance().getServiceUsuario();
        Call<List<Usuario>> call2 = resSeguidores.listSeguidores(user.getId_usuario());
        call2.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                meusSeguindo = response.body();
                int tamanholista = meusSeguindo.size();
                tvSeguindo.setText(tamanholista + " Seguindo");
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

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


    @OnClick(R.id.gotimeline)
    public void goTimeline(){
        Intent i = new Intent(PerfilActivity.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }

    @OnClick(R.id.criarpost)
    public void goCriarPost(){
        Intent i = new Intent(PerfilActivity.this, NewPostActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
    @OnClick(R.id.perfil)
    public void goPerfil(){
        Intent i = new Intent(PerfilActivity.this, PerfilActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
    @OnClick(R.id.configuracao)
    public void goConfiguracao(){
        Intent i = new Intent(PerfilActivity.this, ConfiguracaoActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);


    }
}
