package com.dharmesh.task8;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        new MyFirstClass().execute("http://rapidans.esy.es/test/getallcat.php");
    }

    class MyFirstClass extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;
        private GridView gridView;
        private Cat_Adapter adapter;

        private ListView ls;
        private Cat_Adapter ad;
        private List<Cat_Post> list_items = new ArrayList<Cat_Post>();


        private static final String TAG = "null" ;





        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();*/
        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection;
            try {
                URL url = new URL(params[0]);

                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer bf = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        bf.append(line);

                    }

                    String jsonData = bf.toString();
                    return jsonData;


                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (MalformedURLException e) {

                e.printStackTrace();
            }
            return null;

        }

        @Override

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayList<Cat_Post> Cat_List = new ArrayList<Cat_Post>();
            try {
                JSONObject rootObj = new JSONObject(s);

                int success = rootObj.getInt("success");
                String msg = rootObj.getString("message");

                JSONArray data_main =rootObj.getJSONArray("data");
                for (int i = 0; i <data_main.length() ; i++) {
                    JSONObject data = data_main.getJSONObject(i);


                    String Id = data.getString("id");
                    Log.d(TAG , "ID : "+id);
                    String Name = data.getString("name");
                    Log.d(TAG ,"Categories : "+Name);

                    Cat_Post p = new Cat_Post();
                    p.setId(Id);
                    p.setName(Name);
                    Cat_List.add(p);



                }

                String data = rootObj.getString("data");



            }
            catch (JSONException e) {
                e.printStackTrace();
            }


            gridView= (GridView)findViewById(R.id.view_gridview);
            adapter = new Cat_Adapter(MainActivity.this,R.layout.layout_category,Cat_List);
            gridView.setAdapter(adapter);

        }
    }
}


