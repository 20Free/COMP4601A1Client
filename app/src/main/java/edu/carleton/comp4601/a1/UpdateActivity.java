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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

    //Declare Volley variables
    private RequestQueue queue;
    private int statusCode;

    private Document doc;
    private byte[] objArray;


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

        //init other useful items
        ipAddress = getIntent().getExtras().getString(getString(R.string.ip_extra_name));
        ipAddressContentText.setText(ipAddress);
        name = "";
        id = "";
        text = "";
        tags = "";
        links = "";

        //init Volley variables
        queue = Volley.newRequestQueue(this);
        statusCode = 0;
    }

    public void update(View view) {
        ipAddress = ipAddressContentText.getText().toString();
        id = idContentView.getText().toString();
        name = nameContentView.getText().toString();
        text = textContentView.getText().toString();
        tags = tagsContentView.getText().toString();
        links = linksContentView.getText().toString();

        doc = new Document();
        doc.setName(name);
        doc.setId(Integer.parseInt(id));
        doc.setText(text);
        ArrayList<String> tagList = new ArrayList<>();
        tagList.addAll(Arrays.asList(tags.split(":")));
        doc.setTags(tagList);
        ArrayList<String> linkList = new ArrayList<>();
        linkList.addAll(Arrays.asList(links.split(" ")));
        doc.setLinks(linkList);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        objArray = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(doc);
            out.flush();
            objArray = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + id;
        StringRequest updateRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (String.valueOf(statusCode).equals(getString(R.string.status_code_200))) {
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_update_success), Toast.LENGTH_LONG).show();
                } else if (String.valueOf(statusCode).equals(getString(R.string.status_code_204))) {
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_update_failure), Toast.LENGTH_LONG).show();
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

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/xml");
                return super.getHeaders();
            }

            @Override
            public Object getTag() {
                return doc;
            }
        };
        queue.add(updateRequest);
    }
}