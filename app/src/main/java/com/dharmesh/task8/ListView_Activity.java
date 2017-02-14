package com.dharmesh.task8;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;
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

public class ListView_Activity extends AppCompatActivity {


    private ListView ls;
    ListView listView;
    List_Post q;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_view );

        int gotid = getIntent().getIntExtra("pos", 1);

        new List_Quotes().execute("http://rapidans.esy.es/test/getquotes.php?cat_id=" + gotid);



    }
    class List_Quotes extends AsyncTask<String,Void,String>{
        private ProgressDialog dialog;
        ArrayList<List_Post> quotesArrayList = new ArrayList<>();
        List_Adapter listview_adapter;

       private Context context;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        /*    dialog = new ProgressDialog(Main2Activity.this);
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

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    String bufferString = buffer.toString();
                    return bufferString;


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
           /* if (dialog.isShowing()) {
                dialog.dismiss();
            }*/
            quotesArrayList = new ArrayList<List_Post>();

            try {

                JSONObject jsonObject1 = new JSONObject(s);

                JSONArray jsonArray = jsonObject1.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    q = new List_Post();
                    q.setId(jsonObject.getInt("id"));
                    q.setCat_id(jsonObject.getInt("cat_id"));
                    q.setQuotes(jsonObject.getString("quotes"));

                    quotesArrayList.add(q);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        //

            listView = (ListView) findViewById(R.id.lst_view);
            listview_adapter = new List_Adapter (ListView_Activity.this,R.layout.listed_items,quotesArrayList);

            listView.setAdapter(listview_adapter);

            String passedVar=null;


          /*  gridView= (GridView)findViewById(R.id.view_gridview);
            adapter = new Cat_Adapter(MainActivity.this,R.layout.layout_category,Cat_List);
            gridView.setAdapter(adapter);*/


        }
    }
    }

