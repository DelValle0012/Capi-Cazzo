package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private ListView listView;
    private TextView totalPriceText;
    private Button btnFinalizeOrder;  // Declare o botão aqui

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);  // Certifique-se que este layout existe

        // Vincula os componentes da UI
        listView = findViewById(R.id.orderListView);  // Certifique-se que o ID está correto no layout XML
        totalPriceText = findViewById(R.id.totalPriceText);  // Certifique-se que o ID está correto no layout XML
        btnFinalizeOrder = findViewById(R.id.btn_finalize_order);  // Certifique-se de que este ID está correto no XML

        // Recupera o objeto Order (Singleton)
        Order order = Order.getInstance();  // Pega a instância do pedido

        if (order != null && !order.getItems().isEmpty()) {
            // Configura o adapter para o ListView
            OrderAdapter adapter = new OrderAdapter(this, order.getItems());
            listView.setAdapter(adapter);

            // Define o preço total no TextView
            double totalPrice = order.getTotalPrice();
            totalPriceText.setText("Total: R$ " + String.format("%.2f", totalPrice));
        } else {
            totalPriceText.setText("Nenhum pedido encontrado.");
        }

        // Configura o clique no botão de finalizar pedido
        btnFinalizeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OrderActivity", "Botão Finalizar Pedido Clicado");

                Order order = Order.getInstance();

                if (order != null && order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
                    Intent intent = new Intent(OrderActivity.this, InvoiceActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(OrderActivity.this, "Pedido vazio!", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}

