package defpackage;

import android.app.Activity;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Jd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0560Jd1 implements Z9 {
    public WD F;
    public final C1280Va G;
    public List H = new ArrayList();
    public Map I = new HashMap();

    public C0560Jd1(WD wd, C1280Va va) {
        this.F = wd;
        this.G = va;
        ApplicationStatus.g.b(this);
        for (int i = 0; i < 3; i++) {
            this.H.add(null);
        }
    }

    public Tab a(int i) {
        Tab o;
        for (int i2 = 0; i2 < this.H.size(); i2++) {
            AbstractC0124Ca1 ca1 = (AbstractC0124Ca1) this.H.get(i2);
            if (!(ca1 == null || (o = ((AbstractC0246Ea1) ca1).o(i)) == null)) {
                return o;
            }
        }
        if (this.G.b(i)) {
            return ((AbstractC1097Sa) this.G.f9093a.get(i)).m();
        }
        return null;
    }

    public AbstractC0124Ca1 b(Context context, AbstractC3226ja1 ja1, AbstractC3780mo0 mo0, int i) {
        if (this.I.get(ja1) != null) {
            return (AbstractC0124Ca1) this.I.get(ja1);
        }
        if (i < 0 || i >= this.H.size()) {
            i = 0;
        }
        if (this.H.get(i) != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.H.size()) {
                    break;
                } else if (this.H.get(i2) == null) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (this.H.get(i) != null) {
            return null;
        }
        Objects.requireNonNull(this.F);
        C0551Ja1 ja12 = new C0551Ja1(null, ja1, new C1169Td1(i, false), new C4136ot(), mo0, AbstractC1341Wa.f9155a, true, true, false);
        this.H.set(i, ja12);
        this.I.put(ja1, ja12);
        return ja12;
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        int indexOf;
        if (i == 6 && this.I.containsKey(activity) && (indexOf = this.H.indexOf(this.I.remove(activity))) >= 0) {
            this.H.set(indexOf, null);
        }
    }
}
