package defpackage;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

/* renamed from: aK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1647aK0 {

    /* renamed from: a  reason: collision with root package name */
    public final Vr1 f9424a;
    public final AbstractC5717y9 b;
    public final View c;

    public C1647aK0(Activity activity, View view, AbstractC5717y9 y9Var) {
        this.c = view;
        this.b = y9Var;
        this.f9424a = new Vr1(activity, new Handler(Looper.getMainLooper()), new VJ0());
    }
}
