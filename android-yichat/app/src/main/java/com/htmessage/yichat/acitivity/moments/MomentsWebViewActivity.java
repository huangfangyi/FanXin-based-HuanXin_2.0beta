package com.htmessage.yichat.acitivity.moments;



import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.htmessage.yichat.R;
import com.htmessage.yichat.acitivity.BaseActivity;
import com.htmessage.yichat.utils.LoggerUtils;

public class MomentsWebViewActivity extends BaseActivity {

    WebView webView;
    ProgressBar progressBar1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_webview);
       
        initView();
    }

    private void initView() {
        progressBar1 = (ProgressBar) this.findViewById(R.id.progressBar1);

        String url = this.getIntent().getStringExtra("url");
        LoggerUtils.e("url------------------->>>"+url);
        webView = (WebView) this.findViewById(R.id.webView);
      //设置 缓存模式 
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能 
        webView.getSettings().setDomStorageEnabled(true); 
        
        
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar1.setVisibility(View.GONE);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)

            { // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                                                          
                view.loadUrl(url);             
                return true;

            }

        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 返回
     * 
     * @param view
     */
    public void back(View view) {
        finish();
    }
}