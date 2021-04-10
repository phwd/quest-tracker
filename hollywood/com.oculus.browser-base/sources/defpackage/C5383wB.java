package defpackage;

import J.N;
import android.text.TextUtils;
import com.oculus.os.Version;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* renamed from: wB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5383wB extends AbstractC2145dC0 {
    public static final /* synthetic */ int N = 0;
    public String O;
    public GURL P;
    public int Q;
    public int R;
    public long S;
    public Ax1 T;
    public int U;
    public String V;
    public int W;
    public Integer X;
    public C1322Vq0 Y = new C1322Vq0();

    public C5383wB(Tab tab, String str, String str2, int i, int i2, long j, Ax1 ax1, int i3, String str3, int i4, Integer num) {
        super(tab, EnumC3169jC0.a(C5383wB.class, tab.a()).b(), EnumC3169jC0.a(C5383wB.class, tab.a()).R);
        this.P = str.isEmpty() ? GURL.emptyGURL() : new GURL(str);
        this.O = str2;
        this.Q = i;
        this.R = i2;
        this.S = j;
        this.T = null;
        this.U = i3;
        this.V = str3;
        this.W = i4;
        this.X = null;
    }

    public static C5383wB q(Tab tab) {
        C4873tB tBVar = new C4873tB(tab);
        AbstractC2145dC0 dc0 = (AbstractC2145dC0) tab.M().c(C5383wB.class);
        if (dc0 == null) {
            dc0 = (AbstractC2145dC0) tab.M().e(C5383wB.class, (AbstractC2145dC0) tBVar.get());
        }
        return (C5383wB) dc0;
    }

    public static int r(Integer num) {
        if (num == null) {
            return 15;
        }
        switch (num.intValue()) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case Version.VERSION_7:
                return 8;
            case Version.VERSION_8:
                return 9;
            case Version.VERSION_9:
                return 10;
            case Version.VERSION_10:
                return 11;
            case Version.VERSION_11:
                return 12;
            case Version.VERSION_12:
                return 13;
            case Version.VERSION_13:
                return 14;
            default:
                return 15;
        }
    }

    public static Integer s(int i) {
        switch (AbstractC5580xK0.a(i)) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case Version.VERSION_7:
                return 7;
            case Version.VERSION_8:
                return 8;
            case Version.VERSION_9:
                return 9;
            case Version.VERSION_10:
                return 10;
            case Version.VERSION_11:
                return 11;
            case Version.VERSION_12:
                return 12;
            case Version.VERSION_13:
                return 13;
            default:
                return null;
        }
    }

    public static Ax1 t(Tab tab) {
        ByteBuffer byteBuffer;
        LoadUrlParams E = tab.E();
        if (E == null) {
            byteBuffer = (ByteBuffer) N.MNwGha8e(tab.l());
        } else {
            C2512fL0 fl0 = E.d;
            byteBuffer = (ByteBuffer) N.M_N0bb_o(E.f10938a, fl0 != null ? fl0.f9916a : null, fl0 != null ? fl0.b : 0, E.b, tab.a());
        }
        if (byteBuffer == null) {
            return null;
        }
        Ax1 ax1 = new Ax1(byteBuffer);
        ax1.b = 2;
        return ax1;
    }

    @Override // defpackage.AbstractC2145dC0
    public void c() {
        this.H.b(this.G.getId(), this.I);
    }

    @Override // defpackage.Qr1, defpackage.Pr1
    public void destroy() {
        this.Y.clear();
    }

    @Override // defpackage.AbstractC2145dC0
    public boolean e(byte[] bArr) {
        GURL gurl;
        String str;
        try {
            TraceEvent j0 = TraceEvent.j0("CriticalPersistedTabData.Deserialize");
            try {
                C5213vB vBVar = (C5213vB) AbstractC2360eV.k(C5213vB.e, bArr);
                this.Q = vBVar.h;
                this.R = vBVar.i;
                this.S = vBVar.j;
                byte[] l = vBVar.k.l();
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(l.length);
                this.T = new Ax1(allocateDirect);
                allocateDirect.put(l);
                Ax1 ax1 = this.T;
                ax1.b = 2;
                if (ax1.a() == null) {
                    gurl = GURL.emptyGURL();
                } else {
                    gurl = new GURL(this.T.a());
                }
                this.P = gurl;
                Ax1 ax12 = this.T;
                this.O = N.MZZlQD12(ax12.f7707a, ax12.b);
                this.U = vBVar.l;
                if (TextUtils.isEmpty(vBVar.m)) {
                    str = null;
                } else {
                    str = vBVar.m;
                }
                this.V = str;
                this.W = vBVar.n;
                int h = AbstractC5580xK0.h(vBVar.o);
                if (h == 0) {
                    h = 1;
                }
                this.X = s(h);
                if (j0 != null) {
                    j0.close();
                }
                return true;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
        } catch (L30 e) {
            AbstractC1220Ua0.a("CriticalPTD", String.format(Locale.ENGLISH, "There was a problem deserializing Tab %d. Details: %s", Integer.valueOf(this.G.getId()), e.getMessage()), new Object[0]);
            return false;
        }
    }

    @Override // defpackage.AbstractC2145dC0
    public String k() {
        return "Critical";
    }

    @Override // defpackage.AbstractC2145dC0
    public void n() {
        GURL gurl = this.P;
        boolean z = false;
        if (gurl != null && !TextUtils.isEmpty(gurl.h()) && (!AbstractC5154ur1.g(this.P.h()) || this.G.h() || this.G.k())) {
            String h = this.P.h();
            if (!(h != null && h.startsWith("content"))) {
                z = true;
            }
        }
        if (z) {
            super.n();
        }
    }

    @Override // defpackage.AbstractC2145dC0
    public byte[] o() {
        AbstractC1248Uk uk;
        TraceEvent j0 = TraceEvent.j0("CriticalPersistedTabData.Serialize");
        try {
            Ax1 ax1 = this.T;
            if (ax1 == null) {
                ax1 = t(this.G);
            }
            C5213vB vBVar = C5213vB.e;
            Objects.requireNonNull(vBVar);
            Objects.requireNonNull(vBVar);
            C5213vB vBVar2 = new C5213vB();
            int i = this.Q;
            int i2 = vBVar2.g | 1;
            vBVar2.g = i2;
            vBVar2.h = i;
            int i3 = this.R;
            int i4 = i2 | 2;
            vBVar2.g = i4;
            vBVar2.i = i3;
            long j = this.S;
            vBVar2.g = i4 | 4;
            vBVar2.j = j;
            if (ax1 == null) {
                uk = AbstractC1248Uk.F;
            } else {
                ByteBuffer byteBuffer = ax1.f7707a;
                int limit = byteBuffer.limit();
                byte[] bArr = new byte[limit];
                byteBuffer.rewind();
                byteBuffer.get(bArr);
                uk = AbstractC1248Uk.c(bArr, 0, limit);
            }
            uk.getClass();
            int i5 = vBVar2.g | 8;
            vBVar2.g = i5;
            vBVar2.k = uk;
            int i6 = this.U;
            int i7 = i5 | 16;
            vBVar2.g = i7;
            vBVar2.l = i6;
            String str = this.V;
            if (str == null) {
                str = "";
            }
            int i8 = i7 | 32;
            vBVar2.g = i8;
            vBVar2.m = str;
            int i9 = this.W;
            vBVar2.g = i8 | 64;
            vBVar2.n = i9;
            vBVar2.o = AbstractC5580xK0.a(r(this.X));
            vBVar2.g |= 128;
            C2163dI0.f9768a.b(vBVar2).c(vBVar2);
            if (vBVar2.i()) {
                byte[] c = vBVar2.c();
                if (j0 != null) {
                    j0.close();
                }
                return c;
            }
            throw new C5488wp1();
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public void u(int i) {
        if (this.R != i) {
            this.R = i;
            Iterator it = this.Y.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC5553xB) uq0.next()).f(this.G, i);
                } else {
                    this.G.D(true);
                    n();
                    return;
                }
            }
        }
    }

    public C5383wB(Tab tab, byte[] bArr, AbstractC3511lC0 lc0, String str) {
        super(tab, lc0, str);
        h(bArr);
    }
}
