package X;

import java.util.zip.ZipEntry;

/* renamed from: X.0it  reason: invalid class name and case insensitive filesystem */
public final class C02510it extends AnonymousClass0l7 implements Comparable {
    public final int A00;
    public final ZipEntry A01;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return super.A01.compareTo(((AnonymousClass0l7) obj).A01);
    }

    public C02510it(String str, ZipEntry zipEntry, int i) {
        super(str, String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), Long.valueOf(zipEntry.getSize()), Long.valueOf(zipEntry.getCompressedSize()), Long.valueOf(zipEntry.getCrc())));
        this.A01 = zipEntry;
        this.A00 = i;
    }
}
