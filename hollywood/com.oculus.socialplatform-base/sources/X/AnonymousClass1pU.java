package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* renamed from: X.1pU  reason: invalid class name */
public final class AnonymousClass1pU extends AnonymousClass1pT {
    public final WeakReference<Context> A00;

    @Override // X.AnonymousClass1pT, android.content.res.Resources
    public final Drawable getDrawable(int i) throws Resources.NotFoundException {
        AbstractC10831pk r0;
        Drawable drawable = super.getDrawable(i);
        Context context = this.A00.get();
        if (!(drawable == null || context == null || (r0 = C10821pj.A01().A01) == null)) {
            r0.AAi(context, i, drawable);
        }
        return drawable;
    }
}
