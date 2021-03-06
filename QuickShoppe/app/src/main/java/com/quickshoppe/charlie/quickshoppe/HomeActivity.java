package com.quickshoppe.charlie.quickshoppe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.*;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

//import com.google.ads.*;

/**
 * need to 
 */
public class HomeActivity extends ActionBarActivity {
    String[] grocery_items = {"Coffee: $13.91", "Chips: $10.00", "StrawBerry: $15.55", "Fruit Drinks: $12.95"};
    Button scannerButton;
    Button batteryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, grocery_items);
        ListView listView = (ListView) findViewById(R.id.shopping_list);
        listView.setAdapter(adapter);
        scannerButton = (Button) findViewById(R.id.scanner_button);
        batteryButton = (Button) findViewById(R.id.BatteryMonitor);
        final Intent scanItemIntent = new Intent(this, ScannerActivity.class);
        final Intent itemInfoIntent = new Intent(this, ItemActivity.class);
        final Intent batteryInfoIntent = new Intent(this, BatteryActivity.class);

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView) view).getText().toString();
                String[] itemComponents = item.split(" ");
                Toast.makeText(getBaseContext(), "Opening item information for: " + itemComponents[0], Toast.LENGTH_LONG).show();
                itemInfoIntent.putExtra("itemInfo", itemComponents[0]);
                startActivity((itemInfoIntent));
            }
        });

        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Scanning Item!", Toast.LENGTH_SHORT).show();
                startActivity(scanItemIntent);
            }
        });

        batteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Showing battery info...", Toast.LENGTH_LONG).show();
                startActivity(batteryInfoIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
