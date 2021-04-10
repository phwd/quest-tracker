package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import org.chromium.components.browser_ui.contacts_picker.ContactView;
import org.chromium.components.browser_ui.contacts_picker.TopView;
import org.chromium.ui.widget.ChipView;

/* renamed from: Hs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0472Hs extends AbstractC5750yK0 implements VG0, AbstractC5513wy, AbstractC3091im1 {
    public static boolean I;

    /* renamed from: J  reason: collision with root package name */
    public static boolean f8185J;
    public static boolean K;
    public static boolean L;
    public static boolean M;
    public IC0 N;
    public TopView O;
    public String P;
    public ContentResolver Q;
    public ArrayList R;
    public String S;
    public C5683xy T;
    public boolean U;
    public ArrayList V;
    public WG0 W;
    public boolean X;
    public boolean Y;

    public C0472Hs(Context context) {
        this.W = new WG0(context, context.getResources().getDimensionPixelSize(R.dimen.f17660_resource_name_obfuscated_RES_2131165385));
    }

    @Override // defpackage.VG0
    public void D(String str) {
        if (this.Y && TextUtils.equals(str, this.S)) {
            this.Y = false;
            if (this.X) {
                this.X = false;
                this.W.Y(this);
            }
            ((C3638ly) this.R.get(0)).M = this.W.W(this.S).b;
            u();
        }
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        ArrayList arrayList = this.V;
        if (arrayList != null) {
            return arrayList.size();
        }
        ArrayList arrayList2 = this.R;
        if (arrayList2 == null || arrayList2.size() == 0) {
            return 0;
        }
        return this.R.size() + (!this.U ? 1 : 0);
    }

    @Override // defpackage.AbstractC5750yK0
    public int d(int i) {
        return (i != 0 || this.U) ? 1 : 0;
    }

    @Override // defpackage.AbstractC5750yK0
    public void i(RecyclerView recyclerView) {
        if (!this.X) {
            this.X = true;
            this.W.U(this);
        }
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        C3638ly lyVar;
        ArrayList arrayList;
        if (xk0.L == 1) {
            C5173uy uyVar = (C5173uy) xk0;
            boolean z = this.U;
            if (!z || (arrayList = this.V) == null) {
                lyVar = (C3638ly) this.R.get(i - (!z ? 1 : 0));
            } else {
                lyVar = (C3638ly) this.R.get(((Integer) arrayList.get(i)).intValue());
            }
            uyVar.c0 = lyVar;
            Drawable drawable = lyVar.M;
            if (drawable != null) {
                uyVar.b0.m(lyVar, ((BitmapDrawable) drawable).getBitmap());
                return;
            }
            Bitmap a2 = uyVar.Z.R.a(lyVar.F);
            if (a2 == null && !lyVar.F.equals("-1")) {
                C5589xP xPVar = new C5589xP(uyVar.c0.F, uyVar.a0, uyVar);
                uyVar.d0 = xPVar;
                Executor executor = AbstractC2032cb.f9616a;
                xPVar.f();
                ((ExecutorC1463Ya) executor).execute(xPVar.e);
            }
            uyVar.b0.m(lyVar, a2);
        }
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        int i2 = 0;
        if (i == 0) {
            TopView topView = (TopView) AbstractC2531fV.r(viewGroup, R.layout.f42090_resource_name_obfuscated_RES_2131624518, viewGroup, false);
            this.O = topView;
            ((TextView) topView.findViewById(R.id.explanation)).setText(FY0.a(topView.F.getString(R.string.f50840_resource_name_obfuscated_RES_2131952401, this.P), new EY0("<b>", "</b>", new StyleSpan(1))));
            TopView topView2 = this.O;
            IC0 ic0 = this.N;
            topView2.f10814J = ic0;
            topView2.P = this;
            if (ic0.V) {
                topView2.H.setOnCheckedChangeListener(topView2);
            } else {
                topView2.G.setVisibility(8);
            }
            TopView topView3 = this.O;
            IC0 ic02 = this.N;
            boolean z = ic02.W;
            boolean z2 = ic02.c0;
            boolean z3 = ic02.a0;
            boolean z4 = ic02.b0;
            boolean z5 = ic02.d0;
            topView3.K.setVisibility(z ? 0 : 8);
            topView3.L.setVisibility(z2 ? 0 : 8);
            topView3.M.setVisibility(z3 ? 0 : 8);
            topView3.N.setVisibility(z4 ? 0 : 8);
            ChipView chipView = topView3.O;
            if (!z5) {
                i2 = 8;
            }
            chipView.setVisibility(i2);
            IC0 ic03 = this.N;
            TopView topView4 = this.O;
            ic03.M = topView4;
            ArrayList arrayList = this.R;
            if (arrayList != null) {
                topView4.I.setText(NumberFormat.getInstance().format((long) arrayList.size()));
            }
            return new View$OnClickListenerC5217vC0(this, this.O);
        } else if (i != 1) {
            return null;
        } else {
            ContactView contactView = (ContactView) AbstractC2531fV.r(viewGroup, R.layout.f37430_resource_name_obfuscated_RES_2131624052, viewGroup, false);
            IC0 ic04 = this.N;
            contactView.c0 = ic04;
            C3209jS0 js0 = ic04.Q;
            contactView.d0 = js0;
            contactView.h(js0);
            return new C5173uy(contactView, this.N, this.Q);
        }
    }

    @Override // defpackage.AbstractC5750yK0
    public void n(RecyclerView recyclerView) {
        if (this.X) {
            this.X = false;
            this.W.Y(this);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0090  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void s(java.util.ArrayList r12) {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0472Hs.s(java.util.ArrayList):void");
    }

    public void t(String str) {
        if (str.equals("")) {
            ArrayList arrayList = this.V;
            if (arrayList != null) {
                arrayList.clear();
                this.V = null;
            } else {
                return;
            }
        } else {
            this.V = new ArrayList();
            Integer num = 0;
            String lowerCase = str.toLowerCase(Locale.getDefault());
            Iterator it = this.R.iterator();
            while (it.hasNext()) {
                C3638ly lyVar = (C3638ly) it.next();
                if (lyVar.G.toLowerCase(Locale.getDefault()).contains(lowerCase) || lyVar.b(I, K, L).toLowerCase(Locale.getDefault()).contains(lowerCase)) {
                    this.V.add(num);
                }
                num = Integer.valueOf(num.intValue() + 1);
            }
        }
        g();
    }

    public void u() {
        TopView topView = this.O;
        if (topView != null) {
            topView.I.setText(NumberFormat.getInstance().format((long) this.R.size()));
        }
        g();
    }
}
