package com.sinau.belajarconstraintlayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by addin on 07/12/17.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context c;
    ArrayList<String> model;

    public MyAdapter(Context c, ArrayList<String> spacecrafts) {
        this.c = c;
        this.model = spacecrafts;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.card_view,parent,false);
        return new MyAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(model.get(position).toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "You clicked " + model.get(position).toString(), Toast.LENGTH_SHORT).show();


            }
        });
    }
    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textView) TextView textView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            ButterKnife.bind(this, itemLayoutView);
        }
    }}