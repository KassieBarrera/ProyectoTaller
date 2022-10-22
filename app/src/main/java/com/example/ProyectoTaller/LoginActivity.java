package com.example.ProyectoTaller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button imgButton = findViewById(R.id.img_login);
        final TextView edtUser = findViewById(R.id.txt_user);
        final String user = edtUser.getText().toString();
        final TextView edtPass = findViewById(R.id.txt_password);
        final String pass = edtPass.getText().toString();
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( edtUser.getEditableText().toString().isEmpty() && edtPass.getEditableText().toString().isEmpty() ) {
                    edtUser.setError("Requerido");
                    edtPass.setError("Requerdio");
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}