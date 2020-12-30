package com.example.myapplication.ui.login;

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
import com.example.myapplication.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    EditText userName;
    EditText userPassword;
    Button btnLogin;
    TextView openRegister;
    UserService userService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.loginName);
        userPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.loginButton);
        openRegister = findViewById(R.id.loginBackRegister);

        userService = new UserService(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString();
                String passw = userPassword.getText().toString();

                boolean isLoginSuccess = userService.checkUser(username,passw);
                if(isLoginSuccess){
                    Toast.makeText(getApplicationContext(), "Giriş Başarılı ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Kullanıcı Adı veya Şifre Hatalı ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        openRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
