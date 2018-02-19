package com.example.pc_gaming.libreriabbdd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FragmentPaginaWeb extends Fragment {


    ///////////////////////////////////////////////////////////////////////////////////
    ///////////////// ELIMINAMOS TODOS MENOS ESTE METODO  /////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    final String url = "https://www.re-read.com/libreria/re-read-alcala-de-henares/";
    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fragment_pagina_web, container, false);

        webView = v.findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());  // PARA QUE SE MANTENGA EN EL FRAGMENT
        webView.getSettings().setJavaScriptEnabled(true);   // HABILITO SCRIPTS DE JAVASCRIPT
        webView.loadUrl(url);
        Toast.makeText(v.getContext(), url, Toast.LENGTH_SHORT).show();


        // Inflate the layout for this fragment
        return v;
    }


}
