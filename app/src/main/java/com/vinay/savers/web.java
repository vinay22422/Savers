package com.vinay.savers;



import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URISyntaxException;

public class web extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        String weba=new String();
        if(bd != null)
        {
            weba = (String) bd.get("weba");

        }



        webView=findViewById(R.id.webview);



        webView.setWebViewClient(new WebViewClient());
        Toast.makeText(this, ""+weba, Toast.LENGTH_SHORT).show();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webView.loadUrl("https://www.journaldev.com");


        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(weba));
        startActivity(browserIntent);

        //webView.loadUrl(weba);
        //setContentView(webView);

    }


    // webView.loadUrl(weba);
    //setContentView(webView);



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}


