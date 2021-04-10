package defpackage;

import J.N;
import android.content.Context;
import android.graphics.RectF;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.compositor.scene_layer.ContextualSearchSceneLayer;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.resources.ResourceManager;

/* renamed from: bA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1796bA extends AbstractC0292Et0 {
    public final C1967cA F0;
    public final float G0;
    public boolean H0;
    public AbstractC0669Kz I0;
    public boolean J0;
    public ContextualSearchSceneLayer K0;
    public GP0 L0;
    public UH0 M0;
    public float N0 = -1.0f;
    public C4666rz O0;
    public C4154oz P0;
    public C3675mA Q0;
    public C1616aA R0;

    public C1796bA(Context context, D70 d70, C1328Vt0 vt0) {
        super(context, d70, vt0);
        this.K0 = new ContextualSearchSceneLayer(context.getResources().getDisplayMetrics().density);
        this.F0 = new C1967cA();
        this.G0 = ((float) context.getResources().getDimensionPixelSize(R.dimen.f17790_resource_name_obfuscated_RES_2131165398)) * this.F;
        N.M09VlOh_("ContextualSearchLongpressPanelHelp");
        context.getResources().getString(R.string.f49970_resource_name_obfuscated_RES_2131952314);
        context.getResources().getString(R.string.f49960_resource_name_obfuscated_RES_2131952313);
    }

    @Override // defpackage.AbstractC0536It0
    public float E() {
        float f = this.Y;
        Objects.requireNonNull(o0());
        return f + 0.0f;
    }

    @Override // defpackage.AbstractC0536It0
    public boolean P(int i) {
        return l0() || i != 4;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v4, resolved type: Vt0 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v8, types: [Et0, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0327, code lost:
        if (r13 != 3) goto L_0x032c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0331, code lost:
        if (r13 == 4) goto L_0x0337;
     */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x02cd  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x030e  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0316  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0318  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x033c  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x036c  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x0388  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x03d4  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x0425  */
    /* JADX WARNING: Removed duplicated region for block: B:284:0x0475  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0479  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x0486  */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x0489  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0490  */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x04b8  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x04c5  */
    /* JADX WARNING: Removed duplicated region for block: B:308:0x04c8 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x0516  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x051b  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00d1  */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // defpackage.AbstractC0536It0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void T(int r22, int r23) {
        /*
        // Method dump skipped, instructions count: 1721
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1796bA.T(int, int):void");
    }

    @Override // defpackage.AbstractC0292Et0
    public float X() {
        return (r0().X * this.F) + E() + this.T;
    }

    @Override // defpackage.LO0
    public boolean a() {
        if (!O()) {
            return false;
        }
        ((ContextualSearchManager) this.I0).i(2);
        return true;
    }

    @Override // defpackage.AbstractC0292Et0
    public void j0(int i) {
        this.C0 = true;
        this.f8254J = y();
        w(2, i, 218);
        int i2 = this.L;
        if (i2 == 1 || i2 == 2) {
            this.J0 = false;
        }
        if ((i2 == 0 || i2 == 1) && i == 3) {
            C1967cA cAVar = this.F0;
            Objects.requireNonNull(cAVar);
            cAVar.s = System.nanoTime();
        }
    }

    public boolean l0() {
        return !r0().V;
    }

    public boolean m0() {
        return this.s0.L0() == 0 && l0();
    }

    public void n0(int i) {
        w(3, i, 218);
    }

    public final C4154oz o0() {
        if (this.P0 == null) {
            this.P0 = new C4154oz(this, this.K, this.k0, this.l0);
        }
        return this.P0;
    }

    public C0242Dz p0() {
        return s0().h;
    }

    @Override // defpackage.LO0
    public MO0 q(RectF rectF, RectF rectF2, ResourceManager resourceManager, float f) {
        float f2;
        float f3;
        ContextualSearchSceneLayer contextualSearchSceneLayer = this.K0;
        C4666rz s0 = s0();
        C4154oz o0 = o0();
        C3675mA r0 = r0();
        C0242Dz p0 = p0();
        Objects.requireNonNull(contextualSearchSceneLayer);
        if (resourceManager != null && O()) {
            if (!contextualSearchSceneLayer.H) {
                N.MFh7xXWg(contextualSearchSceneLayer.G, contextualSearchSceneLayer, resourceManager);
                contextualSearchSceneLayer.H = true;
            }
            contextualSearchSceneLayer.f10637J = p0;
            int i = this.G;
            int i2 = s0.b.G;
            int i3 = s0.c.G;
            int i4 = s0.d.G;
            int i5 = m0() ? R.drawable.f34370_resource_name_obfuscated_RES_2131231477 : -1;
            int i6 = r0.G;
            boolean z = r0.U;
            float f4 = r0.X;
            float f5 = r0.W;
            int i7 = r0.T;
            int i8 = o0.G;
            float f6 = o0.U;
            float f7 = o0.R;
            float f8 = o0.S;
            float f9 = o0.T;
            float f10 = p0.h;
            if (p0.g == 0) {
                f2 = f9;
                p0.g = p0.f7929a.K.getResources().getDimensionPixelSize(R.dimen.f17760_resource_name_obfuscated_RES_2131165395);
            } else {
                f2 = f9;
            }
            int i9 = p0.g;
            boolean z2 = p0.d;
            int i10 = p0.c;
            boolean z3 = p0.f;
            String str = p0.e;
            if (str == null) {
                str = "";
            }
            float f11 = this.S;
            float f12 = this.T;
            float f13 = this.Q;
            float f14 = this.U;
            float f15 = this.W;
            float f16 = this.X;
            float f17 = this.Y;
            float f18 = s0.i;
            float f19 = s0.j;
            C5006tz tzVar = s0.d;
            if (!tzVar.e0) {
                f3 = 0.0f;
            } else {
                f3 = tzVar.c0;
            }
            boolean z4 = tzVar.b0;
            boolean z5 = this.Z;
            float f20 = this.a0;
            int i11 = this.H;
            int i12 = this.I;
            float f21 = this.b0;
            boolean z6 = this.h0;
            float f22 = this.i0;
            float f23 = this.g0;
            float f24 = this.j0;
            boolean z7 = s0.q;
            float f25 = s0.r;
            float f26 = s0.s;
            WebContents Z = Z();
            int color = this.K.getResources().getColor(R.color.f14500_resource_name_obfuscated_RES_2131100140);
            long j = contextualSearchSceneLayer.G;
            float f27 = contextualSearchSceneLayer.I;
            N.MP4UE9Nn(j, contextualSearchSceneLayer, R.drawable.f34380_resource_name_obfuscated_RES_2131231478, i, i2, i3, i4, R.drawable.f33760_resource_name_obfuscated_RES_2131231416, R.drawable.f30810_resource_name_obfuscated_RES_2131231121, i10, R.drawable.f29170_resource_name_obfuscated_RES_2131230957, i5, -1, R.drawable.f34710_resource_name_obfuscated_RES_2131231511, R.drawable.f34720_resource_name_obfuscated_RES_2131231512, i6, R.drawable.f28920_resource_name_obfuscated_RES_2131230932, i8, f27, this.N * f27, this.O * f27, this.f0, this.e0 * f27, Z, z, f4, f5, i7, false, 0.0f, f6, f7, f8, f2, f11 * f27, f12 * f27, f13 * f27, f14 * f27, f15 * f27, f16 * f27, f17 * f27, f18, s0.k, f19, s0.l, f3, z4, z5, f20 * f27, z2, z3, str, f10, i9, i11, i12, f21, z6, f22 * f27, f23, f24, z7, f25, f26, Profile.b(), R.drawable.f35420_resource_name_obfuscated_RES_2131231582, color);
        }
        return this.K0;
    }

    public float q0() {
        if (LocalizationUtils.isLayoutRtl()) {
            return this.S + this.W;
        }
        float f = (this.S + this.Q) - this.W;
        if (this.c0 == 0.0f) {
            this.c0 = ((float) AbstractC3153j7.c(this.K.getResources(), R.drawable.f28430_resource_name_obfuscated_RES_2131230883).getIntrinsicWidth()) * this.F;
        }
        return f - this.c0;
    }

    public final C3675mA r0() {
        if (this.Q0 == null) {
            if (this.R0 == null) {
                this.R0 = new C1616aA(this);
            }
            this.Q0 = new C3675mA(this, this.R0, this.K, this.k0, this.l0);
        }
        return this.Q0;
    }

    public C4666rz s0() {
        if (this.O0 == null) {
            this.O0 = new C4666rz(this, this.K, this.k0, this.l0);
        }
        return this.O0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    @Override // defpackage.Z9
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void t(android.app.Activity r6, int r7) {
        /*
        // Method dump skipped, instructions count: 214
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1796bA.t(android.app.Activity, int):void");
    }

    public final boolean t0() {
        Objects.requireNonNull((ContextualSearchManager) this.I0);
        if (!SysUtils.isLowEndDevice()) {
            return false;
        }
        ContextualSearchManager contextualSearchManager = (ContextualSearchManager) this.I0;
        C4188pA pAVar = contextualSearchManager.n0;
        if (pAVar == null || pAVar.c() == null) {
            return true;
        }
        AbstractC0124Ca1 P = contextualSearchManager.G.P();
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) P;
        ea1.k.S(ea1.r()).b(new LoadUrlParams(contextualSearchManager.n0.c(), 0), 0, ea1.j());
        return true;
    }

    public void u0(boolean z, boolean z2) {
        if (z) {
            C3675mA r0 = r0();
            if (!r0.U) {
                r0.f(false);
                r0.U = true;
                r0.V = z2;
                r0.b0 = false;
                r0.X = r0.Y;
            }
        } else {
            r0().o();
        }
        this.F0.c = z;
    }

    public void v0(String str) {
        p0().b(true);
        s0().c(str);
        C1967cA cAVar = this.F0;
        Objects.requireNonNull(cAVar);
        cAVar.u = System.nanoTime();
        R();
    }

    @Override // defpackage.AbstractC0292Et0, defpackage.AbstractC0536It0
    public void z(int i, boolean z) {
        if (this.C0) {
            if (!z) {
                S(1, i);
            } else if (this.m0.intValue() != 1) {
                w(1, i, 218);
            }
        }
        this.J0 = false;
    }
}
