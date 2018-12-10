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
import com.s2start.matheus.fiveconn.model.Usuario;
import com.s2start.matheus.fiveconn.services.ServiceUsuario;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    //importar as variaveis
    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.senha)
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //colocando a tela em fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //escondendo a action bar
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //importar o Butther Knife
        ButterKnife.bind(this);
    }
    //pegar a ação do click
    @OnClick(R.id.login)
    public void function(){
        Usuario u = new Usuario();
        u.setEmail(email.getText().toString());
        u.setSenha(senha.getText().toString());



        ServiceUsuario s = FiveConnApplication.getInstance().getServiceUsuario();
        Call<Usuario> call = s.login(u);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.code() == 200){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    //pegando o response e colocando no objeto usuario
                    Gson gson = new Gson();
                    //tranformando em string o response
                    String userString = gson.toJson(response.body());
                    //Criando a sessão com nome usuario
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //salvando em um tipo de sessão
                    editor.putString("user", userString);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "logado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Usuário ou Senha Inválidos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Ops", Toast.LENGTH_LONG).show();

            }
        });

    }

    @OnClick(R.id.cadastro)
    public void cadastro(){
                    Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
                    startActivity(i);
                    finish();
    }

}
