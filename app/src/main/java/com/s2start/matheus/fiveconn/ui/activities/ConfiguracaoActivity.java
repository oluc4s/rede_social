package com.s2start.matheus.fiveconn.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfiguracaoActivity extends AppCompatActivity {
    @BindView(R.id.nome)
    TextView nome;

    @BindView(R.id.tipo)
    TextView tipo;

    @BindView(R.id.sobrenomeeditar)
    EditText sobrenomeeditar;

    @BindView(R.id.nomeeditar)
    EditText nomeeditar;

    @BindView(R.id.emaileditar)
    EditText emaileditar;

    @BindView(R.id.seguidores)
    TextView tvSeguidores;

    @BindView(R.id.seguindo)
    TextView tvSeguindo;


    List<Usuario> meusSeguidores;
    List<Usuario> meusSeguindo;
    Usuario u = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //colocando a pagina fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

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
        u = user;

        String nomecompleto = user.getNome() + " " + user.getSobrenome();

        if(user.getTipo_usuario() == 2){
            tipo.setText("Administrador");
        }
        nome.setText(nomecompleto);

        nomeeditar.setText(user.getNome());
        sobrenomeeditar.setText(user.getSobrenome());
        emaileditar.setText(user.getEmail());


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
                Toast.makeText(ConfiguracaoActivity.this,"erro ", Toast.LENGTH_LONG).show();

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
                Toast.makeText(ConfiguracaoActivity.this,"erro ", Toast.LENGTH_LONG).show();

            }
        });


    }

    @OnClick(R.id.sair)
    public void sair()
    {
        Intent i = new Intent(ConfiguracaoActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }

    @OnClick(R.id.editar)
    public void editar()
    {
        //criar um objeto para salvar o usuario editado
        u.setEmail(emaileditar.getText().toString());
        u.setNome(nomeeditar.getText().toString());
        u.setSobrenome(sobrenomeeditar.getText().toString());

        //mostrar na tela
        String nomecompleto = nomeeditar.getText().toString() +" "+ sobrenomeeditar.getText().toString();
        nome.setText(nomecompleto);


        //pegando o novo usuario e colocando no objeto usuario
        Gson gson = new Gson();
        String userString = gson.toJson(u);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("user", userString);
        editor.apply();
        Toast.makeText(ConfiguracaoActivity.this, "Editado com sucesso", Toast.LENGTH_LONG).show();

    }
    
    @OnClick(R.id.gotimeline)
    public void goTimeline(){
        Intent i = new Intent(ConfiguracaoActivity.this, MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }

    @OnClick(R.id.criarpost)
    public void goCriarPost(){
        Intent i = new Intent(ConfiguracaoActivity.this, NewPostActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
    @OnClick(R.id.perfil)
    public void goPerfil(){
        Intent i = new Intent(ConfiguracaoActivity.this, PerfilActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);

    }
    @OnClick(R.id.configuracao)
    public void goConfiguracao(){
        Intent i = new Intent(ConfiguracaoActivity.this, ConfiguracaoActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(0,0);
    }


}
