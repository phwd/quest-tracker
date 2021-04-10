package defpackage;

import android.content.ComponentName;
import android.os.Bundle;

/* renamed from: jo1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3268jo1 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC4419qZ f10238a;
    public final ComponentName b;

    public C3268jo1(AbstractC4419qZ qZVar, ComponentName componentName) {
        this.f10238a = qZVar;
        this.b = componentName;
    }

    public Bundle a(String str, Bundle bundle, Tn1 tn1) {
        BinderC2927ho1 ho1 = null;
        BinderC2927ho1 ho12 = tn1 == null ? null : new BinderC2927ho1(tn1);
        if (ho12 != null) {
            ho1 = ho12;
        }
        return ((C4077oZ) this.f10238a).f(str, bundle, ho1);
    }
}
