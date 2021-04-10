package X;

import java.util.zip.ZipEntry;

/* renamed from: X.0hS  reason: invalid class name and case insensitive filesystem */
public final class C04340hS extends C03210cR implements Comparable {
    public final int A00;
    public final ZipEntry A01;

    public C04340hS(String str, ZipEntry zipEntry, int i) {
        super(str, String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), Long.valueOf(zipEntry.getSize()), Long.valueOf(zipEntry.getCompressedSize()), Long.valueOf(zipEntry.getCrc())));
        this.A01 = zipEntry;
        this.A00 = i;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return super.A01.compareTo(((C03210cR) obj).A01);
    }
}
