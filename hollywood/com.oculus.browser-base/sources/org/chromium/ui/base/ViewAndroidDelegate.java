package org.chromium.ui.base;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewAndroidDelegate {

    /* renamed from: a  reason: collision with root package name */
    public ViewGroup f11021a;
    public int[] b = new int[2];
    public C1322Vq0 c = new C1322Vq0();
    public Callback d;
    public Pt1 e = new Pt1(this);

    public ViewAndroidDelegate(ViewGroup viewGroup) {
        this.f11021a = viewGroup;
    }

    public void a() {
    }

    public View acquireView() {
        ViewGroup viewGroup = this.f11021a;
        if (viewGroup == null || viewGroup.getParent() == null) {
            return null;
        }
        View view = new View(viewGroup.getContext());
        viewGroup.addView(view);
        return view;
    }

    public final View getContainerView() {
        return this.f11021a;
    }

    public int[] getDisplayFeature() {
        return null;
    }

    public int getViewportInsetBottom() {
        return 0;
    }

    public final int getXLocationOfContainerViewInWindow() {
        View containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        containerView.getLocationInWindow(this.b);
        return this.b[0];
    }

    public final int getXLocationOnScreen() {
        View containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        containerView.getLocationOnScreen(this.b);
        return this.b[0];
    }

    public final int getYLocationOfContainerViewInWindow() {
        View containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        containerView.getLocationInWindow(this.b);
        return this.b[1];
    }

    public final int getYLocationOnScreen() {
        View containerView = getContainerView();
        if (containerView == null) {
            return 0;
        }
        containerView.getLocationOnScreen(this.b);
        return this.b[1];
    }

    public final boolean hasFocus() {
        View containerView = getContainerView();
        if (containerView == null) {
            return false;
        }
        return AbstractC4656rv1.e(containerView);
    }

    public void onBackgroundColorChanged(int i) {
    }

    public void onBottomControlsChanged(int i, int i2) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCursorChanged(int r5) {
        /*
        // Method dump skipped, instructions count: 182
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.ViewAndroidDelegate.onCursorChanged(int):void");
    }

    public void onCursorChangedToCustom(Bitmap bitmap, int i, int i2) {
        this.f11021a.setPointerIcon(PointerIcon.create(bitmap, (float) i, (float) i2));
    }

    public void onTopControlsChanged(int i, int i2, int i3) {
    }

    public void onVerticalScrollDirectionChanged(boolean z, float f) {
    }

    public void removeView(View view) {
        ViewGroup viewGroup = this.f11021a;
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
    }

    public final void requestDisallowInterceptTouchEvent() {
        ViewGroup viewGroup = this.f11021a;
        if (viewGroup != null) {
            viewGroup.requestDisallowInterceptTouchEvent(true);
        }
    }

    public final void requestFocus() {
        ViewGroup viewGroup = this.f11021a;
        if (viewGroup != null) {
            if ((viewGroup.isInTouchMode() ? viewGroup.isFocusableInTouchMode() : viewGroup.isFocusable()) && !viewGroup.isFocused()) {
                viewGroup.requestFocus();
            }
        }
    }

    public final void requestUnbufferedDispatch(MotionEvent motionEvent) {
        ViewGroup viewGroup = this.f11021a;
        if (viewGroup != null) {
            for (int i = 0; i < motionEvent.getPointerCount(); i++) {
                if (motionEvent.getToolType(i) == 2) {
                    return;
                }
            }
            viewGroup.requestUnbufferedDispatch(motionEvent);
        }
    }

    public void setViewPosition(View view, float f, float f2, float f3, float f4, int i, int i2) {
        ViewGroup viewGroup = this.f11021a;
        if (viewGroup != null) {
            int round = Math.round(f3);
            int round2 = Math.round(f4);
            if (viewGroup.getLayoutDirection() == 1) {
                i = viewGroup.getMeasuredWidth() - Math.round(f3 + f);
            }
            if (round + i > viewGroup.getWidth()) {
                round = viewGroup.getWidth() - i;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.width = round;
            marginLayoutParams.height = round2;
            marginLayoutParams.setMarginStart(i);
            marginLayoutParams.topMargin = i2;
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public final boolean startDragAndDrop(String str, Bitmap bitmap) {
        ViewGroup viewGroup = this.f11021a;
        if (viewGroup == null) {
            return false;
        }
        ImageView imageView = new ImageView(viewGroup.getContext());
        imageView.setImageBitmap(bitmap);
        imageView.layout(0, 0, bitmap.getWidth(), bitmap.getHeight());
        return viewGroup.startDragAndDrop(ClipData.newPlainText(null, str), new View.DragShadowBuilder(imageView), null, 256);
    }
}
