package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.tasks.tab_management.NewTabTileView;

/* renamed from: Qc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0983Qc1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        ViewGroup viewGroup = (ViewGroup) obj2;
        KH0 kh0 = (KH0) obj3;
        NewTabTileView newTabTileView = (NewTabTileView) viewGroup;
        TH0 th0 = AbstractC3609lo0.f10374a;
        if (th0 == kh0) {
            viewGroup.setOnClickListener((View.OnClickListener) uh0.g(th0));
            return;
        }
        RH0 rh0 = AbstractC3609lo0.b;
        if (rh0 == kh0) {
            newTabTileView.F = uh0.e(rh0);
            return;
        }
        SH0 sh0 = AbstractC3609lo0.c;
        if (sh0 == kh0) {
            newTabTileView.G = uh0.f(sh0);
            return;
        }
        QH0 qh0 = AbstractC3609lo0.d;
        if (qh0 == kh0) {
            boolean h = uh0.h(qh0);
            Context context = newTabTileView.getContext();
            int i = h ? R.color.f12750_resource_name_obfuscated_RES_2131099965 : R.color.f12740_resource_name_obfuscated_RES_2131099964;
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            ColorStateList colorStateList = context.getColorStateList(i);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            newTabTileView.setBackgroundTintList(colorStateList);
            newTabTileView.H.setImageTintList(newTabTileView.getContext().getColorStateList(h ? R.color.f14340_resource_name_obfuscated_RES_2131100124 : R.color.f14330_resource_name_obfuscated_RES_2131100123));
        }
    }
}
