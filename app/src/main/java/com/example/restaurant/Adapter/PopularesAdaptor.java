package com.example.restaurant.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurant.Domain.PopularesDomain;
import com.example.restaurant.R;

import java.util.ArrayList;

public class PopularesAdaptor extends RecyclerView.Adapter<PopularesAdaptor.ViewHolder> {

    ArrayList<PopularesDomain>popularesDomains;

    public PopularesAdaptor(ArrayList<PopularesDomain> popularesDomains)
    {
        this.popularesDomains = popularesDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate (R.layout.viewholder_populares,parent, false);
        return new PopularesAdaptor.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nomePopular.setText(popularesDomains.get(position).getNome());
        String picUrl = "";
        switch (position)
        {
            case 0:{
                picUrl="cat_1";
                holder.layoutPrincipal.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_backgroud1));
                break;
            }
            case 1:{
                picUrl="cat_2";
                holder.layoutPrincipal.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_backgroud2));
                break;
            }
            case 3:{
                picUrl="cat_3";
                holder.layoutPrincipal.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_backgroud3));
                break;
            }
            case 4:{
                picUrl="cat_4";
                holder.layoutPrincipal.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_backgroud4));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getOpPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.fotoPopular);

    }

    @Override
    public int getItemCount() {
        return popularesDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomePopular;
        ImageView fotoPopular;
        ConstraintLayout layoutPrincipal;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            nomePopular = itemView.findViewById(R.id.nomePopular);
            fotoPopular = itemView.findViewById(R.id.fotoPopular);
            layoutPrincipal = itemView.findViewById(R.id.layoutPrincipal);
        }
    }
}
