package X;

import java.util.zip.ZipEntry;

/* renamed from: X.sP  reason: case insensitive filesystem */
public final class C0497sP extends C0519t9 implements Comparable {
    public final int A00;
    public final ZipEntry A01;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return super.A01.compareTo(((C0519t9) obj).A01);
    }

    public C0497sP(String str, ZipEntry zipEntry, int i) {
        super(str, String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), Long.valueOf(zipEntry.getSize()), Long.valueOf(zipEntry.getCompressedSize()), Long.valueOf(zipEntry.getCrc())));
        this.A01 = zipEntry;
        this.A00 = i;
    }
}
