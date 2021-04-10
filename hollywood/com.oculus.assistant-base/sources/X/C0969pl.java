package X;

import java.util.zip.ZipEntry;

/* renamed from: X.pl  reason: case insensitive filesystem */
public final class C0969pl extends KQ implements Comparable {
    public final int A00;
    public final ZipEntry A01;

    public C0969pl(String str, ZipEntry zipEntry, int i) {
        super(str, String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), Long.valueOf(zipEntry.getSize()), Long.valueOf(zipEntry.getCompressedSize()), Long.valueOf(zipEntry.getCrc())));
        this.A01 = zipEntry;
        this.A00 = i;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return super.A01.compareTo(((KQ) obj).A01);
    }
}
