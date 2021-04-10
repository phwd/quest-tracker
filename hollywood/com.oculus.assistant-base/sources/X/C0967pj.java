package X;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/* renamed from: X.pj  reason: case insensitive filesystem */
public final class C0967pj extends KU {
    public final C0968pk[] A00;
    public final /* synthetic */ AnonymousClass2O A01;

    public C0967pj(AnonymousClass2O r16, VS vs) {
        this.A01 = r16;
        File file = new File(AnonymousClass08.A05("/data/local/tmp/exopackage/", r16.A02.getPackageName(), "/native-libs/"));
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String[] A012 = KM.A01();
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
                                        String A04 = AnonymousClass08.A04(readLine.substring(i, indexOf), ".so");
                                        int size = arrayList.size();
                                        int i2 = 0;
                                        while (true) {
                                            if (i2 >= size) {
                                                String substring = readLine.substring(indexOf + 1);
                                                arrayList.add(new C0968pk(A04, substring, new File(file2, substring)));
                                                break;
                                            }
                                            if (((C0968pk) arrayList.get(i2)).A01.equals(A04)) {
                                                break;
                                            }
                                            i2++;
                                        }
                                        i = 0;
                                    } else {
                                        throw new RuntimeException(AnonymousClass08.A05("illegal line in exopackage metadata: [", readLine, "]"));
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
        vs.A01 = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
        this.A00 = (C0968pk[]) arrayList.toArray(new C0968pk[arrayList.size()]);
        return;
        throw th;
        throw th;
    }
}
