package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

public class ProductsActivity extends Activity {
    // Array of strings...
    String[] PArray = {"Pay","Send Money","Save","Invest",
            "Borrow a Loan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.products_listview, PArray);

        ListView listView = (ListView) findViewById(R.id.products_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductsActivity.this,PayActivity.class);
                intent.putExtra("Product Name", listView.getItemAtPosition(position).toString());
                startActivity(intent);

            }
        });

    }
}
