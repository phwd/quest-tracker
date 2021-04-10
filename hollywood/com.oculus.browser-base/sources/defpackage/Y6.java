package defpackage;

import android.content.Context;
import android.os.Looper;

/* renamed from: Y6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Y6 extends AbstractC1958c7 {
    public AbstractC2129d7 a(Context context, Looper looper, C3800mv mvVar, Object obj, AbstractC0482Hx hx, AbstractC0777Ms0 ms0) {
        throw new UnsupportedOperationException("buildClient must be implemented");
    }

    @Deprecated
    public AbstractC2129d7 b(Context context, Looper looper, C3800mv mvVar, Object obj, WV wv, XV xv) {
        return a(context, looper, mvVar, obj, wv, xv);
    }
}
