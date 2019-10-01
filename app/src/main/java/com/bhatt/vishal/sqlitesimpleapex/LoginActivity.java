package com.bhatt.vishal.sqlitesimpleapex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText etusername,etpassword;
    Button btlogin;
    TextView tvregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etusername=(EditText)findViewById(R.id.etusername);
        etpassword=(EditText)findViewById(R.id.etpassword);
        btlogin=(Button)findViewById(R.id.btlogin);
        tvregister=(TextView)findViewById(R.id.tvregister);

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                // login attempt
                DB db = new DB(LoginActivity.this);
                boolean result  = db.loginUser(username,password);

                if(result)
                {
                    Toast.makeText(LoginActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                    i.putExtra("username",username);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Login Fail",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
