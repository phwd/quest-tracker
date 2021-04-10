package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class IN {
    public int A00 = 0;
    public int A01 = 0;
    public int A02 = 0;
    public ArrayList A03 = new ArrayList();
    public ArrayList A04 = new ArrayList();
    public List A05;
    public List A06;
    public byte[] A07 = new byte[20];
    public double[] A08 = new double[15];
    public long[] A09 = new long[15];

    public static void A00(IN in, byte b) {
        int i = in.A02;
        byte[] bArr = in.A07;
        int length = bArr.length;
        if (i == length) {
            bArr = Arrays.copyOf(bArr, (int) (((double) length) * 1.4d));
            in.A07 = bArr;
        }
        int i2 = in.A02;
        in.A02 = i2 + 1;
        bArr[i2] = b;
    }

    public static void A01(IN in, long j) {
        int i = in.A01;
        long[] jArr = in.A09;
        int length = jArr.length;
        if (i == length) {
            jArr = Arrays.copyOf(jArr, (int) (((double) length) * 1.4d));
            in.A09 = jArr;
        }
        int i2 = in.A01;
        in.A01 = i2 + 1;
        jArr[i2] = j;
    }
}
