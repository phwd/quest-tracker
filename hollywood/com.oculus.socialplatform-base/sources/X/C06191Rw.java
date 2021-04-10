package X;

import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/* renamed from: X.1Rw  reason: invalid class name and case insensitive filesystem */
public final class C06191Rw implements AbstractC07051bX<InputStream> {
    @VisibleForTesting
    public static final AnonymousClass1S1 A06 = new AnonymousClass1S1();
    public InputStream A00;
    public HttpURLConnection A01;
    public final int A02;
    public final AnonymousClass1S1 A03;
    public final AnonymousClass1Rx A04;
    public volatile boolean A05;

    private InputStream A00(URL url, int i, URL url2, Map<String, String> map) throws IOException {
        InputStream inputStream;
        if (i < 5) {
            if (url2 != null) {
                try {
                    if (url.toURI().equals(url2.toURI())) {
                        throw new AnonymousClass1S0("In re-direct loop", -1);
                    }
                } catch (URISyntaxException unused) {
                }
            }
            this.A01 = (HttpURLConnection) url.openConnection();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.A01.addRequestProperty(entry.getKey(), entry.getValue());
            }
            HttpURLConnection httpURLConnection = this.A01;
            int i2 = this.A02;
            httpURLConnection.setConnectTimeout(i2);
            this.A01.setReadTimeout(i2);
            this.A01.setUseCaches(false);
            this.A01.setDoInput(true);
            this.A01.setInstanceFollowRedirects(false);
            this.A01.connect();
            this.A00 = this.A01.getInputStream();
            if (this.A05) {
                return null;
            }
            int responseCode = this.A01.getResponseCode();
            int i3 = responseCode / 100;
            boolean z = false;
            if (i3 == 2) {
                z = true;
            }
            if (z) {
                HttpURLConnection httpURLConnection2 = this.A01;
                if (TextUtils.isEmpty(httpURLConnection2.getContentEncoding())) {
                    inputStream = new AnonymousClass1OK(httpURLConnection2.getInputStream(), (long) httpURLConnection2.getContentLength());
                } else {
                    if (Log.isLoggable("HttpUrlFetcher", 3)) {
                        httpURLConnection2.getContentEncoding();
                    }
                    inputStream = httpURLConnection2.getInputStream();
                }
                this.A00 = inputStream;
                return inputStream;
            }
            boolean z2 = false;
            if (i3 == 3) {
                z2 = true;
            }
            if (z2) {
                String headerField = this.A01.getHeaderField("Location");
                if (!TextUtils.isEmpty(headerField)) {
                    URL url3 = new URL(url, headerField);
                    A26();
                    return A00(url3, i + 1, url, map);
                }
                throw new AnonymousClass1S0("Received empty or null redirect url", -1);
            } else if (responseCode == -1) {
                throw new AnonymousClass1S0(AnonymousClass006.A03("Http request failed with status code: ", -1), -1);
            } else {
                throw new AnonymousClass1S0(this.A01.getResponseMessage(), responseCode);
            }
        } else {
            throw new AnonymousClass1S0("Too many (> 5) redirects!", -1);
        }
    }

    @Override // X.AbstractC07051bX
    public final void cancel() {
        this.A05 = true;
    }

    public C06191Rw(AnonymousClass1Rx r2, int i) {
        AnonymousClass1S1 r0 = A06;
        this.A04 = r2;
        this.A02 = i;
        this.A03 = r0;
    }

    @Override // X.AbstractC07051bX
    public final void A26() {
        InputStream inputStream = this.A00;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        HttpURLConnection httpURLConnection = this.A01;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.A01 = null;
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final Class<InputStream> A3h() {
        return InputStream.class;
    }

    @Override // X.AbstractC07051bX
    @NonNull
    public final AnonymousClass1fM A3i() {
        return AnonymousClass1fM.REMOTE;
    }

    @Override // X.AbstractC07051bX
    public final void A6H(@NonNull AnonymousClass1cY r8, @NonNull AnonymousClass1Ry<? super InputStream> r9) {
        SystemClock.elapsedRealtimeNanos();
        try {
            AnonymousClass1Rx r6 = this.A04;
            URL url = r6.A01;
            if (url == null) {
                if (TextUtils.isEmpty(r6.A00)) {
                    String str = r6.A04;
                    if (TextUtils.isEmpty(str)) {
                        URL url2 = r6.A05;
                        AnonymousClass1S2.A00(url2);
                        str = url2.toString();
                    }
                    r6.A00 = Uri.encode(str, "@#&=*+-_.,:!?()/~'%;$");
                }
                url = new URL(r6.A00);
                r6.A01 = url;
            }
            r9.A6x(A00(url, 0, null, r6.A03.A43()));
        } catch (IOException e) {
            r9.A7F(e);
        } catch (Throwable th) {
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                SystemClock.elapsedRealtimeNanos();
            }
            throw th;
        }
        if (Log.isLoggable("HttpUrlFetcher", 2)) {
            SystemClock.elapsedRealtimeNanos();
        }
    }
}
