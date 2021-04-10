package defpackage;

import android.content.res.Resources;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.base.Callback;

/* renamed from: qp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4467qp0 extends ClickableSpan {
    public final int F;
    public final Callback G;

    public C4467qp0(Resources resources, int i, Callback callback) {
        this.F = resources.getColor(i);
        this.G = callback;
    }

    public final void onClick(View view) {
        this.G.onResult(view);
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.setUnderlineText(false);
        textPaint.setColor(this.F);
    }

    public C4467qp0(Resources resources, Callback callback) {
        this.F = resources.getColor(R.color.f11590_resource_name_obfuscated_RES_2131099849);
        this.G = callback;
    }
}
