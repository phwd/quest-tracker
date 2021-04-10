package X;

import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

/* renamed from: X.1Q1  reason: invalid class name */
public abstract class AnonymousClass1Q1 implements AnonymousClass1YG {
    @Nullable
    public AnonymousClass0Uh A00;
    public final Context A01;
    public final AnonymousClass1PM A02;
    public final AnonymousClass1YF A03;
    public final Integer A04;
    public volatile String A05;
    public volatile String A06;

    public final boolean A01(String str) {
        Set<String> unmodifiableSet;
        if (str != null) {
            if (!str.startsWith("https://")) {
                str = AnonymousClass006.A07("https://", str);
            }
            try {
                Uri A002 = C02980kg.A00(str);
                if (!(this instanceof AnonymousClass1PH)) {
                    unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("facebook.com", "workplace.com", "pushnotifs.com")));
                } else {
                    unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList("facebook.com", "workplace.com")));
                }
                for (String str2 : unmodifiableSet) {
                    String host = A002.getHost();
                    if (host != null) {
                        if (!host.equalsIgnoreCase(str2)) {
                            if (host.endsWith(AnonymousClass006.A07(".", str2))) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            } catch (SecurityException unused) {
                return false;
            }
        }
        return true;
    }

    public final void A00() {
        String str;
        AnonymousClass0Uh r1 = this.A00;
        if (r1 != null) {
            try {
                this.A01.unregisterReceiver(r1);
            } catch (IllegalArgumentException e) {
                if (!(this instanceof AnonymousClass1PI)) {
                    str = "WorkConnectionConfigOverrides";
                } else {
                    str = "ZeroRatingConnectionConfigOverrides";
                }
                AnonymousClass0MD.A0D(str, e, "Failed to unregister broadcast receiver");
            }
            this.A00 = null;
        }
    }

    @Override // X.AnonymousClass1YG
    public final String A3O() {
        return this.A05;
    }

    @Override // X.AnonymousClass1YG
    public final String A4T() {
        return this.A06;
    }

    public AnonymousClass1Q1(Context context, AnonymousClass1YF r2, Integer num, @Nullable AnonymousClass1PM r4) {
        this.A01 = context;
        this.A03 = r2;
        this.A04 = num;
        this.A02 = r4;
    }
}
