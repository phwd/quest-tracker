package defpackage;

import org.chromium.base.ContextUtils;

/* renamed from: O51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O51 {

    /* renamed from: a  reason: collision with root package name */
    public static O51 f8599a;
    public final C1322Vq0 b = new C1322Vq0();
    public boolean c;

    public O51() {
        a();
    }

    public final void a() {
        this.c = (ContextUtils.getApplicationContext().getResources().getConfiguration().uiMode & 48) == 32;
    }
}
