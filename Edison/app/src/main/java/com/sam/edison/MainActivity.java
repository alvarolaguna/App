package com.sam.edison;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;




public class MainActivity extends AppCompatActivity {

    EditText user, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText)findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
    }

    public void loginClick(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("user", user.getText().toString());
        intent.putExtra("pass", password.getText().toString());
        startActivity(intent);
    }
}
