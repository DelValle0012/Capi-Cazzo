package com.example.restaurant.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant.Adapter.CategoryAdaptor;
import com.example.restaurant.Domain.CategoryDomain;
import com.example.restaurant.Domain.PopularesDomain;
import com.example.restaurant.Adapter.PopularesAdaptor;
import com.example.restaurant.MainActivity;
import com.example.restaurant.R;
import com.example.restaurant.databinding.FragmentHomeBinding;
import com.example.restaurant.ui.dashboard.DashboardFragment;
import com.example.restaurant.ui.notifications.NotificationsFragment;
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

        binding.btnPecaja.setOnClickListener(v -> pecaJa());

        // Configura o RecyclerView
        recyclerViewCategory();
        //recyclerViewPopulares();

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

        // Renomeie a variável ArrayList
        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("Entradas", "cat_1"));
        categoryList.add(new CategoryDomain("Principal", "cat_2"));
        categoryList.add(new CategoryDomain("Bebidas", "cat_3"));
        categoryList.add(new CategoryDomain("Sobremesas", "cat_4"));

        adapter = new CategoryAdaptor(categoryList, selectedCategory -> {
            String selectedTitle = selectedCategory.getTitle();
            // Crie o fragmento de destino
            NotificationsFragment notificationsFragment = new NotificationsFragment();

            // Crie um Bundle para passar a categoria selecionada
            Bundle bundle = new Bundle();
            bundle.putString("selectedCategory", selectedCategory.getTitle());

            // Defina os argumentos no fragmento
            notificationsFragment.setArguments(bundle);

            // Navegue para o fragmento
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.action_navigation_home_to_navigation_notifications);
        });

        recyclerViewCategoryList.setAdapter(adapter);
    }


//    private void recyclerViewPopulares() {
//        // Configuração do layout manager para exibir horizontalmente
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewPopularesList = binding.recyclerViewPopularesList;
//        recyclerViewPopularesList.setLayoutManager(linearLayoutManager);
//
//        // Inicialização do ArrayList e do adaptador
//        ArrayList<PopularesDomain> popularesList = new ArrayList<>();
//        adapter = new PopularesAdaptor(popularesList);
//        recyclerViewPopularesList.setAdapter(adapter);
//
//        // Chamada para carregar os produtos do Firestore
//        carregarPopulares(popularesList);
//    }
//
//    private void carregarPopulares(ArrayList<PopularesDomain> popularesList) {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("produtos")
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        popularesList.clear(); // Limpa a lista para evitar duplicações
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            String nome = document.getString("nome");
//                            String foto = document.getString("foto");
//                            String descricao = document.getString("descricao");
//                            Double preco = document.getDouble("preco");
//
//                            PopularesDomain produto = new PopularesDomain(nome, foto, descricao, preco);
//                            popularesList.add(produto);
//                        }
//                        adapter.notifyDataSetChanged(); // Atualiza a RecyclerView com os dados carregados
//                    } else {
//                        Toast.makeText(getContext(), "Erro ao carregar produtos", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    private void pecaJa() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.action_navigation_home_to_navigation_notifications);
    }



}