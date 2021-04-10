package X;

import android.content.Context;
import com.facebook.graphservice.asset.GraphServiceAsset;
import com.facebook.graphservice.factory.GraphQLServiceFactory;
import com.facebook.graphservice.interfaces.TreeJsonSerializer;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Pj  reason: invalid class name */
public final class AnonymousClass0Pj {
    @GuardedBy("GraphServiceAsset.class")
    public static volatile GraphServiceAsset A00;
    public static volatile GraphQLServiceFactory A01;
    @GuardedBy("GraphQLServiceFactory.class")
    public static volatile GraphQLServiceFactory A02;
    @GuardedBy("TreeJsonSerializer.class")
    public static volatile TreeJsonSerializer A03;

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x019c, code lost:
        if (r1 != 0) goto L_0x01a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x019e, code lost:
        X.AnonymousClass0JA.A02(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01c2, code lost:
        throw new java.io.IOException("Could not create the destination directory");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01c3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01c4, code lost:
        X.AnonymousClass0JA.A02(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01c7, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0128, code lost:
        if (r1.exists() == false) goto L_0x012a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        X.AnonymousClass0JA.A02(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0146, code lost:
        if (r7.mkdirs() == false) goto L_0x01bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0148, code lost:
        r10 = 0;
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x014a, code lost:
        if (r9 >= r11) goto L_0x0189;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x014c, code lost:
        r0 = r12[r9];
        r8 = r0.A00(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r0 = r0.A00;
        X.AnonymousClass0Q1.A00(r0);
        r6 = new java.io.FileOutputStream(r0);
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r1 = r8.read(r14, 0, java.lang.Math.min(Integer.MAX_VALUE - r2, (int) com.squareup.okhttp.internal.framed.Http2.INITIAL_MAX_FRAME_SIZE));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x016b, code lost:
        if (r1 == -1) goto L_0x0174;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x016d, code lost:
        r6.write(r14, 0, r1);
        r2 = r2 + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0171, code lost:
        if (r2 >= Integer.MAX_VALUE) goto L_0x0174;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0174, code lost:
        r6.close();
        r8.close();
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x017d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0182, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0183, code lost:
        if (r8 != null) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0189, code lost:
        r1 = r3.A04;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x018b, code lost:
        if (r1 == null) goto L_0x0196;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x018d, code lost:
        r1.execute(new X.AnonymousClass0J6(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0196, code lost:
        X.AnonymousClass0JA.A00(r3);
        r10 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x019a, code lost:
        r1 = r10 | 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.graphservice.factory.GraphQLServiceFactory A00(@javax.annotation.Nullable android.content.Context r19) {
        /*
        // Method dump skipped, instructions count: 541
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0Pj.A00(android.content.Context):com.facebook.graphservice.factory.GraphQLServiceFactory");
    }

    public static TreeJsonSerializer A01(@Nullable Context context) {
        if (A03 == null) {
            GraphQLServiceFactory A002 = A00(context);
            synchronized (TreeJsonSerializer.class) {
                if (A03 == null) {
                    A03 = A002.newTreeJsonSerializer();
                }
            }
        }
        return A03;
    }
}
