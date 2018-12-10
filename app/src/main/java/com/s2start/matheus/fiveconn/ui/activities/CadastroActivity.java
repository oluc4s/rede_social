package com.s2start.matheus.fiveconn.ui.activities;
import com.s2start.matheus.fiveconn.R;
import com.s2start.matheus.fiveconn.app.FiveConnApplication;
import com.s2start.matheus.fiveconn.model.Usuario;
import com.s2start.matheus.fiveconn.services.ServiceUsuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {
    @BindView(R.id.nickname)
    EditText nickname;

    @BindView(R.id.nome)
    EditText nome;

    @BindView(R.id.sobrenome)
    EditText sobrenome;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.senha)
    EditText senha;

    @BindView(R.id.confsenha)
    EditText confsenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.cadastrar)
    public void cadastrar(){
        Usuario u = new Usuario();
        u.setNickname(nickname.getText().toString());
        u.setNome(nome.getText().toString());
        u.setSobrenome(sobrenome.getText().toString());
        u.setEmail(email.getText().toString());
        u.setSenha(senha.getText().toString());
        u.setDt_nascimento("00.00.0000");
        u.setSexo("outros");
        u.setTipo_usuario(1);

        if(senha.getText().toString().equals(confsenha.getText().toString())){

            ServiceUsuario s = FiveConnApplication.getInstance().getServiceUsuario();
            Call<Usuario> call = s.cadastro(u);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if(response.code() == 200){

                        Intent i = new Intent(CadastroActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                        Toast.makeText(CadastroActivity.this, "Cadastrado com sucesso !", Toast.LENGTH_LONG).show();

                    }else if(response.code() == 400){

                        Toast.makeText(CadastroActivity.this, response.message(), Toast.LENGTH_LONG).show();


                    } else{
                        Toast.makeText(CadastroActivity.this, "error", Toast.LENGTH_LONG).show();
                    }

                }
                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(CadastroActivity.this, "Ocorreu algum erro ", Toast.LENGTH_LONG).show();
                }
            });

        }else{
            Toast.makeText(CadastroActivity.this, "Senhas não conferem!", Toast.LENGTH_LONG).show();
        }
    }


    @OnClick(R.id.login)
    public void returnLogin(){
        Intent i = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(i);
        finish();

    }

}
