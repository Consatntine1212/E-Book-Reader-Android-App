package com.unipi.conchro;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

//import nl.siegmann.epublib.domain.Book;
//import nl.siegmann.epublib.domain.TOCReference;
//import nl.siegmann.epublib.epub.EpubReader;


public class e_book_view extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook_view);
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        // Load and display the EPUB content
//        try {
//            InputStream epubInputStream = getAssets().open("your_epub_file.epub");
//            Book book = (new EpubReader()).readEpub(epubInputStream);
//            TOCReference tocReference = book.getTableOfContents().getTocReferences().get(0);
//            webView.loadUrl(tocReference.getCompleteHref());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Ensure that links within the WebView are opened in the WebView itself
        webView.setWebViewClient(new WebViewClient());


    }
}