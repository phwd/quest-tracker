package X;

import java.io.File;

/* renamed from: X.1nb  reason: invalid class name */
public final class AnonymousClass1nb {
    public long A00;
    public AnonymousClass1ne A01;
    public boolean A02;
    public File[] A03;
    public File[] A04;
    public final String A05;
    public final long[] A06;
    public final /* synthetic */ C10521nZ A07;

    public AnonymousClass1nb(C10521nZ r9, String str) {
        this.A07 = r9;
        this.A05 = str;
        int i = r9.A06;
        this.A06 = new long[i];
        this.A03 = new File[i];
        this.A04 = new File[i];
        StringBuilder sb = new StringBuilder(str);
        sb.append('.');
        int length = sb.length();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(i2);
            File[] fileArr = this.A03;
            File file = r9.A07;
            fileArr[i2] = new File(file, sb.toString());
            sb.append(".tmp");
            this.A04[i2] = new File(file, sb.toString());
            sb.setLength(length);
        }
    }
}
