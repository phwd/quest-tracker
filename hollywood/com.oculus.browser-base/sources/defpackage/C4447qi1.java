package defpackage;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: qi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4447qi1 implements Q31 {
    public final C4276pi1 F = new C4276pi1();
    public final Resources G;
    public final int H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f11157J;

    public C4447qi1(Context context) {
        Resources resources = context.getResources();
        this.G = resources;
        this.H = resources.getDimensionPixelOffset(R.dimen.f26100_resource_name_obfuscated_RES_2131166229);
        this.I = resources.getDimensionPixelOffset(R.dimen.f26030_resource_name_obfuscated_RES_2131166222);
        this.f11157J = resources.getDimensionPixelOffset(R.dimen.f26030_resource_name_obfuscated_RES_2131166222);
        a();
    }

    public void a() {
        int i = this.G.getDisplayMetrics().widthPixels - (this.f11157J * 2);
        int i2 = this.I;
        double d = ((double) (i + i2)) / ((double) (this.H + i2));
        double floor = Math.floor(d) + Math.max(0.3d, Math.min(0.7d, d - Math.floor(d)));
        double floor2 = (((double) (this.G.getDisplayMetrics().widthPixels - (this.f11157J * 2))) - (Math.floor(floor) * ((double) this.I))) / floor;
        Objects.requireNonNull(this.F);
        this.F.f11083a = (int) floor2;
        AbstractC3364kK0.h("Search.QueryTiles.TileWidth", (int) (floor2 / ((double) this.G.getDisplayMetrics().density)), 50, 150, 101);
        AbstractC3364kK0.h("Search.QueryTiles.TilesFitPerRow", (int) floor, 0, 20, 21);
    }

    @Override // defpackage.Q31
    public Object get() {
        return this.F;
    }
}
