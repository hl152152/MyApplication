package xinweilai.com.bit.common.util;


import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Created by lvluogang on 2017/6/12.
 */
public class SignatureUtil {
    private static final Logger log = Logger.getLogger(SignatureUtil.class.getName());

    private static final String ENCRYPTION_ALGORITHM = "SHA-1";

    /**
     * 使用指定算法生成消息摘要，默认md5
     *
     * @param strSrc  the str src
     * @param encName the enc name
     * @return the string
     */
    public static String digest(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes(Charset.forName("utf-8"));
        try {
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = SysUtils.bytesToHexString(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            log.log(Level.WARNING, "Invalid algorithm: " + encName);
            return null;
        }
        return strDes;
    }

    /**
     * 根据appid、appSecret、stamp时间戳、requestData请求参数来生成签名
     *
     * @param appid       the appid
     * @param appSecret   the appSecret
     * @param stamp       the stamp
     * @param requestData the requestData
     * @return the string
     */
    public static String generateSignature(String appid, String appSecret, String stamp, String requestData) {
        String signature = null;
        if (SysUtils.isNotNull(appSecret) && SysUtils.isNotNull(stamp)
                && SysUtils.isNotNull(appid)) {
            List<String> params = new ArrayList<String>();
            params.add(stamp);
            params.add(appid);
            params.add(appSecret);
            params.add(requestData);
            // 按照字典序逆序拼接参数
            Collections.sort(params);
            Collections.reverse(params);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < params.size(); i++) {
                sb.append(params.get(i));
            }
            signature = digest(sb.toString(), ENCRYPTION_ALGORITHM);
            params.clear();
            params = null;
        }
        return signature;
    }

    public static boolean isValid(String signature, String appid, String stamp, String requestData, String appSecret) {
        String calculatedSignature = generateSignature(appid, appSecret, stamp, requestData);
        return calculatedSignature.equalsIgnoreCase(signature);
    }


}
