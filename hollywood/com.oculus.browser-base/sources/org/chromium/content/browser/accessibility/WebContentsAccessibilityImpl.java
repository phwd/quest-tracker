package org.chromium.content.browser.accessibility;

import J.N;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.SparseArray;
import android.view.Display;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import org.chromium.base.ContextUtils;
import org.chromium.content.browser.accessibility.captioning.CaptioningController;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.AccessibilitySnapshotNode;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebContentsAccessibilityImpl extends AccessibilityNodeProvider implements AccessibilityManager.AccessibilityStateChangeListener, AbstractC3808mx1, Wy1, Qr1 {
    public static SparseArray F = new SparseArray();
    public final WebContentsImpl G;
    public AccessibilityManager H;
    public final Context I;

    /* renamed from: J  reason: collision with root package name */
    public String f10921J;
    public long K;
    public Rect L;
    public boolean M;
    public int N = -1;
    public int O;
    public View P;
    public boolean Q;
    public boolean R;
    public boolean S;
    public int T;
    public int U;
    public int V;
    public View W;
    public CaptioningController X;
    public boolean Y;
    public int Z;
    public int a0;
    public String b0;
    public boolean c0;
    public boolean d0;
    public boolean e0;
    public boolean f0;
    public boolean g0;
    public SparseArray h0 = new SparseArray();
    public C5689y i0;
    public String j0;
    public BroadcastReceiver k0;
    public int l0;
    public long m0;

    public WebContentsAccessibilityImpl(WebContents webContents) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.G = webContentsImpl;
        View containerView = webContentsImpl.F().getContainerView();
        this.P = containerView;
        Context context = containerView.getContext();
        this.I = context;
        this.f10921J = webContentsImpl.O;
        this.H = (AccessibilityManager) context.getSystemService("accessibility");
        this.X = new CaptioningController(webContentsImpl);
        Zy1 t0 = Zy1.t0(webContentsImpl);
        t0.F.b(this);
        if (t0.I) {
            onAttachedToWindow();
        }
        HashMap hashMap = new HashMap();
        hashMap.put(4096, 100);
        hashMap.put(2048, 100);
        hashMap.put(128, 50);
        HashSet hashSet = new HashSet();
        hashSet.add(128);
        this.i0 = new C5689y(new C3979nx1(this), hashMap, hashSet);
    }

    @Override // defpackage.XF
    public void A(List list) {
    }

    @Override // defpackage.XF
    public void C(Display.Mode mode) {
    }

    @Override // defpackage.Wy1
    public void S(boolean z, boolean z2) {
    }

    @Override // defpackage.XF
    public void U(float f) {
    }

    public void a(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        AccessibilityNodeInfo.AccessibilityAction accessibilityAction = (AccessibilityNodeInfo.AccessibilityAction) F.get(i);
        if (accessibilityAction == null) {
            accessibilityAction = new AccessibilityNodeInfo.AccessibilityAction(i, null);
            F.put(i, accessibilityAction);
        }
        accessibilityNodeInfo.addAction(accessibilityAction);
    }

    public final void addAccessibilityNodeInfoActions(AccessibilityNodeInfo accessibilityNodeInfo, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17) {
        a(accessibilityNodeInfo, 1024);
        a(accessibilityNodeInfo, 2048);
        a(accessibilityNodeInfo, 16908342);
        a(accessibilityNodeInfo, 16908348);
        if (z15) {
            a(accessibilityNodeInfo, 256);
            a(accessibilityNodeInfo, 512);
        }
        if (z8 && z9) {
            a(accessibilityNodeInfo, 2097152);
            a(accessibilityNodeInfo, 32768);
            a(accessibilityNodeInfo, 16908372);
            if (z14) {
                a(accessibilityNodeInfo, 131072);
                a(accessibilityNodeInfo, 65536);
                a(accessibilityNodeInfo, 16384);
            }
        }
        if (z) {
            a(accessibilityNodeInfo, 4096);
        }
        if (z2) {
            a(accessibilityNodeInfo, 8192);
        }
        if (z3) {
            a(accessibilityNodeInfo, 16908344);
            a(accessibilityNodeInfo, 16908358);
        }
        if (z4) {
            a(accessibilityNodeInfo, 16908346);
            a(accessibilityNodeInfo, 16908359);
        }
        if (z5) {
            a(accessibilityNodeInfo, 16908345);
            a(accessibilityNodeInfo, 16908360);
        }
        if (z6) {
            a(accessibilityNodeInfo, 16908347);
            a(accessibilityNodeInfo, 16908361);
        }
        if (z10) {
            if (z11) {
                a(accessibilityNodeInfo, 2);
            } else {
                a(accessibilityNodeInfo, 1);
            }
        }
        if (this.U == i) {
            a(accessibilityNodeInfo, 128);
        } else {
            a(accessibilityNodeInfo, 64);
        }
        if (z7) {
            a(accessibilityNodeInfo, 16);
        }
        if (z12) {
            a(accessibilityNodeInfo, 262144);
        }
        if (z13) {
            a(accessibilityNodeInfo, 524288);
        }
        if (z16) {
            a(accessibilityNodeInfo, 16908349);
        }
    }

    public final void addAccessibilityNodeInfoChildren(AccessibilityNodeInfo accessibilityNodeInfo, int[] iArr) {
        for (int i : iArr) {
            accessibilityNodeInfo.addChild(this.P, i);
        }
    }

    public final void announceLiveRegionText(String str) {
        AccessibilityEvent obtain;
        if (g() && (obtain = AccessibilityEvent.obtain(16384)) != null) {
            obtain.getText().add(str);
            obtain.setContentDescription(null);
            q(obtain);
        }
    }

    public final AccessibilityEvent b(int i, int i2) {
        if (!g() || !j() || !N.MTBNGzHX(this.K, this, i)) {
            return null;
        }
        this.P.postInvalidate();
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        obtain.setPackageName(this.I.getPackageName());
        obtain.setSource(this.P, i);
        if (N.M2E1dEU9(this.K, this, obtain, i, i2)) {
            return obtain;
        }
        obtain.recycle();
        return null;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:12:0x0042 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r9v12, types: [android.text.SpannableString] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.CharSequence c(java.lang.String r8, boolean r9, java.lang.String r10, int[] r11, int[] r12, java.lang.String[] r13) {
        /*
        // Method dump skipped, instructions count: 129
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl.c(java.lang.String, boolean, java.lang.String, int[], int[], java.lang.String[]):java.lang.CharSequence");
    }

    public void clearNodeInfoCacheForGivenId(int i) {
        if (this.h0.get(i) != null) {
            ((AccessibilityNodeInfo) this.h0.get(i)).recycle();
            this.h0.remove(i);
        }
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        if (!g()) {
            return null;
        }
        int MI8pU34f = N.MI8pU34f(this.K, this);
        if (i == -1) {
            AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain(this.P);
            AccessibilityNodeInfo obtain2 = AccessibilityNodeInfo.obtain(this.P);
            this.P.onInitializeAccessibilityNodeInfo(obtain2);
            Rect rect = new Rect();
            obtain2.getBoundsInParent(rect);
            obtain.setBoundsInParent(rect);
            obtain2.getBoundsInScreen(rect);
            obtain.setBoundsInScreen(rect);
            ViewParent parentForAccessibility = this.P.getParentForAccessibility();
            if (parentForAccessibility instanceof View) {
                obtain.setParent((View) parentForAccessibility);
            }
            obtain.setVisibleToUser(obtain2.isVisibleToUser());
            obtain.setEnabled(obtain2.isEnabled());
            obtain.setPackageName(obtain2.getPackageName());
            obtain.setClassName(obtain2.getClassName());
            if (j()) {
                obtain.addChild(this.P, MI8pU34f);
            }
            return obtain;
        } else if (!j()) {
            return null;
        } else {
            if (this.h0.get(i) != null) {
                AccessibilityNodeInfo obtain3 = AccessibilityNodeInfo.obtain((AccessibilityNodeInfo) this.h0.get(i));
                if (N.MZ7sDynr(this.K, this, obtain3, i)) {
                    return obtain3;
                }
                ((AccessibilityNodeInfo) this.h0.get(i)).recycle();
                this.h0.remove(i);
                return null;
            }
            AccessibilityNodeInfo obtain4 = AccessibilityNodeInfo.obtain(this.P);
            obtain4.setPackageName(this.I.getPackageName());
            obtain4.setSource(this.P, i);
            if (i == MI8pU34f) {
                obtain4.setParent(this.P);
            }
            if (N.MJGtghd9(this.K, this, obtain4, i)) {
                this.h0.put(i, AccessibilityNodeInfo.obtain(obtain4));
                return obtain4;
            }
            obtain4.recycle();
            return null;
        }
    }

    public void d(Rect rect) {
        TL0 tl0 = this.G.M;
        rect.offset(-((int) tl0.f8952a), -((int) tl0.b));
        rect.left = (int) tl0.a((float) rect.left);
        rect.top = (int) tl0.a((float) rect.top);
        rect.bottom = (int) tl0.a((float) rect.bottom);
        rect.right = (int) tl0.a((float) rect.right);
        rect.offset(0, (int) tl0.k);
        int[] iArr = new int[2];
        this.P.getLocationOnScreen(iArr);
        rect.offset(iArr[0], iArr[1]);
        int i = iArr[1] + ((int) tl0.k);
        int b = tl0.b() + i;
        if (rect.top < i) {
            rect.top = i;
        }
        if (rect.bottom > b) {
            rect.bottom = b;
        }
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    public final void e(ViewStructure viewStructure, AccessibilitySnapshotNode accessibilitySnapshotNode, boolean z) {
        viewStructure.setClassName(accessibilitySnapshotNode.h);
        if (accessibilitySnapshotNode.p) {
            viewStructure.setText(accessibilitySnapshotNode.g, accessibilitySnapshotNode.q, accessibilitySnapshotNode.r);
        } else {
            viewStructure.setText(accessibilitySnapshotNode.g);
        }
        TL0 tl0 = this.G.M;
        int a2 = (int) tl0.a((float) accessibilitySnapshotNode.f10937a);
        int a3 = (int) tl0.a((float) accessibilitySnapshotNode.b);
        int a4 = (int) tl0.a((float) accessibilitySnapshotNode.c);
        int a5 = (int) tl0.a((float) accessibilitySnapshotNode.d);
        Rect rect = new Rect(a2, a3, a2 + a4, a3 + a5);
        if (accessibilitySnapshotNode.e) {
            rect.offset(0, (int) tl0.k);
            if (!z) {
                rect.offset(-((int) tl0.c()), -((int) tl0.d()));
            }
        }
        viewStructure.setDimens(rect.left, rect.top, 0, 0, a4, a5);
        viewStructure.setChildCount(accessibilitySnapshotNode.s.size());
        if (accessibilitySnapshotNode.i) {
            viewStructure.setTextStyle(tl0.a(accessibilitySnapshotNode.f), accessibilitySnapshotNode.j, accessibilitySnapshotNode.k, (accessibilitySnapshotNode.l ? 1 : 0) | (accessibilitySnapshotNode.m ? 2 : 0) | (accessibilitySnapshotNode.n ? 4 : 0) | (accessibilitySnapshotNode.o ? 8 : 0));
        }
        for (int i = 0; i < accessibilitySnapshotNode.s.size(); i++) {
            e(viewStructure.asyncNewChild(i), (AccessibilitySnapshotNode) accessibilitySnapshotNode.s.get(i), true);
        }
        viewStructure.asyncCommit();
    }

    public AccessibilityNodeProvider f() {
        if (this.f0) {
            return null;
        }
        boolean z = false;
        if (!k()) {
            if (!this.d0) {
                return null;
            }
            long MjYAnP1s = N.MjYAnP1s(this, this.G);
            this.K = MjYAnP1s;
            this.U = -1;
            this.V = -1;
            this.M = false;
            this.O = -1;
            this.b0 = N.MPyIoFYC(MjYAnP1s, this);
            this.k0 = new C4150ox1(this);
            if (this.P.isAttachedToWindow()) {
                p();
            }
        }
        if (k()) {
            z = N.Mr9fGid2(this.K, this);
        }
        if (z) {
            return this;
        }
        N.Mg$cuhZc(this.K, this);
        return null;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public List findAccessibilityNodeInfosByText(String str, int i) {
        return new ArrayList();
    }

    public final void finishGranularityMoveNext(String str, boolean z, int i, int i2) {
        AccessibilityEvent b = b(this.V, 8192);
        if (b != null) {
            AccessibilityEvent b2 = b(this.V, 131072);
            if (b2 == null) {
                b.recycle();
                return;
            }
            if (z) {
                if (!this.Y) {
                    this.Y = true;
                    this.Z = i;
                }
                b.setFromIndex(this.Z);
                b.setToIndex(i2);
            } else {
                this.Y = false;
                this.Z = i2;
                b.setFromIndex(i2);
                b.setToIndex(i2);
            }
            this.a0 = i2;
            b.setItemCount(str.length());
            u(b);
            b2.setFromIndex(i);
            b2.setToIndex(i2);
            b2.setItemCount(str.length());
            b2.setMovementGranularity(this.T);
            b2.setContentDescription(str);
            b2.setAction(256);
            q(b);
            q(b2);
            this.c0 = true;
        }
    }

    public final void finishGranularityMovePrevious(String str, boolean z, int i, int i2) {
        AccessibilityEvent b = b(this.V, 8192);
        if (b != null) {
            AccessibilityEvent b2 = b(this.V, 131072);
            if (b2 == null) {
                b.recycle();
                return;
            }
            if (z) {
                if (!this.Y) {
                    this.Y = true;
                    this.Z = i2;
                }
                b.setFromIndex(this.Z);
                b.setToIndex(i);
            } else {
                this.Y = false;
                this.Z = i;
                b.setFromIndex(i);
                b.setToIndex(i);
            }
            this.a0 = i;
            b.setItemCount(str.length());
            u(b);
            b2.setFromIndex(i);
            b2.setToIndex(i2);
            b2.setItemCount(str.length());
            b2.setMovementGranularity(this.T);
            b2.setContentDescription(str);
            b2.setAction(512);
            q(b);
            q(b2);
            this.c0 = true;
        }
    }

    public boolean g() {
        return k() && this.H.isEnabled();
    }

    public int getAccessibilityServiceCapabilitiesMask() {
        int i = 0;
        for (AccessibilityServiceInfo accessibilityServiceInfo : this.H.getEnabledAccessibilityServiceList(-1)) {
            i |= accessibilityServiceInfo.getCapabilities();
        }
        return i;
    }

    public final int getAccessibilityServiceEventTypeMask() {
        int i = 0;
        for (AccessibilityServiceInfo accessibilityServiceInfo : this.H.getEnabledAccessibilityServiceList(-1)) {
            i |= accessibilityServiceInfo.eventTypes;
        }
        return i;
    }

    public final int getAccessibilityServiceFeedbackTypeMask() {
        int i = 0;
        for (AccessibilityServiceInfo accessibilityServiceInfo : this.H.getEnabledAccessibilityServiceList(-1)) {
            i |= accessibilityServiceInfo.feedbackType;
        }
        return i;
    }

    public final int getAccessibilityServiceFlagsMask() {
        int i = 0;
        for (AccessibilityServiceInfo accessibilityServiceInfo : this.H.getEnabledAccessibilityServiceList(-1)) {
            i |= accessibilityServiceInfo.flags;
        }
        return i;
    }

    @Override // defpackage.XF
    public void h(float f) {
    }

    @Override // defpackage.XF
    public void h0(int i) {
    }

    public final void handleCheckStateChanged(int i) {
        if (this.U == i) {
            r(i, 1);
        }
    }

    public final void handleClicked(int i) {
        r(i, 1);
    }

    public final void handleContentChanged(int i) {
        int MI8pU34f = N.MI8pU34f(this.K, this);
        if (MI8pU34f != this.O) {
            this.O = MI8pU34f;
            r(-1, 2048);
            return;
        }
        r(i, 2048);
    }

    public final void handleEditableTextChanged(int i) {
        r(i, 16);
    }

    public final void handleFocusChanged(int i) {
        if (this.e0 || this.U != -1) {
            r(i, 8);
            m(i);
        }
    }

    public final void handleHover(int i) {
        if (this.N != i && this.M) {
            r(i, 128);
        }
    }

    public final void handleNavigate() {
        this.U = -1;
        this.L = null;
        this.Q = false;
        r(-1, 2048);
    }

    public final void handlePageLoaded(int i) {
        if (this.e0 && !this.Q) {
            n(i);
        }
    }

    public final void handleScrollPositionChanged(int i) {
        r(i, 4096);
    }

    public final void handleScrolledToAnchor(int i) {
        m(i);
    }

    public final void handleSliderChanged(int i) {
        if (this.U == i) {
            r(i, 4);
        } else {
            r(i, 4096);
        }
    }

    public final void handleTextSelectionChanged(int i) {
        r(i, 8192);
    }

    public boolean i() {
        if (Build.VERSION.SDK_INT < 28) {
            return false;
        }
        String string = Settings.Secure.getString(this.I.getContentResolver(), "enabled_accessibility_services");
        if (string == null || string.isEmpty()) {
            return true;
        }
        return false;
    }

    public final boolean j() {
        WebContentsImpl webContentsImpl = this.G;
        if (webContentsImpl == null) {
            return true;
        }
        TL0 tl0 = webContentsImpl.M;
        if (((double) tl0.c) == 0.0d && ((double) tl0.d) == 0.0d) {
            return false;
        }
        return true;
    }

    public boolean k() {
        return this.K != 0;
    }

    public final boolean l(int i, String str, boolean z) {
        int MavOU0SM = N.MavOU0SM(this.K, this, i, str, z);
        if (MavOU0SM == 0) {
            return false;
        }
        m(MavOU0SM);
        N.MB302_MP(this.K, this, this.U);
        return true;
    }

    public final boolean m(int i) {
        int i2 = this.U;
        if (i == i2) {
            return false;
        }
        N.MPQKLw45(this.K, this, i2, i);
        this.U = i;
        this.L = null;
        this.V = i;
        this.T = 0;
        this.Y = false;
        this.Z = -1;
        this.a0 = N.MhMiVz6m(this.K, this, i);
        this.c0 = false;
        if (N.M5uHFthk(this.K, this, this.U)) {
            this.W.requestFocus();
        }
        r(this.U, 32768);
        return true;
    }

    public final void n(int i) {
        if (i == this.U) {
            r(i, 65536);
            this.U = -1;
        }
        m(i);
    }

    public final void notifyFrameInfoInitialized() {
        int i;
        if (!this.S) {
            this.S = true;
            r(-1, 2048);
            if (this.e0 && (i = this.U) != -1) {
                n(i);
            }
        }
    }

    public void o() {
        v(this.H.isEnabled());
    }

    public void onAccessibilityStateChanged(boolean z) {
        v(z);
    }

    @Override // defpackage.Wy1
    public void onAttachedToWindow() {
        this.H.addAccessibilityStateChangeListener(this);
        o();
        CaptioningController captioningController = this.X;
        C3431km kmVar = (C3431km) captioningController.f10922a;
        if (!kmVar.b.b()) {
            kmVar.c.addCaptioningChangeListener(kmVar);
            kmVar.b();
        }
        kmVar.b.i.put(captioningController, null);
        kmVar.b.c(captioningController);
        p();
    }

    @Override // defpackage.Wy1
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // defpackage.Wy1
    public void onDetachedFromWindow() {
        this.H.removeAccessibilityStateChangeListener(this);
        CaptioningController captioningController = this.X;
        C3431km kmVar = (C3431km) captioningController.f10922a;
        kmVar.b.i.remove(captioningController);
        if (!kmVar.b.b()) {
            kmVar.c.removeCaptioningChangeListener(kmVar);
        }
        if (k()) {
            ContextUtils.getApplicationContext().unregisterReceiver(this.k0);
        }
    }

    public final boolean onHoverEvent(int i) {
        if (!g()) {
            return false;
        }
        if (i == 10) {
            this.M = false;
            this.N = -1;
            if (this.R) {
                N.MB302_MP(this.K, this, this.U);
            }
            this.R = false;
            return true;
        }
        this.M = true;
        this.Q = true;
        return true;
    }

    public void onNativeObjectDestroyed() {
        this.K = 0;
    }

    @Override // defpackage.Wy1
    public void onWindowFocusChanged(boolean z) {
    }

    public final void p() {
        if (k()) {
            try {
                ContextUtils.getApplicationContext().registerReceiver(this.k0, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
            } catch (ReceiverCallNotAllowedException unused) {
            }
            this.j0 = Locale.getDefault().toLanguageTag();
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01e3  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x021c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean performAction(int r11, int r12, android.os.Bundle r13) {
        /*
        // Method dump skipped, instructions count: 692
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl.performAction(int, int, android.os.Bundle):boolean");
    }

    public final void q(AccessibilityEvent accessibilityEvent) {
        if (this.P.getParent() != null) {
            this.P.getParent().requestSendAccessibilityEvent(this.P, accessibilityEvent);
        }
    }

    public final void r(int i, int i2) {
        if (i == -1) {
            this.P.sendAccessibilityEvent(i2);
        } else if (!this.c0 || i2 != 8192) {
            C5689y yVar = this.i0;
            if (!yVar.f11653a.containsKey(Integer.valueOf(i2))) {
                yVar.e.a(i, i2);
                return;
            }
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            long j = yVar.b.contains(Integer.valueOf(i2)) ? (long) i2 : (((long) i) << 32) | ((long) i2);
            if (yVar.c.get(Long.valueOf(j)) == null || timeInMillis - ((Long) yVar.c.get(Long.valueOf(j))).longValue() >= ((long) ((Integer) yVar.f11653a.get(Integer.valueOf(i2))).intValue())) {
                if (yVar.e.a(i, i2)) {
                    yVar.c.put(Long.valueOf(j), Long.valueOf(timeInMillis));
                }
                yVar.e.b((Runnable) yVar.d.get(Long.valueOf(j)));
                yVar.d.remove(Long.valueOf(j));
                return;
            }
            yVar.e.b((Runnable) yVar.d.get(Long.valueOf(j)));
            RunnableC5519x xVar = new RunnableC5519x(yVar, i, i2, j);
            yVar.e.f10522a.P.postDelayed(xVar, (((Long) yVar.c.get(Long.valueOf(j))).longValue() + ((long) ((Integer) yVar.f11653a.get(Integer.valueOf(i2))).intValue())) - timeInMillis);
            yVar.d.put(Long.valueOf(j), xVar);
        } else {
            this.c0 = false;
        }
    }

    public final void s(int i) {
        this.T = i;
        if (N.MCMbXu4W(this.K, this, this.U) && N.M8UuMlLD(this.K, this, this.U)) {
            if (this.Z == -1) {
                this.Z = N.MnVi6Frs(this.K, this, this.U);
            }
            if (this.a0 == -1) {
                this.a0 = N.Mxt_kc4Q(this.K, this, this.U);
            }
        }
    }

    public final void sendDelayedWindowContentChangedEvent() {
        r(-1, 2048);
    }

    public final void setAccessibilityEventBaseAttributes(AccessibilityEvent accessibilityEvent, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2, int i3, int i4, int i5, int i6, String str) {
        accessibilityEvent.setChecked(z);
        accessibilityEvent.setEnabled(z2);
        accessibilityEvent.setPassword(z3);
        accessibilityEvent.setScrollable(z4);
        accessibilityEvent.setCurrentItemIndex(i);
        accessibilityEvent.setItemCount(i2);
        accessibilityEvent.setScrollX(i3);
        accessibilityEvent.setScrollY(i4);
        accessibilityEvent.setMaxScrollX(i5);
        accessibilityEvent.setMaxScrollY(i6);
        accessibilityEvent.setClassName(str);
    }

    public final void setAccessibilityEventSelectionAttrs(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, String str) {
        accessibilityEvent.setFromIndex(i);
        accessibilityEvent.setToIndex(i2);
        accessibilityEvent.setItemCount(i3);
        accessibilityEvent.getText().add(str);
    }

    public final void setAccessibilityEventTextChangedAttrs(AccessibilityEvent accessibilityEvent, int i, int i2, int i3, String str, String str2) {
        accessibilityEvent.setFromIndex(i);
        accessibilityEvent.setAddedCount(i2);
        accessibilityEvent.setRemovedCount(i3);
        accessibilityEvent.setBeforeText(str);
        accessibilityEvent.getText().add(str2);
    }

    public final void setAccessibilityNodeInfoBaseAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, String str, String str2, String str3, String str4, String str5, boolean z2, boolean z3, boolean z4, int i, int i2, String str6) {
        accessibilityNodeInfo.setClassName(str);
        Bundle extras = accessibilityNodeInfo.getExtras();
        extras.putCharSequence("AccessibilityNodeInfo.chromeRole", str2);
        extras.putCharSequence("AccessibilityNodeInfo.roleDescription", str3);
        extras.putCharSequence("AccessibilityNodeInfo.hint", str4);
        if (!str5.isEmpty()) {
            extras.putCharSequence("AccessibilityNodeInfo.targetUrl", str5);
        }
        if (z) {
            extras.putCharSequence("ACTION_ARGUMENT_HTML_ELEMENT_STRING_VALUES", this.b0);
        }
        accessibilityNodeInfo.setCanOpenPopup(z2);
        accessibilityNodeInfo.setDismissable(z3);
        accessibilityNodeInfo.setMultiLine(z4);
        accessibilityNodeInfo.setInputType(i);
        if (accessibilityNodeInfo.isContentInvalid()) {
            accessibilityNodeInfo.setError(str6);
        }
    }

    public final void setAccessibilityNodeInfoBooleanAttributes(AccessibilityNodeInfo accessibilityNodeInfo, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12) {
        accessibilityNodeInfo.setCheckable(z);
        accessibilityNodeInfo.setChecked(z2);
        accessibilityNodeInfo.setClickable(z3);
        accessibilityNodeInfo.setEnabled(z5);
        accessibilityNodeInfo.setFocusable(z6);
        accessibilityNodeInfo.setFocused(z7);
        accessibilityNodeInfo.setPassword(z9);
        accessibilityNodeInfo.setScrollable(z10);
        accessibilityNodeInfo.setSelected(z11);
        accessibilityNodeInfo.setVisibleToUser(z12);
        if (!z4 || !z7) {
            accessibilityNodeInfo.setContentInvalid(z4);
        } else if (i != this.l0) {
            this.l0 = i;
            this.m0 = Calendar.getInstance().getTimeInMillis();
            accessibilityNodeInfo.setContentInvalid(true);
        } else if (Calendar.getInstance().getTimeInMillis() - this.m0 >= 3000) {
            this.m0 = Calendar.getInstance().getTimeInMillis();
            accessibilityNodeInfo.setContentInvalid(true);
        }
        if (z8) {
            accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.hasImage", "true");
        }
        accessibilityNodeInfo.setMovementGranularities(7);
        if (this.U == i) {
            accessibilityNodeInfo.setAccessibilityFocused(true);
        } else {
            accessibilityNodeInfo.setAccessibilityFocused(false);
        }
    }

    public void setAccessibilityNodeInfoCollectionInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, boolean z) {
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z));
    }

    public void setAccessibilityNodeInfoCollectionItemInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, int i3, int i4, boolean z) {
        accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z));
    }

    public final void setAccessibilityNodeInfoLocation(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        Rect rect = new Rect(i4, i5, i4 + i6, i5 + i7);
        if (z) {
            rect.offset(0, (int) this.G.M.k);
        }
        accessibilityNodeInfo.setBoundsInParent(rect);
        Rect rect2 = new Rect(i2, i3, i6 + i2, i7 + i3);
        d(rect2);
        accessibilityNodeInfo.setBoundsInScreen(rect2);
        if (i == this.U && i != this.O) {
            Rect rect3 = this.L;
            if (rect3 == null) {
                this.L = rect2;
            } else if (!rect3.equals(rect2)) {
                this.L = rect2;
                n(i);
            }
        }
    }

    public void setAccessibilityNodeInfoOAttributes(AccessibilityNodeInfo accessibilityNodeInfo, boolean z, String str) {
    }

    public void setAccessibilityNodeInfoPaneTitle(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
    }

    public final void setAccessibilityNodeInfoParent(AccessibilityNodeInfo accessibilityNodeInfo, int i) {
        accessibilityNodeInfo.setParent(this.P, i);
    }

    public void setAccessibilityNodeInfoRangeInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, float f, float f2, float f3) {
        accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(i, f, f2, f3));
    }

    public void setAccessibilityNodeInfoSelectionAttrs(AccessibilityNodeInfo accessibilityNodeInfo, int i, int i2) {
        accessibilityNodeInfo.setEditable(true);
        accessibilityNodeInfo.setTextSelection(i, i2);
    }

    public void setAccessibilityNodeInfoText(AccessibilityNodeInfo accessibilityNodeInfo, String str, boolean z, boolean z2, String str2, int[] iArr, int[] iArr2, String[] strArr, String str3) {
        CharSequence c = c(str, z2, str2, iArr, iArr2, strArr);
        if (z) {
            accessibilityNodeInfo.setContentDescription(c);
        } else {
            accessibilityNodeInfo.setText(c);
        }
        if (str3 != null && !str3.isEmpty()) {
            accessibilityNodeInfo.setText(((Object) c) + ", " + str3);
        }
    }

    public void setAccessibilityNodeInfoViewIdResourceName(AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        accessibilityNodeInfo.setViewIdResourceName(str);
    }

    public boolean shouldExposePasswordText() {
        if (i()) {
            return true;
        }
        ContentResolver contentResolver = this.I.getContentResolver();
        if (Build.VERSION.SDK_INT >= 26) {
            if (Settings.System.getInt(contentResolver, "show_password", 1) == 1) {
                return true;
            }
            return false;
        } else if (Settings.Secure.getInt(contentResolver, "speak_password", 0) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean shouldRespectDisplayedPasswordText() {
        if (!i() && Build.VERSION.SDK_INT >= 26) {
            return true;
        }
        return false;
    }

    public void t(boolean z) {
        if (z != this.f0) {
            this.f0 = z;
            r(-1, 2048);
        }
    }

    public final void u(AccessibilityEvent accessibilityEvent) {
        if (N.MCMbXu4W(this.K, this, this.V) && N.M8UuMlLD(this.K, this, this.V)) {
            N.MVuu0R4P(this.K, this, this.V, accessibilityEvent.getFromIndex(), accessibilityEvent.getToIndex());
        }
    }

    public void v(boolean z) {
        if (!z) {
            this.d0 = false;
            this.g0 = false;
            return;
        }
        this.d0 = true;
        this.g0 = this.H.isTouchExplorationEnabled();
    }

    @Override // defpackage.Wy1
    public void x(WindowAndroid windowAndroid) {
        C5852yx1 yx1;
        Zy1.t0(this.G).F.c(this);
        C3466kx1 kx1 = this.G.N;
        Rr1 rr1 = null;
        if (!(kx1 == null || (yx1 = kx1.f10317a) == null)) {
            rr1 = yx1.f11713a;
        }
        if (rr1 != null) {
            rr1.d(WebContentsAccessibilityImpl.class);
        }
        long j = this.K;
        if (j != 0) {
            N.MxGfnb$m(j);
        }
    }
}
