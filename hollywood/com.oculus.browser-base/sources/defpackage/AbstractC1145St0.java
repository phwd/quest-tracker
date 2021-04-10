package defpackage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: St0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1145St0 extends AbstractC3631lv1 {
    public AbstractC0292Et0 Q;

    public AbstractC1145St0(AbstractC0292Et0 et0, int i, int i2, Context context, ViewGroup viewGroup, IJ ij) {
        super(i, i2, context, viewGroup, ij);
        this.Q = et0;
    }

    public static String l(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("￼", " ").trim();
    }

    @Override // defpackage.AbstractC3631lv1
    public void a() {
        super.a();
        this.Q = null;
    }

    @Override // defpackage.AbstractC3631lv1
    public int d() {
        return View.MeasureSpec.makeMeasureSpec(this.Q.H(), 1073741824);
    }
}
