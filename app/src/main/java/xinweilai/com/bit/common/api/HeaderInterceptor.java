package xinweilai.com.bit.common.api;

import android.text.TextUtils;


import com.huche.myapplication2.common.util.CommonUtil;
import com.huche.myapplication2.common.util.RxBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/11.
 */
class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //去掉外层的result

        Request request = chain.request();

        Request.Builder builder = request.newBuilder();

        HashMap<String, String> params = new HashMap<>();
        builder.addHeader("user-agent", "Android");

        if (request.body() instanceof FormBody) {
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) request.body();
            for (int i = 0; i < oldFormBody.size(); i++) {
                newFormBody.addEncoded(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
                params.put(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
            }
//            newFormBody.add("sign","******");
            String deviceId = CommonUtil.getDeviceId();

       if (TextUtils.isEmpty(params.get("device_id"))) {
                newFormBody.add("device_id", deviceId);
            }

            if (TextUtils.isEmpty(params.get("user_id"))) {
                int userId = UserUtil.getUserId();
                if (userId != -1) {
                    newFormBody.add("user_id", String.valueOf(userId));
                }
            }

            long orgId = UserUtil.getOrgId();
            if (orgId != -1) {
                newFormBody.add("org_id", String.valueOf(orgId));
            }

            String data = JsonUtils.toJson(params);
            if (TextUtils.isEmpty(params.get("data"))) {
                newFormBody.add("data", data);
            }

            long l = System.currentTimeMillis();
            if (TextUtils.isEmpty(params.get("stamp"))) {
                newFormBody.add("stamp", String.valueOf(l));
            }

            if (TextUtils.isEmpty(params.get("sign"))) {
                String appToken = UserUtil.getAppToken();
                String sign = SignatureUtil.generateSignature(deviceId, appToken, String.valueOf(l), data);

                if (!TextUtils.isEmpty(sign)) {
                    newFormBody.add("sign", sign);
                }
            }

            //每页20条
            if (TextUtils.isEmpty(params.get("rows"))) {
                newFormBody.add("rows", String.valueOf(10));
            }

            builder.method(request.method(), newFormBody.build());
        }

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


        Response response = chain.proceed(builder.build());

        if (response.code() == 401 || response.code() == 403 || response.code() == 400) {
            //鉴权失败
            RxBus.getInstance().post(new User());
        }
        return response;
    }
}
