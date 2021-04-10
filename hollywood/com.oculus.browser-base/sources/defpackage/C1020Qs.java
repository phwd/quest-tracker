package defpackage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import com.oculus.browser.R;

/* renamed from: Qs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1020Qs extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1569Zs f8790a;

    public C1020Qs(C1569Zs zs) {
        this.f8790a = zs;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1569Zs zs = this.f8790a;
        View view = (View) obj;
        C2189dU0 du0 = zs.e;
        ((ClipboardManager) zs.f9379a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(du0.b, du0.c));
        C1184Ti1.a(zs.f9379a, R.string.f63310_resource_name_obfuscated_RES_2131953648, 0).b.show();
    }
}
