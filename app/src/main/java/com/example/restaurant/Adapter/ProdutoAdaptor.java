package com.example.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.R;
import com.example.restaurant.Item;

import java.util.ArrayList;

public class ProdutoAdaptor extends RecyclerView.Adapter<ProdutoAdaptor.ProdutoViewHolder> {

    ArrayList<Item> item;
    private OnAddToCartListener addCarrinhoListener;

    public ProdutoAdaptor(Context context, ArrayList<Item> item, OnAddToCartListener addCarrinhoListener) {
        this.item = item;
        this.addCarrinhoListener = addCarrinhoListener;
    }

    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_produto, parent, false);
        return new ProdutoViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Item currentItem = item.get(position);

        String nomeFoto = currentItem.getFoto(); // Nome do recurso drawable
        int imageResId = holder.itemView.getContext().getResources()
                .getIdentifier(nomeFoto, "drawable", holder.itemView.getContext().getPackageName());
        holder.foto.setImageResource(imageResId); // Usa o ID para definir a imagem

        holder.nome.setText(currentItem.getNome());
        holder.preco_unit.setText(String.valueOf("R$" + currentItem.getPreco_unit()));
        holder.preco_total.setText(String.valueOf(currentItem.getPreco_total()));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ProdutoViewHolder extends RecyclerView.ViewHolder {
        private TextView nome;
        private TextView preco_unit;
        private TextView preco_total;
        private ImageView foto;
        private Button btnAdd;
        private TextView btnAumenta, btnDiminui, tvQuant;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.item_name);
            preco_unit = itemView.findViewById(R.id.item_price);
            preco_total = itemView.findViewById(R.id.preco_total);
            foto = itemView.findViewById(R.id.pic);
            btnAdd = itemView.findViewById(R.id.btnRemove);
            btnAumenta = itemView.findViewById(R.id.btn_aumenta);
            btnDiminui = itemView.findViewById(R.id.btn_diminui);
            tvQuant = itemView.findViewById(R.id.TV_quant);


            // Botão de aumentar quantidade
            btnAumenta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantidadeAtual = item.get(getAdapterPosition()).getQuantidade();
                    item.get(getAdapterPosition()).setQuantidade(quantidadeAtual + 1);

                    // Atualiza o valor total
                    double precoTotal = item.get(getAdapterPosition()).getPreco_unit() * item.get(getAdapterPosition()).getQuantidade();
                    item.get(getAdapterPosition()).setPreco_total(precoTotal);

                    // Atualiza a UI
                    tvQuant.setText(String.valueOf(item.get(getAdapterPosition()).getQuantidade()));
                    preco_total.setText("R$ " + precoTotal);
                }
            });

            // Botão de diminuir quantidade
            btnDiminui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantidadeAtual = item.get(getAdapterPosition()).getQuantidade();
                    if (quantidadeAtual > 0) { // Não pode diminuir para um valor negativo
                        item.get(getAdapterPosition()).setQuantidade(quantidadeAtual - 1);

                        // Atualiza o valor total
                        double precoTotal = item.get(getAdapterPosition()).getPreco_unit() * item.get(getAdapterPosition()).getQuantidade();
                        item.get(getAdapterPosition()).setPreco_total(precoTotal);

                        // Atualiza a UI
                        tvQuant.setText(String.valueOf(item.get(getAdapterPosition()).getQuantidade()));
                        preco_total.setText("R$ " + precoTotal);
                    }
                }
            });



            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Pega o item atual e chama o listener
                    addCarrinhoListener.onAddToCart(item.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnAddToCartListener {
        void onAddToCart(Item item);
    }
}
