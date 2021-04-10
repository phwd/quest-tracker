package defpackage;

import android.view.ViewGroup;
import android.widget.Switch;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;

/* renamed from: H00  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H00 {

    /* renamed from: a  reason: collision with root package name */
    public UH0 f8128a;
    public AbstractC0124Ca1 b;
    public AbstractC0612Ka1 c;

    public H00(ViewGroup viewGroup, AbstractC0124Ca1 ca1) {
        this.b = ca1;
        Switch r6 = (Switch) viewGroup.findViewById(R.id.incognito_switch);
        Map c2 = UH0.c(I00.d);
        TH0 th0 = I00.c;
        F00 f00 = new F00(this);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = f00;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(th0, lh0);
        QH0 qh0 = I00.b;
        boolean r = ((AbstractC0246Ea1) this.b).r();
        GH0 gh0 = new GH0(null);
        gh0.f8081a = r;
        hashMap.put(qh0, gh0);
        QH0 qh02 = I00.f8193a;
        boolean z = !AbstractC2793h01.c.c() && !AbstractC2793h01.d.c();
        GH0 gh02 = new GH0(null);
        gh02.f8081a = z;
        hashMap.put(qh02, gh02);
        this.f8128a = new UH0(c2, null);
        G00 g00 = new G00(this);
        this.c = g00;
        ((AbstractC0246Ea1) this.b).c(g00);
        ZH0.a(this.f8128a, r6, new E00());
    }
}
