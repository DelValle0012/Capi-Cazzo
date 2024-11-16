package com.example.restaurant.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.Domain.CategoryDomain;
import com.example.restaurant.R;

import java.util.ArrayList;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {

    ArrayList<CategoryDomain>categoryDomains;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CategoryDomain category);
    }

    public CategoryAdaptor(ArrayList<CategoryDomain> categoryDomains, OnItemClickListener listener) {
        this.categoryDomains = categoryDomains;
        this.listener = listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate (R.layout.viewholder_category,parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        // Defina outras propriedades aqui, como imagem usando o pic
        holder.itemView.setOnClickListener(v -> listener.onItemClick(categoryDomains.get(position)));
        String picUrl = "";
        switch (position)
        {
            case 0:{
                picUrl="cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_backgroud1));
                break;
            }
            case 1:{
                picUrl="cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_backgroud2));
                break;
            }
            case 3:{
                picUrl="cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_backgroud3));
                break;
            }
            case 4:{
                picUrl="cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.cat_backgroud4));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getOpPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);



    }

    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            categoryName = itemView.findViewById(R.id.nomePopular);
            categoryPic = itemView.findViewById(R.id.fotoPopular);
            mainLayout = itemView.findViewById(R.id.layoutPrincipal);
        }
    }

    // Interface para clique em categoria
    public interface OnCategoryClickListener {
        void onCategoryClick(CategoryDomain category);
    }
}
