package defpackage;

import android.util.Pair;
import org.chromium.base.Callback;

/* renamed from: VG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class VG extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f9073a;

    public VG(Callback callback) {
        this.f9073a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Callback callback = this.f9073a;
        Pair pair = (Pair) obj;
        boolean booleanValue = ((Boolean) pair.first).booleanValue();
        String str = (String) pair.second;
        if (booleanValue || str == null) {
            callback.onResult(Boolean.valueOf(booleanValue));
        } else {
            callback.onResult(Boolean.FALSE);
        }
    }
}
