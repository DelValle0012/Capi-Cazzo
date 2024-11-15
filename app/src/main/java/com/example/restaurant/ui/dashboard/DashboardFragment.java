package com.example.restaurant.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.restaurant.Order;
import com.example.restaurant.OrderItem;
import com.example.restaurant.R;
import com.example.restaurant.databinding.FragmentDashboardBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class DashboardFragment extends Fragment {

    public class InvoiceActivity extends AppCompatActivity {

        private TextView invoiceTitle, invoiceDate, invoiceItems, invoiceTotal;
        private Button btnDone;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_invoice);

            // Inicializando as Views
            invoiceTitle = findViewById(R.id.invoice_title);
            invoiceDate = findViewById(R.id.invoice_date);
            invoiceItems = findViewById(R.id.invoice_items);
            invoiceTotal = findViewById(R.id.invoice_total);
            btnDone = findViewById(R.id.btn_done);

            // Definir a data atual
            String currentDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());
            invoiceDate.setText("Data: " + currentDate);

            // Receber o pedido finalizado
            Order order = Order.getInstance();

            if (order == null || order.getOrderItems() == null) {
                Log.e("InvoiceActivity", "Pedido está vazio ou nulo");
                Toast.makeText(this, "Erro: Pedido vazio.", Toast.LENGTH_SHORT).show();
                return;
            }


            // Verificar os itens do pedido
            for (OrderItem orderItem : order.getOrderItems()) {
                Log.d("InvoiceActivity", "Item no pedido: " + orderItem.getItem().getName());
            }

            // Exibir os itens do pedido na "nota fiscal"
            StringBuilder itemsDetails = new StringBuilder();
            double totalPrice = 0.0;

            // Iterar sobre os itens do pedido
            List<OrderItem> items = order.getOrderItems();
            for (OrderItem orderItem : items) {
                itemsDetails.append(orderItem.getQuantity())
                        .append("x ")
                        .append(orderItem.getItem().getName())
                        .append(" - R$ ")
                        .append(String.format(Locale.getDefault(), "%.2f", orderItem.getItem().getPrice() * orderItem.getQuantity()))
                        .append("\n");
                totalPrice += orderItem.getItem().getPrice() * orderItem.getQuantity();
            }

            // Exibir os detalhes dos itens e o preço total
            invoiceItems.setText(itemsDetails.toString());
            invoiceTotal.setText("Total: R$ " + String.format(Locale.getDefault(), "%.2f", totalPrice));

            // Limpar o pedido e concluir a "nota fiscal"
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Certifique-se de que a ordem foi limpa corretamente
                    if (order != null) {
                        order.clearOrder();
                    }
                    finish(); // Fecha a tela e retorna ao menu principal ou finaliza a atividade
                }
            });
        }
    }
}