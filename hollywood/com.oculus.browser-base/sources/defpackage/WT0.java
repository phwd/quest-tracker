package defpackage;

import android.text.TextUtils;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;

/* renamed from: WT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WT0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public Callback f9148a;

    public WT0(Callback callback) {
        this.f9148a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        String str = (String) obj;
        if (TextUtils.isEmpty(str)) {
            this.f9148a.onResult(null);
            return;
        }
        VT0 vt0 = new VT0(this, str);
        Executor executor = AbstractC2032cb.f9616a;
        vt0.f();
        ((ExecutorC1463Ya) executor).execute(vt0.e);
    }
}
