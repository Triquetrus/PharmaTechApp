package com.example.pharmatech;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsFragment extends Fragment {

    View view;
    private SharedPreferences sharedPreferences;
    FirebaseAuth auth;
    Switch switch_notifications1,switch_security,switch_dark_mode;
    Spinner spinner_language;
    TextView text_privacy_policy,text_about_us,button_logout,button_exit_app;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);


        sharedPreferences = getActivity().getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
        switch_notifications1 = view.findViewById(R.id.switch_notifications1);
        switch_security = view.findViewById(R.id.switch_security);
        spinner_language = view.findViewById(R.id.spinner_language);
        text_privacy_policy = view.findViewById(R.id.text_privacy_policy);
        text_about_us = view.findViewById(R.id.text_about_us);
        switch_dark_mode = view.findViewById(R.id.switch_dark_mode);
        button_logout = view.findViewById(R.id.button_logout);
        button_exit_app = view.findViewById(R.id.button_exit_app);


        // Load saved preferences
        switch_notifications1.setChecked(sharedPreferences.getBoolean("notifications", false));
        switch_security.setChecked(sharedPreferences.getBoolean("security", false));
        switch_dark_mode.setChecked(sharedPreferences.getBoolean("dark_mode", false));
        int languagePosition = sharedPreferences.getInt("language", 0);
        spinner_language.setSelection(languagePosition);



        // Set up listeners to save preferences
        switch_notifications1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("notifications", isChecked);
            editor.apply();
        });

        switch_security.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("security", isChecked);
            editor.apply();

            if(switch_security.isChecked()) {
                Toast.makeText(getActivity(), "Security is Enabled", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getActivity(), "Security is Disabled", Toast.LENGTH_SHORT).show();
            }

        });

        spinner_language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("language", position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        text_privacy_policy.setOnClickListener(v -> {

            Intent i = new Intent(getContext(), PrivacyPolicy.class);
            startActivity(i);

        });

        text_about_us.setOnClickListener(v -> {

            Intent i = new Intent(getContext(), AboutUs.class);
            startActivity(i);
        });

        switch_dark_mode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("dark_mode", isChecked);
            editor.apply();

            if (isChecked) {

               getActivity().setTheme(androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dark);
               Toast.makeText(getActivity(), "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
            } else {

                getActivity().setTheme(androidx.constraintlayout.widget.R.style.ThemeOverlay_AppCompat_Light);
                Toast.makeText(getActivity(), "Dark Mode Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        button_logout.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "Logging Out!", Toast.LENGTH_SHORT).show();
            auth = FirebaseAuth.getInstance();
            auth.signOut();
            Intent i = new Intent(getContext(), startpage.class);
            startActivity(i);
        });

        button_exit_app.setOnClickListener(view -> {
            Toast.makeText(getActivity(), "Exiting the App", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            System.exit(0);

        });

        return view;
    }
}