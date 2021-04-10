package defpackage;

import android.util.Pair;
import android.util.SparseBooleanArray;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.PathUtils;

/* renamed from: Sd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1108Sd1 extends AbstractC2032cb {
    public final Callback i;
    public String[] j;
    public String[] k;
    public SparseBooleanArray l;
    public final /* synthetic */ C1169Td1 m;

    public C1108Sd1(C1169Td1 td1, Callback callback) {
        this.m = td1;
        this.i = callback;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Throwable th;
        DataInputStream dataInputStream;
        Exception e;
        DataInputStream dataInputStream2 = null;
        if (!this.m.i) {
            this.j = AbstractC1102Sb1.a().list();
            this.k = new File(PathUtils.getThumbnailCacheDirectory()).list();
            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
            this.l = sparseBooleanArray;
            for (int i2 = 0; i2 < 3; i2++) {
                if (i2 != this.m.f) {
                    Objects.requireNonNull(this.m);
                    File file = new File(AbstractC1102Sb1.a(), C1169Td1.d(i2));
                    if (file.exists()) {
                        try {
                            dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
                            try {
                                C4766sb1.o(dataInputStream, null, sparseBooleanArray, false);
                            } catch (Exception e2) {
                                e = e2;
                            }
                        } catch (Exception e3) {
                            e = e3;
                            dataInputStream = null;
                            try {
                                AbstractC1220Ua0.a("tabmodel", "Unable to read state for " + file.getName() + ": " + e, new Object[0]);
                                O21.a(dataInputStream);
                            } catch (Throwable th2) {
                                th = th2;
                                dataInputStream2 = dataInputStream;
                                O21.a(dataInputStream2);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            O21.a(dataInputStream2);
                            throw th;
                        }
                        O21.a(dataInputStream);
                    }
                }
            }
        }
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void j(Object obj) {
        Void r2 = (Void) obj;
        Object obj2 = C1169Td1.f8971a;
        synchronized (C1169Td1.b) {
            C1169Td1.e = null;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        String[] strArr;
        Void r8 = (Void) obj;
        if (!this.m.i) {
            C0560Jd1 a2 = AbstractC0621Kd1.a();
            if (this.j != null) {
                ArrayList arrayList = new ArrayList();
                String[] strArr2 = this.j;
                for (String str : strArr2) {
                    Pair c = AbstractC1224Ub1.c(str);
                    if (c != null && m(((Integer) c.first).intValue(), a2)) {
                        arrayList.add(str);
                    }
                }
                this.i.onResult(arrayList);
            }
            if (!(this.m.h == null || (strArr = this.k) == null)) {
                for (String str2 : strArr) {
                    try {
                        int parseInt = Integer.parseInt(str2);
                        if (m(parseInt, a2)) {
                            this.m.h.j(parseInt);
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            Object obj2 = C1169Td1.f8971a;
            synchronized (C1169Td1.b) {
                C1169Td1.e = null;
            }
        }
    }

    public final boolean m(int i2, C0560Jd1 jd1) {
        return jd1.a(i2) == null && !this.l.get(i2);
    }
}
