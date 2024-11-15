package com.example.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class TelaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telainicio);

        // Configurando o botão para "Bebidas"
        findViewById(R.id.btn_bebidas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity("Bebidas");
            }
        });

        // Configurando o botão para "Sobremesas"
        findViewById(R.id.btn_sobremesas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity("Sobremesas");
            }
        });

        // Configurando o botão para "Sobremesas"
        findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenuActivity("Sobremesas");
            }
        });
    }

    // Método para abrir a MenuActivity com a categoria especificada
    private void openMenuActivity(String category) {
        Intent intent = new Intent(TelaInicio.this, MenuActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

}
