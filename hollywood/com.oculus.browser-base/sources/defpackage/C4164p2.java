package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageView;
import com.oculus.browser.R;

/* renamed from: p2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4164p2 extends AppCompatImageView implements AbstractC4846t2 {
    public final /* synthetic */ C4676s2 H;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4164p2(C4676s2 s2Var, Context context) {
        super(context, null, R.attr.f1590_resource_name_obfuscated_RES_2130968605);
        this.H = s2Var;
        setClickable(true);
        setFocusable(true);
        setVisibility(0);
        setEnabled(true);
        Il1.a(this, getContentDescription());
        setOnTouchListener(new C3993o2(this, this, s2Var));
    }

    @Override // defpackage.AbstractC4846t2
    public boolean a() {
        return false;
    }

    @Override // defpackage.AbstractC4846t2
    public boolean b() {
        return false;
    }

    public boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        playSoundEffect(0);
        this.H.n();
        return true;
    }

    public boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        Drawable drawable = getDrawable();
        Drawable background = getBackground();
        if (!(drawable == null || background == null)) {
            int width = getWidth();
            int height = getHeight();
            int max = Math.max(width, height) / 2;
            int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
            int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
            background.setHotspotBounds(paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
        }
        return frame;
    }
}
