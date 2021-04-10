package org.chromium.chrome.browser.history;

import J.N;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HistoryItemView extends TR0 implements LargeIconBridge$LargeIconCallback {
    public ImageButton b0;
    public final KN0 c0 = AbstractC4055oO.a(getResources());
    public final int d0 = getResources().getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);
    public final int e0;
    public boolean f0;

    public HistoryItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getResources().getDimensionPixelSize(R.dimen.f18030_resource_name_obfuscated_RES_2131165422);
        this.e0 = context.getResources().getDimensionPixelSize(R.dimen.f18100_resource_name_obfuscated_RES_2131165429);
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        this.W = context.getColorStateList(R.color.f11310_resource_name_obfuscated_RES_2131099821);
    }

    @Override // defpackage.VR0
    public void f() {
        Object obj = this.f9084J;
        if (obj != null) {
            Objects.requireNonNull((C4415qX) obj);
        }
    }

    public final void m() {
        Object obj = this.f9084J;
        if (obj != null && !this.f0) {
            this.f0 = true;
            C4415qX qXVar = (C4415qX) obj;
        }
    }

    @Override // defpackage.TR0, defpackage.VR0
    public void onFinishInflate() {
        super.onFinishInflate();
        this.S.setImageResource(R.drawable.f29030_resource_name_obfuscated_RES_2131230943);
        C4353q8 q8Var = this.T;
        this.b0 = q8Var;
        q8Var.setImageResource(R.drawable.f28450_resource_name_obfuscated_RES_2131230885);
        this.b0.setContentDescription(getContext().getString(R.string.f60190_resource_name_obfuscated_RES_2131953336));
        ImageButton imageButton = this.b0;
        Context context = getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        imageButton.setImageTintList(context.getColorStateList(R.color.f11340_resource_name_obfuscated_RES_2131099824));
        this.b0.setOnClickListener(new View$OnClickListenerC4585rX(this));
        this.b0.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.b0.setPaddingRelative(getResources().getDimensionPixelSize(R.dimen.f19820_resource_name_obfuscated_RES_2131165601), getPaddingTop(), getResources().getDimensionPixelSize(R.dimen.f19820_resource_name_obfuscated_RES_2131165601), getPaddingBottom());
        int i = !N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "history.deleting_enabled") ? 8 : 4;
        this.b0.setVisibility(i);
        int i2 = i == 8 ? this.e0 : 0;
        LinearLayout linearLayout = this.R;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        linearLayout.setPaddingRelative(linearLayout.getPaddingStart(), this.R.getPaddingTop(), i2, this.R.getPaddingBottom());
    }

    @Override // org.chromium.components.favicon.LargeIconBridge$LargeIconCallback
    public void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        this.a0 = AbstractC4055oO.c(bitmap, ((C4415qX) this.f9084J).f11146a, i, this.c0, getResources(), this.d0);
        j(false);
    }
}
