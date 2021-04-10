package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AlertController$RecycleListView extends ListView {
    public final int F;
    public final int G;

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.u0);
        this.G = obtainStyledAttributes.getDimensionPixelOffset(0, -1);
        this.F = obtainStyledAttributes.getDimensionPixelOffset(1, -1);
    }
}
