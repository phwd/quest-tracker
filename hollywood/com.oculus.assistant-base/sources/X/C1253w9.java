package X;

import com.facebook.papaya.client.ILogSink;
import com.oculus.aidl.OVRServiceInterface;

/* renamed from: X.w9  reason: case insensitive filesystem */
public final class C1253w9 implements ILogSink {
    public WF A00;
    public boolean A01;
    public final String A02 = "assistant_smart_keyboard_executor";

    @Override // com.facebook.papaya.client.ILogSink
    public final String getId() {
        return "assistant_keyboard_fl_falco_sink";
    }

    @Override // com.facebook.papaya.client.ILogSink
    public final void log(long j, long j2, long j3, int i, String str, int i2, String str2) {
    }

    public C1253w9(WF wf) {
        this.A00 = wf;
        this.A01 = false;
    }

    @Override // com.facebook.papaya.client.ILogSink
    public final void event(long j, long j2, long j3, int i, String str) {
        WF wf;
        switch (Gu.fromInt(i).ordinal()) {
            case 0:
                if (this.A01 && str != null) {
                    wf = this.A00;
                    wf.A07 = str;
                    break;
                } else {
                    return;
                }
            case 1:
            case 2:
            case 3:
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
            default:
                return;
            case 4:
                if (str.equals(this.A02)) {
                    this.A01 = true;
                    wf = this.A00;
                    wf.A01 = Long.valueOf(j2);
                    break;
                } else {
                    return;
                }
            case 5:
                if (str.equals(this.A02)) {
                    this.A01 = true;
                    wf = this.A00;
                    wf.A02 = Long.valueOf(j2);
                    break;
                } else {
                    return;
                }
            case 6:
                if (this.A01) {
                    wf = this.A00;
                    wf.A03 = Long.valueOf(j2);
                    break;
                } else {
                    return;
                }
            case 8:
                if (this.A01) {
                    wf = this.A00;
                    wf.A06 = Long.valueOf(j2);
                    break;
                } else {
                    return;
                }
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                if (this.A01) {
                    wf = this.A00;
                    wf.A04 = Long.valueOf(j2);
                    break;
                } else {
                    return;
                }
        }
        this.A00 = wf;
    }
}
