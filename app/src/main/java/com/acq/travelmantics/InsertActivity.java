package com.acq.travelmantics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabae;
    private DatabaseReference mDatabaseRefence;
    EditText mTxtTitle;
    EditText mTxtPrice;
    EditText mTxztDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mFirebaseDatabae = FirebaseDatabase.getInstance();
        mDatabaseRefence = mFirebaseDatabae.getReference().child("traveldeals");

        mTxtTitle = (EditText) findViewById(R.id.txtTitle);
        mTxtPrice = (EditText) findViewById(R.id.txtPrice);
        mTxztDescription = (EditText) findViewById(R.id.txtDescription);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveMenu();
                  Toast.makeText(this, "Deal Saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
               return super.onOptionsItemSelected(item);
        }
    }

    private void clean() {
        String title = mTxtTitle.getText().toString();
        String price = mTxtPrice.getText().toString();
        String description = mTxztDescription.getText().toString();
        TravelDeal travelDeal = new TravelDeal(title, price, description, "");
        mDatabaseRefence.push().setValue(travelDeal);
    }

    private void saveMenu() {
        mTxtTitle.setText("");
        mTxtPrice.setText("");
        mTxztDescription.setText("");
        mTxtTitle.requestFocus();
    }
}