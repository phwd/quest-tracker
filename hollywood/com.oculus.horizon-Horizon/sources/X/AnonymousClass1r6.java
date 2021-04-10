package X;

import android.util.SparseIntArray;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.squareup.okhttp.internal.framed.Http2;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.1r6  reason: invalid class name */
public final class AnonymousClass1r6 {
    public final AbstractC10671uj A00;
    public final C10471su A01 = AnonymousClass1s7.A00();
    public final C10471su A02;
    public final C10471su A03;
    public final C10471su A04;
    public final AbstractC10691uo A05 = AnonymousClass1uQ.A00();
    public final AbstractC10691uo A06;
    public final AbstractC10691uo A07;

    public AnonymousClass1r6() {
        AnonymousClass1uS r0;
        int i;
        int i2;
        AnonymousClass1zo.A00();
        int i3 = AnonymousClass1k7.A00;
        int i4 = i3 * 4194304;
        int i5 = 131072;
        SparseIntArray sparseIntArray = new SparseIntArray();
        do {
            sparseIntArray.put(i5, i3);
            i5 <<= 1;
        } while (i5 <= 4194304);
        this.A02 = new C10471su(4194304, i4, sparseIntArray, i3);
        synchronized (AnonymousClass1uS.class) {
            r0 = AnonymousClass1uS.A00;
            if (r0 == null) {
                r0 = new AnonymousClass1uS();
                AnonymousClass1uS.A00 = r0;
            }
        }
        this.A00 = r0;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1024, 5);
        sparseIntArray2.put(2048, 5);
        sparseIntArray2.put(4096, 5);
        sparseIntArray2.put(8192, 5);
        sparseIntArray2.put(Http2.INITIAL_MAX_FRAME_SIZE, 5);
        sparseIntArray2.put(32768, 5);
        sparseIntArray2.put(65536, 5);
        sparseIntArray2.put(131072, 5);
        sparseIntArray2.put(262144, 2);
        sparseIntArray2.put(ApkUpdateInfoContract.STATE_TYPE_CANCELABLE, 2);
        sparseIntArray2.put(ApkUpdateInfoContract.UPDATE_TYPE_FULL, 2);
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            i = 3145728;
        } else {
            i = 12582912;
            if (min < 33554432) {
                i = 6291456;
            }
        }
        int min2 = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min2 < 16777216) {
            i2 = min2 >> 1;
        } else {
            i2 = (min2 >> 2) * 3;
        }
        this.A03 = new C10471su(i, i2, sparseIntArray2, -1);
        this.A06 = AnonymousClass1uQ.A00();
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray3.put(Http2.INITIAL_MAX_FRAME_SIZE, 5);
        this.A04 = new C10471su(81920, ApkUpdateInfoContract.UPDATE_TYPE_FULL, sparseIntArray3, -1);
        this.A07 = AnonymousClass1uQ.A00();
        AnonymousClass1zo.A00();
    }
}
