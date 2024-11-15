package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<OrderItem> {

    public OrderAdapter(Context context, ArrayList<OrderItem> orderItems) {
        super(context, 0, orderItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
        }

        OrderItem orderItem = getItem(position);

        TextView itemName = convertView.findViewById(R.id.order_item_name);
        TextView itemQuantity = convertView.findViewById(R.id.order_item_quantity);
        TextView itemTotalPrice = convertView.findViewById(R.id.order_item_total_price);

        itemName.setText(orderItem.getItem().getName());
        itemQuantity.setText("Quantidade: " + orderItem.getQuantity());
        itemTotalPrice.setText("R$ " + String.format("%.2f", orderItem.getItem().getPrice() * orderItem.getQuantity()));

        return convertView;
    }
}
