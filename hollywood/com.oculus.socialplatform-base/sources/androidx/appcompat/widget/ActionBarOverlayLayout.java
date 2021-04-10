package androidx.appcompat.widget;

import X.AbstractC05480vn;
import X.AbstractC06001Eq;
import X.AbstractC11941tc;
import X.AnonymousClass006;
import X.AnonymousClass02C;
import X.AnonymousClass057;
import X.AnonymousClass07K;
import X.AnonymousClass07n;
import X.AnonymousClass07o;
import X.AnonymousClass0WA;
import X.AnonymousClass1EQ;
import X.AnonymousClass1En;
import X.AnonymousClass1Eo;
import X.AnonymousClass1Es;
import X.AnonymousClass1rl;
import X.C003307q;
import X.C05991Em;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;

@SuppressLint({"UnknownNullness"})
@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public class ActionBarOverlayLayout extends ViewGroup implements AnonymousClass1rl, AbstractC05480vn, AnonymousClass0WA {
    public static final int[] A0V = {R.attr.actionBarSize, 16842841};
    public ViewPropertyAnimator A00;
    public boolean A01;
    public OverScroller A02;
    public ActionBarContainer A03;
    public boolean A04;
    public boolean A05;
    public int A06;
    public int A07;
    public int A08;
    public int A09;
    public Drawable A0A;
    public AnonymousClass1Es A0B;
    public ContentFrameLayout A0C;
    public AbstractC06001Eq A0D;
    @NonNull
    public C003307q A0E;
    @NonNull
    public C003307q A0F;
    @NonNull
    public C003307q A0G;
    @NonNull
    public C003307q A0H;
    public boolean A0I;
    public boolean A0J;
    public final AnimatorListenerAdapter A0K;
    public final Runnable A0L;
    public final Runnable A0M;
    public final Rect A0N;
    public final Rect A0O;
    public final Rect A0P;
    public final Rect A0Q;
    public final Rect A0R;
    public final Rect A0S;
    public final Rect A0T;
    public final AnonymousClass07K A0U;

    public final void onMeasure(int i, int i2) {
        boolean z;
        int measuredHeight;
        C003307q A002;
        A00();
        measureChildWithMargins(this.A03, i, 0, i2, 0);
        AnonymousClass1EQ r2 = (AnonymousClass1EQ) this.A03.getLayoutParams();
        int max = Math.max(0, this.A03.getMeasuredWidth() + r2.leftMargin + r2.rightMargin);
        int max2 = Math.max(0, this.A03.getMeasuredHeight() + r2.topMargin + r2.bottomMargin);
        int combineMeasuredStates = View.combineMeasuredStates(0, this.A03.getMeasuredState());
        if ((getWindowSystemUiVisibility() & 256) != 0) {
            z = true;
            measuredHeight = this.A06;
            if (this.A04 && this.A03.A05 != null) {
                measuredHeight += measuredHeight;
            }
        } else {
            z = false;
            measuredHeight = this.A03.getVisibility() != 8 ? this.A03.getMeasuredHeight() : 0;
        }
        Rect rect = this.A0P;
        rect.set(this.A0N);
        C003307q r6 = this.A0E;
        this.A0F = r6;
        if (this.A05 || z) {
            AnonymousClass057 A003 = AnonymousClass057.A00(r6.A00.A01().A01, this.A0F.A00.A01().A03 + measuredHeight, this.A0F.A00.A01().A02, this.A0F.A00.A01().A00 + 0);
            AnonymousClass07o r0 = new AnonymousClass07n(this.A0F).A00;
            r0.A02(A003);
            A002 = r0.A00();
        } else {
            rect.top += measuredHeight;
            rect.bottom += 0;
            A002 = r6.A00.A06(0, measuredHeight, 0, 0);
        }
        this.A0F = A002;
        A02(this.A0C, rect, true);
        if (!this.A0H.equals(this.A0F)) {
            C003307q r1 = this.A0F;
            this.A0H = r1;
            ContentFrameLayout contentFrameLayout = this.A0C;
            WindowInsets A022 = r1.A02();
            if (A022 != null && !contentFrameLayout.dispatchApplyWindowInsets(A022).equals(A022)) {
                C003307q.A01(A022);
            }
        }
        measureChildWithMargins(this.A0C, i, 0, i2, 0);
        AnonymousClass1EQ r3 = (AnonymousClass1EQ) this.A0C.getLayoutParams();
        int max3 = Math.max(max, this.A0C.getMeasuredWidth() + r3.leftMargin + r3.rightMargin);
        int max4 = Math.max(max2, this.A0C.getMeasuredHeight() + r3.topMargin + r3.bottomMargin);
        int combineMeasuredStates2 = View.combineMeasuredStates(combineMeasuredStates, this.A0C.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(max3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, combineMeasuredStates2), View.resolveSizeAndState(Math.max(max4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, combineMeasuredStates2 << 16));
    }

    public final boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public final void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public void setShowingForActionMode(boolean z) {
    }

    public void setUiOptions(int i) {
    }

    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    private final void A00() {
        AbstractC06001Eq wrapper;
        if (this.A0C == null) {
            this.A0C = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.A03 = (ActionBarContainer) findViewById(R.id.action_bar_container);
            View findViewById = findViewById(R.id.action_bar);
            if (findViewById instanceof AbstractC06001Eq) {
                wrapper = (AbstractC06001Eq) findViewById;
            } else if (findViewById instanceof Toolbar) {
                wrapper = ((Toolbar) findViewById).getWrapper();
            } else {
                throw new IllegalStateException(AnonymousClass006.A07("Can't make a decor toolbar out of ", findViewById.getClass().getSimpleName()));
            }
            this.A0D = wrapper;
        }
    }

    public final void A03() {
        removeCallbacks(this.A0M);
        removeCallbacks(this.A0L);
        ViewPropertyAnimator viewPropertyAnimator = this.A00;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    @Override // X.AbstractC05480vn
    public final void A7N(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // X.AbstractC05480vn
    public final void A7P(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // X.AbstractC05480vn
    public final boolean A86(View view, View view2, int i, int i2) {
        if (i2 != 0 || !onStartNestedScroll(view, view2, i)) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC05480vn
    public final void A88(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new AnonymousClass1EQ();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.A03;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    public int getNestedScrollAxes() {
        AnonymousClass07K r0 = this.A0U;
        return r0.A01 | r0.A00;
    }

    public final boolean onNestedFling(View view, float f, float f2, boolean z) {
        Runnable runnable;
        if (!this.A0I || !z) {
            return false;
        }
        this.A02.fling(0, 0, 0, (int) f2, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.A02.getFinalY() > this.A03.getHeight()) {
            A03();
            runnable = this.A0L;
        } else {
            A03();
            runnable = this.A0M;
        }
        runnable.run();
        this.A01 = true;
        return true;
    }

    public final void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.A07 + i2;
        this.A07 = i5;
        setActionBarHideOffset(i5);
    }

    public final void onNestedScrollAccepted(View view, View view2, int i) {
        this.A0U.A01 = i;
        this.A07 = getActionBarHideOffset();
        A03();
        AnonymousClass1Es r0 = this.A0B;
        if (r0 != null) {
            r0.A6t();
        }
    }

    public final boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.A03.getVisibility() != 0) {
            return false;
        }
        return this.A0I;
    }

    public final void onStopNestedScroll(View view) {
        Runnable runnable;
        if (this.A0I && !this.A01) {
            if (this.A07 <= this.A03.getHeight()) {
                A03();
                runnable = this.A0M;
            } else {
                A03();
                runnable = this.A0L;
            }
            postDelayed(runnable, 600);
        }
    }

    public void setActionBarVisibilityCallback(AnonymousClass1Es r3) {
        this.A0B = r3;
        if (getWindowToken() != null) {
            this.A0B.A8I(this.A09);
            int i = this.A08;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                requestApplyInsets();
            }
        }
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.A0I) {
            this.A0I = z;
            if (!z) {
                A03();
                setActionBarHideOffset(0);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0011, code lost:
        if (getContext().getApplicationInfo().targetSdkVersion >= 19) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setOverlayMode(boolean r4) {
        /*
            r3 = this;
            r3.A05 = r4
            if (r4 == 0) goto L_0x0013
            android.content.Context r0 = r3.getContext()
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()
            int r2 = r0.targetSdkVersion
            r1 = 19
            r0 = 1
            if (r2 < r1) goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            r3.A0J = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.setOverlayMode(boolean):void");
    }

    private void A01(Context context) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(A0V);
        boolean z = false;
        this.A06 = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        this.A0A = drawable;
        boolean z2 = false;
        if (drawable == null) {
            z2 = true;
        }
        setWillNotDraw(z2);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion < 19) {
            z = true;
        }
        this.A0J = z;
        this.A02 = new OverScroller(context);
    }

    public static boolean A02(@NonNull View view, @NonNull Rect rect, boolean z) {
        boolean z2;
        int i;
        AnonymousClass1EQ r3 = (AnonymousClass1EQ) view.getLayoutParams();
        int i2 = r3.leftMargin;
        int i3 = rect.left;
        if (i2 != i3) {
            r3.leftMargin = i3;
            z2 = true;
        } else {
            z2 = false;
        }
        int i4 = r3.topMargin;
        int i5 = rect.top;
        if (i4 != i5) {
            r3.topMargin = i5;
            z2 = true;
        }
        int i6 = r3.rightMargin;
        int i7 = rect.right;
        if (i6 != i7) {
            r3.rightMargin = i7;
            z2 = true;
        }
        if (!z || r3.bottomMargin == (i = rect.bottom)) {
            return z2;
        }
        r3.bottomMargin = i;
        return true;
    }

    @Override // X.AnonymousClass1rl
    public final boolean A1z() {
        A00();
        return this.A0D.A1z();
    }

    @Override // X.AnonymousClass1rl
    public final void A2e() {
        A00();
        this.A0D.A2d();
    }

    @Override // X.AnonymousClass1rl
    public final boolean A5V() {
        A00();
        return this.A0D.A5V();
    }

    @Override // X.AnonymousClass1rl
    public final void A5d(int i) {
        A00();
        if (i != 2 && i != 5 && i == 109) {
            setOverlayMode(true);
        }
    }

    @Override // X.AnonymousClass1rl
    public final boolean A66() {
        A00();
        return this.A0D.A66();
    }

    @Override // X.AnonymousClass1rl
    public final boolean A67() {
        A00();
        return this.A0D.A67();
    }

    @Override // X.AnonymousClass1rl
    public final void AA1() {
        A00();
        this.A0D.AA1();
    }

    @Override // X.AnonymousClass1rl
    public final boolean AAR() {
        A00();
        return this.A0D.AAR();
    }

    public final void draw(Canvas canvas) {
        int i;
        super.draw(canvas);
        if (this.A0A != null && !this.A0J) {
            if (this.A03.getVisibility() == 0) {
                i = (int) (((float) this.A03.getBottom()) + this.A03.getTranslationY() + 0.5f);
            } else {
                i = 0;
            }
            this.A0A.setBounds(0, i, getWidth(), this.A0A.getIntrinsicHeight() + i);
            this.A0A.draw(canvas);
        }
    }

    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    public CharSequence getTitle() {
        A00();
        return this.A0D.getTitle();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x007a, code lost:
        if (r5 != false) goto L_0x0062;
     */
    @androidx.annotation.RequiresApi(21)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.WindowInsets onApplyWindowInsets(@androidx.annotation.NonNull android.view.WindowInsets r9) {
        /*
        // Method dump skipped, instructions count: 129
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.onApplyWindowInsets(android.view.WindowInsets):android.view.WindowInsets");
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        A01(getContext());
        requestApplyInsets();
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        A03();
    }

    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                AnonymousClass1EQ r0 = (AnonymousClass1EQ) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = r0.leftMargin + paddingLeft;
                int i7 = r0.topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    public final void onWindowSystemUiVisibilityChanged(int i) {
        super.onWindowSystemUiVisibilityChanged(i);
        A00();
        int i2 = this.A08 ^ i;
        this.A08 = i;
        boolean z = false;
        boolean z2 = false;
        if ((i & 4) == 0) {
            z2 = true;
        }
        if ((i & 256) != 0) {
            z = true;
        }
        AnonymousClass1Es r1 = this.A0B;
        if (r1 != null) {
            r1.A2l(!z);
            if (z2 || !z) {
                r1.AAQ();
            } else {
                r1.A5U();
            }
        }
        if ((i2 & 256) != 0 && this.A0B != null) {
            requestApplyInsets();
        }
    }

    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.A09 = i;
        AnonymousClass1Es r0 = this.A0B;
        if (r0 != null) {
            r0.A8I(i);
        }
    }

    public void setActionBarHideOffset(int i) {
        A03();
        this.A03.setTranslationY((float) (-Math.max(0, Math.min(i, this.A03.getHeight()))));
    }

    public void setLogo(int i) {
        A00();
        this.A0D.A9z(i);
    }

    @Override // X.AnonymousClass1rl
    public void setMenu(Menu menu, AbstractC11941tc r3) {
        A00();
        this.A0D.setMenu(menu, r3);
    }

    @Override // X.AnonymousClass1rl
    public void setWindowCallback(Window.Callback callback) {
        A00();
        this.A0D.setWindowCallback(callback);
    }

    @Override // X.AnonymousClass1rl
    public void setWindowTitle(CharSequence charSequence) {
        A00();
        this.A0D.setWindowTitle(charSequence);
    }

    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof AnonymousClass1EQ;
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.A04 = z;
    }

    @Override // X.AbstractC05480vn
    public final void A7M(View view, int i, int i2, int[] iArr, int i3) {
    }

    @Override // X.AnonymousClass0WA
    public final void A7O(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        A7N(view, i, i2, i3, i4, i5);
    }

    public ActionBarOverlayLayout(@NonNull Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A09 = 0;
        this.A0N = new Rect();
        this.A0R = new Rect();
        this.A0P = new Rect();
        this.A0O = new Rect();
        this.A0S = new Rect();
        this.A0Q = new Rect();
        this.A0T = new Rect();
        C003307q r0 = C003307q.A01;
        this.A0E = r0;
        this.A0G = r0;
        this.A0F = r0;
        this.A0H = r0;
        this.A0K = new C05991Em(this);
        this.A0M = new AnonymousClass1En(this);
        this.A0L = new AnonymousClass1Eo(this);
        A01(context);
        this.A0U = new AnonymousClass07K();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new AnonymousClass1EQ(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new AnonymousClass1EQ(layoutParams);
    }

    public void setIcon(int i) {
        A00();
        this.A0D.A9w(i);
    }

    public void setIcon(Drawable drawable) {
        A00();
        this.A0D.A9x(drawable);
    }
}
