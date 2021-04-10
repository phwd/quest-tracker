package defpackage;

import android.app.Activity;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;

/* renamed from: vl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5305vl0 {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f11495a;
    public final int b;
    public final AbstractC5135ul0 c;
    public C1184Ti1 d;

    public C5305vl0(Tab tab, int i, AbstractC5135ul0 ul0) {
        this.f11495a = tab;
        this.b = i;
        this.c = ul0;
    }

    public void a() {
        C1184Ti1 ti1 = this.d;
        if (ti1 != null) {
            ti1.b.cancel();
            this.d = null;
        }
        Activity b2 = AbstractC5112ud1.b(this.f11495a);
        if (b2 == null) {
            AbstractC5135ul0 ul0 = this.c;
            if (ul0 != null) {
                ul0.a(false);
                return;
            }
            return;
        }
        SimpleConfirmInfoBarBuilder.a(this.f11495a.l(), new C4965tl0(this), 88, b2, R.drawable.f30080_resource_name_obfuscated_RES_2131231048, String.format(b2.getString(R.string.f55040_resource_name_obfuscated_RES_2131952821), b2.getResources().getString(this.b)), b2.getString(R.string.f63810_resource_name_obfuscated_RES_2131953698), b2.getString(R.string.f48470_resource_name_obfuscated_RES_2131952164), null, true);
    }

    public void b() {
        Activity b2 = AbstractC5112ud1.b(this.f11495a);
        if (b2 != null) {
            C1184Ti1 b3 = C1184Ti1.b(b2, b2.getString(R.string.f55050_resource_name_obfuscated_RES_2131952822, b2.getString(this.b)), 0);
            this.d = b3;
            b3.b.show();
        }
    }

    public void c() {
        C1184Ti1 ti1 = this.d;
        if (ti1 != null) {
            ti1.b.cancel();
            this.d = null;
        }
        Activity b2 = AbstractC5112ud1.b(this.f11495a);
        if (b2 != null) {
            C1184Ti1.b(b2, b2.getResources().getText(R.string.f55060_resource_name_obfuscated_RES_2131952823), 0).b.show();
        }
    }
}
