package X;

import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.0mG  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05970mG implements AnonymousClass0X4 {
    @Nullable
    public C00910Hi A00;
    public final AnonymousClass0X3 A01;
    public final Integer A02;
    public final Context A03;
    public final C06510nV A04;
    public volatile String A05;
    public volatile String A06;

    public abstract String A00();

    public abstract String A01();

    public Set<String> A02() {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList("facebook.com", "workplace.com", "pushnotifs.com")));
    }

    public abstract void A05(String str, String str2);

    public final boolean A06(String str) {
        if (str != null) {
            if (!str.startsWith("https://")) {
                str = AnonymousClass006.A05("https://", str);
            }
            try {
                Uri A002 = C03030bw.A00(str);
                for (String str2 : A02()) {
                    String host = A002.getHost();
                    if (host != null && (host.equalsIgnoreCase(str2) || host.endsWith(AnonymousClass006.A05(".", str2)))) {
                        return true;
                    }
                }
                return false;
            } catch (SecurityException unused) {
                return false;
            }
        }
        return true;
    }

    public void A03() {
        if (this.A00 == null) {
            String A002 = A00();
            C00910Hi r4 = new C00910Hi(A002, new C05980mH(this));
            this.A00 = r4;
            this.A03.registerReceiver(r4, new IntentFilter(A002), "com.facebook.permission.prod.FB_APP_COMMUNICATION", null);
        }
    }

    public final void A04() {
        C00910Hi r1 = this.A00;
        if (r1 != null) {
            try {
                this.A03.unregisterReceiver(r1);
            } catch (IllegalArgumentException e) {
                AnonymousClass0NO.A0I(A01(), e, "Failed to unregister broadcast receiver");
            }
            this.A00 = null;
        }
    }

    public AbstractC05970mG(Context context, AnonymousClass0X3 r2, Integer num, @Nullable C06510nV r4) {
        this.A03 = context;
        this.A01 = r2;
        this.A02 = num;
        this.A04 = r4;
    }

    @Override // X.AnonymousClass0X4
    public final String A2y() {
        return this.A05;
    }

    @Override // X.AnonymousClass0X4
    public final String A3v() {
        return this.A06;
    }
}
