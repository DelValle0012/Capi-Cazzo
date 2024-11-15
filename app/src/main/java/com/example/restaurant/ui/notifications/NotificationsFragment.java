package com.example.restaurant.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.restaurant.InvoiceActivity;
import com.example.restaurant.Item;
import com.example.restaurant.MenuAdapter;
import com.example.restaurant.Order;
import com.example.restaurant.OrderActivity;
import com.example.restaurant.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    public class MenuActivity extends AppCompatActivity {

        private Button btnViewOrder, btnFinalizeOrder;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu);


            // Receber a categoria selecionada
            String category = getIntent().getStringExtra("category");

            // Atualizar o título da categoria
            TextView categoryTextView = findViewById(R.id.category_text_view);
            categoryTextView.setText(category);

            // Inicializar os botões
            btnViewOrder = findViewById(R.id.btn_view_order);
            btnFinalizeOrder = findViewById(R.id.btn_finalize_order);

            // Carregar os itens corretos com base na categoria
            ArrayList<Item> items = new ArrayList<>();

            // Verifique se a string da categoria está sendo passada corretamente
            if (category.equals("Pratos Principais")) {
                items.add(new Item("Carne Assada", 30.00));
                items.add(new Item("Peixe Grelhado", 40.00));
                items.add(new Item("Massa ao Sugo", 20.00));
                // Adicione outros itens de Pratos Principais
            } else if (category.equals("Bebidas")) {
                items.add(new Item("Refrigerante", 5.00));
                items.add(new Item("Água com Gás", 3.50));
                items.add(new Item("Chá Gelado", 4.00));
                // Adicione outras bebidas
            } else if (category.equals("Sobremesas")) {
                items.add(new Item("Torta de Limão", 12.00));
                items.add(new Item("Pudim de Leite", 10.00));
                items.add(new Item("Brownie", 15.00));
                // Adicione outras sobremesas
            }

            // Configurar o adaptador para mostrar os itens
            ListView menuListView = findViewById(R.id.menu_list);
            MenuAdapter menuAdapter = new MenuAdapter(this, items);
            menuListView.setAdapter(menuAdapter);

            // Adicione logs para verificar se os botões estão funcionando corretamente
            btnViewOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Order order = Order.getInstance();  // Certifique-se de pegar o pedido corretamente
                    if (order != null && order.getItems() != null && !order.getItems().isEmpty()) {
                        Intent intent = new Intent(MenuActivity.this, OrderActivity.class);  // Mude para OrderActivity
                        startActivity(intent);  // Inicia a OrderActivity para exibir os pedidos
                    } else {
                        Toast.makeText(MenuActivity.this, "O pedido está vazio!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnFinalizeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("MenuActivity", "Botão Finalizar Pedido Clicado");
                    Intent intent = new Intent(MenuActivity.this, InvoiceActivity.class);
                    startActivity(intent);
                }
            });


        }
    }
}