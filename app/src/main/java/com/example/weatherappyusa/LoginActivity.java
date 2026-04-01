package com.example.weatherappyusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin, btnToRegister;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtLoginUsername);
        edtPassword = findViewById(R.id.edtLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnToRegister = findViewById(R.id.btnToRegister);
        dbHelper = new DatabaseHelper(LoginActivity.this);

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            if (dbHelper.checkUser(username, password)) {
                Toast.makeText(this, "Giriş başarılı!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Hatalı kullanıcı adı veya şifre", Toast.LENGTH_SHORT).show();
            }
        });

        btnToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}
