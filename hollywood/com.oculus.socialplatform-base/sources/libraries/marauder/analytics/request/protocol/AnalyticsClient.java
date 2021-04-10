package libraries.marauder.analytics.request.protocol;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.DeflaterOutputStream;
import libraries.debug.log.BLog;
import libraries.marauder.analytics.utils.hash.SecureHashUtil;

public class AnalyticsClient {
    public static final String API_PARAM_API_KEY = "api_key";
    public static final String API_PARAM_COMPRESSED = "compressed";
    public static final String API_PARAM_MESSAGE = "message";
    public static final String API_PARAM_METHOD = "method";
    public static final String API_PARAM_SIGNATURE = "sig";
    public static final Class<?> TAG = AnalyticsClient.class;
    public static AnalyticsClient sInstance;
    public final AnalyticsService mAnalyticsService = new NativeAnalyticsService();

    public static String compressJSONObject(String str) throws IOException {
        byte[] bytes = getBytes(str, "UTF-8");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
        deflaterOutputStream.write(bytes);
        deflaterOutputStream.close();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
    }

    public static byte[] getBytes(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("data may not be null");
        } else if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("charset may not be null or empty");
        } else {
            try {
                return str.getBytes(str2);
            } catch (UnsupportedEncodingException unused) {
                return str.getBytes();
            }
        }
    }

    public static synchronized AnalyticsClient getInstance() {
        AnalyticsClient analyticsClient;
        synchronized (AnalyticsClient.class) {
            analyticsClient = sInstance;
            if (analyticsClient == null) {
                analyticsClient = new AnalyticsClient();
                sInstance = analyticsClient;
            }
        }
        return analyticsClient;
    }

    public int sendBatchLogs(String str, String str2, String str3, String str4) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        treeMap.put(API_PARAM_API_KEY, str3);
        try {
            treeMap.put("message", compressJSONObject(str2));
            treeMap.put(API_PARAM_COMPRESSED, "1");
        } catch (IOException e) {
            BLog.e(AnalyticsClient.class, "failed to compress", e);
            treeMap.put("message", str2);
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : treeMap.entrySet()) {
            sb.append((String) entry.getKey());
            sb.append("=");
            sb.append((String) entry.getValue());
        }
        sb.append(str4);
        treeMap.put(API_PARAM_SIGNATURE, SecureHashUtil.makeMD5Hash(sb.toString()));
        return this.mAnalyticsService.log(str, treeMap);
    }
}
