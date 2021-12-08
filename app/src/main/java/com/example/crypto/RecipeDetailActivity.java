package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class RecipeDetailActivity extends AppCompatActivity {

    static int coinIndex = 0;

    ImageView mainImageView;
    TextView title, description;

    String data1, data2;
    int myImage;

    private TextView mCrytoPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        mCrytoPrice = (TextView) findViewById(R.id.currentPrice);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        getData();
        setData();
    }
    private void getData() {
        if (getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") &&
        getIntent().hasExtra("data2")) {
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            myImage = getIntent().getIntExtra("myImage", 1);
        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        title.setText(data1);
        description.setText(data2);
        mainImageView.setImageResource(myImage);
    }

    private void setCoinID() {
        if (data1.equals("Bitcoin")) {
            coinIndex = 0;
        }
        else if (data1.equals("Ethereum")) {
            coinIndex = 1;
        }
        else if (data1.equals("Dogecoin")) {
            coinIndex = 10;
        }
    }


    public void searchCrypto(View view) {
        setCoinID();
        String queryString = "bitcoin";
        FetchPrice fb = new FetchPrice(mCrytoPrice);
        fb.execute(queryString);
    }
}