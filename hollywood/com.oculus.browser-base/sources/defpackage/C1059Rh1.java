package defpackage;

import android.content.Context;
import org.chromium.base.Callback;

/* renamed from: Rh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1059Rh1 {

    /* renamed from: a  reason: collision with root package name */
    public final C1547Zh1 f8846a;
    public final C2909hi1 b;

    public C1059Rh1(Context context, C0998Qh1 qh1, Callback callback, SZ sz) {
        C1547Zh1 zh1 = new C1547Zh1();
        this.f8846a = zh1;
        this.b = new C2909hi1(context, zh1);
        new C3592li1(qh1, zh1, callback, sz);
    }
}
