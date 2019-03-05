package xinweilai.com.bit.common.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * 封装JSON转换类，目前使用GSON
 * 作者：Administrator on 2016/8/18 0018 13:38
 * 邮箱：yantianfeng@seemmo.com
 *
 * @author ytf
 */
public class JsonUtils {
    private static Gson mGson = new Gson();


    public static Gson getInstance(){
        return mGson;
    }

    /**
     * 通过字符串及类获取对象实例
     *
     * @param json     JSON字符串
     * @param classOfT 类定义
     * @param <T>      泛型类型
     * @return
     */
    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return mGson.fromJson(json, classOfT);
    }

    /**
     * 通过字符串及类型获取对象实例
     *
     * @param json    JSON字符串
     * @param typeOfT type定义  如new TypeToken<TestGeneric<String>>(){}.getType();
     * @param <T>     泛型类型
     * @return
     */
    public static  <T> T fromJson(String json, Type typeOfT)  throws JsonSyntaxException {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return mGson.fromJson(json, typeOfT);
    }

    /**
     * 将对象转为JSON字符串
     *
     * @param src 源对象
     * @return JSON字符串，如果对象为空则返回null
     */
    public static String toJson(Object src) {
        if (src == null) {
            return null;
        }
        return mGson.toJson(src);
    }

}
