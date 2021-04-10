package defpackage;

import android.content.Context;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: u9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5037u9 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11391a;
    public final AbstractC0025Ai0 b;
    public final AbstractC5207v9 c;
    public B9 d;
    public C5887z9 e;

    public C5037u9(Context context, M2 m2, AbstractC0025Ai0 ai0, AbstractC5207v9 v9Var, View view, View view2) {
        this.f11391a = context;
        this.b = ai0;
        this.c = v9Var;
        B9 m = v9Var.m();
        this.d = m;
        Objects.requireNonNull((F9) m);
        this.e = new C5887z9(m, v9Var, (CachedFeatureFlags.isEnabled("TabbedAppOverflowMenuRegroup") || F9.m()) ? R.menu.f42440_resource_name_obfuscated_RES_2131689478 : R.menu.f42430_resource_name_obfuscated_RES_2131689477, view, m2, view2);
    }
}
