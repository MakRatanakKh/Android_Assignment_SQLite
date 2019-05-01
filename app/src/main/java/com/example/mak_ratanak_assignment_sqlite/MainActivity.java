package com.example.mak_ratanak_assignment_sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mak_ratanak_assignment_sqlite.model.UserModel;
import com.example.mak_ratanak_assignment_sqlite.sqlite.DBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String DB_NAME="user_db";
    public static final String TABLE_USER="users";

    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        final DBHelper helper = new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // check if user is already exists
                ArrayList<UserModel> userList = helper.getAllUser();
                boolean alreadyExists = false;
                for(int i = 0; i < userList.size(); i++){
                    UserModel model = userList.get(i);
                    if(model.getEmail().equals(email) && model.getPassword().equals(password)){
                        alreadyExists = true;
                        Toast toast = Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG);
                        toast.show();
                        break;
                    }
                }
                if(alreadyExists == false){
                    Toast toast = Toast.makeText(getApplicationContext(), "Login fail!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}
