package com.quickshoppe.charlie.quickshoppe;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

public class SignUpActivity extends ActionBarActivity {
    Button create_user_button;
    Button back_to_home_button;
    EditText first_name;
    EditText last_name;
    EditText user_name;
    EditText password;
    EditText confirm_password;
    EditText advantage_number;
    EditText email;
    EditText credit_card;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        UserDbHelper mDbHelper = new UserDbHelper(getApplicationContext());
        final SQLiteDatabase sqlDb_write = mDbHelper.getWritableDatabase();
        final SQLiteDatabase sqlDb_read = mDbHelper.getReadableDatabase();

        back_to_home_button = (Button) findViewById(R.id.return_to_home);
        back_to_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Returning!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        create_user_button = (Button) findViewById(R.id.create_account);
        password = (EditText) findViewById(R.id.registration_password);
        confirm_password = (EditText) findViewById(R.id.registration_confirm_password);
        first_name = (EditText) findViewById(R.id.registration_first_name);
        last_name = (EditText) findViewById(R.id.registration_last_name);
        advantage_number = (EditText) findViewById(R.id.registration_advantage_number);
        email = (EditText) findViewById(R.id.registration_email);
        credit_card = (EditText) findViewById(R.id.registration_credit_card);
        user_name = (EditText) findViewById(R.id.registration_user_name);
        create_user_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields(sqlDb_read)) {
                    ContentValues userValues = new ContentValues();
                    userValues.put(QuickShoppeUserContract.UserEntry.COLUMN_ACCOUNT_NUMBER, advantage_number.getText().toString());
                    userValues.put(QuickShoppeUserContract.UserEntry.COLUMN_CREDIT_CARD, credit_card.getText().toString());
                    userValues.put(QuickShoppeUserContract.UserEntry.COLUMN_EMAIL, email.getText().toString());
                    userValues.put(QuickShoppeUserContract.UserEntry.COLUMN_FIRST_NAME, first_name.getText().toString());
                    userValues.put(QuickShoppeUserContract.UserEntry.COLUMN_LAST_NAME, last_name.getText().toString());
                    userValues.put(QuickShoppeUserContract.UserEntry.COLUMN_NAME_USER_NAME, user_name.getText().toString());
                    userValues.put(QuickShoppeUserContract.UserEntry.COLUMN_PASSWORD, password.getText().toString());
                    long newRowId;
                    newRowId = sqlDb_write.insert(
                            QuickShoppeUserContract.UserEntry.TABLE_NAME,
                            QuickShoppeUserContract.UserEntry.COLUMN_NAME_NULLABLE,
                            userValues);
                    sqlDb_write.close();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    private boolean validateFields(SQLiteDatabase sqlDb_read) {
        boolean ret = true;

        if(!password.getText().toString().equals(confirm_password.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Passwords don't match! Try again!", Toast.LENGTH_SHORT).show();
            ret = false;
        } else if(first_name.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "First Name Empty!", Toast.LENGTH_SHORT).show();
            ret = false;
        } else if(last_name.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Last Name Empty!", Toast.LENGTH_SHORT).show();
            ret = false;
        } else if(user_name.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Username Empty!", Toast.LENGTH_SHORT).show();
            ret = false;
        } else if(advantage_number.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Advantage Number Empty!", Toast.LENGTH_SHORT).show();
            ret = false;
        } else if(email.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Email Empty!", Toast.LENGTH_SHORT).show();
            ret = false;
        } else if(credit_card.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Credit Card Empty", Toast.LENGTH_SHORT).show();
            ret = false;
        } else if(password.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Password Empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if(confirm_password.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Confirm Password Empty", Toast.LENGTH_SHORT).show();
            ret = false;
        } else {
            String selectQuery = "SELECT " + QuickShoppeUserContract.UserEntry.COLUMN_NAME_USER_NAME + " FROM " + "user " + "WHERE " +
                    QuickShoppeUserContract.UserEntry.COLUMN_NAME_USER_NAME + "=" + "'" + user_name.getText().toString() + "'";
            Cursor c = sqlDb_read.rawQuery(selectQuery, null);
            if(c.getCount() != 0) {
                Toast.makeText(getApplicationContext(), "Username already taken!", Toast.LENGTH_SHORT).show();
                ret = false;
            }

        }
        sqlDb_read.close();
        return true;
    }
}
