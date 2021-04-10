package defpackage;

import android.util.Pair;
import org.chromium.base.Callback;

/* renamed from: WG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class WG implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9137a;

    public WG(Callback callback) {
        this.f9137a = callback;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        Callback callback = this.f9137a;
        boolean z = false;
        if (iArr.length > 0 && iArr[0] == 0) {
            z = true;
        }
        callback.onResult(Pair.create(Boolean.valueOf(z), null));
    }
}
