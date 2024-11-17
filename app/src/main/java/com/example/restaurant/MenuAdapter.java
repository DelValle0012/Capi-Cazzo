package com.example.restaurant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<Item> {

    private ArrayList<Integer> itemQuantities;  // Lista para armazenar a quantidade de cada item

    public MenuAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        itemQuantities = new ArrayList<>();  // Inicializa a lista de quantidades
        for (int i = 0; i < items.size(); i++) {
            itemQuantities.add(1);  // Define a quantidade inicial de cada item como 1
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        Item item = getItem(position);


        TextView itemName = convertView.findViewById(R.id.item_name);
        TextView itemPrice = convertView.findViewById(R.id.item_price);
        TextView itemQuantity = convertView.findViewById(R.id.item_quantity);  // Quantidade do item
        Button addButton = convertView.findViewById(R.id.btn_add);
        Button increaseButton = convertView.findViewById(R.id.btn_increase);
        Button decreaseButton = convertView.findViewById(R.id.btn_decrease);

        itemName.setText(item.getNome());
        itemPrice.setText("R$ " + String.format("%.2f", item.getPreco_unit()));

        // Exibe a quantidade atual para o item atual
        itemQuantity.setText(String.valueOf(itemQuantities.get(position)));

        // Botão para aumentar a quantidade
        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = itemQuantities.get(position);
                itemQuantities.set(position, currentQuantity + 1);  // Incrementa a quantidade
                itemQuantity.setText(String.valueOf(itemQuantities.get(position)));  // Atualiza a quantidade exibida
            }
        });

        // Botão para diminuir a quantidade (não permite valor menor que 1)
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = itemQuantities.get(position);
                if (currentQuantity > 1) {
                    itemQuantities.set(position, currentQuantity - 1);  // Decrementa a quantidade
                    itemQuantity.setText(String.valueOf(itemQuantities.get(position)));  // Atualiza a quantidade exibida
                }
            }
        });

        // Botão "Adicionar" adiciona o item com a quantidade certa ao pedido
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Verifique se itemQuantity ou item não são nulos antes de continuar
                    if (itemQuantity == null || item == null) {
                        Toast.makeText(MenuAdapter.this.getContext(), "Erro: item ou campo de quantidade não inicializados.", Toast.LENGTH_SHORT).show();
                        return; // Pare a execução se algo estiver nulo
                    }

                    String quantityString = itemQuantity.getText().toString();
                    int newQuantity = 0;

                    // Verifique se a quantidade é válida
                    if (!quantityString.isEmpty()) {
                        try {
                            newQuantity = Integer.parseInt(quantityString);
                        } catch (NumberFormatException e) {
                            Toast.makeText(MenuAdapter.this.getContext(), "Erro: quantidade inválida.", Toast.LENGTH_SHORT).show();
                            return; // Pare a execução se houver erro de número
                        }
                    }

                    // Adiciona o item ao pedido se a quantidade for maior que 0
                    if (newQuantity > 0) {
                        Order.getInstance().addItem(item, newQuantity);
                        Toast.makeText(MenuAdapter.this.getContext(), newQuantity + "x " + item.getNome() + " adicionados ao pedido.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MenuAdapter.this.getContext(), "Quantidade inválida. Por favor, insira um valor maior que 0.", Toast.LENGTH_SHORT).show();
                    }

                } catch (NullPointerException e) {
                    Toast.makeText(MenuAdapter.this.getContext(), "Erro: item ou contexto nulo.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace(); // Opcional: log para entender a origem do problema
                } catch (Exception e) {
                    Toast.makeText(MenuAdapter.this.getContext(), "Erro desconhecido.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace(); // Opcional: log para entender a origem do problema
                }
            }
        });




        // Configurações adicionais do layout
        itemName.setTextColor(Color.parseColor("#FFD700"));  // Define a cor dinamicamente

        return convertView;
    }
}
