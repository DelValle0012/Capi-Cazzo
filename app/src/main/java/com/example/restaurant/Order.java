package com.example.restaurant;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class Order implements Parcelable {
    private ArrayList<OrderItem> orderItems;

    // Singleton pattern (para obter a mesma instância da classe)
    private static Order instance;

    private Order() {
        orderItems = new ArrayList<>();
    }

    public static synchronized Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }

    // Método para adicionar um item ao pedido
    public void addItem(Item item) {
        boolean itemExists = false;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getItem().getNome().equals(item.getNome())) {
                orderItem.incrementQuantity(); // Incrementa a quantidade
                itemExists = true;
                break;
            }
        }
        if (!itemExists) {
            orderItems.add(new OrderItem(item));
        }
    }

    // Método para obter os itens do pedido
    public ArrayList<OrderItem> getItems() {
        return orderItems;
    }

    // Método para calcular o preço total do pedido
    public double getTotalPrice() {
        double total = 0;
        for (OrderItem item : orderItems) {
            total += item.getQuantity() * item.getItem().getPreco_unit();
        }
        return total;
    }

    // Método para limpar o pedido
    public void clearOrder() {
        orderItems.clear();
    }

    // Método para adicionar itens com quantidade específica
    public void addItem(Item item, int quantity) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getItem().equals(item)) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                return;
            }
        }
        orderItems.add(new OrderItem(item, quantity));
    }

    // Construtor que será chamado ao recuperar o objeto da outra Activity
    protected Order(Parcel in) {
        orderItems = in.createTypedArrayList(OrderItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(orderItems);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    // Retorna a lista de itens do pedido (corrigido para ArrayList)
    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;  // Não deve retornar null, sempre retorna a lista
    }
}

