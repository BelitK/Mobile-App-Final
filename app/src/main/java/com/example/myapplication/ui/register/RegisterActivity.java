package com.example.myapplication.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.service.UserService;
import com.example.myapplication.ui.home.MainActivity;
import com.example.myapplication.ui.image.SaveActivity;
import com.example.myapplication.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText userName;
    EditText userPassword;
    Button btnSave;
    TextView openLogin;
    UserService userService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.registerName);
        userPassword = findViewById(R.id.registerPassword);
        btnSave = findViewById(R.id.registerButton);
        openLogin = findViewById(R.id.registerBackLogin);

        userService = new UserService(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String passw = userPassword.getText().toString();
                if (username!=null && passw!=null){
                    userService.saveUser(username,passw);
                    Toast.makeText(getApplicationContext(), "Kayıt Başarılı "+username, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Kullanıcı adı ve şifre boş olamaz ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        openLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
