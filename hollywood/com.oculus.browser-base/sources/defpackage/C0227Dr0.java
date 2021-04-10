package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: Dr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0227Dr0 extends Y11 {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f7916a;
    public final Uk1 b;
    public final AbstractC5717y9 c;
    public final Handler d;
    public final Vr1 e;
    public final Z11 f;

    public C0227Dr0(Activity activity, Uk1 uk1, AbstractC5717y9 y9Var, Z11 z11) {
        Handler handler = new Handler();
        this.d = handler;
        this.f7916a = activity;
        this.b = uk1;
        this.c = y9Var;
        this.e = new Vr1(activity, handler, new C0044Ar0());
        this.f = z11;
        z11.f9314a.H.add(this);
    }

    @Override // defpackage.Y11
    public void h() {
        Objects.requireNonNull(C3618lr0.b());
        if (NU0.f8549a.d("Chrome.OfflineIndicatorV2.HasPersistentOfflineContent", false) && this.b.k().isShown()) {
            Vr1 vr1 = this.e;
            Resources resources = this.f7916a.getResources();
            View k = this.b.k();
            RunnableC0105Br0 br0 = new RunnableC0105Br0(this);
            vr1.a(new XY("IPH_DownloadIndicator", resources.getString(R.string.f53240_resource_name_obfuscated_RES_2131952641), resources.getString(R.string.f53220_resource_name_obfuscated_RES_2131952639), true, false, true, k, new RunnableC0166Cr0(this), br0, new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
        }
    }
}
