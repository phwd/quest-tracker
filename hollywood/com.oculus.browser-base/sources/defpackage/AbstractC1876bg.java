package defpackage;

import android.content.Context;

/* renamed from: bg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1876bg {

    /* renamed from: a  reason: collision with root package name */
    public Context f9554a;

    public AbstractC1876bg(Context context) {
        if (context != null) {
            this.f9554a = context;
            return;
        }
        throw new IllegalArgumentException("Application context cannot be null");
    }
}
