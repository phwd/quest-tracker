package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: sa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4762sa0 extends AbstractC1740ar1 {

    /* renamed from: a  reason: collision with root package name */
    public UH0 f11282a;
    public final GP0 b;
    public boolean c;
    public int d;
    public final AbstractC4422qa0 e;
    public final Runnable f;
    public final Context g;

    public C4762sa0(GP0 gp0, Callback callback, Context context, AbstractC4422qa0 qa0, Runnable runnable, View view) {
        this.b = gp0;
        this.e = qa0;
        this.f = runnable;
        this.g = context;
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f25730_resource_name_obfuscated_RES_2131166192);
        this.d = resources.getColor(R.color.f14470_resource_name_obfuscated_RES_2131100137);
        Map c2 = UH0.c(MP0.m);
        OH0 oh0 = MP0.c;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = view;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(oh0, lh0);
        MH0 mh0 = MP0.d;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(mh0, gh0);
        MH0 mh02 = MP0.b;
        GH0 gh02 = new GH0(null);
        gh02.f8081a = false;
        hashMap.put(mh02, gh02);
        NH0 nh0 = MP0.f8474a;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = dimensionPixelSize;
        hashMap.put(nh0, jh0);
        OH0 oh02 = MP0.f;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = runnable;
        hashMap.put(oh02, lh02);
        OH0 oh03 = MP0.e;
        LH0 lh03 = new LH0(null);
        lh03.f8415a = callback;
        hashMap.put(oh03, lh03);
        SH0 sh0 = MP0.h;
        JH0 jh02 = new JH0(null);
        jh02.f8282a = 0;
        hashMap.put(sh0, jh02);
        this.f11282a = new UH0(c2, null);
    }

    @Override // defpackage.AbstractC1740ar1
    public void d(boolean z) {
        if (z && this.e.e().h()) {
            this.b.a(this.f11282a);
            this.c = true;
        }
    }

    @Override // defpackage.AbstractC1740ar1
    public void e(boolean z) {
        this.f11282a.l(MP0.h, !DeviceFormFactor.a(this.g) && !this.e.a() && !AbstractC1270Uv.e(this.g) ? this.d : 0);
        if (z && !this.e.e().h()) {
            this.b.a(this.f11282a);
            this.c = true;
        } else if (!z && this.c) {
            this.b.b.a(true);
            this.c = false;
        }
    }
}
