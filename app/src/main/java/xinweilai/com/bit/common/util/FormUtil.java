package xinweilai.com.bit.common.util;

import android.text.TextUtils;

import com.dunqi.gpm.chaotian.mine.util.UserUtil;
import com.nanchen.compresshelper.CompressHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/30.
 */

public class FormUtil {


    public static HashMap<String, RequestBody> AddHeader(HashMap<String, String> param) {

        String data = JsonUtils.toJson(param);
        if (!TextUtils.isEmpty(data)) {
            param.put("data", data);
        }

        String deviceId = CommonUtil.getDeviceId();
        param.put("device_id", deviceId);

        int userId = UserUtil.getUserId();
        if (userId != -1) {
            param.put("user_id", String.valueOf(userId));
        }

        long l = System.currentTimeMillis();
        param.put("stamp", String.valueOf(l));

        String appToken = UserUtil.getAppToken();
        String sign = SignatureUtil.generateSignature(deviceId, appToken, String.valueOf(l), data);
        if (!TextUtils.isEmpty(sign)) {
            param.put("sign", sign);
        }
        param.put("rows", "10");

        HashMap<String, RequestBody> bodyHashMap = new HashMap<>();

        for (Map.Entry<String, String> stringStringEntry : param.entrySet()) {
            bodyHashMap.put(stringStringEntry.getKey(), RequestBodyUtil.get(stringStringEntry.getValue()));
        }

        return bodyHashMap;
    }

    public static HashMap<String, RequestBody> AddHeader(HashMap<String, String> param, String removeKey) {

        String data = JsonUtils.toJson(param);
        if (!TextUtils.isEmpty(data)) {
            param.put("data", data);
        }

        param.remove(removeKey);

        String deviceId = CommonUtil.getDeviceId();
        param.put("device_id", deviceId);

        int userId = UserUtil.getUserId();
        if (userId != -1) {
            param.put("user_id", String.valueOf(userId));
        }

        long l = System.currentTimeMillis();
        param.put("stamp", String.valueOf(l));

        String appToken = UserUtil.getAppToken();
        String sign = SignatureUtil.generateSignature(deviceId, appToken, String.valueOf(l), data);
        if (!TextUtils.isEmpty(sign)) {
            param.put("sign", sign);
        }
        param.put("rows", "10");

        HashMap<String, RequestBody> bodyHashMap = new HashMap<>();

        for (Map.Entry<String, String> stringStringEntry : param.entrySet()) {
            bodyHashMap.put(stringStringEntry.getKey(), RequestBodyUtil.get(stringStringEntry.getValue()));
        }

        return bodyHashMap;
    }


    public static HashMap<String, RequestBody> AddHeader(HashMap<String, String> param, String photoKey, ArrayList<String> photos) {

        String data = JsonUtils.toJson(param);
        if (!TextUtils.isEmpty(data)) {
            param.put("data", data);
        }

        String deviceId = CommonUtil.getDeviceId();
        param.put("device_id", deviceId);

        int userId = UserUtil.getUserId();
        if (userId != -1) {
            param.put("user_id", String.valueOf(userId));
        }

        long l = System.currentTimeMillis();
        param.put("stamp", String.valueOf(l));

        String appToken = UserUtil.getAppToken();
        String sign = SignatureUtil.generateSignature(deviceId, appToken, String.valueOf(l), data);
        if (!TextUtils.isEmpty(sign)) {
            param.put("sign", sign);
        }
        param.put("rows", "10");


        HashMap<String, RequestBody> bodyHashMap = new HashMap<>();

        for (Map.Entry<String, String> stringStringEntry : param.entrySet()) {
            bodyHashMap.put(stringStringEntry.getKey(), RequestBodyUtil.get(stringStringEntry.getValue()));
        }

        for (String photo : photos) {
            File file = CompressHelper.getDefault(CommonUtil.getContext())
                    .compressToFile(new File(photo));
            bodyHashMap.put(photoKey+"\"; filename=\"" + file.getName(), RequestBodyUtil.get(file));

        }

        return bodyHashMap;


    }
}
