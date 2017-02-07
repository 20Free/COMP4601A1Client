package edu.carleton.comp4601.a1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends AppCompatActivity {

    //Declare UI variables
    private EditText ipAddressContentView;

    //Declare other useful items
    private String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ipAddressContentView = (EditText) findViewById(R.id.ipAddressContentText);

        ipAddress = "";
    }

    public void toCreate(View view) {
        ipAddress = ipAddressContentView.getText().toString();

        Intent createIntent = new Intent(this, CreateActivity.class);
        createIntent.putExtra(getString(R.string.ip_extra_name), ipAddress);
        startActivity(createIntent);
    }

    public void toUpdate(View view) {
        ipAddress = ipAddressContentView.getText().toString();

        Intent updateIntent = new Intent(this, UpdateActivity.class);
        updateIntent.putExtra(getString(R.string.ip_extra_name), ipAddress);
        startActivity(updateIntent);
    }

    public void toDelete(View view) {
        ipAddress = ipAddressContentView.getText().toString();

        Intent deleteIntent = new Intent(this, DeleteActivity.class);
        deleteIntent.putExtra(getString(R.string.ip_extra_name), ipAddress);
        startActivity(deleteIntent);
    }

    public void toView(View view) {
        ipAddress = ipAddressContentView.getText().toString();

        Intent viewIntent = new Intent(this, ViewActivity.class);
        viewIntent.putExtra(getString(R.string.ip_extra_name), ipAddress);
        startActivity(viewIntent);
    }

    public void toSearch(View view) {
        ipAddress = ipAddressContentView.getText().toString();

        Intent searchIntent = new Intent(this, SearchActivity.class);
        searchIntent.putExtra(getString(R.string.ip_extra_name), ipAddress);
        startActivity(searchIntent);
    }

    public void toViewLib(View view) {
        ipAddress = ipAddressContentView.getText().toString();

        Intent viewLibIntent = new Intent(this, ViewLibActivity.class);
        viewLibIntent.putExtra(getString(R.string.ip_extra_name), ipAddress);
        startActivity(viewLibIntent);
    }
}
