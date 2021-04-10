package X;

import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1tr  reason: invalid class name */
public final class AnonymousClass1tr {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 0;
    public ArrayList<String> A03 = new ArrayList<>();
    public ArrayList<Object> A04 = new ArrayList<>();
    @Nullable
    public List<Integer> A05;
    @Nullable
    public List<String> A06;
    public byte[] A07 = new byte[20];
    public double[] A08 = new double[15];
    public long[] A09 = new long[15];

    public static void A00(AnonymousClass1tr r5, byte b) {
        int i = r5.A02;
        byte[] bArr = r5.A07;
        int length = bArr.length;
        if (i == length) {
            bArr = Arrays.copyOf(bArr, (int) (((double) length) * 1.4d));
            r5.A07 = bArr;
        }
        int i2 = r5.A02;
        r5.A02 = i2 + 1;
        bArr[i2] = b;
    }

    public static void A01(AnonymousClass1tr r5, long j) {
        int i = r5.A01;
        long[] jArr = r5.A09;
        int length = jArr.length;
        if (i == length) {
            jArr = Arrays.copyOf(jArr, (int) (((double) length) * 1.4d));
            r5.A09 = jArr;
        }
        int i2 = r5.A01;
        r5.A01 = i2 + 1;
        jArr[i2] = j;
    }
}
