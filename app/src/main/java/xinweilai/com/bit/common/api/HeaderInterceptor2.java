package xinweilai.com.bit.common.api;


import com.huche.myapplication2.common.util.CommonUtil;
import com.huche.myapplication2.common.util.JsonUtils;
import com.huche.myapplication2.common.util.RxBus;
import com.huche.myapplication2.common.util.SignatureUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSource;

/**
 * Created by Administrator on 2017/11/11.
 */
class HeaderInterceptor2 implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //去掉外层的result

//        Request request = chain.request();
//
//        Request.Builder builder = request.newBuilder();

        HashMap<String, String> params = new HashMap<>();


////            newFormBody.add("sign","******");
//            String deviceId = CommonUtil.getDeviceId();
//
//            if (TextUtils.isEmpty(params.get("device_id"))) {
//                newFormBody.add("device_id", deviceId);
//            }
//
//            if (TextUtils.isEmpty(params.get("user_id"))) {
//                int userId = UserUtil.getUserId();
//                if (userId != -1) {
//                    newFormBody.add("user_id", String.valueOf(userId));
//                }
//            }
//
//            String data = JsonUtils.toJson(params);
//            if (TextUtils.isEmpty(params.get("data"))) {
//                newFormBody.add("data", data);
//            }
//
//            long l = System.currentTimeMillis();
//            if (TextUtils.isEmpty(params.get("stamp"))) {
//                newFormBody.add("stamp", String.valueOf(l));
//            }
//
//            if (TextUtils.isEmpty(params.get("sign"))) {
//                String appToken = UserUtil.getAppToken();
//                String sign = SignatureUtil.generateSignature(deviceId, appToken, String.valueOf(l), data);
//
//                if (!TextUtils.isEmpty(sign)) {
//                    newFormBody.add("sign", sign);
//                }
//            }
//
//            //每页20条
//            if (TextUtils.isEmpty(params.get("rows"))) {
//                newFormBody.add("rows", String.valueOf(10));
//            }
//
//            builder.method(request.method(), newFormBody.build());
//        }


        Request oldRequest = chain.request();
        //获取不到公共参数
        if (oldRequest.body() instanceof FormBody) {
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) oldRequest.body();
            for (int i = 0; i < oldFormBody.size(); i++) {
                newFormBody.addEncoded(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
                params.put(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
            }
        }

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        Request.Builder builder = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body());

        List<Cookie> cookies = CookiesManager.getInstence().getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.name();
                String cookieString = cookie.toString();
                if ("JSESSIONID".equals(name)) {
                    builder.addHeader("Set-Cookie", cookieString);
                }
            }
        }
        builder.addHeader("user-agent", "Android");

/*       String deviceId = CommonUtil.getDeviceId();
       authorizedUrlBuilder.addQueryParameter("device_id", deviceId);
        int userId = UserUtil.getUserId();
        if (userId != -1) {
            authorizedUrlBuilder.addQueryParameter("user_id", String.valueOf(userId));
        }
        String data = JsonUtils.toJson(params);
        authorizedUrlBuilder.addQueryParameter("data", data);
        long l = System.currentTimeMillis();
        authorizedUrlBuilder.addQueryParameter("stamp", String.valueOf(l));
        String appToken = UserUtil.getAppToken();
        String sign = SignatureUtil.generateSignature(deviceId, appToken, String.valueOf(l), data);
        if (sign != null) {
            authorizedUrlBuilder.addQueryParameter("sign", sign);
        }
        */

        // 新的请求
        Request newRequest = builder.url(authorizedUrlBuilder.build()).build();
        Response response;
        try {
            response = chain.proceed(newRequest);
        } catch (Exception e) {
            throw e;
        }
        //这个是因为，如果请求下载链接的话，会导致无法获取response
        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE);

        if (response.code() == 401 || response.code() == 403 ||
                response.code() == 400) {
            //鉴权失败
            UserUtil.clear();
            RxBus.getInstance().post(new User());
        }

        return response;
    }
}
