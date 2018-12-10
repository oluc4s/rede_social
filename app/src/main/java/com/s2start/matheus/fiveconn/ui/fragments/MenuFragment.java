package com.s2start.matheus.fiveconn.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.s2start.matheus.fiveconn.R;
import com.s2start.matheus.fiveconn.ui.activities.ConfiguracaoActivity;
import com.s2start.matheus.fiveconn.ui.activities.MainActivity;
import com.s2start.matheus.fiveconn.ui.activities.NewPostActivity;
import com.s2start.matheus.fiveconn.ui.activities.PerfilActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MenuFragment extends Fragment {
    public MenuFragment() {
    }

    public static MenuFragment create(){
        MenuFragment f = new MenuFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }


        //menu inferior
    @OnClick(R.id.gotimeline)
    public void goTimeline(){
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);

    }

    @OnClick(R.id.criarpost)
    public void goCriarPost(){
        Intent i = new Intent(getActivity(), NewPostActivity.class);
        startActivity(i);

    }
    @OnClick(R.id.perfil)
    public void goPerfil(){
        Intent i = new Intent(getActivity(), PerfilActivity.class);
        startActivity(i);

    }
    @OnClick(R.id.configuracao)
    public void goConfiguracao(){
        Intent i = new Intent(getActivity(), ConfiguracaoActivity.class);
        startActivity(i);

    }

}
