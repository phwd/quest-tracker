package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: SY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class SY0 implements AbstractC1678aa {
    @Override // defpackage.AbstractC1678aa
    public void a(int i) {
        Integer num;
        if (i == 1) {
            C3762mi0 mi0 = C3762mi0.f10441a;
            Objects.requireNonNull(mi0);
            Object obj = ThreadUtils.f10596a;
            if (!mi0.e) {
                mi0.e = true;
                if (!mi0.d && (num = (Integer) mi0.f.get()) != null) {
                    mi0.d(num.intValue());
                }
            }
        } else if (i == 3) {
            C3762mi0 mi02 = C3762mi0.f10441a;
            Objects.requireNonNull(mi02);
            Object obj2 = ThreadUtils.f10596a;
            if (mi02.e) {
                mi02.e = false;
            }
        }
    }
}
