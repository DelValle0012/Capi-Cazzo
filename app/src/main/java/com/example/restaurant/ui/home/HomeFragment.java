package com.example.restaurant.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.Adapter.CategoryAdaptor;
import com.example.restaurant.Domain.CategoryDomain;
import com.example.restaurant.Domain.PopularesDomain;
import com.example.restaurant.Adapter.PopularesAdaptor;
import com.example.restaurant.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewPopularesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configura o RecyclerView
        recyclerViewCategory();
        recyclerViewPopulares();

        // Exemplo de observador para o TextView
        final TextView textView = binding.textView;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = binding.recyclerViewCategoryList;
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Entradas", "cat_1"));
        category.add(new CategoryDomain("Principal", "cat_2"));
        category.add(new CategoryDomain("Bebidas", "cat_3"));
        category.add(new CategoryDomain("Sobremesas", "cat_4"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);

    }

    private void recyclerViewPopulares() {
        // Configuração do layout manager para exibir horizontalmente
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularesList = binding.recyclerViewPopularesList;
        recyclerViewPopularesList.setLayoutManager(linearLayoutManager);

        // Inicialização do ArrayList e do adaptador
        ArrayList<PopularesDomain> popularesList = new ArrayList<>();
        adapter = new PopularesAdaptor(popularesList);
        recyclerViewPopularesList.setAdapter(adapter);

        // Chamada para carregar os produtos do Firestore
        carregarPopulares(popularesList);
    }

    private void carregarPopulares(ArrayList<PopularesDomain> popularesList) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("produtos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        popularesList.clear(); // Limpa a lista para evitar duplicações
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String nome = document.getString("nome");
                            String foto = document.getString("foto");
                            String descricao = document.getString("descricao");
                            Double preco = document.getDouble("preco");

                            PopularesDomain produto = new PopularesDomain(nome, foto, descricao, preco);
                            popularesList.add(produto);
                        }
                        adapter.notifyDataSetChanged(); // Atualiza a RecyclerView com os dados carregados
                    } else {
                        Toast.makeText(getContext(), "Erro ao carregar produtos", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
