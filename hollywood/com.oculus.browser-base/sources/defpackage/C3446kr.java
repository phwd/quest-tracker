package defpackage;

import android.net.Uri;
import java.util.Objects;
import org.chromium.ui.base.Clipboard;

/* renamed from: kr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3446kr extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC2787gz f10307a;

    public C3446kr(AbstractC2787gz gzVar) {
        this.f10307a = gzVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Objects.requireNonNull((C4349q61) this.f10307a);
        Clipboard.getInstance().d((Uri) obj);
    }
}
