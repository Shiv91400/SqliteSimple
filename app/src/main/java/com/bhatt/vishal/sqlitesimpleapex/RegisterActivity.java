package com.bhatt.vishal.sqlitesimpleapex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText etusername,etpassword,etphone;
    Button btregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etusername=(EditText)findViewById(R.id.etusername);
        etpassword=(EditText)findViewById(R.id.etpassword);
        etphone=(EditText)findViewById(R.id.etphone);
        btregister=(Button)findViewById(R.id.btregister);
        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();
                String phone = etphone.getText().toString();
                // registration
                DB db = new DB(RegisterActivity.this);
                long result =  db.registerUser(username,password,phone);
                /*if(result>0)
                {
                    Toast.makeText(RegisterActivity.this,"Register Successfull....",Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }
}
