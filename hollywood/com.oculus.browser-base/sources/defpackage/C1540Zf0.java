package defpackage;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;

/* renamed from: Zf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1540Zf0 extends AbstractC5750yK0 {
    public final ArrayList I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public final LayoutInflater f9357J;
    public final Drawable K;
    public final Drawable L;
    public final Drawable M;
    public final Drawable N;
    public final /* synthetic */ DialogC1877bg0 O;

    public C1540Zf0(DialogC1877bg0 bg0) {
        this.O = bg0;
        this.f9357J = LayoutInflater.from(bg0.f9555J);
        this.K = AbstractC4783sh0.e(bg0.f9555J, R.attr.f6430_resource_name_obfuscated_RES_2130969089);
        this.L = AbstractC4783sh0.e(bg0.f9555J, R.attr.f6520_resource_name_obfuscated_RES_2130969098);
        this.M = AbstractC4783sh0.e(bg0.f9555J, R.attr.f6490_resource_name_obfuscated_RES_2130969095);
        this.N = AbstractC4783sh0.e(bg0.f9555J, R.attr.f6480_resource_name_obfuscated_RES_2130969094);
        s();
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.I.size();
    }

    @Override // defpackage.AbstractC5750yK0
    public int d(int i) {
        return ((C1357Wf0) this.I.get(i)).b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0063, code lost:
        if (r2 != null) goto L_0x0093;
     */
    @Override // defpackage.AbstractC5750yK0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j(defpackage.XK0 r9, int r10) {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1540Zf0.j(XK0, int):void");
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new C1296Vf0(this, this.f9357J.inflate(R.layout.f39450_resource_name_obfuscated_RES_2131624254, viewGroup, false));
        }
        if (i == 2) {
            return new C1479Yf0(this, this.f9357J.inflate(R.layout.f39460_resource_name_obfuscated_RES_2131624255, viewGroup, false));
        }
        Log.w("RecyclerAdapter", "Cannot create ViewHolder because of wrong view type");
        return null;
    }

    public void s() {
        this.I.clear();
        this.I.add(new C1357Wf0(this, this.O.f9555J.getString(R.string.f55210_resource_name_obfuscated_RES_2131952838)));
        for (C2392eh0 eh0 : this.O.L) {
            this.I.add(new C1357Wf0(this, eh0));
        }
        this.F.b();
    }
}
