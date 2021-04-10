package defpackage;

import android.content.Context;

/* renamed from: Ng  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0810Ng {

    /* renamed from: a  reason: collision with root package name */
    public final C5361w31 f8565a;
    public final Runnable b;
    public final String c;

    public C0810Ng(C5361w31 w31, String str, Runnable runnable) {
        this.f8565a = w31;
        this.c = str;
        this.b = runnable;
    }

    public C0810Ng(Context context, C5361w31 w31, int i, Runnable runnable) {
        String string = context.getResources().getString(i);
        this.f8565a = w31;
        this.c = string;
        this.b = runnable;
    }
}
