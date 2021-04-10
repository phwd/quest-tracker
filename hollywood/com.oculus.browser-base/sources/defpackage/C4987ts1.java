package defpackage;

import android.os.SystemClock;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;

/* renamed from: ts1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4987ts1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f11375a = new Object();
    public static C4987ts1 b;

    public C4647rs1 a(int i, String str, String str2, String str3) {
        C4647rs1 rs1 = new C4647rs1();
        HttpURLConnection httpURLConnection = null;
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            HttpURLConnection d = d(i, str, str2, str3);
            d.setReadTimeout(3000);
            d.setConnectTimeout(1000);
            d.setDoInput(true);
            d.setRequestProperty("A-IM", "gzip");
            d.connect();
            int responseCode = d.getResponseCode();
            rs1.f11229a = responseCode;
            if (responseCode == 200) {
                AbstractC3364kK0.k("Variations.FirstRun.SeedConnectTime", SystemClock.elapsedRealtime() - elapsedRealtime);
                C4817ss1 ss1 = new C4817ss1();
                ss1.e = c(d);
                ss1.f11306a = b(d, "X-Seed-Signature");
                ss1.b = b(d, "X-Country");
                ss1.c = new Date().getTime();
                ss1.d = b(d, "IM").equals("gzip");
                e(SystemClock.elapsedRealtime() - elapsedRealtime);
                rs1.b = ss1;
                d.disconnect();
                return rs1;
            }
            String str4 = "Non-OK response code = " + responseCode;
            AbstractC1220Ua0.f("VariationsSeedFetch", str4, new Object[0]);
            throw new IOException(str4);
        } catch (SocketTimeoutException e) {
            rs1.f11229a = -2;
            AbstractC1220Ua0.f("VariationsSeedFetch", "SocketTimeoutException timeout when fetching variations seed.", e);
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            return rs1;
        } catch (UnknownHostException e2) {
            rs1.f11229a = -3;
            AbstractC1220Ua0.f("VariationsSeedFetch", "UnknownHostException unknown host when fetching variations seed.", e2);
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            return rs1;
        } catch (IOException e3) {
            rs1.f11229a = -1;
            AbstractC1220Ua0.f("VariationsSeedFetch", "IOException when fetching variations seed.", e3);
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            return rs1;
        } catch (Throwable unused) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            return rs1;
        }
    }

    public final String b(HttpURLConnection httpURLConnection, String str) {
        String headerField = httpURLConnection.getHeaderField(str);
        if (headerField == null) {
            return "";
        }
        return headerField.trim();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] c(java.net.HttpURLConnection r5) {
        /*
            r4 = this;
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ all -> 0x0023 }
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0021 }
            r0.<init>()     // Catch:{ all -> 0x0021 }
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x0021 }
        L_0x000d:
            int r2 = r5.read(r1)     // Catch:{ all -> 0x0021 }
            r3 = -1
            if (r2 == r3) goto L_0x0019
            r3 = 0
            r0.write(r1, r3, r2)     // Catch:{ all -> 0x0021 }
            goto L_0x000d
        L_0x0019:
            byte[] r0 = r0.toByteArray()     // Catch:{ all -> 0x0021 }
            r5.close()
            return r0
        L_0x0021:
            r0 = move-exception
            goto L_0x0025
        L_0x0023:
            r0 = move-exception
            r5 = 0
        L_0x0025:
            if (r5 == 0) goto L_0x002a
            r5.close()
        L_0x002a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4987ts1.c(java.net.HttpURLConnection):byte[]");
    }

    public HttpURLConnection d(int i, String str, String str2, String str3) {
        String str4 = "https://clientservices.googleapis.com/chrome-variations/seed?osname=";
        if (i == 0) {
            str4 = AbstractC2531fV.f(str4, "android");
        } else if (i == 1) {
            str4 = AbstractC2531fV.f(str4, "android_webview");
        }
        if (str != null && !str.isEmpty()) {
            str4 = AbstractC2531fV.g(str4, "&restrict=", str);
        }
        if (str2 != null && !str2.isEmpty()) {
            str4 = AbstractC2531fV.g(str4, "&milestone=", str2);
        }
        if (str3 != null && !str3.isEmpty()) {
            str4 = AbstractC2531fV.g(str4, "&channel=", str3);
        }
        return (HttpURLConnection) new URL(str4).openConnection();
    }

    public final void e(long j) {
        AbstractC1220Ua0.d("VariationsSeedFetch", "Fetched first run seed in " + j + " ms", new Object[0]);
        AbstractC3364kK0.k("Variations.FirstRun.SeedFetchTime", j);
    }
}
