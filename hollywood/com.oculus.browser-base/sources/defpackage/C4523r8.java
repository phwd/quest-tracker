package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: r8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4523r8 {

    /* renamed from: a  reason: collision with root package name */
    public final ImageView f11184a;
    public C0392Gi1 b;

    public C4523r8(ImageView imageView) {
        this.f11184a = imageView;
    }

    public void a() {
        C0392Gi1 gi1;
        Drawable drawable = this.f11184a.getDrawable();
        if (drawable != null) {
            int[] iArr = XI.f9200a;
        }
        if (drawable != null && (gi1 = this.b) != null) {
            C3840n8.f(drawable, gi1, this.f11184a.getDrawableState());
        }
    }

    public void b(AttributeSet attributeSet, int i) {
        int l;
        Context context = this.f11184a.getContext();
        int[] iArr = FJ0.k;
        C0514Ii1 q = C0514Ii1.q(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f11184a;
        AbstractC1920bu1.m(imageView, imageView.getContext(), iArr, attributeSet, q.b, i, 0);
        try {
            Drawable drawable = this.f11184a.getDrawable();
            if (!(drawable != null || (l = q.l(1, -1)) == -1 || (drawable = AbstractC5544x8.a(this.f11184a.getContext(), l)) == null)) {
                this.f11184a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                int[] iArr2 = XI.f9200a;
            }
            if (q.o(2)) {
                this.f11184a.setImageTintList(q.c(2));
            }
            if (q.o(3)) {
                this.f11184a.setImageTintMode(XI.c(q.j(3, -1), null));
            }
        } finally {
            q.b.recycle();
        }
    }

    public void c(int i) {
        if (i != 0) {
            Drawable a2 = AbstractC5544x8.a(this.f11184a.getContext(), i);
            if (a2 != null) {
                int[] iArr = XI.f9200a;
            }
            this.f11184a.setImageDrawable(a2);
        } else {
            this.f11184a.setImageDrawable(null);
        }
        a();
    }
}
