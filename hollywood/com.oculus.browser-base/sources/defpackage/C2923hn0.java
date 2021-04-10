package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: hn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2923hn0 {

    /* renamed from: a  reason: collision with root package name */
    public final C1558Zm0 f10100a;
    public final C3542lO b = new C3542lO();
    public final KN0 c;
    public final int d;
    public final C4935tb0 e;
    public final Drawable f;
    public final Drawable g;
    public final String h;
    public final Profile i;
    public C0948Pm0 j;

    public C2923hn0(Context context, C4935tb0 tb0, Profile profile, C1558Zm0 zm0) {
        this.e = tb0;
        this.f10100a = zm0;
        this.i = profile;
        this.c = AbstractC4055oO.a(context.getResources());
        this.d = context.getResources().getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);
        this.f = C0636Ki1.b(context, R.drawable.f30680_resource_name_obfuscated_RES_2131231108, R.color.f11220_resource_name_obfuscated_RES_2131099812);
        this.g = C0636Ki1.b(context, R.drawable.f29770_resource_name_obfuscated_RES_2131231017, R.color.f11220_resource_name_obfuscated_RES_2131099812);
        this.h = context.getResources().getString(R.string.f54690_resource_name_obfuscated_RES_2131952786);
    }
}
