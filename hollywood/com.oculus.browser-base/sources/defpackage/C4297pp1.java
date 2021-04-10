package defpackage;

import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: pp1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4297pp1 extends RK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C4978tp1 f11089a;

    public C4297pp1(C4978tp1 tp1) {
        this.f11089a = tp1;
    }

    @Override // defpackage.RK
    public void a(List list, List list2, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Tab tab = (Tab) list.get(i);
            arrayList.add(new C4808sp1(this.f11089a, tab, ((Integer) list2.get(i)).intValue(), z ? ((Tab) list.get(0)).getId() : tab.getId()));
        }
        C4978tp1 tp1 = this.f11089a;
        Objects.requireNonNull(tp1);
        String format = String.format(Locale.getDefault(), "%d", Integer.valueOf(arrayList.size()));
        View$OnClickListenerC5098uY0 U = tp1.c.U();
        C4076oY0 c = C4076oY0.c(format, tp1, 0, 32);
        c.c = tp1.f11372a.getString(R.string.f63970_resource_name_obfuscated_RES_2131953714);
        c.d = tp1.f11372a.getString(R.string.f63930_resource_name_obfuscated_RES_2131953710);
        c.e = arrayList;
        U.l(c);
    }
}
