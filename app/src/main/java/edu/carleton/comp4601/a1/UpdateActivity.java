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
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

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

    private int statusCode;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init items from editor layout
        ipAddressContentText = (EditText) findViewById(R.id.ipAddressContentTextUpdate);
        nameContentView = (EditText) findViewById(R.id.nameContentViewUpdate);
        idContentView = (EditText) findViewById(R.id.idContentViewUpdate);
        textContentView = (EditText) findViewById(R.id.textContentViewUpdate);
        tagsContentView = (EditText) findViewById(R.id.tagsContentViewUpdate);
        linksContentView = (EditText) findViewById(R.id.linksContentViewUpdate);

        queue = Volley.newRequestQueue(this);
        statusCode = 0;

        //init other useful items
        ipAddress = getIntent().getExtras().getString(getString(R.string.ip_extra_name));
        ipAddressContentText.setText(ipAddress);
        name = "";
        id = "";
        text = "";
        tags = "";
        links = "";
    }

    public void update(View view) {
        ipAddress = ipAddressContentText.getText().toString();
        id = idContentView.getText().toString();
        name = nameContentView.getText().toString();
        text = textContentView.getText().toString();
        tags = tagsContentView.getText().toString();
        links = linksContentView.getText().toString();

        String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + id;
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(String.valueOf(statusCode).equals(getString(R.string.status_code_200))) {
                    Toast.makeText(getApplicationContext(),getString(R.string.toast_update_success),Toast.LENGTH_LONG).show();
                } else if(String.valueOf(statusCode).equals(getString(R.string.status_code_204))) {
                    Toast.makeText(getApplicationContext(),getString(R.string.toast_update_failure),Toast.LENGTH_LONG).show();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {
                if(response!= null) {
                    statusCode = response.statusCode;
                    return super.parseNetworkResponse(response);
                } else
                    return null;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("id", id);
                params.put("text", text);
                params.put("tags", tags);
                params.put("links", links);

                return params;
            }
        };

        queue.add(request);
    }
}