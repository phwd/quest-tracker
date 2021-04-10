package defpackage;

import J.N;
import android.content.Context;
import android.view.View;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.translate.TranslateBridge;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* renamed from: L60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class L60 extends H60 implements S60 {
    public final Context S;

    public L60(Context context) {
        super(context);
        this.S = context;
    }

    @Override // defpackage.H60, defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        super.j(xk0, i);
        B60 b60 = (B60) this.L.get(i);
        G60 g60 = (G60) xk0;
        if (b() > 1 && (!this.P.c)) {
            g60.b0.setVisibility(0);
            g60.b0.setImageResource(R.drawable.f29960_resource_name_obfuscated_RES_2131231036);
            g60.b0.setOnTouchListener(new C60(this, g60));
        }
        C4935tb0 tb0 = new C4935tb0();
        if (N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "translate.enabled")) {
            int i2 = N.MeNcRA0y(b60.f7716a) ? 0 : R.drawable.f29730_resource_name_obfuscated_RES_2131231013;
            boolean z = b60.d;
            Map c = UH0.c(Y80.h);
            SH0 sh0 = Y80.f9255a;
            JH0 jh0 = new JH0(null);
            jh0.f8282a = R.string.f53910_resource_name_obfuscated_RES_2131952708;
            HashMap hashMap = (HashMap) c;
            hashMap.put(sh0, jh0);
            SH0 sh02 = Y80.f;
            JH0 jh02 = new JH0(null);
            jh02.f8282a = 0;
            hashMap.put(sh02, jh02);
            SH0 sh03 = Y80.d;
            JH0 jh03 = new JH0(null);
            jh03.f8282a = i2;
            hashMap.put(sh03, jh03);
            QH0 qh0 = Y80.g;
            GH0 gh0 = new GH0(null);
            gh0.f8081a = z;
            hashMap.put(qh0, gh0);
            SH0 sh04 = Y80.e;
            JH0 jh04 = new JH0(null);
            jh04.f8282a = R.color.f11340_resource_name_obfuscated_RES_2131099824;
            hashMap.put(sh04, jh04);
            UH0 uh0 = new UH0(c, null);
            C4765sb0 sb0 = new C4765sb0(1, uh0);
            uh0.l(sh04, R.color.f11230_resource_name_obfuscated_RES_2131099813);
            tb0.q(sb0);
        }
        int b = b();
        tb0.q(new C4765sb0(1, C1237Ug.b(R.string.f60190_resource_name_obfuscated_RES_2131953336, 0, 0, b > 1)));
        if (!(!this.P.c)) {
            if (i > 0) {
                tb0.q(C1237Ug.a(R.string.f54650_resource_name_obfuscated_RES_2131952782, 0, 0));
                tb0.q(C1237Ug.a(R.string.f54660_resource_name_obfuscated_RES_2131952783, 0, 0));
            }
            if (i < b - 1) {
                tb0.q(C1237Ug.a(R.string.f54640_resource_name_obfuscated_RES_2131952781, 0, 0));
            }
        }
        K60 k60 = new K60(this, tb0, new J60(this, b60, i));
        g60.c0.setVisibility(0);
        ListMenuButton listMenuButton = g60.c0;
        listMenuButton.d();
        listMenuButton.M = k60;
        View view = g60.G;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        view.setPaddingRelative(view.getPaddingStart(), g60.G.getPaddingTop(), 0, g60.G.getPaddingBottom());
    }

    public void u() {
        if (!this.P.c) {
            if (this.f9020J == null) {
                TI ti = new TI(this, null);
                this.K = ti;
                this.f9020J = new C5533x40(ti);
            }
            this.f9020J.j(this.M);
        } else {
            C5533x40 x40 = this.f9020J;
            if (x40 != null) {
                x40.j(null);
            }
        }
        T60 a2 = T60.a();
        Objects.requireNonNull(a2);
        List a3 = TranslateBridge.a();
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) a3).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (a2.b.containsKey(str)) {
                arrayList.add((B60) a2.b.get(str));
            }
        }
        t(arrayList);
    }
}
