package com.burning_glow.wanpipteacustomer.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.burning_glow.wanpipteacustomer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

public class ContactFragment extends Fragment {

    Context ctx;

    private TextView contactBranch;
    private TextView contactName;
    private TextView contactAddress;
    private TextView contactMobile;

    private FirebaseFirestore firebaseFirestore;

    public static final String KEY_ADDRESS = "Address";
    public static final String KEY_BRANCH = "Branch";
    public static final String KEY_MOBILE = "Mobile";
    public static final String KEY_NAME = "Name";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_contact, container, false);

        ctx = container.getContext();

        contactBranch = view.findViewById(R.id.contact_branch);
        contactName = view.findViewById(R.id.contact_name);
        contactAddress = view.findViewById(R.id.contact_address);
        contactMobile = view.findViewById(R.id.contact_mobile);

        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("Contact").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (!queryDocumentSnapshots.isEmpty()) {

                    // Note: .get(0) refers to the index. Since only 1 element in array (firestore collection: Contact, index is 0
                    contactBranch.setText(queryDocumentSnapshots.getDocuments().get(0).get(KEY_BRANCH).toString());
                    contactName.setText(queryDocumentSnapshots.getDocuments().get(0).get(KEY_NAME).toString());
                    contactAddress.setText(queryDocumentSnapshots.getDocuments().get(0).get(KEY_ADDRESS).toString());
                    contactMobile.setText(queryDocumentSnapshots.getDocuments().get(0).get(KEY_MOBILE).toString());


                } else {
                    Toast.makeText(ctx, "No data", Toast.LENGTH_SHORT).show();
                }

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Log.d("CAZ", "onFailure: " + e.toString());
                    }
                });


        return view;
    }
}