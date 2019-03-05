package xinweilai.com.bit.common.api;


import com.huche.myapplication2.common.util.CommonUtil;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by 空 on 2017/8/9 0009.
 */

public class CookiesManager implements CookieJar {

    private static CookiesManager cookiesManager;

    private CookiesManager() {
    }

    public synchronized static CookiesManager getInstence() {
        if (cookiesManager == null) {
            cookiesManager = new CookiesManager();
        }
        return cookiesManager;
    }


    private final PersistentCookieStore cookieStore = new PersistentCookieStore(CommonUtil.getContext());

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if (cookies != null && cookies.size() > 0) {
            cookieStore.add(url, cookies);
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        return cookies;
    }

    public List<Cookie> getCookies() {
        return cookieStore.getCookies();
    }

    public void addCookie(HttpUrl httpUrl, List<Cookie> cookies) {
        cookieStore.add(httpUrl, cookies);
    }

    public void clearCookie() {
        if (cookieStore != null) {
            cookieStore.removeAll();
        }
    }
}
