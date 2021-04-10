package defpackage;

import android.content.Context;
import android.os.SystemClock;
import com.oculus.browser.R;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: qI0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4384qI0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Map f11132a = new LinkedHashMap();
    public static final Object b = new Object();
    public static boolean c;
    public static List d;
    public static C4384qI0 e;
    public final Integer f;
    public final WeakReference g;

    public C4384qI0(int i) {
        this.f = Integer.valueOf(i);
        this.g = null;
        f11132a.put(Integer.valueOf(c()), this);
    }

    public static C4384qI0 a(Tab tab) {
        synchronized (b) {
            C4384qI0 qi0 = (C4384qI0) f11132a.get(Integer.valueOf(tab.getId()));
            if (qi0 == null || !qi0.l()) {
                return new C4384qI0(tab);
            } else if (qi0.g() == tab) {
                return qi0;
            } else {
                return new C4384qI0(tab);
            }
        }
    }

    public static C4384qI0 b(int i) {
        synchronized (b) {
            C4384qI0 qi0 = (C4384qI0) f11132a.get(Integer.valueOf(i));
            if (qi0 != null) {
                return qi0;
            }
            return new C4384qI0(i);
        }
    }

    public static List d(List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a((Tab) it.next()));
        }
        return arrayList;
    }

    public static List e(C4384qI0 qi0, AbstractC0124Ca1 ca1) {
        List list;
        synchronized (b) {
            int c2 = qi0.c();
            if (!((AbstractC0246Ea1) ca1).h) {
                list = null;
            } else {
                C3910na1 na1 = ((AbstractC0246Ea1) ca1).c;
                List N = na1.g(false).N(c2);
                if (N.size() > 0) {
                    list = N;
                } else {
                    list = na1.g(true).N(c2);
                }
            }
            if (list != null) {
                return d(list);
            }
            ArrayList arrayList = new ArrayList();
            int f2 = qi0.f();
            if (f2 != -1) {
                if (AbstractC4772sd1.g()) {
                    for (Integer num : f11132a.keySet()) {
                        C4384qI0 qi02 = (C4384qI0) f11132a.get(num);
                        if (qi02.f() != -1) {
                            if (qi02.f() == f2) {
                                arrayList.add(qi02);
                            }
                        }
                    }
                    return arrayList;
                }
            }
            arrayList.add(qi0);
            return arrayList;
        }
    }

    public static void n() {
        Throwable th;
        IOException e2;
        if (!c) {
            c = true;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            File file = new File(AbstractC1102Sb1.a(), C1169Td1.d(0));
            if (!file.exists()) {
                AbstractC1220Ua0.d("PseudoTab", "State file does not exist.", new Object[0]);
                return;
            }
            FileInputStream fileInputStream = null;
            try {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[((int) file.length())];
                    fileInputStream2.read(bArr);
                    O21.a(fileInputStream2);
                    AbstractC1220Ua0.d("PseudoTab", "Finished fetching tab list.", new Object[0]);
                    DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
                    HashSet hashSet = new HashSet();
                    d = new ArrayList();
                    try {
                        C4766sb1.o(dataInputStream, new C4213pI0(hashSet), null, false);
                        StringBuilder i = AbstractC2531fV.i("All pre-native tabs: ");
                        i.append(d);
                        i.toString();
                        AbstractC1220Ua0.d("PseudoTab", "readAllPseudoTabsFromStateFile() took %dms", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                    } catch (IOException e3) {
                        AbstractC1220Ua0.a("PseudoTab", "Could not read state file.", e3);
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    fileInputStream = fileInputStream2;
                    try {
                        AbstractC1220Ua0.a("PseudoTab", "Could not read state file.", e2);
                        O21.a(fileInputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        O21.a(fileInputStream2);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    O21.a(fileInputStream2);
                    throw th;
                }
            } catch (IOException e5) {
                e2 = e5;
                AbstractC1220Ua0.a("PseudoTab", "Could not read state file.", e2);
                O21.a(fileInputStream);
            }
        }
    }

    public int c() {
        return this.f.intValue();
    }

    public int f() {
        WeakReference weakReference = this.g;
        if (weakReference != null && weakReference.get() != null && ((Tab) this.g.get()).isInitialized()) {
            return C5383wB.q((Tab) this.g.get()).R;
        }
        return X51.d().getInt(X51.c(this.f.intValue()), -1);
    }

    @Deprecated
    public Tab g() {
        WeakReference weakReference = this.g;
        if (weakReference != null) {
            return (Tab) weakReference.get();
        }
        return null;
    }

    public long h() {
        if (!((Tab) this.g.get()).isInitialized()) {
            return -1;
        }
        return C5383wB.q((Tab) this.g.get()).S;
    }

    public String i() {
        WeakReference weakReference = this.g;
        if (weakReference != null && weakReference.get() != null && ((Tab) this.g.get()).isInitialized()) {
            return ((Tab) this.g.get()).getTitle();
        }
        return X51.d().getString(X51.e(this.f.intValue()), "");
    }

    public String j(C0618Kc1 kc1) {
        if (kc1 == null) {
            return i();
        }
        AbstractC0124Ca1 ca1 = kc1.f8374a;
        Context context = kc1.b;
        int size = e(this, ca1).size();
        if (size == 1) {
            return i();
        }
        return context.getResources().getQuantityString(R.plurals.f42660_resource_name_obfuscated_RES_2131820558, size, Integer.valueOf(size));
    }

    public String k() {
        WeakReference weakReference = this.g;
        if (weakReference != null && weakReference.get() != null && ((Tab) this.g.get()).isInitialized()) {
            return ((Tab) this.g.get()).s();
        }
        return X51.d().getString(X51.f(this.f.intValue()), "");
    }

    public boolean l() {
        return g() != null;
    }

    public boolean m() {
        WeakReference weakReference = this.g;
        if (weakReference == null || weakReference.get() == null) {
            return false;
        }
        return ((Tab) this.g.get()).a();
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("Tab ");
        i.append(this.f);
        return i.toString();
    }

    public C4384qI0(Tab tab) {
        this.f = Integer.valueOf(tab.getId());
        this.g = new WeakReference(tab);
        f11132a.put(Integer.valueOf(c()), this);
    }
}
