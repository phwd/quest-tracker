package defpackage;

import android.os.Bundle;
import android.text.TextUtils;

/* renamed from: Yn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Yn1 extends Tn1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3268jo1 f9297a;
    public final /* synthetic */ Zn1 b;

    public Yn1(Zn1 zn1, C3268jo1 jo1) {
        this.b = zn1;
        this.f9297a = jo1;
    }

    @Override // defpackage.Tn1
    public void a(String str, Bundle bundle) {
        this.b.f9373a.a(this.f9297a.b, (!TextUtils.equals(str, "checkAndroidLocationPermission") || bundle == null) ? false : bundle.getBoolean("locationPermissionResult"));
    }
}
