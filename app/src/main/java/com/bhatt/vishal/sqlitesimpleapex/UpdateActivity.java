package com.bhatt.vishal.sqlitesimpleapex;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    TextView tvusername;
    EditText etpassword,etphone;
    Button btupdate;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        username = getIntent().getStringExtra("username");
        tvusername=(TextView)findViewById(R.id.tvusername);
        etpassword=(EditText)findViewById(R.id.etpassword);
        etphone=(EditText)findViewById(R.id.etphone);
        btupdate=(Button)findViewById(R.id.btupdate);
        DB db = new DB(this);
        Cursor cursor = db.getUserDetail(username);
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("password"));
        String phone = cursor.getString(cursor.getColumnIndex("phone"));

        tvusername.setText(username);
        etpassword.setText(password);
        etphone.setText(phone);

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = etpassword.getText().toString();
                String phone = etphone.getText().toString();
                DB db = new DB(UpdateActivity.this);
                long result =  db.updateUser(username,password,phone);
                if(result>0)
                {
                    Toast.makeText(UpdateActivity.this,"Update Successfull",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
