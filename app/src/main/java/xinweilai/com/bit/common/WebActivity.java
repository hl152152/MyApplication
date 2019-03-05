package xinweilai.com.bit.common;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dunqi.gpm.chaotian.R;
import com.dunqi.gpm.chaotian.common.base.BaseActivity;
import com.dunqi.gpm.chaotian.common.util.WebUtil;
import com.dunqi.gpm.chaotian.common.view.ComTitleBar;

import butterknife.BindView;

public class WebActivity extends BaseActivity {
    private static final String URL = "url";
    @BindView(R.id.title_bar)
    ComTitleBar titleBar;
    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void refreshData(Bundle savedInstanceState) {
        String url = getIntent().getStringExtra(URL);
        WebUtil.webSetting(webView, this, false);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (error.getPrimaryError() == SslError.SSL_INVALID) {
                    handler.proceed();
                } else {
                    handler.cancel();
                }
            }
        });
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (titleBar != null) {
                    titleBar.setTitle(title);
                }
            }

        };
    }

    @Override
    protected void initUiAndListener() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(URL, url);
        context.startActivity(intent);
    }
}
