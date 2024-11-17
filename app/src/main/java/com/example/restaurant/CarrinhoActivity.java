package com.example.restaurant;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.restaurant.Adapter.CarrinhoAdaptor;
import com.example.restaurant.Domain.CarrinhoDomain;
import com.example.restaurant.databinding.ActivityCarrinhoBinding;

import java.util.ArrayList;

public class CarrinhoActivity extends AppCompatActivity {

    private ActivityCarrinhoBinding binding;
    private double descontoCupom = 0.0;
    private double valorTotal = 0.0;
    private double totalFinal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCarrinhoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configuração para ajustar padding com base no sistema de barras
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar RecyclerView
        binding.cardViewCompras.setLayoutManager(new LinearLayoutManager(this));

        // Recuperar a lista de compras
        ArrayList<CarrinhoDomain> listaCompras = getIntent().getParcelableArrayListExtra("compras");
        if (listaCompras != null) {
            CarrinhoAdaptor adaptor = new CarrinhoAdaptor(this, listaCompras);
            binding.cardViewCompras.setAdapter(adaptor);

            // Calcular o valor total
            for (CarrinhoDomain item : listaCompras) {
                valorTotal += item.getValor_total(); // Soma o valor total de cada item
            }

            // Exibir o subtotal
            binding.textViewSubtotal.setText("R$ " + String.format("%.2f", valorTotal));

            // Calcular o total final
            totalFinal = valorTotal - (valorTotal * descontoCupom);
            binding.textViewValorTotal.setText("R$ " + String.format("%.2f", totalFinal));
        } else {
            // Caso a lista seja nula, exibir mensagem ou tratar o erro
            System.out.println("Nenhuma compra foi passada para o carrinho.");
        }

        // Ação do botão "Aplicar Cupom"
        binding.btnAplicarCupom.setOnClickListener(v -> {
            // Lógica de aplicação de cupom
            String cupom = binding.editTextCupom.getText().toString();
            if (cupom.equals("VALE15")) {
                descontoCupom = 0.15; // Desconto de 15%
            } else if (cupom.equals("VALE20")) {
                descontoCupom = 0.20; // Desconto de 20%
            } else if (cupom.equals("VALE50")) {
                descontoCupom = 0.50; // Desconto de 50%
            } else {
                descontoCupom = 0.0; // Nenhum cupom válido
            }

            // Atualizar a interface com o desconto
            double valorDesconto = valorTotal * descontoCupom;
            binding.textViewDesconto.setText("Desconto: R$ " + String.format("%.2f", valorDesconto));

            // Atualizar o total final com o desconto aplicado
            totalFinal = valorTotal - valorDesconto;
            binding.textViewValorTotal.setText("Total: R$ " + String.format("%.2f", totalFinal));
        });

        // Ação do botão "Pagar"
        binding.btnPagar.setOnClickListener(v -> {
            // Lógica para finalizar o pedido
            System.out.println("Pedido finalizado com sucesso!");
        });
    }
}
