package defpackage;

import android.content.Context;
import android.content.Intent;

/* renamed from: oo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4121oo {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f10577a;
    public final /* synthetic */ C5653xo b;

    public C4121oo(C5653xo xoVar, Context context) {
        this.b = xoVar;
        this.f10577a = context;
    }

    public AbstractC2074cp a(Intent intent, int i, C4633ro roVar, String str) {
        Context context = this.f10577a;
        C5653xo xoVar = this.b;
        return new ServiceConnectionC2244dp(context, intent, i, xoVar.e, xoVar.f, roVar, str);
    }
}
