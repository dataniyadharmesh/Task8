package com.dharmesh.task8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReadAndShare extends AppCompatActivity {
    private String id;
    private TextView data;
    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_read_and_share );
        id = getIntent().getStringExtra("Quote");
        data = (TextView)findViewById(R.id.quote);
        data.setText(id);

        share = (Button)findViewById(R.id.btn_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/html");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p><b>Share this quote</b></p>"));
                startActivity( Intent.createChooser(shareIntent, "Share Via"));
            }
        });

    }
}
