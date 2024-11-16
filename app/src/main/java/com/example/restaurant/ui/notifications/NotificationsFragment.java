package com.example.restaurant.ui.notifications;

import android.content.Context;
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

import com.example.restaurant.Item;
import com.example.restaurant.MenuAdapter;
import com.example.restaurant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private ListView menuListView;
    private ArrayList<Item> items;
    private Button btnViewOrder, btnFinalizeOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);

        // Inicializar o ListView e botões
        menuListView = rootView.findViewById(R.id.menu_list);
        btnViewOrder = rootView.findViewById(R.id.btn_view_order);
        btnFinalizeOrder = rootView.findViewById(R.id.btn_finalize_order);

        // Carregar os itens do JSON
        items = loadItemsFromJson(getContext());
        if (items != null) {
            MenuAdapter menuAdapter = new MenuAdapter(getContext(), items);
            menuListView.setAdapter(menuAdapter);
        } else {
            Toast.makeText(getContext(), "Erro ao carregar itens do menu!", Toast.LENGTH_SHORT).show();
        }

        // Configurar eventos dos botões
        btnViewOrder.setOnClickListener(v -> {
            // Implementar a lógica do botão Ver Pedido
            Toast.makeText(getContext(), "Visualizar pedido clicado!", Toast.LENGTH_SHORT).show();
        });

        btnFinalizeOrder.setOnClickListener(v -> {
            // Implementar a lógica do botão Finalizar Pedido
            Toast.makeText(getContext(), "Pedido finalizado!", Toast.LENGTH_SHORT).show();
        });

        return rootView;
    }

    private ArrayList<Item> loadItemsFromJson(Context context) {
        ArrayList<Item> itemList = new ArrayList<>();
        try {
            // Ler o arquivo JSON
            InputStream inputStream = context.getAssets().open("produtos.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            // Converter para string
            String json = new String(buffer, "UTF-8");

            // Parsear o JSON
            JSONObject jsonObject = new JSONObject(json); // O JSON é um objeto, não um array

            //if(categoria.equals = "Pratos Principais"
//            {
//                JSONArray pratosPrincipais = jsonObject.getJSONArray("Pratos Principais");
//                addItemsToList(itemList, pratosPrincipais);
//            }

            // Acesse as categorias diretamente no JSON
            JSONArray pratosPrincipais = jsonObject.getJSONArray("Pratos Principais");
            JSONArray bebidas = jsonObject.getJSONArray("Bebidas");
            JSONArray sobremesas = jsonObject.getJSONArray("Sobremesas");
            JSONArray todosOsPratos = jsonObject.getJSONArray("Todos os Pratos");

            // Adicionar os itens de cada categoria à lista
            addItemsToList(itemList, pratosPrincipais);
            addItemsToList(itemList, bebidas);
            addItemsToList(itemList, sobremesas);
            addItemsToList(itemList, todosOsPratos);

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
            itemList.add(new Item(name, price));
        }
    }

}
