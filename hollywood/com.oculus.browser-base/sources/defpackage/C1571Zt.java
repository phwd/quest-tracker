package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* renamed from: Zt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1571Zt extends VK {
    public final /* synthetic */ AbstractActivityC2601fu F;

    public C1571Zt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void b() {
        int i;
        AbstractActivityC2601fu fuVar = this.F;
        if (fuVar.w1) {
            TabModel l = fuVar.l1.l(false);
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            int i2 = 0;
            for (int i3 = 0; i3 < l.getCount(); i3++) {
                Tab tabAt = l.getTabAt(i3);
                String s = tabAt.s();
                if (hashMap2.containsKey(s)) {
                    i2++;
                    i = ((Integer) hashMap2.get(s)).intValue();
                } else {
                    i = 0;
                }
                hashMap2.put(s, Integer.valueOf(i + 1));
                int i4 = C5383wB.q(tabAt).Q;
                if (!hashMap.containsKey(Integer.valueOf(i4))) {
                    hashMap.put(Integer.valueOf(i4), new ArrayList());
                }
                ((List) hashMap.get(Integer.valueOf(i4))).add(Integer.valueOf(tabAt.getId()));
            }
            int count = l.getCount();
            if (count != 0 && i2 < count) {
                AbstractC3364kK0.d("Tabs.Tasks.DuplicatedTab.DuplicatedTabCount", i2);
                AbstractC3364kK0.g("Tabs.Tasks.DuplicatedTab.DuplicatedTabRatio", (i2 * 100) / count, 101);
            }
            List list = (List) hashMap.get(-1);
            if (list != null) {
                int i5 = 0;
                int i6 = 0;
                for (int i7 = 0; i7 < list.size(); i7++) {
                    int a2 = AbstractC0078Bf1.a(hashMap, ((Integer) list.get(i7)).intValue());
                    if (a2 > 1) {
                        i6 += a2;
                        i5++;
                    }
                }
                int count2 = l.getCount();
                if (count2 != 0) {
                    AbstractC3364kK0.d("Tabs.Tasks.TabGroupCount", i5);
                    AbstractC3364kK0.d("Tabs.Tasks.TabsInGroupCount", i6);
                    double d = (double) count2;
                    AbstractC3364kK0.g("Tabs.Tasks.TabsInGroupRatio", (int) (((((double) i6) * 1.0d) / d) * 100.0d), 101);
                    if (i5 != 0) {
                        AbstractC3364kK0.d("Tabs.Tasks.AverageTabGroupSize", i6 / i5);
                    }
                    AbstractC3364kK0.g("Tabs.Tasks.TabGroupDensity", (int) (((((double) i5) * 1.0d) / d) * 100.0d), 101);
                }
            }
        }
    }
}
