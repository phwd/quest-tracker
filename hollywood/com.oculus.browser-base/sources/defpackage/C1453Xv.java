package defpackage;

import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: Xv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1453Xv extends AbstractC1575Zv {
    public HashMap b = new HashMap();
    public ArrayList c;
    public int d;

    public C1453Xv(String[] strArr) {
        super(null);
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        this.d = 1;
        if (strArr == null || strArr.length == 0 || strArr[0] == null) {
            arrayList.add("");
            return;
        }
        arrayList.add(strArr[0]);
        int i = 1;
        boolean z = true;
        for (String str : strArr) {
            if (i > 0) {
                i--;
            } else {
                z = str.equals("--") ? false : z;
                if (!z || !str.startsWith("--")) {
                    this.c.add(str);
                } else {
                    String[] split = str.split("=", 2);
                    b(split[0].substring(2), split.length > 1 ? split[1] : null);
                }
            }
        }
    }

    @Override // defpackage.AbstractC1575Zv
    public void a(String str) {
        b(str, null);
    }

    @Override // defpackage.AbstractC1575Zv
    public void b(String str, String str2) {
        this.b.put(str, str2 == null ? "" : str2);
        String str3 = "--" + str;
        if (str2 != null && !str2.isEmpty()) {
            str3 = AbstractC2531fV.g(str3, "=", str2);
        }
        ArrayList arrayList = this.c;
        int i = this.d;
        this.d = i + 1;
        arrayList.add(i, str3);
    }

    @Override // defpackage.AbstractC1575Zv
    public String[] d() {
        ArrayList arrayList = this.c;
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // defpackage.AbstractC1575Zv
    public String f(String str) {
        String str2 = (String) this.b.get(str);
        if (str2 == null || str2.isEmpty()) {
            return null;
        }
        return str2;
    }

    @Override // defpackage.AbstractC1575Zv
    public boolean g(String str) {
        return this.b.containsKey(str);
    }
}
