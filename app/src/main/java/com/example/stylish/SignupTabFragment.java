package com.example.stylish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class SignupTabFragment extends Fragment {

    private EditText usernameEditText, passwordEditText,Confirm;
    private Button saveButton;
    private MyDatabaseHelper databaseHelper;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_signup_tab, container, false);
        usernameEditText = view.findViewById(R.id.signup_username);
        passwordEditText = view.findViewById(R.id.signup_password);
        Confirm= view.findViewById(R.id.signup_confirm);

        saveButton = view.findViewById(R.id.signup_button);

        databaseHelper = new MyDatabaseHelper(getActivity());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Check if username or password fields are empty
                if (username.isEmpty() || password.isEmpty() ) {
                    Toast.makeText(getActivity(), "Please enter both username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(Confirm))
                {
                    Toast.makeText(getActivity(), "confirm and password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Add user to the database
                databaseHelper.addUser(username, password);
                Toast.makeText(getActivity(), "User added to database", Toast.LENGTH_SHORT).show();

                // Clear EditText fields
                usernameEditText.setText("");
                passwordEditText.setText("");
            }
        });



        return view;

    }
}
