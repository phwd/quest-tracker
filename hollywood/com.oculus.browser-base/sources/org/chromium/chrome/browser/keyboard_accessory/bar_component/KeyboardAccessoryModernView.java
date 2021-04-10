package org.chromium.chrome.browser.keyboard_accessory.bar_component;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KeyboardAccessoryModernView extends KeyboardAccessoryView {
    public static final /* synthetic */ int L = 0;
    public ImageView M;
    public TextView N;
    public Callback O;
    public final MK0 P = new C5876z50(this);

    public KeyboardAccessoryModernView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    @Override // org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r5 = this;
            android.view.View r0 = r5.d()
            androidx.recyclerview.widget.RecyclerView r1 = r5.G
            yK0 r2 = r1.T
            r3 = 0
            if (r0 == 0) goto L_0x0040
            if (r2 != 0) goto L_0x000e
            goto L_0x0040
        L_0x000e:
            int r1 = r1.indexOfChild(r0)
            int r2 = r2.b()
            r4 = 1
            if (r1 >= r2) goto L_0x001a
            goto L_0x0041
        L_0x001a:
            int r1 = r5.getLayoutDirection()
            if (r1 != r4) goto L_0x002a
            float r0 = r0.getX()
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 >= 0) goto L_0x0040
            goto L_0x003f
        L_0x002a:
            float r1 = r0.getX()
            int r0 = r0.getWidth()
            float r0 = (float) r0
            float r1 = r1 + r0
            androidx.recyclerview.widget.RecyclerView r0 = r5.G
            int r0 = r0.getWidth()
            float r0 = (float) r0
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0040
        L_0x003f:
            r3 = r4
        L_0x0040:
            r4 = r3
        L_0x0041:
            if (r4 == 0) goto L_0x0056
            org.chromium.base.Callback r0 = r5.O
            androidx.recyclerview.widget.RecyclerView r1 = r5.G
            android.view.View r2 = r5.d()
            int r1 = r1.indexOfChild(r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.onResult(r1)
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryModernView.b():void");
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView
    public void c(boolean z) {
        super.c(z);
        if (z) {
            RecyclerView recyclerView = this.G;
            recyclerView.getClass();
            recyclerView.post(new RunnableC5366w50(recyclerView));
            if (getVisibility() != 0 && !this.K) {
                int i = getLayoutDirection() == 1 ? 1 : -1;
                float x = this.G.getX();
                float f = x - ((((float) i) * 200.0f) * getContext().getResources().getDisplayMetrics().density);
                this.G.setTranslationX(f);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.G, "translationX", f, x);
                ofFloat.setDuration(300L);
                ofFloat.setInterpolator(new OvershootInterpolator(1.0f));
                ofFloat.start();
            }
        }
    }

    public final View d() {
        for (int childCount = this.G.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.G.getChildAt(childCount);
            if (childAt != null) {
                return childAt;
            }
        }
        return null;
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView
    public void onFinishInflate() {
        super.onFinishInflate();
        this.N = (TextView) findViewById(R.id.sheet_title);
        ImageView imageView = (ImageView) findViewById(R.id.show_keyboard);
        this.M = imageView;
        imageView.setImageDrawable(AbstractC5544x8.a(getContext(), R.drawable.f29540_resource_name_obfuscated_RES_2131230994));
        this.G.g(new B50(this, getResources().getDimensionPixelSize(R.dimen.f20140_resource_name_obfuscated_RES_2131165633)));
        this.G.i(this.P);
        RecyclerView recyclerView = this.G;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        recyclerView.setPaddingRelative(0, 0, 0, 0);
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        RecyclerView recyclerView = this.G;
        recyclerView.getClass();
        recyclerView.post(new RunnableC5196v50(recyclerView));
    }
}
