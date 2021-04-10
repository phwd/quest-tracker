package defpackage;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;
import org.json.JSONObject;

/* renamed from: iY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3050iY {
    public static void a(Map map, URL url, String str, Proxy proxy) {
        AbstractC2025cY cYVar;
        if (str.equals("application/json")) {
            cYVar = new C2196dY(new JSONObject(map).toString());
        } else {
            cYVar = new C2879hY(map);
        }
        Objects.requireNonNull(AbstractC1585a.b);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        Objects.requireNonNull(AbstractC1585a.b);
        httpURLConnection.setConnectTimeout(3000);
        Objects.requireNonNull(AbstractC1585a.b);
        httpURLConnection.setReadTimeout(3000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", "Android");
        httpURLConnection.setRequestProperty("Content-Type", str);
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.toString();
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(httpURLConnection.getOutputStream());
            cYVar.a(gZIPOutputStream);
            gZIPOutputStream.close();
            httpURLConnection.getResponseCode();
            httpURLConnection.getInputStream().close();
        } finally {
            httpURLConnection.disconnect();
        }
    }
}
