package X;

import X.AnonymousClass1mY;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import javax.annotation.Nullable;

/* renamed from: X.1lg  reason: invalid class name and case insensitive filesystem */
public class C10241lg<DH extends AnonymousClass1mY> extends ImageView {
    public C10261li<DH> A00;
    public float A01 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public boolean A02 = false;
    public boolean A03 = false;
    public final C10451mi A04 = new C10451mi();

    private void A06() {
        Drawable drawable;
        if (this.A03 && (drawable = getDrawable()) != null) {
            boolean z = false;
            if (getVisibility() == 0) {
                z = true;
            }
            drawable.setVisible(z, false);
        }
    }

    public float getAspectRatio() {
        return this.A01;
    }

    @Nullable
    public AnonymousClass1m0 getController() {
        return this.A00.A00;
    }

    public DH getHierarchy() {
        DH dh = this.A00.A01;
        if (dh != null) {
            return dh;
        }
        throw null;
    }

    @Nullable
    public Drawable getTopLevelDrawable() {
        DH dh = this.A00.A01;
        if (dh == null) {
            return null;
        }
        return dh.A53();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002b, code lost:
        if (r3 == -2) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r9, int r10) {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C10241lg.onMeasure(int, int):void");
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        C10261li<DH> r1 = this.A00;
        if (!C10261li.A03(r1) || !r1.A00.A8E(motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setAspectRatio(float f) {
        if (f != this.A01) {
            this.A01 = f;
            requestLayout();
        }
    }

    public void setController(@Nullable AnonymousClass1m0 r2) {
        Drawable A53;
        this.A00.A04(r2);
        DH dh = this.A00.A01;
        if (dh == null) {
            A53 = null;
        } else {
            A53 = dh.A53();
        }
        super.setImageDrawable(A53);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if (r0.isVisible() != false) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setHierarchy(DH r6) {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C10241lg.setHierarchy(X.1mY):void");
    }

    /* JADX INFO: finally extract failed */
    private void A05() {
        try {
            C01060Pq.A00();
            if (!this.A02) {
                this.A02 = true;
                this.A00 = new C10261li<>();
                ColorStateList imageTintList = getImageTintList();
                if (imageTintList != null) {
                    setColorFilter(imageTintList.getDefaultColor());
                    this.A03 = false;
                }
            }
            C01060Pq.A00();
        } catch (Throwable th) {
            C01060Pq.A00();
            throw th;
        }
    }

    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        A06();
        C10261li<DH> r2 = this.A00;
        r2.A05.A00(EnumC00910Md.ON_HOLDER_ATTACH);
        r2.A03 = true;
        C10261li.A02(r2);
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        A06();
        C10261li<DH> r2 = this.A00;
        r2.A05.A00(EnumC00910Md.ON_HOLDER_DETACH);
        r2.A03 = false;
        C10261li.A02(r2);
    }

    public final void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        A06();
        C10261li<DH> r2 = this.A00;
        r2.A05.A00(EnumC00910Md.ON_HOLDER_ATTACH);
        r2.A03 = true;
        C10261li.A02(r2);
    }

    public final void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        A06();
        C10261li<DH> r2 = this.A00;
        r2.A05.A00(EnumC00910Md.ON_HOLDER_DETACH);
        r2.A03 = false;
        C10261li.A02(r2);
    }

    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        A06();
    }

    @Deprecated
    public void setImageBitmap(Bitmap bitmap) {
        A05();
        this.A00.A04(null);
        super.setImageBitmap(bitmap);
    }

    @Deprecated
    public void setImageDrawable(@Nullable Drawable drawable) {
        A05();
        this.A00.A04(null);
        super.setImageDrawable(drawable);
    }

    @Deprecated
    public void setImageResource(int i) {
        A05();
        this.A00.A04(null);
        super.setImageResource(i);
    }

    @Deprecated
    public void setImageURI(Uri uri) {
        A05();
        this.A00.A04(null);
        super.setImageURI(uri);
    }

    public final String toString() {
        String str;
        C00720Ig A002 = C00730Ih.A00(this);
        C10261li<DH> r0 = this.A00;
        if (r0 != null) {
            str = r0.toString();
        } else {
            str = "<no holder set>";
        }
        C00720Ig.A00(A002, "holder", str);
        return A002.toString();
    }

    public void setLegacyVisibilityHandlingEnabled(boolean z) {
        this.A03 = z;
    }

    public C10241lg(Context context) {
        super(context);
        A05();
    }

    public C10241lg(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        A05();
    }

    public C10241lg(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        A05();
    }

    @TargetApi(21)
    public C10241lg(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        A05();
    }
}
