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

/* renamed from: X.0hN  reason: invalid class name and case insensitive filesystem */
public class C04320hN extends AbstractC03240cV {
    @Nullable
    public C04340hS[] A00;
    public final ZipFile A01;
    public final AnonymousClass0HU A02;
    public final /* synthetic */ AnonymousClass08f A03;

    public boolean A02(ZipEntry zipEntry, String str) {
        return true;
    }

    public C04320hN(AnonymousClass08f r3, AnonymousClass0HU r4) throws IOException {
        this.A03 = r3;
        this.A01 = new ZipFile(r3.A00);
        this.A02 = r4;
    }

    @Override // X.AbstractC03240cV
    public final AbstractC03230cU A01() throws IOException {
        return new C04330hR(this);
    }

    public final C04340hS[] A03() {
        C04340hS[] r4 = this.A00;
        if (r4 == null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            HashMap hashMap = new HashMap();
            Pattern compile = Pattern.compile(this.A03.A01);
            String[] A022 = C03190cO.A02();
            Enumeration<? extends ZipEntry> entries = this.A01.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                Matcher matcher = compile.matcher(zipEntry.getName());
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    int i = 0;
                    while (true) {
                        if (i >= A022.length) {
                            break;
                        } else if (A022[i] == null || !group.equals(A022[i])) {
                            i++;
                        } else if (i >= 0) {
                            linkedHashSet.add(group);
                            C04340hS r0 = (C04340hS) hashMap.get(group2);
                            if (r0 == null || i < r0.A00) {
                                hashMap.put(group2, new C04340hS(group2, zipEntry, i));
                            }
                        }
                    }
                }
            }
            this.A02.A01 = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
            C04340hS[] r6 = (C04340hS[]) hashMap.values().toArray(new C04340hS[hashMap.size()]);
            Arrays.sort(r6);
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int length = r6.length;
                if (i2 >= length) {
                    break;
                }
                C04340hS r02 = r6[i2];
                if (A02(r02.A01, ((C03210cR) r02).A01)) {
                    i3++;
                } else {
                    r6[i2] = null;
                }
                i2++;
            }
            r4 = new C04340hS[i3];
            int i4 = 0;
            for (C04340hS r1 : r6) {
                if (r1 != null) {
                    r4[i4] = r1;
                    i4++;
                }
            }
            this.A00 = r4;
        }
        return r4;
    }

    @Override // X.AbstractC03240cV, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01.close();
    }

    @Override // X.AbstractC03240cV
    public final C03220cS A00() throws IOException {
        return new C03220cS(A03());
    }
}
