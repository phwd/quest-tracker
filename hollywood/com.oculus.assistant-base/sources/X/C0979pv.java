package X;

import android.os.Process;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.TraceDirect;

/* renamed from: X.pv  reason: case insensitive filesystem */
public final class C0979pv implements AbstractC0224Kv {
    @Override // X.AbstractC0224Kv
    public final void A2A(long j, String str, C0222Kt kt) {
        if (Systrace.A03(j)) {
            String[] strArr = kt.A01;
            int i = kt.A00;
            if (!Systrace.A03(j)) {
                return;
            }
            if (TraceDirect.checkNative()) {
                TraceDirect.nativeBeginSectionWithArgs(str, strArr, i);
                return;
            }
            L3 l3 = new L3('B');
            int myPid = Process.myPid();
            StringBuilder sb = l3.A00;
            sb.append('|');
            sb.append(myPid);
            l3.A00(str);
            l3.A01(strArr, i);
            L4.A00(l3.toString());
        }
    }
}
