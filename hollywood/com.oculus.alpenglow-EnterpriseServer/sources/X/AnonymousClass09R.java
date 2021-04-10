package X;

import java.util.Comparator;

/* renamed from: X.09R  reason: invalid class name */
public class AnonymousClass09R implements Comparator<byte[]> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // java.util.Comparator
    public final int compare(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int length = bArr3.length;
        int length2 = bArr4.length;
        byte b = length2;
        if (length == length2) {
            for (int i = 0; i < length; i++) {
                byte b2 = bArr3[i];
                byte b3 = bArr4[i];
                if (b2 != b3) {
                    length = b2;
                    b = b3;
                }
            }
            return 0;
        }
        return length - b;
    }
}
