package X;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.annotation.Nullable;

/* renamed from: X.0ir  reason: invalid class name and case insensitive filesystem */
public class C02490ir extends AnonymousClass0lB {
    @Nullable
    public C02510it[] A00;
    public final ZipFile A01;
    public final AnonymousClass0T3 A02;
    public final /* synthetic */ AnonymousClass0HZ A03;

    public boolean A02(ZipEntry zipEntry, String str) {
        return true;
    }

    public C02490ir(AnonymousClass0HZ r3, AnonymousClass0T3 r4) throws IOException {
        this.A03 = r3;
        this.A01 = new ZipFile(r3.A00);
        this.A02 = r4;
    }

    @Override // X.AnonymousClass0lB
    public final AbstractC03090lA A01() throws IOException {
        return new C02500is(this);
    }

    public final C02510it[] A03() {
        C02510it[] r4 = this.A00;
        if (r4 == null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            HashMap hashMap = new HashMap();
            Pattern compile = Pattern.compile(this.A03.A01);
            String[] A012 = AnonymousClass0l3.A01();
            Enumeration<? extends ZipEntry> entries = this.A01.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                Matcher matcher = compile.matcher(zipEntry.getName());
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    int i = 0;
                    while (true) {
                        if (i >= A012.length) {
                            break;
                        } else if (A012[i] == null || !group.equals(A012[i])) {
                            i++;
                        } else if (i >= 0) {
                            linkedHashSet.add(group);
                            C02510it r0 = (C02510it) hashMap.get(group2);
                            if (r0 == null || i < r0.A00) {
                                hashMap.put(group2, new C02510it(group2, zipEntry, i));
                            }
                        }
                    }
                }
            }
            this.A02.A01 = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
            C02510it[] r6 = (C02510it[]) hashMap.values().toArray(new C02510it[hashMap.size()]);
            Arrays.sort(r6);
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int length = r6.length;
                if (i2 >= length) {
                    break;
                }
                C02510it r02 = r6[i2];
                if (A02(r02.A01, ((AnonymousClass0l7) r02).A01)) {
                    i3++;
                } else {
                    r6[i2] = null;
                }
                i2++;
            }
            r4 = new C02510it[i3];
            int i4 = 0;
            for (C02510it r1 : r6) {
                if (r1 != null) {
                    r4[i4] = r1;
                    i4++;
                }
            }
            this.A00 = r4;
        }
        return r4;
    }

    @Override // java.io.Closeable, X.AnonymousClass0lB, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }

    @Override // X.AnonymousClass0lB
    public final AnonymousClass0l8 A00() throws IOException {
        return new AnonymousClass0l8(A03());
    }
}
