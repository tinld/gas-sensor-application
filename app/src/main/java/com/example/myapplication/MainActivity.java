package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    Button btn_door, btn_sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ToggleButton doorToggle = findViewById(R.id.door);
        ToggleButton sensorToggle = findViewById(R.id.sensor);


        doorToggle.setChecked(false);
        sensorToggle.setChecked(true);
        CompoundButton.OnCheckedChangeListener listener =
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        switch (compoundButton.getId()) {
                            case R.id.door:
                                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                                String url = "";
                                if(compoundButton.isChecked()){
                                    url = "https://sgp1.blynk.cloud/external/api/update?token=NgW5gvYOuqRhnBEEINSzmPHu6zy7rIWv&pin=V5&value=1";
                                }else{
                                    url = "https://sgp1.blynk.cloud/external/api/update?token=NgW5gvYOuqRhnBEEINSzmPHu6zy7rIWv&pin=V5&value=0";
                                }
                                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
                                    @Override
                                    public void onResponse(JSONArray response){

                                    }
                                }, new Response.ErrorListener(){
                                    @Override
                                    public void onErrorResponse(VolleyError error){

                                    }
                                });
                                queue.add(request);
                                Toast.makeText(MainActivity.this, "Cửa:" +
                                                compoundButton.isChecked(),
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.sensor:
                                Toast.makeText(MainActivity.this, "Cảm biến:" +
                                                compoundButton.isChecked(),
                                        Toast.LENGTH_SHORT).show();
                                RequestQueue queue2 = Volley.newRequestQueue(MainActivity.this);
                                String urlSensor = "";
                                if(compoundButton.isChecked()){
                                    urlSensor = "https://sgp1.blynk.cloud/external/api/update?token=NgW5gvYOuqRhnBEEINSzmPHu6zy7rIWv&pin=V3&value=1";
                                }else{
                                    urlSensor = "https://sgp1.blynk.cloud/external/api/update?token=NgW5gvYOuqRhnBEEINSzmPHu6zy7rIWv&pin=V3&value=0";
                                }
                                JsonArrayRequest request1 = new JsonArrayRequest(Request.Method.GET, urlSensor, null, new Response.Listener<JSONArray>(){
                                    @Override
                                    public void onResponse(JSONArray response){

                                    }
                                }, new Response.ErrorListener(){
                                    @Override
                                    public void onErrorResponse(VolleyError error){

                                    }
                                });
                                queue2.add(request1);
                                break;
                        }
                    }
                };

        doorToggle.setOnCheckedChangeListener(listener);
        sensorToggle.setOnCheckedChangeListener(listener);
    }
}