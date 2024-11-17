package com.example.restaurant.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.restaurant.Adapter.ProdutoAdaptor;
import com.example.restaurant.Order;
import com.example.restaurant.OrderItem;
import com.example.restaurant.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.CarrinhoActivity;
import com.example.restaurant.Item;
import com.example.restaurant.MenuAdapter;
import com.example.restaurant.R;
import com.example.restaurant.RecuperaContaActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class DashboardFragment extends Fragment {

    private RecyclerView RecyclerViewMenu;
    private ArrayList<Item> items;
    private Button btnViewOrder, btnFinalizeOrder;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Recuperar o argumento da categoria
        String categoria = "Todos os Pratos"; // Valor padrão caso não seja passado
        if (getArguments() != null) {
            categoria = getArguments().getString("categoriaSelecionada", "Todos os Pratos");
        }

        Toast.makeText(getContext(), "Categoria selecionada: " + categoria, Toast.LENGTH_SHORT).show();

        // Inicializar o ListView e botões
        RecyclerViewMenu = rootView.findViewById(R.id.RecyclerViewMenu);
        btnFinalizeOrder = rootView.findViewById(R.id.btn_finalize_order);

        RecyclerViewMenu.setLayoutManager(new LinearLayoutManager(getContext()));

        // Carregar os itens do JSON com base na categoria
        items = loadItemsFromJson(getContext(), categoria);
        if (items != null) {
            ProdutoAdaptor menuAdapter = new ProdutoAdaptor(getContext(), items);
            RecyclerViewMenu.setAdapter(menuAdapter);
        } else {
            Toast.makeText(getContext(), "Erro ao carregar itens do menu!", Toast.LENGTH_SHORT).show();
        }

        // Configurar eventos dos botões
        btnFinalizeOrder.setOnClickListener(v -> {
            // Criar uma Intent para iniciar a Activity
            Intent intent = new Intent(getActivity(), CarrinhoActivity.class);
            startActivity(intent); // Iniciar a Activity
        });


        return rootView;
    }

    private ArrayList<Item> loadItemsFromJson(Context context, String categoria) {
        ArrayList<Item> itemList = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("produtos.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);

            // Recuperar itens específicos da categoria
            JSONArray jsonArray = jsonObject.optJSONArray(categoria);
            if (jsonArray != null) {
                addItemsToList(itemList, jsonArray);
            }
        } catch (IOException | JSONException e) {
            Log.e("NotificationsFragment", "Erro ao carregar o JSON", e);
        }

        return itemList;
    }

    // Método auxiliar para adicionar itens a partir de um JSONArray
    private void addItemsToList(ArrayList<Item> itemList, JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("nome");
            double price = jsonObject.getDouble("preco");
            String photo = jsonObject.optString("foto", "produtos_default"); // Valor padrão
            int quantidade = 1; // Defina uma quantidade padrão, ou recupere do JSON se disponível

            // Criando o objeto Item com os quatro parâmetros
            Item item = new Item(name, price, photo, quantidade);
            itemList.add(item);
        }
    }


}