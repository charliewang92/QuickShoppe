package com.quickshoppe.charlie.quickshoppe;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    Button login_button;
    Button sign_up_button;
    EditText name_text;
    EditText password_text;
    TextView login_counter_holder;
    int counter = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Intent sign_up_activity = new Intent(this, SignUpActivity.class);
        final Intent home_activity_intent = new Intent(this, HomeActivity.class);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button = (Button) findViewById(R.id.login_button);
        sign_up_button = (Button) findViewById(R.id.sign_up_button);
        name_text = (EditText) findViewById(R.id.edit_name_text);
        password_text = (EditText) findViewById(R.id.edit_password_text);
        login_counter_holder = (TextView) findViewById(R.id.login_counter_text);
        login_counter_holder.setVisibility(View.GONE);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_text.getText().toString().equalsIgnoreCase("charlie") &&
                        password_text.getText().toString().equalsIgnoreCase("charlie")) {
                    Toast.makeText(getApplicationContext(), "Logging In!", Toast.LENGTH_SHORT).show();
                    startActivity(home_activity_intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    login_counter_holder.setVisibility(View.VISIBLE);
                    login_counter_holder.setBackgroundColor(Color.RED);
                    counter--;
                    login_counter_holder.setText(Integer.toString(counter));

                    if (counter == 0) {
                        login_button.setEnabled(false);
                    }
                }
            }
        });

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Signing Up!", Toast.LENGTH_SHORT).show();
                startActivity(sign_up_activity);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
