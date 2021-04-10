package defpackage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import com.oculus.browser.R;

/* renamed from: Ns  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0837Ns extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1569Zs f8583a;

    public C0837Ns(C1569Zs zs) {
        this.f8583a = zs;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1569Zs zs = this.f8583a;
        View view = (View) obj;
        C2189dU0 du0 = zs.e;
        ((ClipboardManager) zs.f9379a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(du0.b, du0.d));
        C1184Ti1.a(zs.f9379a, R.string.f54130_resource_name_obfuscated_RES_2131952730, 0).b.show();
    }
}
