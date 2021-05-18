package com.burning_glow.wanpipteacustomer.ui.fragments;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burning_glow.wanpipteacustomer.R;
import com.burning_glow.wanpipteacustomer.model.Products;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MenuFragment extends Fragment {

    private RecyclerView rvMenu;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);
        rvMenu = view.findViewById(R.id.menu_rv);

        firebaseFirestore = FirebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("Products");

        FirestoreRecyclerOptions<Products> options = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(query, Products.class).build();

        adapter = new FirestoreRecyclerAdapter<Products, ProductsViewHolder>(options) {
            @NonNull
            @org.jetbrains.annotations.NotNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MenuFragment.ProductsViewHolder holder, int position, @NonNull @org.jetbrains.annotations.NotNull Products model) {

                holder.productName.setText(model.getName());
                holder.productMedium.setText("Med: " + String.valueOf(model.getMedium()));
                holder.productLarge.setText("Large: " + String.valueOf(model.getLarge()));
                Log.d("CAZ", "onBindViewHolder: " + model.getName());
            }
        };

        rvMenu.setHasFixedSize(true);
        rvMenu.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvMenu.setAdapter(adapter);

        return view;
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder{

        private TextView productName;
        private TextView productMedium;
        private TextView productLarge;

        public ProductsViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productMedium = itemView.findViewById(R.id.product_medium);
            productLarge = itemView.findViewById(R.id.product_large);
        }
    }
}
