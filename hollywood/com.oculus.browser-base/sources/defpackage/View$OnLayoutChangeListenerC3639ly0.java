package defpackage;

import android.content.res.Resources;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.base.task.PostTask;

/* renamed from: ly0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC3639ly0 implements View.OnLayoutChangeListener {
    public final C2746gl0 F;
    public final View G;
    public final AbstractC2400ek H;
    public HH0 I;

    /* renamed from: J  reason: collision with root package name */
    public UH0 f10390J;
    public UH0 K;
    public Resources L;

    public View$OnLayoutChangeListenerC3639ly0(HH0 hh0, C2746gl0 gl0, View view, AbstractC2400ek ekVar, int i) {
        this.F = gl0;
        this.I = hh0;
        this.G = view;
        this.H = ekVar;
        view.addOnLayoutChangeListener(this);
    }

    public final boolean a(int i) {
        Resources resources = this.L;
        if (resources == null) {
            return false;
        }
        AbstractC2400ek ekVar = this.H;
        if ((i - (((C1551Zj) ekVar).M - resources.getDimensionPixelSize(R.dimen.f25670_resource_name_obfuscated_RES_2131166186))) - ((C1551Zj) this.H).O >= this.L.getDimensionPixelSize(R.dimen.f23750_resource_name_obfuscated_RES_2131165994)) {
            return true;
        }
        return false;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9;
        if (this.K != null && (i9 = i4 - i2) != i8 - i6) {
            PostTask.b(Zo1.f9374a, new RunnableC3297jy0(this, i9), 0);
        }
    }
}
