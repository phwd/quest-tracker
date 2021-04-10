package defpackage;

import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.oculus.os.PackageMetadata;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* renamed from: eh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2392eh0 {

    /* renamed from: a  reason: collision with root package name */
    public final C2051ch0 f9872a;
    public final String b;
    public final String c;
    public String d;
    public String e;
    public Uri f;
    public boolean g;
    public int h;
    public boolean i;
    public final ArrayList j = new ArrayList();
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q = -1;
    public Bundle r;
    public IntentSender s;
    public C0869Of0 t;
    public List u = new ArrayList();
    public Map v;

    public C2392eh0(C2051ch0 ch0, String str, String str2) {
        this.f9872a = ch0;
        this.b = str;
        this.c = str2;
    }

    public AbstractC0202Dg0 a() {
        AbstractC0385Gg0 gg0 = C3246jh0.f10229a.s;
        if (gg0 instanceof AbstractC0202Dg0) {
            return (AbstractC0202Dg0) gg0;
        }
        return null;
    }

    public C2222dh0 b(C2392eh0 eh0) {
        Map map = this.v;
        if (map == null || !map.containsKey(eh0.c)) {
            return null;
        }
        return new C2222dh0((C0141Cg0) this.v.get(eh0.c));
    }

    public List c() {
        return Collections.unmodifiableList(this.u);
    }

    public AbstractC0446Hg0 d() {
        C2051ch0 ch0 = this.f9872a;
        Objects.requireNonNull(ch0);
        C3246jh0.b();
        return ch0.f9625a;
    }

    public boolean e() {
        C3246jh0.b();
        if ((C3246jh0.f10229a.f() == this) || this.m == 3) {
            return true;
        }
        return TextUtils.equals(d().b.f8030a.getPackageName(), "android") && n("android.media.intent.category.LIVE_AUDIO") && !n("android.media.intent.category.LIVE_VIDEO");
    }

    public boolean f() {
        return c().size() >= 1;
    }

    public boolean g() {
        return this.t != null && this.g;
    }

    public boolean h() {
        C3246jh0.b();
        return C3246jh0.f10229a.g() == this;
    }

    public boolean i(C0629Kg0 kg0) {
        if (kg0 != null) {
            C3246jh0.b();
            ArrayList arrayList = this.j;
            if (arrayList == null) {
                return false;
            }
            kg0.a();
            int size = kg0.c.size();
            if (size == 0) {
                return false;
            }
            int size2 = arrayList.size();
            for (int i2 = 0; i2 < size2; i2++) {
                IntentFilter intentFilter = (IntentFilter) arrayList.get(i2);
                if (intentFilter != null) {
                    for (int i3 = 0; i3 < size; i3++) {
                        if (intentFilter.hasCategory((String) kg0.c.get(i3))) {
                            return true;
                        }
                    }
                    continue;
                }
            }
            return false;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ea, code lost:
        if (r3.hasNext() != false) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f0, code lost:
        if (r4.hasNext() != false) goto L_0x00f4;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0233  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x00f4 A[EDGE_INSN: B:111:0x00f4->B:55:0x00f4 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int j(defpackage.C0869Of0 r12) {
        /*
        // Method dump skipped, instructions count: 571
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2392eh0.j(Of0):int");
    }

    public void k(int i2) {
        AbstractC0385Gg0 gg0;
        AbstractC0385Gg0 gg02;
        C3246jh0.b();
        C1543Zg0 zg0 = C3246jh0.f10229a;
        int min = Math.min(this.p, Math.max(0, i2));
        if (this == zg0.r && (gg02 = zg0.s) != null) {
            gg02.f(min);
        } else if (!zg0.v.isEmpty() && (gg0 = (AbstractC0385Gg0) zg0.v.get(this.c)) != null) {
            gg0.f(min);
        }
    }

    public void l(int i2) {
        AbstractC0385Gg0 gg0;
        AbstractC0385Gg0 gg02;
        C3246jh0.b();
        if (i2 != 0) {
            C1543Zg0 zg0 = C3246jh0.f10229a;
            if (this == zg0.r && (gg02 = zg0.s) != null) {
                gg02.i(i2);
            } else if (!zg0.v.isEmpty() && (gg0 = (AbstractC0385Gg0) zg0.v.get(this.c)) != null) {
                gg0.i(i2);
            }
        }
    }

    public void m() {
        C3246jh0.b();
        C3246jh0.f10229a.k(this, 3);
    }

    public boolean n(String str) {
        C3246jh0.b();
        int size = this.j.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((IntentFilter) this.j.get(i2)).hasCategory(str)) {
                return true;
            }
        }
        return false;
    }

    public void o(Collection collection) {
        this.u.clear();
        if (this.v == null) {
            this.v = new C4931ta();
        }
        this.v.clear();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            C0141Cg0 cg0 = (C0141Cg0) it.next();
            C2392eh0 a2 = this.f9872a.a(cg0.f7828a.i());
            if (a2 != null) {
                this.v.put(a2.c, cg0);
                int i2 = cg0.b;
                if (i2 == 2 || i2 == 3) {
                    this.u.add(a2);
                }
            }
        }
        C3246jh0.f10229a.k.b(PackageMetadata.Signature.Algorithm.RSASSA_PKCS1_5_SHA_256, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder i2 = AbstractC2531fV.i("MediaRouter.RouteInfo{ uniqueId=");
        i2.append(this.c);
        i2.append(", name=");
        i2.append(this.d);
        i2.append(", description=");
        i2.append(this.e);
        i2.append(", iconUri=");
        i2.append(this.f);
        i2.append(", enabled=");
        i2.append(this.g);
        i2.append(", connectionState=");
        i2.append(this.h);
        i2.append(", canDisconnect=");
        i2.append(this.i);
        i2.append(", playbackType=");
        i2.append(this.k);
        i2.append(", playbackStream=");
        i2.append(this.l);
        i2.append(", deviceType=");
        i2.append(this.m);
        i2.append(", volumeHandling=");
        i2.append(this.n);
        i2.append(", volume=");
        i2.append(this.o);
        i2.append(", volumeMax=");
        i2.append(this.p);
        i2.append(", presentationDisplayId=");
        i2.append(this.q);
        i2.append(", extras=");
        i2.append(this.r);
        i2.append(", settingsIntent=");
        i2.append(this.s);
        i2.append(", providerPackageName=");
        i2.append(this.f9872a.c.f8030a.getPackageName());
        sb.append(i2.toString());
        if (f()) {
            sb.append(", members=[");
            int size = this.u.size();
            for (int i3 = 0; i3 < size; i3++) {
                if (i3 > 0) {
                    sb.append(", ");
                }
                if (this.u.get(i3) != this) {
                    sb.append(((C2392eh0) this.u.get(i3)).c);
                }
            }
            sb.append(']');
        }
        sb.append(" }");
        return sb.toString();
    }
}
