package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* renamed from: Tf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1175Tf1 implements N4 {
    public static final Set F = new HashSet();
    public final Context G;
    public final Handler H;
    public final O4 I;

    /* renamed from: J  reason: collision with root package name */
    public final C5441wa f8973J;
    public final Runnable K;
    public final PopupWindow.OnDismissListener L;
    public long M;
    public final String N;
    public final String O;
    public final boolean P;
    public View Q;

    public C1175Tf1(Context context, View view, int i, int i2, boolean z, C4390qK0 qk0, boolean z2) {
        this(context, view, context.getString(i), context.getString(i2), z, qk0, z2);
    }

    public static void d() {
        Iterator it = new HashSet(F).iterator();
        while (it.hasNext()) {
            ((C1175Tf1) it.next()).c();
        }
    }

    @Override // defpackage.N4
    public void a(boolean z, int i, int i2, int i3, int i4, Rect rect) {
        int i5;
        if (this.f8973J.O) {
            int centerX = rect.centerX() - i;
            C5441wa waVar = this.f8973J;
            waVar.L.getPadding(waVar.F);
            int i6 = (waVar.H / 2) + waVar.G + waVar.F.left;
            C5441wa waVar2 = this.f8973J;
            waVar2.L.getPadding(waVar2.F);
            i5 = AbstractC4089od0.c(centerX, i6, i3 - ((waVar2.H / 2) + (waVar2.G + waVar2.F.right)));
        } else {
            i5 = 0;
        }
        C5441wa waVar3 = this.f8973J;
        if (i5 != waVar3.M || z != waVar3.N) {
            waVar3.M = i5;
            waVar3.N = z;
            waVar3.onBoundsChange(waVar3.getBounds());
            waVar3.invalidateSelf();
        }
    }

    public void b(PopupWindow.OnDismissListener onDismissListener) {
        this.I.P.b(onDismissListener);
    }

    public void c() {
        this.I.K.dismiss();
    }

    public void e(boolean z) {
        O4 o4 = this.I;
        boolean z2 = this.P || z;
        o4.O = z2;
        o4.K.setOutsideTouchable(z2);
    }

    public void f() {
        if (!this.I.c()) {
            if (!this.I.c()) {
                long j = this.M;
                if (j != 0) {
                    this.H.postDelayed(this.K, j);
                }
            }
            this.I.d();
            F.add(this);
        }
    }

    public C1175Tf1(Context context, View view, String str, String str2, boolean z, C4390qK0 qk0, boolean z2) {
        this.K = new RunnableC1053Rf1(this);
        C1114Sf1 sf1 = new C1114Sf1(this);
        this.L = sf1;
        this.M = 0;
        this.G = context;
        String str3 = str;
        this.N = str3;
        this.O = str2;
        this.P = z2;
        C5441wa waVar = new C5441wa(context);
        this.f8973J = waVar;
        waVar.O = z;
        waVar.invalidateSelf();
        View inflate = LayoutInflater.from(context).inflate(R.layout.f41980_resource_name_obfuscated_RES_2131624507, (ViewGroup) null);
        ((TextView) inflate).setText(z2 ? str2 : str3);
        this.Q = inflate;
        inflate.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        O4 o4 = new O4(context, view, waVar, this.Q, qk0);
        this.I = o4;
        o4.V = context.getResources().getDimensionPixelSize(R.dimen.f25910_resource_name_obfuscated_RES_2131166210);
        o4.Z = 1;
        o4.Q = this;
        this.H = new Handler();
        o4.K.setAnimationStyle(R.style.f72360_resource_name_obfuscated_RES_2132017809);
        b(sf1);
        if (z2) {
            e(true);
        }
        int color = context.getResources().getColor(R.color.f11100_resource_name_obfuscated_RES_2131099800);
        waVar.L.setTint(color);
        waVar.K.setColor(color);
        waVar.invalidateSelf();
    }
}
