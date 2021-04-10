package defpackage;

import android.view.View;
import java.util.Comparator;

/* renamed from: Gu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Gu1 implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        Bu1 bu1 = (Bu1) ((View) obj).getLayoutParams();
        Bu1 bu12 = (Bu1) ((View) obj2).getLayoutParams();
        boolean z = bu1.f7770a;
        if (z != bu12.f7770a) {
            return z ? 1 : -1;
        }
        return bu1.e - bu12.e;
    }
}
