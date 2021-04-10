package defpackage;

import android.os.Bundle;

/* renamed from: oL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4050oL0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4221pL0 f10543a;

    public C4050oL0(C4221pL0 pl0) {
        this.f10543a = pl0;
    }

    public void a(String str, Bundle bundle) {
        String str2 = "Error: " + str + ", data: " + bundle;
    }

    public void b(Bundle bundle) {
        this.f10543a.g = bundle.getString("groupableTitle");
        this.f10543a.h = bundle.getString("transferableTitle");
    }
}
