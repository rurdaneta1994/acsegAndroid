package com.example.ricardo.pruebavolley2;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.icu.util.ValueIterator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.color.white;
import static com.example.ricardo.pruebavolley2.R.layout.activity_main;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   ListView listView;
    ArrayList<String> lista=new ArrayList<String>();
       FloatingActionButton btnPersonas;
       FloatingActionButton btnVehiculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        btnPersonas=(FloatingActionButton) findViewById(R.id.btnPersonas);
        btnVehiculos=(FloatingActionButton) findViewById(R.id.btnVehiculos);
        btnPersonas.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Registro de Personas",Toast.LENGTH_SHORT).show();
                Intent vistaSiguiente=new Intent(MainActivity.this,PersonasActivity.class);
                startActivity(vistaSiguiente);
            }
        });

        btnVehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registro de Vehiculos",Toast.LENGTH_SHORT).show();
                Intent vistaSiguiente=new Intent(MainActivity.this,VehiculosActivity.class);
                startActivity(vistaSiguiente);


            }
        });



    }












        //BUSQUEDA GENERICA......
    public void getLogs(){
        String url="https://api.mlab.com/api/1/databases/acseg/collections/logsP?apiKey=LVKt_ocSz3R1uiTDrA0i77vuS-I_6Uud";
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response){
                try {
                    JSONArray jsonArray=new JSONArray(response);

                   for(int i=0;i<jsonArray.length();i++){
                        lista.add(jsonArray.getJSONObject(i).getString("hora")+"    "+jsonArray.getJSONObject(i).getString("month")+"      "+jsonArray.getJSONObject(i).getString("day")+"      "+jsonArray.getJSONObject(i).getString("year")+"      "+jsonArray.getJSONObject(i).getString("CI")+"      "+jsonArray.getJSONObject(i).getString("op"));
                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,lista);
                    listView.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);


    }

    @Override
    public void onClick(View v) {

    }
}
