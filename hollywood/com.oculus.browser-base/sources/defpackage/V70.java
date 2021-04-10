package defpackage;

import android.net.Uri;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: V70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class V70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final W70 f9066a;
    public final Callback b;

    public V70(W70 w70, Callback callback) {
        this.f9066a = w70;
        this.b = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        W70 w70 = this.f9066a;
        Uri uri = (Uri) obj;
        Objects.requireNonNull(w70.f9130a);
        Objects.requireNonNull(w70.b);
    }
}
