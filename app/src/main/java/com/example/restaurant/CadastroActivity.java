package com.example.restaurant;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.restaurant.databinding.ActivityCadastroBinding;
import com.google.firebase.auth.FirebaseAuth;


public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        binding.btnCad.setOnClickListener(v -> validaDados());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void validaDados()
    {
        //Pega o texto do campo de email, converte para string, o o trim(), remove os espaços em brancos no inico e no final
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editSenha.getText().toString().trim();

        if(!email.isEmpty())
        {
            if(!senha.isEmpty())
            {
                binding.progressBar.setVisibility(View.VISIBLE);

                criarContaFirebase(email, senha);
            }
            else
            {
                Toast.makeText(this, "Informe uma senha.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Informe um E-mail.", Toast.LENGTH_SHORT).show();
        }

    }

    private void criarContaFirebase(String email, String senha)
    {
        mAuth.createUserWithEmailAndPassword(
                email,
                senha
        ).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                finish();
                startActivity(new Intent(this, TelaInicio.class));

            } else {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Errp", Toast.LENGTH_SHORT).show();
            }
        });
    }
}