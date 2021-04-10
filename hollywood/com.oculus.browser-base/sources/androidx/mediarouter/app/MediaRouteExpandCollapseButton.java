package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaRouteExpandCollapseButton extends C4353q8 {
    public final AnimationDrawable H;
    public final AnimationDrawable I;

    /* renamed from: J  reason: collision with root package name */
    public final String f9478J;
    public final String K;
    public boolean L;
    public View.OnClickListener M;

    public MediaRouteExpandCollapseButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Object obj = K2.f8337a;
        AnimationDrawable animationDrawable = (AnimationDrawable) context.getDrawable(R.drawable.f33980_resource_name_obfuscated_RES_2131231438);
        this.H = animationDrawable;
        AnimationDrawable animationDrawable2 = (AnimationDrawable) context.getDrawable(R.drawable.f33970_resource_name_obfuscated_RES_2131231437);
        this.I = animationDrawable2;
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(AbstractC4783sh0.c(context, 0), PorterDuff.Mode.SRC_IN);
        animationDrawable.setColorFilter(porterDuffColorFilter);
        animationDrawable2.setColorFilter(porterDuffColorFilter);
        String string = context.getString(R.string.f55270_resource_name_obfuscated_RES_2131952844);
        this.f9478J = string;
        this.K = context.getString(R.string.f55250_resource_name_obfuscated_RES_2131952842);
        setImageDrawable(animationDrawable.getFrame(0));
        setContentDescription(string);
        super.setOnClickListener(new View$OnClickListenerC5630xg0(this));
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.M = onClickListener;
    }
}
