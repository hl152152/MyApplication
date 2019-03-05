package xinweilai.com.bit.common.util;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/12/2.
 */

public class RequestBodyUtil {

    public static RequestBody get(File file) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }

    public static RequestBody get(String content) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), content);
    }

}
