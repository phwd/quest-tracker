package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;

/* renamed from: ll1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3601ll1 extends YI {
    public final Drawable.Callback G;
    public int H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public int f10370J;
    public int K;
    public boolean L;
    public boolean M;

    public C3601ll1(Context context, Drawable.Callback callback) {
        super(AbstractC3153j7.c(context.getResources(), R.drawable.f34320_resource_name_obfuscated_RES_2131231472));
        this.G = callback;
        setCallback(callback);
    }

    public Drawable.Callback getCallback() {
        return this.M ? super.getCallback() : this.G;
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        if (!this.L) {
            this.H = i;
            this.I = i2;
            this.f10370J = i3;
            this.K = i4;
            this.M = true;
        } else {
            this.M = false;
        }
        this.L = false;
    }

    @Override // defpackage.YI
    public boolean setVisible(boolean z, boolean z2) {
        return false;
    }
}
