package defpackage;

import android.os.SystemClock;
import org.chromium.base.ThreadUtils;

/* renamed from: Kv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Kv1 extends AbstractC0823Nl {
    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Object obj2 = ThreadUtils.f10596a;
        Lv1.c = false;
        Lv1.f8448a = (Jv1) obj;
        Lv1.b = SystemClock.elapsedRealtime();
    }
}
