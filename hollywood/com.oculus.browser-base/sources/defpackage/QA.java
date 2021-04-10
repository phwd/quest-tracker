package defpackage;

import android.view.View;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: QA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QA implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        float z = ((View) obj).getZ();
        float z2 = ((View) obj2).getZ();
        if (z > z2) {
            return -1;
        }
        return z < z2 ? 1 : 0;
    }
}
