package X;

import java.io.File;
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

/* renamed from: X.ry  reason: case insensitive filesystem */
public class C0488ry extends AbstractC0508sc {
    @Nullable
    public C0497sP[] A00;
    public final ZipFile A01;
    public final AbstractC0486rs A02;
    public final /* synthetic */ C0502sV A03;

    public C0488ry(C0502sV sVVar, AbstractC0486rs rsVar) throws IOException {
        this.A03 = sVVar;
        this.A01 = new ZipFile(sVVar.A00);
        this.A02 = rsVar;
    }

    public final C0497sP[] A00() {
        C0497sP[] sPVarArr = this.A00;
        if (sPVarArr == null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            HashMap hashMap = new HashMap();
            Pattern compile = Pattern.compile(this.A03.A01);
            String[] A012 = s7.A01();
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
                            C0497sP sPVar = (C0497sP) hashMap.get(group2);
                            if (sPVar == null || i < sPVar.A00) {
                                hashMap.put(group2, new C0497sP(group2, zipEntry, i));
                            }
                        }
                    }
                }
            }
            this.A02.A01 = (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]);
            C0497sP[] sPVarArr2 = (C0497sP[]) hashMap.values().toArray(new C0497sP[hashMap.size()]);
            Arrays.sort(sPVarArr2);
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int length = sPVarArr2.length;
                if (i2 >= length) {
                    break;
                }
                C0497sP sPVar2 = sPVarArr2[i2];
                ZipEntry zipEntry2 = sPVar2.A01;
                String str = ((C0519t9) sPVar2).A01;
                if (this instanceof C0493sC) {
                    C0493sC sCVar = (C0493sC) this;
                    zipEntry2.getName();
                    C0489rz rzVar = sCVar.A02;
                    if (str.equals(((AbstractC0486rs) rzVar).A00)) {
                        ((AbstractC0486rs) rzVar).A00 = null;
                    } else if ((sCVar.A01 & 1) != 0) {
                        File file = new File(sCVar.A00, str);
                        if (file.isFile() && file.length() == zipEntry2.getSize()) {
                            sPVarArr2[i2] = null;
                            i2++;
                        }
                    }
                }
                i3++;
                i2++;
            }
            sPVarArr = new C0497sP[i3];
            int i4 = 0;
            for (C0497sP sPVar3 : sPVarArr2) {
                if (sPVar3 != null) {
                    sPVarArr[i4] = sPVar3;
                    i4++;
                }
            }
            this.A00 = sPVarArr;
        }
        return sPVarArr;
    }
}
