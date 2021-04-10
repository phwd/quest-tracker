package X;

import com.facebook.systrace.Systrace;
import com.facebook.systrace.TraceDirect;

/* renamed from: X.pw  reason: case insensitive filesystem */
public final class C0980pw implements AbstractC0224Kv {
    @Override // X.AbstractC0224Kv
    public final void A2A(long j, String str, C0222Kt kt) {
        if (Systrace.A03(j)) {
            String[] strArr = kt.A01;
            int i = kt.A00;
            if (!Systrace.A03(j)) {
                return;
            }
            if (TraceDirect.checkNative()) {
                TraceDirect.nativeEndSectionWithArgs(strArr, i);
                return;
            }
            L3 l3 = new L3('E');
            StringBuilder sb = l3.A00;
            sb.append('|');
            sb.append('|');
            l3.A01(strArr, i);
            L4.A00(l3.toString());
        }
    }
}
