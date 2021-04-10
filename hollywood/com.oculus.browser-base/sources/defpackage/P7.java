package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

/* renamed from: P7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P7 {

    /* renamed from: a  reason: collision with root package name */
    public final CompoundButton f8669a;
    public ColorStateList b = null;
    public PorterDuff.Mode c = null;
    public boolean d = false;
    public boolean e = false;
    public boolean f;

    public P7(CompoundButton compoundButton) {
        this.f8669a = compoundButton;
    }

    public void a() {
        Drawable buttonDrawable = this.f8669a.getButtonDrawable();
        if (buttonDrawable == null) {
            return;
        }
        if (this.d || this.e) {
            Drawable mutate = buttonDrawable.mutate();
            if (this.d) {
                mutate.setTintList(this.b);
            }
            if (this.e) {
                mutate.setTintMode(this.c);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.f8669a.getDrawableState());
            }
            this.f8669a.setButtonDrawable(mutate);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(android.util.AttributeSet r10, int r11) {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.P7.b(android.util.AttributeSet, int):void");
    }
}
