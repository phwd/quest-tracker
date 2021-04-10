package defpackage;

import J.N;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.zip.GZIPInputStream;
import org.chromium.base.LocaleUtils;

/* renamed from: QN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QN {

    /* renamed from: a  reason: collision with root package name */
    public static final Executor f8756a = Executors.newFixedThreadPool(6);
    public RN b;

    public static HttpURLConnection a(QN qn, URL url, String str, int i) {
        Objects.requireNonNull(qn);
        String M5LbL2nl = N.M5LbL2nl();
        String c = LocaleUtils.c(Locale.getDefault());
        String str2 = Build.MODEL;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        int a2 = AbstractC5580xK0.a(i);
        if (a2 != 0) {
            if (a2 == 1) {
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept", "application/json");
            } else {
                throw new IllegalArgumentException("Unsupported content type specified");
            }
        }
        httpURLConnection.setRequestProperty("User-Agent", M5LbL2nl);
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Accept-Language", c);
        httpURLConnection.setRequestProperty("x-build-model", str2);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod(str != null ? "POST" : "GET");
        if (str != null) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.getOutputStream().write(str.getBytes("UTF-8"));
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        return httpURLConnection;
    }

    public static InputStream b(QN qn, HttpURLConnection httpURLConnection) {
        Objects.requireNonNull(qn);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode >= 400 || responseCode == -1) {
            return httpURLConnection.getErrorStream();
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (contentEncoding == null || !contentEncoding.equalsIgnoreCase("gzip") || (inputStream instanceof GZIPInputStream)) {
            return inputStream;
        }
        return new GZIPInputStream(inputStream);
    }

    public static String c(QN qn, InputStream inputStream, int i) {
        Objects.requireNonNull(qn);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        inputStream.close();
        int a2 = AbstractC5580xK0.a(i);
        if (a2 == 0) {
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        }
        if (a2 == 1) {
            return byteArrayOutputStream.toString("UTF-8");
        }
        throw new IllegalArgumentException("Unsupported return type specified");
    }

    public void d(String str, String str2, int i, int i2) {
        try {
            new PN(this, str, str2, i, i2).d(f8756a);
        } catch (RejectedExecutionException e) {
            StringBuilder i3 = AbstractC2531fV.i("Too many Story log threads: ");
            i3.append(e.toString());
            Log.e("FacebookUrlRequestService", i3.toString());
        }
    }
}
