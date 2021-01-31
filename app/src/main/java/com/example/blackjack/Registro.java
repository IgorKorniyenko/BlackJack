package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registro extends AppCompatActivity {

    private EditText etNombre;
    private EditText etApellidos;
    private EditText etEmail;
    private EditText etUsuario;
    private EditText etPassword;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.editTextNombre);
        etApellidos = findViewById(R.id.editTextApellidos);
        etEmail = findViewById(R.id.editTextEmail);
        etUsuario = findViewById(R.id.editTextNombreUsuario);
        etPassword = findViewById(R.id.editTextPassword);
        btnEnviar = findViewById(R.id.buttonEnviar);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Código para registrar un nuevo usuario
            }
        });
    }
}