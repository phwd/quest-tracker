package X;

import android.content.Context;
import com.facebook.msys.mci.ProxyProvider;

/* renamed from: X.1Yu  reason: invalid class name and case insensitive filesystem */
public class C06331Yu {
    public final Context A00;
    public final ProxyProvider A01;
    public final AnonymousClass1O7 A02;
    public final String A03;

    public C06331Yu(Context context, String str, AnonymousClass1O7 r4, ProxyProvider proxyProvider) {
        this.A00 = context;
        this.A02 = r4;
        if (proxyProvider != null) {
            this.A01 = proxyProvider;
            if (str != null) {
                this.A03 = str;
                return;
            }
            throw null;
        }
        throw null;
    }
}
