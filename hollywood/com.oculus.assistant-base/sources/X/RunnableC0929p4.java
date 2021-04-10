package X;

import com.facebook.common.util.TriState;
import com.oculus.aidl.OVRServiceInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: X.p4  reason: case insensitive filesystem */
public final class RunnableC0929p4 implements Ii, Runnable {
    public static final String __redex_internal_original_name = "com.facebook.quicklog.QuickEventImpl";
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    public int A06;
    public long A07;
    public long A08;
    public long A09;
    public long A0A;
    public TriState A0B;
    public IT A0C;
    public IW A0D;
    public Ix A0E;
    public Ix A0F;
    public short A0G;
    public boolean A0H;
    public boolean A0I;
    public boolean A0J;
    public Ic A0K;
    public final IN A0L = new IN();
    public final ArrayList A0M = new ArrayList();

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A00(long r6, java.util.concurrent.TimeUnit r8, int r9, java.lang.String r10, X.Ie r11, X.Ix r12) {
        /*
        // Method dump skipped, instructions count: 159
        */
        throw new UnsupportedOperationException("Method not decompiled: X.RunnableC0929p4.A00(long, java.util.concurrent.TimeUnit, int, java.lang.String, X.Ie, X.Ix):void");
    }

    @Override // X.Ii
    public final int A2N() {
        return (int) TimeUnit.NANOSECONDS.toMillis(this.A07);
    }

    @Override // X.Ii
    public final List A2R() {
        int i;
        Object obj;
        int i2;
        IN in = this.A0L;
        List list = in.A06;
        if (list != null) {
            return list;
        }
        int i3 = in.A02;
        if (i3 == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(i3 << 1);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i4 < in.A02) {
            byte b = in.A07[i4];
            switch (b) {
                case 1:
                    i = i5 + 1;
                    obj = in.A04.get(i5);
                    continue;
                    arrayList.add(in.A03.get(i4));
                    arrayList.add(obj);
                    i4++;
                    i5 = i;
                case 2:
                    i2 = i6 + 1;
                    obj = Integer.toString((int) in.A09[i6]);
                    break;
                case 3:
                    i2 = i6 + 1;
                    obj = Long.toString(in.A09[i6]);
                    break;
                case 4:
                    i = i5 + 1;
                    obj = IM.A03((String[]) in.A04.get(i5));
                    continue;
                    arrayList.add(in.A03.get(i4));
                    arrayList.add(obj);
                    i4++;
                    i5 = i;
                case 5:
                    i = i5 + 1;
                    obj = IM.A01((int[]) in.A04.get(i5));
                    continue;
                    arrayList.add(in.A03.get(i4));
                    arrayList.add(obj);
                    i4++;
                    i5 = i;
                case 6:
                    obj = Double.toString(in.A08[i7]);
                    i = i5;
                    i7++;
                    continue;
                    arrayList.add(in.A03.get(i4));
                    arrayList.add(obj);
                    i4++;
                    i5 = i;
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                    i = i5 + 1;
                    obj = IM.A00((double[]) in.A04.get(i5));
                    continue;
                    arrayList.add(in.A03.get(i4));
                    arrayList.add(obj);
                    i4++;
                    i5 = i;
                case 8:
                    i2 = i6 + 1;
                    boolean z = false;
                    if (in.A09[i6] != 0) {
                        z = true;
                    }
                    obj = Boolean.toString(z);
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                    i = i5 + 1;
                    obj = IM.A04((boolean[]) in.A04.get(i5));
                    continue;
                    arrayList.add(in.A03.get(i4));
                    arrayList.add(obj);
                    i4++;
                    i5 = i;
                case 10:
                    i = i5 + 1;
                    obj = IM.A02((long[]) in.A04.get(i5));
                    continue;
                    arrayList.add(in.A03.get(i4));
                    arrayList.add(obj);
                    i4++;
                    i5 = i;
                default:
                    throw new UnsupportedOperationException(AnonymousClass08.A00("Unsupported type ", b));
            }
            i = i5;
            i6 = i2;
            arrayList.add(in.A03.get(i4));
            arrayList.add(obj);
            i4++;
            i5 = i;
        }
        in.A06 = arrayList;
        return arrayList;
    }

    @Override // X.Ii
    public final List A2S() {
        IN in = this.A0L;
        List list = in.A05;
        if (list != null) {
            return list;
        }
        int i = in.A02;
        if (i == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(Integer.valueOf(in.A07[i2]));
        }
        in.A05 = arrayList;
        return arrayList;
    }

    @Override // X.Ii
    public final boolean A2T() {
        return this.A0B.asBoolean(false);
    }

    @Override // X.Ii
    public final int A2d() {
        if ((this.A00 & 2) > 0) {
            return 2;
        }
        return 1;
    }

    @Override // X.Ii
    public final Ic A2f() {
        Ic ic = this.A0K;
        if (ic != null) {
            return ic;
        }
        Ic ic2 = new Ic();
        this.A0K = ic2;
        return ic2;
    }

    @Override // X.Ii
    public final long A2i() {
        return TimeUnit.NANOSECONDS.toMillis(this.A09);
    }

    @Override // X.Ii
    public final String A31() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList = this.A0M;
        int size = arrayList.size();
        for (String str : arrayList) {
            sb.append(str);
            if (size > 1) {
                sb.append(",");
            }
            size--;
        }
        return sb.toString();
    }

    @Override // X.Ii
    public final boolean A3A() {
        if (this.A08 != 0) {
            return true;
        }
        return false;
    }

    @Override // X.Ii
    public final boolean A3P() {
        if ((this.A00 & 1) <= 0) {
            return false;
        }
        return true;
    }

    @Override // X.Ii
    public final boolean A3V() {
        TriState triState = this.A0B;
        if (triState == null || !triState.isSet()) {
            return false;
        }
        return true;
    }

    public final void run() {
        this.A0C.A3l(this);
    }

    @Override // X.Ii
    public final short A2G() {
        return this.A0G;
    }

    @Override // X.Ii
    public final long A2O() {
        return this.A07;
    }

    @Override // X.Ii
    public final int A2Z() {
        return 0;
    }

    @Override // X.Ii
    public final IW A2k() {
        return this.A0D;
    }

    @Override // X.Ii
    public final int A2p() {
        return this.A04;
    }

    @Override // X.Ii
    public final int A2q() {
        return this.A05;
    }

    @Override // X.Ii
    public final String A2z() {
        return null;
    }

    @Override // X.Ii
    public final List A30() {
        return this.A0M;
    }

    @Override // X.Ii
    public final long A32() {
        return this.A0A;
    }

    @Override // X.Ii
    public final long A33() {
        return -1;
    }

    @Override // X.Ii
    public final int A35() {
        return this.A06;
    }

    @Override // X.Ii
    public final String A36() {
        return null;
    }

    @Override // X.Ii
    public final boolean A3O() {
        return this.A0I;
    }

    @Override // X.Ii
    public final boolean A3Z() {
        return false;
    }

    @Override // X.Ii
    public final boolean A3c() {
        return false;
    }

    @Override // X.Ii
    public final int getMarkerId() {
        return this.A02;
    }
}
