package defpackage;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsSessionToken;

/* renamed from: gv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2775gv implements AbstractC5159ut0 {

    /* renamed from: a  reason: collision with root package name */
    public final C3287jv f10031a;
    public final C4649rt0 b;
    public final CustomTabsSessionToken c;
    public final int d;
    public final boolean e;
    public final C3116iv f;

    public C2775gv(C3287jv jvVar, C4649rt0 rt0, CustomTabsSessionToken customTabsSessionToken, int i, boolean z, C3116iv ivVar) {
        this.f10031a = jvVar;
        this.b = rt0;
        this.c = customTabsSessionToken;
        this.d = i;
        this.e = z;
        this.f = ivVar;
    }

    @Override // defpackage.AbstractC5159ut0
    public void a(String str, C4649rt0 rt0, boolean z, Boolean bool) {
        C3287jv jvVar = this.f10031a;
        C4649rt0 rt02 = this.b;
        CustomTabsSessionToken customTabsSessionToken = this.c;
        int i = this.d;
        boolean z2 = this.e;
        C3116iv ivVar = this.f;
        C5216vC b2 = jvVar.b(customTabsSessionToken);
        if (b2 != null) {
            Bundle bundle = null;
            if (z && bool != null) {
                bundle = new Bundle();
                bundle.putBoolean("online", bool.booleanValue());
            }
            try {
                ((C5607xY) b2.f11465a.f9465a).e0(i, rt02.f11230a, z, bundle);
            } catch (RemoteException unused) {
                Log.e("CustomTabsSessionToken", "RemoteException during ICustomTabsCallback transaction");
            }
        }
        if (z2) {
            ivVar.d.a(str, rt0, z, bool);
        }
    }
}
