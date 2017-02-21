package edu.carleton.comp4601.a1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.*;

import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {

    //Declare items from editor layout
    private EditText ipAddressContentText;
    private EditText nameContentView;
    private EditText idContentView;
    private EditText textContentView;
    private EditText tagsContentView;
    private EditText linksContentView;

    //Declare other useful items
    private String ipAddress;
    private String name;
    private String id;
    private String text;
    private String tags;
    private String links;

    //Declare Volley variables
    private RequestQueue queue;
    private int statusCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init items from editor layout
        ipAddressContentText = (EditText) findViewById(R.id.ipAddressContentTextCreate);
        nameContentView = (EditText) findViewById(R.id.nameContentViewCreate);
        idContentView = (EditText) findViewById(R.id.idContentViewCreate);
        textContentView = (EditText) findViewById(R.id.textContentViewCreate);
        tagsContentView = (EditText) findViewById(R.id.tagsContentViewCreate);
        linksContentView = (EditText) findViewById(R.id.linksContentViewCreate);

        //Assign IP Address
        ipAddress = getIntent().getExtras().getString(getString(R.string.ip_extra_name));
        ipAddressContentText.setText(ipAddress);

        //init Volley variables
        queue = Volley.newRequestQueue(this);
        statusCode = 0;
    }

    public void create(View view) {
        ipAddress = ipAddressContentText.getText().toString();
        name = nameContentView.getText().toString();
        id = idContentView.getText().toString();
        text = textContentView.getText().toString();
        tags = tagsContentView.getText().toString();
        links = linksContentView.getText().toString();
        String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2);



        StringRequest createRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(String.valueOf(statusCode).equals(getString(R.string.status_code_200))) {
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_creation_success), Toast.LENGTH_LONG).show();
                } else if (String.valueOf(statusCode).equals(getString(R.string.status_code_204))){
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_creation_failure), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if (response != null) {
                    statusCode = response.statusCode;
                    return super.parseNetworkResponse(response);
                } else
                    return null;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put(getString(R.string.param_name), name);
                params.put(getString(R.string.param_id), id);
                params.put(getString(R.string.param_text), text);
                params.put(getString(R.string.param_tags), tags);
                params.put(getString(R.string.param_links), links);
                return params;
            }
        };
        queue.add(createRequest);
    }
}