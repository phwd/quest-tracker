package defpackage;

import J.N;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeProvider;
import android.widget.FrameLayout;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.SmartClipProvider;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.ViewAndroidDelegate;

/* renamed from: Yy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractViewGroup$OnHierarchyChangeListenerC1520Yy extends FrameLayout implements AbstractC2432eu1, SmartClipProvider, ViewGroup.OnHierarchyChangeListener, View.OnSystemUiVisibilityChangeListener {
    public static final int F = View.MeasureSpec.makeMeasureSpec(0, 0);
    public WebContents G;
    public final C1322Vq0 H = new C1322Vq0();
    public final C1322Vq0 I = new C1322Vq0();

    /* renamed from: J  reason: collision with root package name */
    public AbstractC2603fu1 f9309J;
    public int K;
    public int L;
    public final WL M;

    public AbstractViewGroup$OnHierarchyChangeListenerC1520Yy(Context context, WL wl, WebContents webContents) {
        super(context, null, 16842885);
        int i = F;
        this.K = i;
        this.L = i;
        if (getScrollBarStyle() == 0) {
            setHorizontalScrollBarEnabled(false);
            setVerticalScrollBarEnabled(false);
        }
        this.G = webContents;
        this.M = wl;
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (Build.VERSION.SDK_INT >= 26) {
            C3837n7.i(this, false);
        }
        super.setOnHierarchyChangeListener(this);
        super.setOnSystemUiVisibilityChangeListener(this);
    }

    public final EventForwarder a() {
        if (e() && this.G.I() != null) {
            return this.G.n0();
        }
        return null;
    }

    public boolean awakenScrollBars(int i, boolean z) {
        if (getScrollBarStyle() == 0) {
            return false;
        }
        return super.awakenScrollBars(i, z);
    }

    public final SL0 b() {
        if (e()) {
            return ((WebContentsImpl) this.G).M;
        }
        return null;
    }

    public final AbstractC2603fu1 c() {
        if (this.f9309J == null && e()) {
            this.f9309J = C3115iu1.c(this.G);
        }
        return this.f9309J;
    }

    public int computeHorizontalScrollExtent() {
        SL0 b = b();
        if (b == null) {
            return 0;
        }
        TL0 tl0 = (TL0) b;
        return (int) Math.ceil((double) tl0.a(tl0.e));
    }

    public int computeHorizontalScrollOffset() {
        SL0 b = b();
        if (b != null) {
            return (int) Math.floor((double) ((TL0) b).c());
        }
        return 0;
    }

    public int computeHorizontalScrollRange() {
        SL0 b = b();
        if (b == null) {
            return 0;
        }
        TL0 tl0 = (TL0) b;
        return (int) Math.ceil((double) tl0.a(tl0.c));
    }

    public int computeVerticalScrollExtent() {
        SL0 b = b();
        if (b != null) {
            return ((TL0) b).b();
        }
        return 0;
    }

    public int computeVerticalScrollOffset() {
        SL0 b = b();
        if (b != null) {
            return ((TL0) b).e();
        }
        return 0;
    }

    public int computeVerticalScrollRange() {
        SL0 b = b();
        if (b == null) {
            return 0;
        }
        TL0 tl0 = (TL0) b;
        return (int) Math.ceil((double) tl0.a(tl0.d));
    }

    public AbstractC3808mx1 d() {
        if (e()) {
            return AbstractC3637lx1.a(this.G);
        }
        return null;
    }

    public boolean dispatchDragEvent(DragEvent dragEvent) {
        int action;
        WL wl = this.M;
        if (wl != null) {
            dragEvent.getAction();
            wl.a();
        }
        boolean dispatchDragEvent = super.dispatchDragEvent(dragEvent);
        WL wl2 = this.M;
        if (wl2 != null && ((action = dragEvent.getAction()) == 6 || action == 4 || action == 3)) {
            wl2.c(0.0f);
        }
        return dispatchDragEvent;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!isFocused()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        EventForwarder a2 = a();
        if (a2 == null) {
            return false;
        }
        long j = a2.b;
        if (j == 0) {
            return false;
        }
        return N.MZE$0qqv(j, a2, keyEvent);
    }

    public final boolean e() {
        WebContents webContents = this.G;
        return webContents != null && !webContents.g();
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public void extractSmartClipData(int i, int i2, int i3, int i4) {
        if (e()) {
            this.G.K(i, i2, i3, i4);
        }
    }

    public boolean f(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean g(MotionEvent motionEvent) {
        return super.onGenericMotionEvent(motionEvent);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        AbstractC3808mx1 d = d();
        AccessibilityNodeProvider f = d != null ? ((WebContentsAccessibilityImpl) d).f() : null;
        return f != null ? f : super.getAccessibilityNodeProvider();
    }

    public boolean h(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (e()) {
            ((C3115iu1) c()).e();
        }
    }

    public boolean onCheckIsTextEditor() {
        int i;
        if (!e() || (i = ImeAdapterImpl.s0(this.G).O) == 0) {
            return false;
        }
        if (!(i == 8 || i == 12 || i == 9 || i == 10 || i == 11 || i == 13)) {
            return true;
        }
        return false;
    }

    public void onChildViewAdded(View view, View view2) {
        Iterator it = this.H.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((ViewGroup.OnHierarchyChangeListener) uq0.next()).onChildViewAdded(view, view2);
            } else {
                return;
            }
        }
    }

    public void onChildViewRemoved(View view, View view2) {
        Iterator it = this.H.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((ViewGroup.OnHierarchyChangeListener) uq0.next()).onChildViewRemoved(view, view2);
            } else {
                return;
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (e()) {
            C3115iu1 iu1 = (C3115iu1) c();
            Objects.requireNonNull(iu1);
            try {
                TraceEvent.Y("ViewEventSink.onConfigurationChanged", null);
                Iterator it = Zy1.t0(iu1.F).F.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (!uq0.hasNext()) {
                        break;
                    }
                    ((Wy1) uq0.next()).onConfigurationChanged(configuration);
                }
                ViewAndroidDelegate F2 = iu1.F.F();
                if (F2 != null) {
                    F2.getContainerView().requestLayout();
                }
            } finally {
                TraceEvent.f0("ViewEventSink.onConfigurationChanged");
            }
        }
        super.onConfigurationChanged(configuration);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0111, code lost:
        if ((r9 & 1024) != 0) goto L_0x0123;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0238  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x02ca  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0107 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x013b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.inputmethod.InputConnection onCreateInputConnection(android.view.inputmethod.EditorInfo r20) {
        /*
        // Method dump skipped, instructions count: 770
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.onCreateInputConnection(android.view.inputmethod.EditorInfo):android.view.inputmethod.InputConnection");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (e()) {
            ((C3115iu1) c()).h();
        }
    }

    public boolean onDragEvent(DragEvent dragEvent) {
        String[] strArr;
        EventForwarder a2 = a();
        if (a2 == null || a2.b == 0) {
            return false;
        }
        ClipDescription clipDescription = dragEvent.getClipDescription();
        if (clipDescription == null) {
            strArr = new String[0];
        } else {
            strArr = clipDescription.filterMimeTypes("text/*");
        }
        if (dragEvent.getAction() != 1) {
            StringBuilder sb = new StringBuilder("");
            if (dragEvent.getAction() == 3) {
                ClipData clipData = dragEvent.getClipData();
                int itemCount = clipData.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    sb.append(clipData.getItemAt(i).coerceToStyledText(getContext()));
                }
            }
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            int x = (int) (dragEvent.getX() + a2.c);
            int y = (int) (dragEvent.getY() + a2.d);
            int i2 = iArr[0] + x;
            int i3 = iArr[1] + y;
            float b = a2.b();
            N.MZ1ZkPta(a2.b, a2, dragEvent.getAction(), (int) (((float) x) / b), (int) (((float) y) / b), (int) (((float) i2) / b), (int) (((float) i3) / b), strArr, sb.toString());
        } else if (strArr == null || strArr.length <= 0 || !a2.f11016a) {
            return false;
        }
        return true;
    }

    public void onFocusChanged(boolean z, int i, Rect rect) {
        try {
            TraceEvent.Y("ContentView.onFocusChanged", null);
            super.onFocusChanged(z, i, rect);
            if (e()) {
                ((C3115iu1) c()).f10171J = true;
                C3115iu1 iu1 = (C3115iu1) c();
                Boolean bool = iu1.G;
                if (bool == null || bool.booleanValue() != z) {
                    iu1.G = Boolean.valueOf(z);
                    iu1.j();
                }
            }
        } finally {
            TraceEvent.f0("ContentView.onFocusChanged");
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        EventForwarder a2 = a();
        if (a2 != null) {
            return a2.d(motionEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onHoverEvent(android.view.MotionEvent r24) {
        /*
        // Method dump skipped, instructions count: 206
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.onHoverEvent(android.view.MotionEvent):boolean");
    }

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        WL wl = this.M;
        if (wl != null) {
            wl.b(motionEvent, true);
        }
        return super.onInterceptHoverEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        WL wl = this.M;
        if (wl != null) {
            wl.b(motionEvent, false);
        }
        return onInterceptTouchEvent;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        EventForwarder a2 = a();
        if (a2 == null) {
            return false;
        }
        long j = a2.b;
        if (j == 0) {
            return false;
        }
        return N.MRbfSEI1(j, a2, keyEvent, i);
    }

    public void onMeasure(int i, int i2) {
        int i3 = this.K;
        int i4 = F;
        if (i3 != i4) {
            i = i3;
        }
        int i5 = this.L;
        if (i5 != i4) {
            i2 = i5;
        }
        super.onMeasure(i, i2);
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void onSystemUiVisibilityChange(int i) {
        Iterator it = this.I.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((View.OnSystemUiVisibilityChangeListener) uq0.next()).onSystemUiVisibilityChange(i);
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:115:0x01f2  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r44) {
        /*
        // Method dump skipped, instructions count: 503
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (e()) {
            ((C3115iu1) c()).k(z);
        }
    }

    public boolean performAccessibilityAction(int i, Bundle bundle) {
        d();
        return super.performAccessibilityAction(i, bundle);
    }

    public boolean performLongClick() {
        return false;
    }

    public void scrollBy(int i, int i2) {
        EventForwarder a2 = a();
        if (a2 != null) {
            float f = (float) i;
            float f2 = (float) i2;
            long j = a2.b;
            if (j != 0) {
                N.MMwH$VBb(j, a2, f, f2);
            }
        }
    }

    public void scrollTo(int i, int i2) {
        EventForwarder a2 = a();
        if (a2 != null) {
            a2.f((float) i, (float) i2);
        }
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        super.setOnHierarchyChangeListener(onHierarchyChangeListener);
    }

    public void setOnSystemUiVisibilityChangeListener(View.OnSystemUiVisibilityChangeListener onSystemUiVisibilityChangeListener) {
        super.setOnSystemUiVisibilityChangeListener(onSystemUiVisibilityChangeListener);
    }

    @Override // org.chromium.content_public.browser.SmartClipProvider
    public void setSmartClipResultHandler(Handler handler) {
        if (e()) {
            this.G.setSmartClipResultHandler(handler);
        }
    }
}
