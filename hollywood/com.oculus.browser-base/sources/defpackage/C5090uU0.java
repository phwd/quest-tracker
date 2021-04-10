package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.List;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: uU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5090uU0 extends AbstractC4277pj implements AdapterView.OnItemClickListener {
    public final Context F;
    public final X60 G;
    public final View$OnLayoutChangeListenerC5940zU0 H;
    public ViewGroup I;

    /* renamed from: J  reason: collision with root package name */
    public C2189dU0 f11413J;
    public ScrollView K;

    public C5090uU0(Context context, X60 x60, View$OnLayoutChangeListenerC5940zU0 zu0, C2189dU0 du0) {
        this.F = context;
        this.G = x60;
        this.H = zu0;
        this.f11413J = du0;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41450_resource_name_obfuscated_RES_2131624454, (ViewGroup) null);
        this.I = viewGroup;
        this.K = (ScrollView) viewGroup.findViewById(R.id.share_sheet_scrollview);
    }

    public static void w(UH0 uh0, ViewGroup viewGroup, KH0 kh0) {
        TH0 th0 = AU0.f7673a;
        if (th0.equals(kh0)) {
            ((ImageView) viewGroup.findViewById(R.id.icon)).setImageDrawable((Drawable) uh0.g(th0));
            return;
        }
        TH0 th02 = AU0.b;
        if (th02.equals(kh0)) {
            ((TextView) viewGroup.findViewById(R.id.text)).setText((CharSequence) uh0.g(th02));
            return;
        }
        TH0 th03 = AU0.c;
        if (th03.equals(kh0)) {
            viewGroup.findViewById(R.id.layout).setOnClickListener((View.OnClickListener) uh0.g(th03));
            return;
        }
        QH0 qh0 = AU0.d;
        if (qh0.equals(kh0)) {
            ((TextView) viewGroup.findViewById(R.id.display_new)).setVisibility(uh0.h(qh0) ? 0 : 8);
        }
    }

    public final void A(List list, RecyclerView recyclerView, boolean z) {
        YH0 yh0;
        C4935tb0 tb0 = new C4935tb0();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            tb0.q(new C4765sb0(0, (UH0) it.next()));
        }
        JW0 jw0 = new JW0(tb0);
        L70 l70 = new L70(R.layout.f41460_resource_name_obfuscated_RES_2131624455);
        if (z) {
            yh0 = new C4410qU0();
        } else {
            yh0 = new C4580rU0();
        }
        jw0.v(0, l70, yh0);
        recyclerView.q0(jw0);
        recyclerView.t0(new LinearLayoutManager(0, false));
    }

    public final void B(Drawable drawable) {
        ImageView imageView = (ImageView) this.I.findViewById(R.id.image_preview);
        imageView.setImageDrawable(drawable);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        int dimensionPixelSize = this.F.getResources().getDimensionPixelSize(R.dimen.f25000_resource_name_obfuscated_RES_2131166119);
        imageView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
    }

    public final void C(String str, String str2) {
        TextView textView = (TextView) this.I.findViewById(R.id.title_preview);
        textView.setText(str);
        ((TextView) this.I.findViewById(R.id.subtitle_preview)).setText(str2);
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
        }
    }

    public final void D(int i) {
        AbstractC3153j7.i((TextView) this.I.findViewById(R.id.title_preview), i);
    }

    @Override // defpackage.AbstractC4277pj
    public void f() {
        AbstractC2018cU0 cu0;
        View$OnLayoutChangeListenerC5940zU0 zu0 = this.H;
        C2189dU0 du0 = zu0.T;
        if (!(du0 == null || (cu0 = du0.i) == null)) {
            cu0.a();
        }
        WindowAndroid windowAndroid = zu0.V;
        if (windowAndroid != null) {
            windowAndroid.W.c(zu0);
            zu0.V = null;
        }
        M2 m2 = zu0.R;
        if (m2 != null) {
            m2.b(zu0);
            zu0.R = null;
        }
    }

    @Override // defpackage.AbstractC4277pj
    public View g() {
        return this.I;
    }

    @Override // defpackage.AbstractC4277pj
    public float h() {
        return -1.0f;
    }

    @Override // defpackage.AbstractC4277pj
    public int j() {
        return -2;
    }

    @Override // defpackage.AbstractC4277pj
    public int k() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public int l() {
        return R.string.f61260_resource_name_obfuscated_RES_2131953443;
    }

    @Override // defpackage.AbstractC4277pj
    public int m() {
        return R.string.f61180_resource_name_obfuscated_RES_2131953435;
    }

    @Override // defpackage.AbstractC4277pj
    public int n() {
        return R.string.f61270_resource_name_obfuscated_RES_2131953444;
    }

    @Override // defpackage.AbstractC4277pj
    public int o() {
        return R.string.f61280_resource_name_obfuscated_RES_2131953445;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
    }

    @Override // defpackage.AbstractC4277pj
    public View p() {
        return null;
    }

    @Override // defpackage.AbstractC4277pj
    public int q() {
        ScrollView scrollView = this.K;
        if (scrollView != null) {
            return scrollView.getScrollY();
        }
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean v() {
        return true;
    }

    public void x(List list) {
        RecyclerView recyclerView = (RecyclerView) this.I.findViewById(R.id.share_sheet_chrome_apps);
        if (list != null && list.size() > 0) {
            this.I.findViewById(R.id.share_sheet_divider).setVisibility(0);
            recyclerView.setVisibility(0);
            A(list, recyclerView, true);
            recyclerView.i(new C4920tU0("SharingHubAndroid.FirstPartyAppsScrolled"));
        }
    }

    public final void y(String str) {
        if (!str.isEmpty()) {
            this.G.b(new GURL(str), this.F.getResources().getDimensionPixelSize(R.dimen.f18030_resource_name_obfuscated_RES_2131165422), new C4750sU0(this));
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r6.equals("image") == false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String z(java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 164
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5090uU0.z(java.lang.String):java.lang.String");
    }
}
