package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Gj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Gj1 implements Runnable {
    public final Oj1 F;

    public Gj1(Oj1 oj1) {
        this.F = oj1;
    }

    public void run() {
        Oj1 oj1 = this.F;
        Objects.requireNonNull(oj1);
        if (Nt1.f8584a.a(3)) {
            Integer valueOf = Integer.valueOf(DownloadUtils.c((Tab) oj1.P.get()) ? F9.h() : R.id.downloads_menu_id);
            Vr1 vr1 = oj1.O;
            Resources resources = oj1.G.getResources();
            vr1.a(new XY(null, resources.getString(R.string.f64420_resource_name_obfuscated_RES_2131953759), resources.getString(R.string.f64420_resource_name_obfuscated_RES_2131953759), true, false, true, oj1.L, new Dj1(oj1), new Cj1(oj1, valueOf), new Rect(0, 0, 0, resources.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627)), 0, null));
        }
    }
}
