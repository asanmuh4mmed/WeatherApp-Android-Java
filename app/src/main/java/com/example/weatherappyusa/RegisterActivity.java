package com.example.weatherappyusa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText edtFirstName, edtLastName, edtEmail, edtUsername, edtPassword;
    RadioGroup radioGender;
    CheckBox chkAgreement;
    Button btnRegister;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // senin XML’inle aynı

        dbHelper = new DatabaseHelper(this);

        edtFirstName = findViewById(R.id.editTextFirstName);
        edtLastName = findViewById(R.id.editTextLastName);
        edtEmail = findViewById(R.id.editTextEmail);
        edtUsername = findViewById(R.id.editTextUsername);
        edtPassword = findViewById(R.id.editTextPassword);
        radioGender = findViewById(R.id.radioGroupGender);
        chkAgreement = findViewById(R.id.checkBoxAgreement);
        btnRegister = findViewById(R.id.buttonRegister);

        btnRegister.setOnClickListener(v -> {
            String fname = edtFirstName.getText().toString();
            String lname = edtLastName.getText().toString();
            String email = edtEmail.getText().toString();
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String gender = ((RadioButton) findViewById(radioGender.getCheckedRadioButtonId())).getText().toString();

            if (chkAgreement.isChecked() && !username.isEmpty() && !password.isEmpty()) {
                User user = new User(fname, lname, email, username, password, gender);
                boolean inserted = dbHelper.insertUser(user);
                if (inserted) {
                    Toast.makeText(this, "Kayıt başarılı!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(this, "Kayıt başarısız veya kullanıcı adı zaten var!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Lütfen tüm alanları doldurun ve sözleşmeyi kabul edin", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
