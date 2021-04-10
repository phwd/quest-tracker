package defpackage;

import android.net.Uri;
import org.chromium.ui.base.Clipboard;

/* renamed from: pv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4313pv extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Clipboard f11099a;

    public C4313pv(Clipboard clipboard) {
        this.f11099a = clipboard;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f11099a.d((Uri) obj);
    }
}
