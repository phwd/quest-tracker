package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewStub;
import com.oculus.browser.R;
import org.chromium.base.TraceEvent;
import org.chromium.components.browser_ui.widget.ViewResourceFrameLayout;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ToolbarControlContainer extends OptimizedFrameLayout implements DA {
    public static final /* synthetic */ int G = 0;
    public final float H;
    public AbstractC5130uj1 I;

    /* renamed from: J  reason: collision with root package name */
    public ToolbarViewResourceFrameLayout f10790J;
    public AbstractC5364w41 K;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ToolbarViewResourceFrameLayout extends ViewResourceFrameLayout {
        public boolean I;

        public ToolbarViewResourceFrameLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // org.chromium.components.browser_ui.widget.ViewResourceFrameLayout
        public View$OnLayoutChangeListenerC2948hv1 d() {
            return new Rj1(this);
        }

        @Override // org.chromium.components.browser_ui.widget.ViewResourceFrameLayout
        public boolean e() {
            return this.I && getVisibility() == 0;
        }
    }

    public ToolbarControlContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.H = context.getResources().getDimension(R.dimen.f25730_resource_name_obfuscated_RES_2131166192);
    }

    public void d(int i) {
        TraceEvent j0 = TraceEvent.j0("ToolbarControlContainer.initWithToolbar");
        try {
            this.f10790J = (ToolbarViewResourceFrameLayout) findViewById(R.id.toolbar_container);
            ViewStub viewStub = (ViewStub) findViewById(R.id.toolbar_stub);
            viewStub.setLayoutResource(i);
            viewStub.inflate();
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public final boolean e(MotionEvent motionEvent) {
        return motionEvent.getY() <= this.H;
    }

    public final boolean f() {
        return Float.compare(0.0f, getTranslationY()) == 0 && this.f10790J.getVisibility() == 0;
    }

    public boolean gatherTransparentRegion(Region region) {
        float translationY = getTranslationY();
        setTranslationY(0.0f);
        int[] iArr = AbstractC4656rv1.f11234a;
        getLocationInWindow(iArr);
        region.op(iArr[0], iArr[1], (getRight() + iArr[0]) - getLeft(), (getBottom() + iArr[1]) - getTop(), Region.Op.DIFFERENCE);
        setTranslationY(translationY);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!f()) {
            return true;
        }
        if (this.K == null || e(motionEvent)) {
            return false;
        }
        return this.K.a(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.K == null) {
            return false;
        }
        if (!f()) {
            return true;
        }
        if (motionEvent.getActionMasked() != 0 || e(motionEvent)) {
            return this.K.a(motionEvent);
        }
        return true;
    }
}
