package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.editTextUsuario);
        etPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegistrarse = findViewById(R.id.buttonRegistrarse);


        // Mientras no haya usuarios, haciendo click en el botón login, nos lleva al menú
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Menu.class);
                startActivity(i);
                // TODO: Código para realizar la autenticación
            }
        });

        // Botón que conduce a la Activity Registro
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Registro.class);
                startActivity(i);
            }
        });
    }
}