package com.example.restaurant;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Esta linha 'Handler(...) tem a função de esperar um tempo para executar um comando...
        new Handler(getMainLooper()).postDelayed(() -> {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }, 3000);
        //O tempo é dado em milissegundos, ou seja, a ação vai ser executado após 3 seg.
    }
}