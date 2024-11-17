package com.example.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ViewOrderActivity extends AppCompatActivity {

    private ListView orderListView;
    private TextView totalPriceTextView;  // Declaração correta do TextView
    private Button btnFinalizeOrder;
    private OrderAdapter orderAdapter;
    private ArrayList<OrderItem> currentOrderItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        int itemQuantity = intent.getIntExtra("itemQuantity", 0);
        double itemPrice = intent.getDoubleExtra("itemPrice", 0.0);

        // Exibe essas informações em TextViews ou qualquer outro widget que você deseje
        TextView orderSummary = findViewById(R.id.order_summary);
        orderSummary.setText(itemQuantity + "x " + itemName + " - R$ " + String.format("%.2f", itemPrice));


        // Inicializando os componentes da interface
        orderListView = findViewById(R.id.order_list_view);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        btnFinalizeOrder = findViewById(R.id.btn_finalize_order_from_view);

        // Pegando os itens do pedido atual
        currentOrderItems = Order.getInstance().getItems();

        // Configurando o adaptador
        orderAdapter = new OrderAdapter(this, currentOrderItems);
        orderListView.setAdapter(orderAdapter);

        // Atualiza o preço total
        updateTotalPrice();

        // Permitir que o usuário altere a quantidade de itens
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lógica para alterar a quantidade do item
                OrderItem selectedItem = currentOrderItems.get(position);
                selectedItem.incrementQuantity();
                orderAdapter.notifyDataSetChanged();
                updateTotalPrice();
            }
        });

        // Finalizar o pedido
        btnFinalizeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Abrir a tela de nota fiscal (InvoiceActivity)
                    Intent intent = new Intent(ViewOrderActivity.this, InvoiceActivity.class);
                    startActivity(intent);

                    // Log para verificar se o botão foi clicado
                    Log.d("ViewOrderActivity", "Botão Finalizar Pedido foi clicado.");

                } catch (Exception e) {
                    // Capturar qualquer erro que possa ocorrer e exibir
                    Log.e("ViewOrderActivity", "Erro ao finalizar pedido: " + e.getMessage());
                    e.printStackTrace();
                    Toast.makeText(ViewOrderActivity.this, "Erro ao finalizar pedido", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    // Método para atualizar o valor total
    private void updateTotalPrice() {
        double totalPrice = 0.0;
        for (OrderItem item : currentOrderItems) {
            totalPrice += item.getItem().getPreco_unit() * item.getQuantity();
        }
        totalPriceTextView.setText("Total: R$ " + String.format("%.2f", totalPrice));  // Atualiza o valor total
    }
}

