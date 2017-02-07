package edu.carleton.comp4601.a1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class ViewLibActivity extends AppCompatActivity {

    //Declare items from doc lib view layout
    private EditText ipAddressContentTextLib;
    private TextView docLibView;

    //Declare other useful items
    private String ipAddress;

    //Declare Volley variables
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_lib_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init items from doc lib view layout
        ipAddressContentTextLib = (EditText) findViewById(R.id.ipAddressContentTextLib);
        docLibView = (TextView) findViewById(R.id.documentLibraryView);


        //init other useful items
        ipAddress = getIntent().getExtras().getString(getString(R.string.ip_extra_name));
        ipAddressContentTextLib.setText(ipAddress);

        //init Volley variables
        queue = Volley.newRequestQueue(this);
    }

    public void viewDocLib(View view) {
        ipAddress = ipAddressContentTextLib.getText().toString();
/*
        AlertDialog viewDocLibDialog = new AlertDialog.Builder(this)
                .setMessage("choose between xml and html")
                .setPositiveButton("xml", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + getString(R.string.documents);
                        StringRequest viewDocLibRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                docLibView.setText(response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        queue.add(viewDocLibRequest);
                    }
                })
                .setNegativeButton("html", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
  */                      String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + getString(R.string.documents);
                        StringRequest viewDocLibRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                docLibView.setText(response);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        queue.add(viewDocLibRequest);
    /*                }
                })
                .create();
        viewDocLibDialog.show();
    */}
}