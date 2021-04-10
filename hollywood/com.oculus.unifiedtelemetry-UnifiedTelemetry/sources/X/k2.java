package X;

import com.oculus.unifiedtelemetry.collectors.CollectorsControl;

public final class k2 {
    public final char[][] A00 = new char[AnonymousClass07.A00(4).length][];

    public k2() {
        AnonymousClass07.A00(4);
    }

    public final char[] A00(Integer num, int i) {
        int i2;
        int intValue = num.intValue();
        switch (intValue) {
            case 2:
            case 3:
                i2 = 200;
                break;
            default:
                i2 = CollectorsControl.SCHEDULER_TICK_PERIOD_MSEC;
                break;
        }
        if (i2 > i) {
            i = i2;
        }
        char[][] cArr = this.A00;
        char[] cArr2 = cArr[intValue];
        if (cArr2 == null || cArr2.length < i) {
            return new char[i];
        }
        cArr[intValue] = null;
        return cArr2;
    }
}
