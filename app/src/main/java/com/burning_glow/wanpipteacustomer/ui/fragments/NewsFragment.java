package com.burning_glow.wanpipteacustomer.ui.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.burning_glow.wanpipteacustomer.R;

public class NewsFragment extends Fragment {

    private TextView newsFeed;
    private CardView cvMenu;
    private CardView cvContact;
    private CardView cvLoyaltea;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        cvMenu = view.findViewById(R.id.cv_home_menu);
        cvContact = view.findViewById(R.id.cv_home_contact);
        cvLoyaltea = view.findViewById(R.id.cv_home_loyaltea);

        newsFeed = view.findViewById(R.id.tv_news_feed);

        newsFeed.setText("Releasing of Wampip Tea 1.0 soon!!!\n\nNews Flash! Justine is a puppy.");
        newsFeed.setMovementMethod(new ScrollingMovementMethod());

        cvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Note: fragment_container is the container (FrameLayout or other layouts) for the activity that calls the fragment
                // Located in activity_welcome_screen, since WelcomeScreen.class is activity all 4 fragments originate from
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new MenuFragment());
                fr.commit();

            }
        });

        cvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Note: fragment_container is the container (FrameLayout or other layouts) for the activity that calls the fragment
                // Located in activity_welcome_screen, since WelcomeScreen.class is activity all 4 fragments originate from
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new ContactFragment());
                fr.commit();

            }
        });

        cvLoyaltea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Note: fragment_container is the container (FrameLayout or other layouts) for the activity that calls the fragment
                // Located in activity_welcome_screen, since WelcomeScreen.class is activity all 4 fragments originate from
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new LoyaltyFragment());
                fr.commit();


            }
        });

        return view;
    }
}
