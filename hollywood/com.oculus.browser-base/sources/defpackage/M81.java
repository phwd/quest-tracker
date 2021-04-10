package defpackage;

import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

/* renamed from: M81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M81 extends E81 {
    public ArrayList d;
    public int e = 0;

    public M81(TabLayout tabLayout, ArrayList arrayList) {
        super(tabLayout);
        this.d = arrayList;
    }

    @Override // defpackage.Du1, defpackage.E81
    public void c(int i) {
        this.e = i;
        super.c(i);
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            if (this.e == i2) {
                ((ZI0) this.d.get(i2)).c();
            } else {
                ((ZI0) this.d.get(i2)).d();
            }
        }
    }
}
