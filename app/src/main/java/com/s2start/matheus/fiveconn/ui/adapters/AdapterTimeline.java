package com.s2start.matheus.fiveconn.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.s2start.matheus.fiveconn.R;

import com.s2start.matheus.fiveconn.model.Post;

import java.util.List;

public class AdapterTimeline extends BaseAdapter {

    private Context context;
    private List<Post> posts;

    public AdapterTimeline(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = posts.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_timeline, null);

        TextView titulo = v.findViewById(R.id.titulo);
        TextView descricao = v.findViewById(R.id.descricao);
        TextView nomecompleto = v.findViewById(R.id.nomecompleto);

        titulo.setText(post.getTitulo());
        descricao.setText(post.getDescricao());
        nomecompleto.setText(post.getUser().getNome() + " "+ post.getUser().getSobrenome());


        return v;
    }
}
