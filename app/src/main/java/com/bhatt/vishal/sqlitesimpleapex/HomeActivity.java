package com.bhatt.vishal.sqlitesimpleapex;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    String username;
    TextView tvwelcome;
    Button btdelete,btupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = getIntent().getStringExtra("username");
        tvwelcome=(TextView)findViewById(R.id.tvwelcome);
        tvwelcome.setText("Welcome "+username);
        btdelete=(Button)findViewById(R.id.btDelete);
        btupdate=(Button)findViewById(R.id.btupdate);
        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Alert");
                builder.setMessage("Do you want to delete your account ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DB db = new DB(HomeActivity.this);
                        long result = db.deleteUser(username);

                        if(result>0)
                        {
                            Toast.makeText(HomeActivity.this,"Account deleted",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(HomeActivity.this,LoginActivity.class);
                            startActivity(i);
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,UpdateActivity.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });


    }
}
