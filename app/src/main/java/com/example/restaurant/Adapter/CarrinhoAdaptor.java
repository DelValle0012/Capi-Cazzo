package com.example.restaurant.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.icu.text.NumberFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.Domain.CarrinhoDomain;
import com.example.restaurant.Item;
import com.example.restaurant.R;


import java.util.ArrayList;

public class CarrinhoAdaptor extends RecyclerView.Adapter<CarrinhoAdaptor.CarrinhoViewHolder> {
    ArrayList<CarrinhoDomain> compras;
    private Context context;


    public CarrinhoAdaptor(Context context, ArrayList<CarrinhoDomain> compras)
    {
        this.context = context;
        this.compras = compras;
    }

    @Override
    public CarrinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_carrinho, parent, false);
        return new CarrinhoAdaptor.CarrinhoViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrinhoViewHolder holder, int position) {
        CarrinhoDomain currentCompra = compras.get(position);

        String nomeFoto = currentCompra.getFoto(); // Nome do recurso drawable
        int imageResId = holder.itemView.getContext().getResources()
                .getIdentifier(nomeFoto, "drawable", holder.itemView.getContext().getPackageName());
        if (imageResId != 0) {
            holder.foto.setImageResource(imageResId);
        } else {
            // Defina uma imagem padrão em caso de erro
            holder.foto.setImageResource(R.drawable.produtos_default);
        }


        holder.nome.setText(currentCompra.getNome());
        holder.quantidade.setText(String.valueOf(currentCompra.getQuantidade()));

        holder.valor_total.setText(NumberFormat.getCurrencyInstance().format(currentCompra.getValor_total()));
    }

    @Override
    public int getItemCount() {
        return compras != null ? compras.size() : 0;
    }

    public class CarrinhoViewHolder extends RecyclerView.ViewHolder{
        private TextView nome, quantidade, valor_total;
        private ImageView foto;

        public CarrinhoViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nome = itemView.findViewById(R.id.item_name);
            valor_total = itemView.findViewById(R.id.preco_total);
            foto = itemView.findViewById(R.id.pic);
            quantidade = itemView.findViewById(R.id.TV_quant);
        }
    }
}
