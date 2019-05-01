package com.example.mak_ratanak_assignment_sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mak_ratanak_assignment_sqlite.model.UserModel;
import com.example.mak_ratanak_assignment_sqlite.sqlite.DBHelper;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPassword, etConfirmPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_pwd);
        etConfirmPassword = findViewById(R.id.et_confirm_pwd);
        btnRegister = findViewById(R.id.btn_register);

        final DBHelper helper = new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if(email.equals("")){
                    Toast toast=Toast.makeText(getApplicationContext(),"Email field is empty!", Toast.LENGTH_LONG);
                    toast.show();
                }else if(password.equals("")){
                    Toast toast=Toast.makeText(getApplicationContext(),"Password field is empty!", Toast.LENGTH_LONG);
                    toast.show();
                }else if(confirmPassword.equals("")){
                    Toast toast=Toast.makeText(getApplicationContext(),"Confirm Password field is empty!", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    if(password.equals(confirmPassword)){
                        // check if user is already exists
                        ArrayList<UserModel> userList = helper.getAllUser();
                        boolean alreadyExists = false;
                        for(int i = 0; i < userList.size(); i++){
                            UserModel model = userList.get(i);
                            if(model.getEmail() == email && model.getPassword() == password){
                                alreadyExists = true;
                                Toast toast = Toast.makeText(getApplicationContext(), "This user is already exists!", Toast.LENGTH_LONG);
                                toast.show();
                                break;
                            }
                        }
                        if(alreadyExists == false){
                            helper.insertUser(email, password);
                            Toast toast = Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG);
                            toast.show();
                            Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(i);
                        }
                    }
                    else {
                        Toast toast=Toast.makeText(getApplicationContext(),"Password and Confirm Password is not match!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
    }
}
