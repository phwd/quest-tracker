package X;

import android.os.Environment;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1oA  reason: invalid class name */
public final class AnonymousClass1oA implements AnonymousClass1oV {
    public static final long A05 = TimeUnit.MINUTES.toMillis(30);
    public final AnonymousClass0LA A00;
    public final File A01;
    public final File A02;
    public final C09611oe A03;
    public final boolean A04;

    @Override // X.AnonymousClass1oV
    public final Collection A3N() throws IOException {
        AnonymousClass1oC r1 = new AnonymousClass1oC(this);
        A02(this.A02, r1);
        return Collections.unmodifiableList(r1.A00);
    }

    @Override // X.AnonymousClass1oV
    @Nullable
    public final AnonymousClass1oH A4I(String str, Object obj) {
        String str2 = new C09561oF(".cnt", str).A00;
        File file = new File(AnonymousClass006.A08(A01(this, str2), File.separator, str2, ".cnt"));
        if (!file.exists()) {
            return null;
        }
        file.setLastModified(this.A00.now());
        return new AnonymousClass1oH(file);
    }

    @Override // X.AnonymousClass1oV
    public final AnonymousClass1oM A4r(String str, Object obj) throws IOException {
        String str2 = new C09561oF(".tmp", str).A00;
        File file = new File(A01(this, str2));
        if (!file.exists()) {
            try {
                AnonymousClass1oD.A00(file);
            } catch (AnonymousClass1oI e) {
                throw e;
            }
        }
        return new AnonymousClass1oM(this, str, File.createTempFile(AnonymousClass006.A05(str2, "."), ".tmp", file));
    }

    @Override // X.AnonymousClass1oV
    public final void A7g() {
        A02(this.A01, new AnonymousClass1oB(this));
    }

    @Override // X.AnonymousClass1oV
    public final long A88(AnonymousClass1oE r5) {
        File file = r5.A02.A00;
        if (!file.exists()) {
            return 0;
        }
        long length = file.length();
        if (!file.delete()) {
            return -1;
        }
        return length;
    }

    public AnonymousClass1oA(File file, int i, C09611oe r8) {
        this.A01 = file;
        boolean z = false;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            try {
                z = file.getCanonicalPath().contains(externalStorageDirectory.toString());
            } catch (Exception unused) {
            }
        }
        this.A04 = z;
        this.A02 = new File(this.A01, String.format(null, "%s.ols%d.%d", "v2", 100, Integer.valueOf(i)));
        this.A03 = r8;
        File file2 = this.A01;
        if (file2.exists()) {
            if (!this.A02.exists()) {
                AnonymousClass1gm.A00(file2);
            }
            this.A00 = AnonymousClass0pW.A00;
        }
        try {
            AnonymousClass1oD.A00(this.A02);
        } catch (AnonymousClass1oI unused2) {
        }
        this.A00 = AnonymousClass0pW.A00;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001f, code lost:
        if (r4.equals(r1) != false) goto L_0x0021;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static X.C09561oF A00(X.AnonymousClass1oA r8, java.io.File r9) {
        /*
            java.lang.String r7 = r9.getName()
            r6 = 46
            int r5 = r7.lastIndexOf(r6)
            r3 = 0
            if (r5 <= 0) goto L_0x0054
            java.lang.String r1 = r7.substring(r5)
            java.lang.String r4 = ".cnt"
            boolean r0 = r4.equals(r1)
            if (r0 != 0) goto L_0x0021
            java.lang.String r4 = ".tmp"
            boolean r0 = r4.equals(r1)
            if (r0 == 0) goto L_0x0054
        L_0x0021:
            r2 = 0
            java.lang.String r1 = r7.substring(r2, r5)
            java.lang.String r0 = ".tmp"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0038
            int r0 = r1.lastIndexOf(r6)
            if (r0 <= 0) goto L_0x0054
            java.lang.String r1 = r1.substring(r2, r0)
        L_0x0038:
            X.1oF r3 = new X.1oF
            r3.<init>(r4, r1)
            r2 = 0
            java.lang.String r0 = r3.A00
            java.lang.String r0 = A01(r8, r0)
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            java.io.File r0 = r9.getParentFile()
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x0054
            return r2
        L_0x0054:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1oA.A00(X.1oA, java.io.File):X.1oF");
    }

    public static String A01(AnonymousClass1oA r3, String str) {
        String valueOf = String.valueOf(Math.abs(str.hashCode() % 100));
        StringBuilder sb = new StringBuilder();
        sb.append(r3.A02);
        sb.append(File.separator);
        sb.append(valueOf);
        return sb.toString();
    }

    public static void A02(File file, AnonymousClass1oK r6) {
        r6.A7P(file);
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    A02(file2, r6);
                } else {
                    r6.A9u(file2);
                }
            }
        }
        r6.A7O(file);
    }

    @Override // X.AnonymousClass1oV
    public final boolean A4y() {
        return this.A04;
    }
}
