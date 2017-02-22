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

public class SearchActivity extends AppCompatActivity {

    //Declare items from doc lib view layout
    private EditText ipAddressContentTextSearch;
    private TextView docLibView;
    private EditText searchTagsContentView;

    //Declare other useful items
    private String ipAddress;
    private String tags;

    //Declare Volley variables
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init items from doc lib view layout
        ipAddressContentTextSearch = (EditText) findViewById(R.id.ipAddressContentTextSearch);
        docLibView = (TextView) findViewById(R.id.documentSearchView);
        searchTagsContentView = (EditText) findViewById(R.id.searchText);


        //init other useful items
        ipAddress = getIntent().getExtras().getString(getString(R.string.ip_extra_name));
        ipAddressContentTextSearch.setText(ipAddress);
        tags = "";

        //init Volley variables
        queue = Volley.newRequestQueue(this);
    }

    public void search(View view) {
        ipAddress = ipAddressContentTextSearch.getText().toString();
        tags = searchTagsContentView.getText().toString();

/*        AlertDialog searchDialog = new AlertDialog.Builder(this)
                .setMessage("Choose either xml or html")
                .setPositiveButton("xml", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + getString(R.string.search) + tags;
                        StringRequest searchRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                List<String> linksList = new ArrayList<>();
                                linksList.addAll(Arrays.asList(response.split("<link>")));
                                linksList.remove(0);
                                String listOfLinks = "";
                                for (int i = 0; i < linksList.size(); i++) {
                                    listOfLinks += linksList.get(i).substring(0, linksList.get(i).indexOf("</")) + "\n";
                                }
                                docLibView.setText(listOfLinks);

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }) {
                            @Override
                            public String getBodyContentType() {
                                return "application/xml";
                            }
                        };
                        queue.add(searchRequest);
                    }
                })
                .setNegativeButton("html", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
*/                        String url = getString(R.string.linkpt1) + ipAddress + getString(R.string.linkpt2) + getString(R.string.search) + tags;
                        StringRequest searchRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                List<String> docLinks = new ArrayList<>();
                                docLinks.addAll(Arrays.asList(response.split("<a")));
                                docLinks.remove(0);
                                String docLinksString = "";
                                for(int i = 0; i < docLinks.size(); i++) {
                                    docLinks.set(i,docLinks.get(i).substring(0, docLinks.indexOf("</")));
                                    docLinksString += docLinks.get(i) + "/n";
                                }

                                docLibView.setText(docLinksString);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }){
                            @Override
                            public String getBodyContentType() {
                                return "text/html";
                            }
                        };
                        queue.add(searchRequest);
 /*                   }
                })
                .create();
        searchDialog.show();
  */  }
}
