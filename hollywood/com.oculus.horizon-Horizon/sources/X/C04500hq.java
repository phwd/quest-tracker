package X;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

/* renamed from: X.0hq  reason: invalid class name and case insensitive filesystem */
public final class C04500hq extends AbstractC03240cV {
    public final C04490hp[] A00;
    public final /* synthetic */ AnonymousClass08g A01;

    public C04500hq(AnonymousClass08g r16, AnonymousClass0HU r17) throws IOException {
        this.A01 = r16;
        File file = new File(AnonymousClass006.A07("/data/local/tmp/exopackage/", r16.A03.getPackageName(), "/native-libs/"));
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String[] A02 = C03190cO.A02();
        int i = 0;
        for (String str : A02) {
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
                                        String A05 = AnonymousClass006.A05(readLine.substring(i, indexOf), ".so");
                                        int size = arrayList.size();
                                        int i2 = 0;
                                        while (true) {
                                            if (i2 >= size) {
                                                String substring = readLine.substring(indexOf + 1);
                                                arrayList.add(new C04490hp(A05, substring, new File(file2, substring)));
                                                break;
                                            }
                                            if (((C04490hp) arrayList.get(i2)).A01.equals(A05)) {
                                                break;
                                            }
                                            i2++;
                                        }
                                        i = 0;
                                    } else {
                                        throw new RuntimeException(AnonymousClass006.A07("illegal line in exopackage metadata: [", readLine, "]"));
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
        this.A00 = (C04490hp[]) arrayList.toArray(new C04490hp[arrayList.size()]);
        return;
        throw th;
        throw th;
    }

    @Override // X.AbstractC03240cV
    public final C03220cS A00() throws IOException {
        return new C03220cS(this.A00);
    }

    @Override // X.AbstractC03240cV
    public final AbstractC03230cU A01() throws IOException {
        return new C04510hr(this);
    }
}
