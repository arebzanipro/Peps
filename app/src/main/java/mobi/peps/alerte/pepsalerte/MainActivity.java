package mobi.peps.alerte.pepsalerte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {


    private static final String TAG ="Main";
    String stringDocument = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WebView mTextView = (WebView) findViewById(R.id.webview);
        mTextView.loadUrl("http://peps.mobi");

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="http://peps.mobi";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                         stringDocument = response;
                        Document document = Jsoup.parse(response);
                        Log.d(TAG, "Response : "+url);
                        Log.d(TAG, "Response : "+stringDocument);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                stringDocument = "That didn't work!";
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }
}
