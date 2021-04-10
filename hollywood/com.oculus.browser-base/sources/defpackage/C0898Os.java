package defpackage;

import android.net.Uri;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.ui.base.Clipboard;

/* renamed from: Os  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0898Os extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1569Zs f8654a;

    public C0898Os(C1569Zs zs) {
        this.f8654a = zs;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1569Zs zs = this.f8654a;
        View view = (View) obj;
        if (!zs.e.f.isEmpty()) {
            Clipboard.getInstance().d((Uri) zs.e.f.get(0));
            C1184Ti1.a(zs.f9379a, R.string.f52870_resource_name_obfuscated_RES_2131952604, 0).b.show();
        }
    }
}
