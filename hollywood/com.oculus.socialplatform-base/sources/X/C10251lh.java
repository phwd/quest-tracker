package X;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lh  reason: invalid class name and case insensitive filesystem */
public final class C10251lh extends AnonymousClass1lb implements AnonymousClass1mo {
    @Nullable
    @VisibleForTesting
    public Drawable A00 = null;
    @Nullable
    public C10261li A01;

    @Override // X.AnonymousClass1lb
    public final int getIntrinsicHeight() {
        return -1;
    }

    @Override // X.AnonymousClass1lb
    public final int getIntrinsicWidth() {
        return -1;
    }

    @Override // X.AnonymousClass1lb
    public final boolean setVisible(boolean z, boolean z2) {
        EnumC00910Md r0;
        C10261li r2 = this.A01;
        if (!(r2 == null || r2.A04 == z)) {
            C00920Me r1 = r2.A05;
            if (z) {
                r0 = EnumC00910Md.ON_DRAWABLE_SHOW;
            } else {
                r0 = EnumC00910Md.ON_DRAWABLE_HIDE;
            }
            r1.A00(r0);
            r2.A04 = z;
            C10261li.A02(r2);
        }
        return super.setVisible(z, z2);
    }

    public C10251lh(Drawable drawable) {
        super(drawable);
    }

    @Override // X.AnonymousClass1lb
    @SuppressLint({"WrongCall"})
    public final void draw(Canvas canvas) {
        if (isVisible()) {
            C10261li r5 = this.A01;
            if (r5 != null && !r5.A02) {
                AnonymousClass0J5.A02(C00920Me.class, "%x: Draw requested for a non-attached controller %x. %s", Integer.valueOf(System.identityHashCode(r5)), Integer.valueOf(System.identityHashCode(r5.A00)), r5.toString());
                r5.A03 = true;
                r5.A04 = true;
                C10261li.A02(r5);
            }
            super.draw(canvas);
            Drawable drawable = this.A00;
            if (drawable != null) {
                drawable.setBounds(getBounds());
                this.A00.draw(canvas);
            }
        }
    }

    @Override // X.AnonymousClass1mo
    public final void AAI(@Nullable C10261li r1) {
        this.A01 = r1;
    }
}
