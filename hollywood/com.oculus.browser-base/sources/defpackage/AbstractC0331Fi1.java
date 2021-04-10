package defpackage;

import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: Fi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0331Fi1 extends ContextWrapper {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8036a = new Object();

    public static Context a(Context context) {
        if (!(context instanceof AbstractC0331Fi1) && !(context.getResources() instanceof AbstractC0453Hi1)) {
            context.getResources();
            int i = Gs1.f8118a;
        }
        return context;
    }
}
