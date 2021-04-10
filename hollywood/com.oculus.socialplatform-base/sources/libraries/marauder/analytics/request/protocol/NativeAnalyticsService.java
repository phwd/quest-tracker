package libraries.marauder.analytics.request.protocol;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class NativeAnalyticsService implements AnalyticsService {
    public static final int ERROR_RESPONSE_CODE = -1;

    public static HttpURLConnection getHttpURLConnection(String str) {
        try {
            return (HttpURLConnection) new URL(str).openConnection();
        } catch (IOException unused) {
            return null;
        }
    }

    public static boolean writeParams(Map<String, String> map, HttpURLConnection httpURLConnection) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() != 0) {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        try {
            dataOutputStream.writeBytes(sb.toString());
            dataOutputStream.close();
            return true;
        } catch (IOException unused) {
            dataOutputStream.close();
            return false;
        } catch (Throwable th) {
            dataOutputStream.close();
            throw th;
        }
    }

    @Override // libraries.marauder.analytics.request.protocol.AnalyticsService
    public int log(String str, Map<String, String> map) {
        HttpURLConnection httpURLConnection = getHttpURLConnection(str);
        if (httpURLConnection == null) {
            return -1;
        }
        try {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty(HttpRequestMultipart.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
            if (!writeParams(map, httpURLConnection)) {
                return -1;
            }
            int responseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            return responseCode;
        } catch (IOException unused) {
            return -1;
        } finally {
            httpURLConnection.disconnect();
        }
    }
}
