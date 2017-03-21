package com.example.ricardo.pruebavolley2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class VehiculosActivity extends AppCompatActivity {
    ArrayList<String> lista;
    ListView listView;
    FloatingActionButton btnPersonas;
    FloatingActionButton btnVehiculos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);
            btnPersonas=(FloatingActionButton)findViewById(R.id.btnPersonas);
            btnVehiculos=(FloatingActionButton) findViewById(R.id.btnVehiculos);

            btnPersonas.setOnClickListener((View.OnClickListener) this);
            btnVehiculos.setOnClickListener((View.OnClickListener) this);
    }



    public void getLogsAutos(){
        String url="https://api.mlab.com/api/1/databases/acseg/collections/vehiculos?apiKey=LVKt_ocSz3R1uiTDrA0i77vuS-I_6Uud";
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response){
                try {
                    JSONArray jsonArray=new JSONArray(response);

                    for(int i=0;i<jsonArray.length();i++){
                        lista.add("enCampus:"+jsonArray.getJSONObject(i).getString("enCampus")+"              mtrAuto"+jsonArray.getJSONObject(i).getString("mtrAuto"));
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
                Toast.makeText(getApplicationContext(),"Resultados no encontrados", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
        Toast.makeText(getApplicationContext(), "Resultados obtenidos", Toast.LENGTH_SHORT).show();
    }







}
