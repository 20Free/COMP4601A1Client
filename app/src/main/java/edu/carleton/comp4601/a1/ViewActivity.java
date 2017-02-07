package edu.carleton.comp4601.a1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    //Declare items from doc view layout
    private EditText ipAddressContentTextView;
    private TextView nameContentView;
    private EditText idContentView;
    private TextView textContentView;
    private TextView tagsContentView;
    private TextView linksContentView;

    //Declare other useful items
    private String ipAddress;
    private String id;

    //Declare Volley variables
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init items from doc view layout
        ipAddressContentTextView = (EditText) findViewById(R.id.ipAddressContentTextView);
        nameContentView = (TextView) findViewById(R.id.nameContentView);
        idContentView = (EditText) findViewById(R.id.idContentView);
        textContentView = (TextView) findViewById(R.id.textContentView);
        tagsContentView = (TextView) findViewById(R.id.tagsContentView);
        linksContentView = (TextView) findViewById(R.id.linksContentView);

        //init other useful items
        ipAddress = getIntent().getExtras().getString(getString(R.string.ip_extra_name));
        ipAddressContentTextView.setText(ipAddress);
        id = "";

        //init Volley variables
        queue = Volley.newRequestQueue(this);
    }

    public void viewDoc(View view) {
        ipAddress = ipAddressContentTextView.getText().toString();
        id = idContentView.getText().toString();

        //AlertDialog viewDocDialog = new AlertDialog.Builder(this)
        //        .setMessage("choose between xml and html")
        //        .setPositiveButton("xml", new DialogInterface.OnClickListener() {
        //            @Override
        //            public void onClick(DialogInterface dialog, int which) {
        /*                final String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + id;
                        StringRequest viewRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i("RESPONSE", response);
                                idContentView.setText(response.substring(response.indexOf("<i") + 4, response.indexOf("</i")));
                                nameContentView.setText(response.substring(response.indexOf("<n") + 6, response.indexOf("</n")));
                                textContentView.setText(response.substring(response.indexOf("<te") + 6, response.indexOf("</te")));
                                tagsContentView.setText(response.substring(response.indexOf("<ta") + 6, response.indexOf("</ta")));
                                linksContentView.setText(response.substring(response.indexOf("<l") + 7, response.indexOf("</li")));
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }) {
                            @Override
                            public Map<String, String> getHeaders() throws AuthFailureError {
                                Map<String, String> headers = new HashMap<>();
                                headers.put("Content-Type", MediaType.APPLICATION_XML);
                                return headers;
                            }
                        };
                        queue.add(viewRequest);
                    }
        //        })
        //       .setNegativeButton("html", new DialogInterface.OnClickListener() {
        //            @Override
        *///            public void onClick(DialogInterface dialog, int which) {
                        String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + id;
                        StringRequest viewRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                List<String> html = new ArrayList<>();
                                html.addAll(Arrays.asList(response.split("<p>")));
                                html.remove(0);
                                for(int i = 0; i < html.size(); i++) {
                                    html.set(i, html.get(i).replace(html.get(i), html.get(i).substring(0, html.get(i).indexOf("</"))));
                                }
                                nameContentView.setText(html.get(0));
                                idContentView.setText(html.get(1));
                                textContentView.setText(html.get(2));
                                tagsContentView.setText(html.get(3));
                                linksContentView.setText(html.get(4));
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                       }) {
                            @Override
                            public String getBodyContentType() {
                                return "text/html";
                            }
                        };

                        queue.add(viewRequest);
                    //}
            //    })
            //    .create();
        //viewDocDialog.show();
    }
}