package X;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* renamed from: X.17B  reason: invalid class name */
public final class AnonymousClass17B extends AnonymousClass174 {
    public final WeakReference<Context> A00;

    @Override // X.AnonymousClass174, android.content.res.Resources
    public final Drawable getDrawable(int i) throws Resources.NotFoundException {
        AnonymousClass17H r0;
        Drawable drawable = super.getDrawable(i);
        Context context = this.A00.get();
        if (!(drawable == null || context == null || (r0 = AnonymousClass17F.A01().A01) == null)) {
            r0.A8b(context, i, drawable);
        }
        return drawable;
    }
}
