package com.example.restaurant;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MenuItemActivity extends AppCompatActivity {

    private TextView itemName, itemPrice, itemQuantity;
    private Button btnIncrease, btnDecrease, btnAdd;
    private OrderItem currentOrderItem;
    private int quantity = 1;  // Quantidade inicial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_item); // Certifique-se de que o layout está correto

        itemName = findViewById(R.id.item_name);
        itemPrice = findViewById(R.id.item_price);
        itemQuantity = findViewById(R.id.item_quantity);
        btnIncrease = findViewById(R.id.btn_increase);
        btnDecrease = findViewById(R.id.btn_decrease);
        btnAdd = findViewById(R.id.btn_add);

        // Simule um item (substitua isso por seu método de obter o item real)
        Item item = new Item("Pizza", 20.0);
        currentOrderItem = new OrderItem(item);

        // Inicializa a quantidade e exibe na UI
        itemQuantity.setText(String.valueOf(quantity));
        itemName.setText(currentOrderItem.getItem().getName());
        itemPrice.setText(String.format("R$ %.2f", currentOrderItem.getItem().getPrice()));

        // Aumenta a quantidade quando o botão "+" é clicado
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;  // Incrementa a quantidade
                itemQuantity.setText(String.valueOf(quantity));  // Atualiza o valor exibido
            }
        });

        // Diminui a quantidade quando o botão "-" é clicado (mantém o valor mínimo de 1)
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {  // Verifica se a quantidade é maior que 1
                    quantity--;  // Decrementa a quantidade
                    itemQuantity.setText(String.valueOf(quantity));  // Atualiza o valor exibido
                }
            }
        });

        // Adiciona o item ao pedido quando o botão "Add" é clicado
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtém o item que deseja adicionar
                Item item = new Item("Pizza", 20.00); // Exemplo

                // Adiciona o item ao pedido
                Order order = Order.getInstance();
                order.addItem(item);

                // Debug para verificar se o item foi adicionado
                Log.d("Order", "Item adicionado: " + item.getName());

                // Opcional: Atualizar a UI após adicionar o item (se necessário)
                Toast.makeText(getApplicationContext(), "Item adicionado ao pedido", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


