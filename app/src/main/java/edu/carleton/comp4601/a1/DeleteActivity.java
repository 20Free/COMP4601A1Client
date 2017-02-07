package edu.carleton.comp4601.a1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DeleteActivity extends AppCompatActivity {

    //Declare items from doc view layout
    private EditText ipAddressContentTextDelete;
    private EditText idContentViewDelete;
    private TextView tagsContentViewDelete;

    //Declare other useful items
    private String ipAddress;
    private String id;
    private String tags;

    //Declare Volley variables
    private RequestQueue queue;
    private int statusCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init items from doc view layout
        ipAddressContentTextDelete = (EditText) findViewById(R.id.ipAddressContentTextDelete);
        idContentViewDelete = (EditText) findViewById(R.id.idContentViewDelete);
        tagsContentViewDelete = (TextView) findViewById(R.id.tagsContentViewDelete);

        //init other useful items
        ipAddress = getIntent().getExtras().getString(getString(R.string.ip_extra_name));
        ipAddressContentTextDelete.setText(ipAddress);
        id = "";
        tags = "";

        //init Volley variables
        queue = Volley.newRequestQueue(this);
        statusCode = 0;
    }

    public void delete(View view) {
        if(!tagsContentViewDelete.getText().toString().equals("")) {
            ipAddress = ipAddressContentTextDelete.getText().toString();
            tags = tagsContentViewDelete.getText().toString();

            String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + getString(R.string.tagDelete) + tags;
            StringRequest deleteRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(String.valueOf(statusCode).equals(getString(R.string.status_code_200))) {
                        Toast.makeText(getApplicationContext(), getString(R.string.toast_delete_success), Toast.LENGTH_LONG).show();
                    } else if(String.valueOf(statusCode).equals(getString(R.string.status_code_204))) {
                        Toast.makeText(getApplicationContext(), getString(R.string.toast_delete_failure), Toast.LENGTH_LONG).show();
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
            };
            queue.add(deleteRequest);
        }
        if(!idContentViewDelete.getText().toString().equals("")) {
            ipAddress = ipAddressContentTextDelete.getText().toString();
            id = idContentViewDelete.getText().toString();
            String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + id;
            StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(String.valueOf(statusCode).equals(getString(R.string.status_code_200))) {
                        Toast.makeText(getApplicationContext(), getString(R.string.toast_delete_success), Toast.LENGTH_LONG).show();
                    } else if(String.valueOf(statusCode).equals(getString(R.string.status_code_204))) {
                        Toast.makeText(getApplicationContext(), getString(R.string.toast_delete_failure), Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            }) {
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    if (response != null) {
                        statusCode = response.statusCode;
                        return super.parseNetworkResponse(response);
                    } else
                        return null;
                }
            };
            queue.add(deleteRequest);
        }
    }
}