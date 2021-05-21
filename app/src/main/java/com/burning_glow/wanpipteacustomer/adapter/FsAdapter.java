package com.burning_glow.wanpipteacustomer.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burning_glow.wanpipteacustomer.R;
import com.burning_glow.wanpipteacustomer.model.Products;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

import org.jetbrains.annotations.NotNull;

public class FsAdapter extends FirestoreRecyclerAdapter<Products, FsAdapter.ProductsViewHolder> {
    private OnItemClickListener listener;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FsAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull FsAdapter.ProductsViewHolder holder, int position, @NonNull @NotNull Products model) {
        holder.productName.setText(model.getName());
        holder.productMedium.setText("Med: " + String.valueOf(model.getMedium()));
        holder.productLarge.setText("Large: " + String.valueOf(model.getLarge()));

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(v.getContext(), "you clicked " + model.getName(), Toast.LENGTH_SHORT).show();
////                        cartDialog.show();
//                    }
//                });

    }

    @NonNull
    @NotNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);

        return new ProductsViewHolder(view);
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {

        private TextView productName;
        private TextView productMedium;
        private TextView productLarge;

        public ProductsViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productMedium = itemView.findViewById(R.id.product_medium);
            productLarge = itemView.findViewById(R.id.product_large);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}






//package com.burning_glow.wanpipteacustomer.adapter;
//
//
//
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.TextView;
//
//        import androidx.annotation.NonNull;
//        import androidx.recyclerview.widget.RecyclerView;
//
//        import com.burning_glow.wanpipteacustomer.R;
//        import com.burning_glow.wanpipteacustomer.model.Products;
//        import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
//        import com.firebase.ui.firestore.FirestoreRecyclerOptions;
//
//        import org.jetbrains.annotations.NotNull;
//
//public class FsAdapter extends FirestoreRecyclerAdapter<Products, FsAdapter.ProductsViewHolder> {
//
//    /**
//     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
//     * FirestoreRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */
//    public FsAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Products> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull @NotNull FsAdapter.ProductsViewHolder holder, int position, @NonNull @NotNull Products model) {
//        holder.productName.setText(model.getName());
//        holder.productMedium.setText("Med: " + String.valueOf(model.getMedium()));
//        holder.productLarge.setText("Large: " + String.valueOf(model.getLarge()));
//
//    }
//
//    @NonNull
//    @NotNull
//    @Override
//    public ProductsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
//
//        return new ProductsViewHolder(view);
//    }
//
//    public class ProductsViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView productName;
//        private TextView productMedium;
//        private TextView productLarge;
//
//        public ProductsViewHolder(@NonNull @NotNull View itemView) {
//            super(itemView);
//
//            productName = itemView.findViewById(R.id.product_name);
//            productMedium = itemView.findViewById(R.id.product_medium);
//            productLarge = itemView.findViewById(R.id.product_large);
//        }
//    }
//
//}
