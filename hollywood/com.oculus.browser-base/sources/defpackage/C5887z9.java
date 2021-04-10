package defpackage;

import android.content.res.Configuration;
import android.view.Menu;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5887z9 implements AbstractC5717y9, AbstractC1768b01, AbstractC3464kx {
    public View$OnKeyListenerC2476f9 F;
    public C5547x9 G;
    public Menu H;
    public final List I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public final List f11727J = new ArrayList();
    public final int K;
    public final View L;
    public final B9 M;
    public final AbstractC5207v9 N;
    public final View O;
    public final M2 P;
    public Integer Q;

    public C5887z9(B9 b9, AbstractC5207v9 v9Var, int i, View view, M2 m2, View view2) {
        this.N = v9Var;
        this.M = b9;
        this.O = view;
        this.K = i;
        this.L = view2;
        this.P = m2;
        m2.a(this);
    }

    @Override // defpackage.AbstractC1768b01
    public void d() {
    }

    @Override // defpackage.AbstractC1768b01
    public void e() {
        f();
    }

    public void f() {
        View$OnKeyListenerC2476f9 f9Var = this.F;
        if (f9Var != null && f9Var.b()) {
            this.F.a();
        }
    }

    public boolean g() {
        View$OnKeyListenerC2476f9 f9Var = this.F;
        return f9Var != null && f9Var.b();
    }

    public void h(boolean z) {
        C5321vq1 vq1;
        C5491wq1 wq1;
        C5321vq1 vq12;
        for (int i = 0; i < this.f11727J.size(); i++) {
            C0330Fi0 fi0 = (C0330Fi0) ((A9) this.f11727J.get(i));
            if (z) {
                fi0.g.a(false, 12);
                View currentFocus = fi0.k.getCurrentFocus();
                if (currentFocus != null) {
                    fi0.l.d(currentFocus);
                }
                if (!((Boolean) fi0.r.get()).booleanValue() && ((C0391Gi0) fi0.h.g(AbstractC0513Ii0.g)).f8103a) {
                    fi0.a(true);
                    fi0.i.run();
                }
                fi0.p = fi0.f.r(fi0.p);
            } else {
                fi0.f.p(fi0.p);
            }
            if (z && (vq1 = C2249dq1.a().e) != null && vq1.f11503a == 2 && (vq12 = (wq1 = AbstractC4981tq1.f11374a).f11572J) != null) {
                String str = AbstractC0456Hk.f8178a.f;
                String str2 = vq12.d;
                if (str2 == null || !str2.equals(str)) {
                    NU0.f8549a.p("android_os_unsupported_chrome_version", str);
                    wq1.f11572J.d = str;
                    wq1.d();
                }
            }
        }
    }

    public void i(Integer num) {
        Integer num2 = this.Q;
        if (!(num2 == null && num == null)) {
            if (num2 == null || !num2.equals(num)) {
                this.Q = num;
                boolean z = num != null;
                for (A9 a9 : this.f11727J) {
                    C0330Fi0 fi0 = (C0330Fi0) a9;
                    fi0.h.j(AbstractC0513Ii0.e, z);
                    if (z) {
                        fi0.q = fi0.f.r(fi0.q);
                    } else {
                        fi0.f.p(fi0.q);
                    }
                }
            }
        }
    }

    public boolean j() {
        for (int i = 0; i < this.I.size(); i++) {
            if (!((AbstractC4356q9) this.I.get(i)).O()) {
                return false;
            }
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v25, resolved type: android.widget.ListView */
    /* JADX DEBUG: Multi-variable search result rejected for r0v27, resolved type: z9 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v32, resolved type: android.widget.ListView */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v27, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r3v65 */
    /* JADX WARN: Type inference failed for: r3v66 */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0496, code lost:
        if (defpackage.C5222vE.d(r0.e) != 2) goto L_0x0498;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x063f, code lost:
        if (r10.getItemId() == com.oculus.browser.R.id.add_to_menu_id) goto L_0x0645;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a3, code lost:
        if (r2 == false) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ac, code lost:
        if (r0.g.H != null) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:430:0x0aaa, code lost:
        if (r6 != 2) goto L_0x0abc;
     */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0263  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0298  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x029a  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x02b6  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x02b8  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x02c2  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02d2  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x02de  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x03d5  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x03eb  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x041a  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x042d  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x043e A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0455  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x04a3  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x04b8  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x04e3  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x04eb  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x0516  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0541  */
    /* JADX WARNING: Removed duplicated region for block: B:245:0x0583  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x05c4  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x05cf  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x05ed  */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x0603  */
    /* JADX WARNING: Removed duplicated region for block: B:314:0x06b3  */
    /* JADX WARNING: Removed duplicated region for block: B:317:0x06c1  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x06d0  */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x06df  */
    /* JADX WARNING: Removed duplicated region for block: B:330:0x06fd  */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x0725  */
    /* JADX WARNING: Removed duplicated region for block: B:339:0x0759  */
    /* JADX WARNING: Removed duplicated region for block: B:347:0x0797  */
    /* JADX WARNING: Removed duplicated region for block: B:348:0x079e  */
    /* JADX WARNING: Removed duplicated region for block: B:351:0x083b  */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0849  */
    /* JADX WARNING: Removed duplicated region for block: B:358:0x0892  */
    /* JADX WARNING: Removed duplicated region for block: B:377:0x093f  */
    /* JADX WARNING: Removed duplicated region for block: B:378:0x0943  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0978  */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x097a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:393:0x09ac  */
    /* JADX WARNING: Removed duplicated region for block: B:396:0x09d1  */
    /* JADX WARNING: Removed duplicated region for block: B:397:0x09d8  */
    /* JADX WARNING: Removed duplicated region for block: B:399:0x09dd  */
    /* JADX WARNING: Removed duplicated region for block: B:402:0x09f4  */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x09ff A[LOOP:4: B:404:0x09f9->B:406:0x09ff, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:408:0x0a0f  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:427:0x0aa5  */
    /* JADX WARNING: Removed duplicated region for block: B:435:0x0ac6  */
    /* JADX WARNING: Removed duplicated region for block: B:440:0x0b14  */
    /* JADX WARNING: Removed duplicated region for block: B:443:0x0b26  */
    /* JADX WARNING: Removed duplicated region for block: B:446:0x0b46  */
    /* JADX WARNING: Removed duplicated region for block: B:456:0x0715 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0130 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x018d  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean k(android.view.View r33, boolean r34) {
        /*
        // Method dump skipped, instructions count: 2908
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5887z9.k(android.view.View, boolean):boolean");
    }

    @Override // defpackage.AbstractC3464kx
    public void onConfigurationChanged(Configuration configuration) {
        f();
    }
}
