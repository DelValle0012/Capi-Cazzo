package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class FinalizeOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_order);

        // Redireciona para a "nota fiscal"
        Intent intent = new Intent(FinalizeOrderActivity.this, InvoiceActivity.class);
        startActivity(intent);
        finish(); // Fecha a atividade de finalização do pedido
    }
}
