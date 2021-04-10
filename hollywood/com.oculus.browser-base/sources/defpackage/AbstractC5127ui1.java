package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: ui1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5127ui1 extends FrameLayout {
    public ImageView F;
    public TextView G;
    public Runnable H;
    public ImageView I;

    public AbstractC5127ui1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean isFocused() {
        return super.isFocused() || (isSelected() && !isInTouchMode());
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.I = (ImageView) findViewById(R.id.tile_view_icon);
        this.F = (ImageView) findViewById(R.id.offline_badge);
        this.G = (TextView) findViewById(R.id.tile_view_title);
    }

    public void setSelected(boolean z) {
        Runnable runnable;
        super.setSelected(z);
        if (z && (runnable = this.H) != null) {
            runnable.run();
        }
    }
}
