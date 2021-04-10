package X;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/* renamed from: X.0iv  reason: invalid class name and case insensitive filesystem */
public final class C02530iv extends AnonymousClass0lB {
    public final C02520iu[] A00;
    public final /* synthetic */ C00560Ha A01;

    public C02530iv(C00560Ha r16, AnonymousClass0T3 r17) throws IOException {
        this.A01 = r16;
        File file = new File(AnonymousClass006.A09("/data/local/tmp/exopackage/", r16.A03.getPackageName(), "/native-libs/"));
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String[] A012 = AnonymousClass0l3.A01();
        int i = 0;
        for (String str : A012) {
            File file2 = new File(file, str);
            if (file2.isDirectory()) {
                linkedHashSet.add(str);
                File file3 = new File(file2, "metadata.txt");
                if (file3.isFile()) {
                    FileReader fileReader = new FileReader(file3);
                    try {
                        BufferedReader bufferedReader = new BufferedReader(fileReader);
                        while (true) {
                            try {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    bufferedReader.close();
                                    fileReader.close();
                                    break;
                                } else if (readLine.length() != 0) {
                                    int indexOf = readLine.indexOf(32);
                                    if (indexOf != -1) {
                                        String A07 = AnonymousClass006.A07(readLine.substring(i, indexOf), ".so");
                                        int size = arrayList.size();
                                        int i2 = 0;
                                        while (true) {
                                            if (i2 >= size) {
                                                String substring = readLine.substring(indexOf + 1);
                                                arrayList.add(new C02520iu(A07, substring, new File(file2, substring)));
                                                break;
                                            }
                                            if (((C02520iu) arrayList.get(i2)).A01.equals(A07)) {
                                                break;
                                            }
                                            i2++;
                                        }
                                        i = 0;
                                    } else {
                                        throw new RuntimeException(AnonymousClass006.A09("illegal line in exopackage metadata: [", readLine, "]"));
                                    }
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    } catch (Throwable unused2) {
                    }
                } else {
                    continue;
                }
            }
        }
        r17.A01 = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
        this.A00 = (C02520iu[]) arrayList.toArray(new C02520iu[arrayList.size()]);
        return;
        throw th;
        throw th;
    }

    @Override // X.AnonymousClass0lB
    public final AnonymousClass0l8 A00() throws IOException {
        return new AnonymousClass0l8(this.A00);
    }

    @Override // X.AnonymousClass0lB
    public final AbstractC03090lA A01() throws IOException {
        return new C02540j4(this);
    }
}
