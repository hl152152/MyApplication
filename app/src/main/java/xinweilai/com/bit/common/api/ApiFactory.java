package xinweilai.com.bit.common.api;


import com.huche.myapplication2.WPConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 空 on 2017/6/8 0008.
 */

public class ApiFactory {

    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .addInterceptor(new HeaderInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            // .cookieJar(CookiesManager.getInstence())
            .build();

    private static ApiService apiService = new Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(ApiService.class);




    public static ApiService getInstance() {
        return apiService;
    }

    public static String getBaseUrl() {
        return WPConfig.INSTANCE.getBaseUrl();
    }


}
