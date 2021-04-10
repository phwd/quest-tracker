package X;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.acra.LogCatCollector;
import com.oculus.uploader.OculusHttpRequestExecutor;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.apache.http.client.HttpResponseException;

/* renamed from: X.1kE  reason: invalid class name */
public final class AnonymousClass1kE {
    public int A00;
    public long A01 = -1;
    public AnonymousClass1kH A02;
    public AnonymousClass1kI A03;
    public URI A04;
    public final AnonymousClass1kJ A05;
    public final AnonymousClass1Q9 A06;
    public final Object A07 = new Object();
    public final OculusHttpRequestExecutor A08;
    public volatile AnonymousClass1Rm A09;
    @Nullable
    public volatile AnonymousClass1kG A0A;
    @Nullable
    public volatile AnonymousClass1kF A0B;
    @Nullable
    public volatile AnonymousClass1kY A0C;
    public volatile boolean A0D;

    public static /* synthetic */ long A00(Exception exc) {
        if (!(exc instanceof HttpResponseException)) {
            return -1;
        }
        try {
            String message = exc.getMessage();
            if (message == null || message.isEmpty()) {
                return -1;
            }
            String[] split = message.split(LogCatCollector.COMPRESS_NEWLINE);
            if (split.length < 2) {
                return -1;
            }
            String str = split[1];
            try {
                AbstractC04100gp A002 = AnonymousClass1A2.A00.A00(str);
                A002.A0b();
                AnonymousClass1kU r2 = new AnonymousClass1kU();
                if (A002.A0a() != EnumC04820ji.START_OBJECT) {
                    A002.A0Z();
                } else {
                    while (A002.A0b() != EnumC04820ji.END_OBJECT) {
                        String A0d = A002.A0d();
                        A002.A0b();
                        if ("backoff".equals(A0d)) {
                            r2.A00 = A002.A0D();
                        }
                        A002.A0Z();
                    }
                    long j = r2.A00;
                    if (j >= 0) {
                        return j;
                    }
                }
                throw new AnonymousClass1kQ(str);
            } catch (IOException unused) {
                throw new AnonymousClass1kQ();
            }
        } catch (Exception unused2) {
            return -1;
        }
    }

    public static void A01(AnonymousClass1kE r5, int i) {
        int i2 = r5.A00 + i;
        r5.A00 = i2;
        r5.A02.A00((long) i2);
        r5.A02.A6d(((float) r5.A00) / ((float) r5.A06.A00));
    }

    public static void A02(AnonymousClass1kE r15, long j, String str, Exception exc, boolean z, AnonymousClass1Rk r21) {
        File file;
        long j2 = j;
        r15.A02.A01(j2, str, exc, z, r21.name());
        if (z) {
            AnonymousClass1kR r5 = r15.A05.A03;
            int i = r5.A01;
            r5.A01 = i + 1;
            if (i < 50 && (file = r15.A06.A01) != null && file.exists()) {
                if (j2 < 0) {
                    int i2 = r5.A00;
                    r5.A00 = Math.min(i2 << 1, 500);
                    j2 = (long) i2;
                }
                try {
                    Thread.sleep(j2);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
                r15.A03();
                return;
            }
        }
        r15.A02.A6A(new AnonymousClass1QC(str, (long) r15.A00, false, exc, z, r21));
        r15.A09 = null;
        r15.A0A = null;
        r15.A0B = null;
    }

    public final void A03() {
        URI uri = this.A04;
        HashMap hashMap = new HashMap();
        AnonymousClass1kJ r2 = this.A05;
        hashMap.putAll(r2.A04);
        this.A02.A04(uri.toString(), hashMap);
        this.A0A = new AnonymousClass1kG(this, r2);
        this.A09 = this.A08.A00(AnonymousClass1Rk.GET, hashMap, uri, null, this.A0A);
    }

    public final void A04(long j, boolean z) {
        this.A02.A02(j, z);
        this.A00 = (int) j;
        AnonymousClass1kI r2 = this.A03;
        r2.A00 = SystemClock.uptimeMillis();
        r2.A01.add(new AnonymousClass1kL());
        AnonymousClass1kI.A00(r2, 0, 0);
        AnonymousClass1kJ r1 = this.A05;
        this.A0B = new AnonymousClass1kF(this, r2, z, r1);
        OculusHttpRequestExecutor oculusHttpRequestExecutor = this.A08;
        AnonymousClass1Rk r4 = AnonymousClass1Rk.POST;
        HashMap hashMap = new HashMap(r1.A04);
        AnonymousClass1Q9 r22 = this.A06;
        hashMap.put("X-Entity-Length", Long.toString(r22.A00));
        hashMap.put("Offset", Long.toString(j));
        hashMap.put("X-Entity-Type", r22.A03);
        hashMap.put("X-Entity-Name", r22.A02);
        this.A09 = oculusHttpRequestExecutor.A00(r4, hashMap, this.A04, new AnonymousClass1Rl(r22, j), this.A0B);
    }

    public AnonymousClass1kE(AnonymousClass1Q9 r5, AnonymousClass1kJ r6, AnonymousClass1kH r7, OculusHttpRequestExecutor oculusHttpRequestExecutor) {
        this.A06 = r5;
        this.A05 = r6;
        this.A02 = r7;
        this.A08 = oculusHttpRequestExecutor;
        String A052 = AnonymousClass006.A05("rupload.", "facebook.com");
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.encodedAuthority(A052);
        builder.appendPath(this.A05.A01.getUriPathElement());
        builder.appendPath(this.A06.A02);
        Map<String, String> map = r6.A04;
        if (map != null && map.containsKey("Stream-Id")) {
            builder.appendQueryParameter("segmented", "true");
            builder.appendQueryParameter("phase", "transfer");
        }
        try {
            this.A04 = new URI(builder.build().toString());
        } catch (URISyntaxException unused) {
            this.A04 = null;
        }
        this.A09 = null;
        this.A00 = 0;
        this.A03 = new AnonymousClass1kI();
    }
}
