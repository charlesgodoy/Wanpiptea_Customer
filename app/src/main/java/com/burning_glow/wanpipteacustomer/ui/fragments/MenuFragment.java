package com.burning_glow.wanpipteacustomer.ui.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.burning_glow.wanpipteacustomer.R;
import com.burning_glow.wanpipteacustomer.adapter.FsAdapter;
import com.burning_glow.wanpipteacustomer.model.Products;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.jar.Attributes;

public class MenuFragment extends Fragment {

    private RecyclerView rvMenu;
    private FirebaseFirestore firebaseFirestore;
    private FsAdapter adapter;

    Dialog cartDialog;
    Context ctx;
    Button btnAddToCart;
    Button btnCancel;
    ImageView ibMedium;
    ImageView ibLarge;
    String drinkSizeSelected = "Small";
    String largeDrink = "Large";
    String mediumDrink = "Medium";
    TextView teaChoice;

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

        ctx = container.getContext();

        rvMenu = view.findViewById(R.id.menu_rv);

        firebaseFirestore = FirebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("Products");

        FirestoreRecyclerOptions<Products> options = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(query, Products.class).build();

        adapter = new FsAdapter(options);

        rvMenu.setHasFixedSize(true);
        rvMenu.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvMenu.setAdapter(adapter);

        cartDialog = new Dialog(ctx);
        cartDialog.setContentView(R.layout.add_to_art_dialog_alternative);
        cartDialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.add_cart_background));
        cartDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        cartDialog.setCancelable(false);
        cartDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        btnAddToCart = cartDialog.findViewById(R.id.btn_add_to_cart);
        btnCancel = cartDialog.findViewById(R.id.btn_add_to_cart_cancel);
        ibMedium = cartDialog.findViewById(R.id.ib_medium);
        ibLarge = cartDialog.findViewById(R.id.ib_large);
        ibMedium.setImageResource(R.drawable.medium_button);
        ibLarge.setImageResource(R.drawable.large_button);

        teaChoice = cartDialog.findViewById(R.id.tv_add_to_card_tea_name);

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Add to Cart dialog button clicked", Toast.LENGTH_SHORT).show();
                cartDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Cancel dialog button clicked", Toast.LENGTH_SHORT).show();
                cartDialog.dismiss();
            }
        });

        adapter.setOnItemClickListener(new FsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Toast.makeText(ctx, "position: " + position, Toast.LENGTH_SHORT).show();
                teaChoice.setText(documentSnapshot.getString("Name"));
//                Log.d("CAZ", "documentsnapshot: " + documentSnapshot.getString("Name"));
                drinkSizeSelected = mediumDrink;
                ibMedium.setImageResource(R.drawable.medium_check);
                ibLarge.setImageResource(R.drawable.large_button);
                drinkSize();
                cartDialog.show();
            }
        });

        return view;
    }

    private void drinkSize() {

        ibMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibMedium.setImageResource(R.drawable.medium_check);
                ibLarge.setImageResource(R.drawable.large_button);
                drinkSizeSelected = mediumDrink;

                Toast.makeText(ctx, "Drink size = " + drinkSizeSelected, Toast.LENGTH_SHORT).show();
            }
        });

        ibLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ibLarge.setImageResource(R.drawable.large_check);
                ibMedium.setImageResource(R.drawable.medium_button);
                drinkSizeSelected = largeDrink;

                Toast.makeText(ctx, "Drink size = " + drinkSizeSelected, Toast.LENGTH_SHORT).show();
            }
        });
    }
}