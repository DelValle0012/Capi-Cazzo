package com.example.restaurant;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderSummaryActivity extends AppCompatActivity {

    private ListView orderListView;
    private TextView totalPriceTextView;
    private Order currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        orderListView = findViewById(R.id.order_list);
        totalPriceTextView = findViewById(R.id.total_price);

        currentOrder = Order.getInstance();
        OrderAdapter orderAdapter = new OrderAdapter(this, currentOrder.getItems());
        orderListView.setAdapter((ListAdapter) orderAdapter);

        totalPriceTextView.setText("Total: R$ " + String.format("%.2f", currentOrder.getTotalPrice()));
    }
}
