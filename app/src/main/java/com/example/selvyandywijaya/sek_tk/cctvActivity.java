package com.example.selvyandywijaya.sek_tk;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.common.api.Response;

public class cctvActivity extends AppCompatActivity {

    WebView myWebView;
    SwipeRefreshLayout swipeLayout;
    boolean IsError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        myWebView = (WebView) findViewById(R.id.webview);

        myWebView.setWebViewClient(new MyWebViewClient(){
            @Override
            @SuppressWarnings("deprecation")
            public void onReceivedError(WebView view,int errorCode,String description,String failingUrl){
                //myWebView.loadUrl("javascript:document.open();document.close();");
                //myWebView.loadUrl("about:blank");

                //myWebView.stopLoading();
                IsError = true;
                myWebView.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT);
                toast.show();

                super.onReceivedError(view,errorCode,description,failingUrl);

            }
            @Override
            public void onPageFinished(WebView view,String url){
                //         swipeLayout.setRefreshing(false);
                if (IsError){
                    IsError = false;
                    Toast toast = Toast.makeText(getApplicationContext(), "Load Failed", Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    myWebView.setVisibility(View.VISIBLE);
                    Toast toast = Toast.makeText(getApplicationContext(), "Succeed", Toast.LENGTH_SHORT);
                    toast.show();
                }




            }
        });

        myWebView.getSettings().setAppCachePath(this.getCacheDir().getAbsolutePath());
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        if( !isNetworkStatusAvailable()){
            myWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            myWebView.setVisibility(View.VISIBLE);
            Toast toast = Toast.makeText(this, "Cache", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Toast toast = Toast.makeText(this, "Not Cache", Toast.LENGTH_SHORT);
            toast.show();
        }


        myWebView.loadUrl("https://192.168.43.1:8080");

    }

    public  void ReloadWeb(){
        myWebView.reload();
    }

    private class MyWebViewClient extends WebViewClient {

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().contains("192.168.43.1")) {
                // if (Uri.parse(url).getHost().equals("10.0.2.2")) {//emulator
                // if (Uri.parse(url).getHost().equals("192.168.43.51")) {//adb
                // This is my web site, so do not override; let my WebView load the page
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            return true;
        }
    }

    //check connection by checking if connected to a network
    public boolean isNetworkStatusAvailable(){
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan != null){
            NetworkInfo netInfos = conMan.getActiveNetworkInfo();
            if (netInfos != null)
                if (netInfos.isConnected())
                    return true;
        }
        return false;


    }

}
