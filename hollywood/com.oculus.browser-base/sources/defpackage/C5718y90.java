package defpackage;

import android.os.Looper;

/* renamed from: y90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5718y90 {

    /* renamed from: a  reason: collision with root package name */
    public final HandlerC5548x90 f11667a;
    public volatile Object b;
    public final C5378w90 c;

    public C5718y90(Looper looper, Object obj, String str) {
        this.f11667a = new HandlerC5548x90(this, looper);
        SE0.i(obj, "Listener must not be null");
        this.b = obj;
        SE0.f(str);
        this.c = new C5378w90(obj, str);
    }
}
