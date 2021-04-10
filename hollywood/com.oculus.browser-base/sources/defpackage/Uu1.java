package defpackage;

import android.content.Context;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Objects;

/* renamed from: Uu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Uu1 extends RecyclerView {
    public final /* synthetic */ ViewPager2 k1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Uu1(ViewPager2 viewPager2, Context context) {
        super(context, null);
        this.k1 = viewPager2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public CharSequence getAccessibilityClassName() {
        Objects.requireNonNull(this.k1.a0);
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setFromIndex(this.k1.f9488J);
        accessibilityEvent.setToIndex(this.k1.f9488J);
        accessibilityEvent.setSource(((Su1) this.k1.a0).d);
        accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.k1.V && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.k1.V && super.onTouchEvent(motionEvent);
    }
}
