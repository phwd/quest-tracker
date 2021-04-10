package defpackage;

import java.util.Iterator;
import org.chromium.base.MemoryPressureListener;

/* renamed from: ji0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3249ji0 implements AbstractC2737gi0 {
    @Override // defpackage.AbstractC2737gi0
    public void a(int i) {
        Iterator it = MemoryPressureListener.f10590a.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2737gi0) uq0.next()).a(i);
            } else {
                return;
            }
        }
    }
}
