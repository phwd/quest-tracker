package com.oculus.browser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebVRActivity extends Activity implements AbstractC3226ja1 {
    public WebVRActivity() {
        new Handler();
        AbstractC1220Ua0.d("WebVRActivity.Oculus", "WebVRActivity CTOR! " + this, new Object[0]);
    }

    @Override // defpackage.AbstractC3226ja1
    public AbstractC0124Ca1 P() {
        return ((PanelApp) c()).P();
    }

    @Override // defpackage.AbstractC3226ja1
    public A61 S(boolean z) {
        C5613xa1 xa1 = ((PanelApp) c()).f0;
        return xa1.z(z, xa1.Q);
    }

    public final boolean a(Intent intent) {
        boolean z = intent.getShortExtra("WEBVR_PRESENT_REQUESTED", 0) != 0;
        boolean z2 = intent.getStringExtra("intent_pkg") != null;
        if (z || z2) {
            return true;
        }
        AbstractC1220Ua0.d("WebVRActivity.Oculus", "The activity wasn't invoked by us or by SystemActivities: just start the browser.", new Object[0]);
        d();
        finish();
        return false;
    }

    public VrShellDelegate b() {
        return ((HydraApplication) getApplication()).M;
    }

    public final AbstractC3226ja1 c() {
        return ((HydraApplication) getApplication()).N;
    }

    public final void d() {
        AbstractC1220Ua0.d("WebVRActivity.Oculus", "restartBrowserAsPanel", new Object[0]);
        Intent intent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("intent_data", Uri.parse("systemux://browser"));
        sendBroadcast(intent);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        motionEvent.setSource(motionEvent.getSource() & -8195);
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        if (r0 != false) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchKeyEvent(android.view.KeyEvent r8) {
        /*
            r7 = this;
            int r0 = r8.getKeyCode()
            android.view.KeyEvent.keyCodeToString(r0)
            r8.toString()
            int r0 = r8.getKeyCode()
            r1 = 1
            r2 = 142(0x8e, float:1.99E-43)
            if (r0 != r2) goto L_0x0014
            return r1
        L_0x0014:
            int r0 = r8.getKeyCode()
            r2 = 125(0x7d, float:1.75E-43)
            if (r0 != r2) goto L_0x001d
            return r1
        L_0x001d:
            com.oculus.browser.VrShellDelegate r0 = r7.b()
            r2 = 0
            if (r0 == 0) goto L_0x0055
            boolean r3 = r0.m(r7)
            if (r3 == 0) goto L_0x0055
            com.oculus.browser.VrShellImpl r0 = r0.K
            if (r0 == 0) goto L_0x0053
            org.chromium.chrome.browser.tab.Tab r3 = r0.e
            if (r3 == 0) goto L_0x0053
            org.chromium.content_public.browser.WebContents r3 = r3.l()
            if (r3 == 0) goto L_0x0053
            org.chromium.chrome.browser.tab.Tab r0 = r0.e
            org.chromium.content_public.browser.WebContents r0 = r0.l()
            org.chromium.ui.base.EventForwarder r0 = r0.n0()
            long r3 = r0.b
            r5 = 0
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 != 0) goto L_0x004c
            r0 = r2
            goto L_0x0050
        L_0x004c:
            boolean r0 = J.N.MZE$0qqv(r3, r0, r8)
        L_0x0050:
            if (r0 == 0) goto L_0x0053
            goto L_0x0054
        L_0x0053:
            r1 = r2
        L_0x0054:
            r2 = r1
        L_0x0055:
            if (r2 != 0) goto L_0x005b
            boolean r2 = super.dispatchKeyEvent(r8)
        L_0x005b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.browser.WebVRActivity.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003b A[Catch:{ all -> 0x0059, all -> 0x0060 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003f A[Catch:{ all -> 0x0059, all -> 0x0060 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.browser.WebVRActivity.e():void");
    }

    public void finish() {
        AbstractC1220Ua0.d("WebVRActivity.Oculus", "finish!", new Object[0]);
        super.finish();
    }

    @Override // defpackage.AbstractC3226ja1
    public boolean j() {
        Objects.requireNonNull((PanelApp) c());
        return false;
    }

    public void onBackPressed() {
        AbstractC1220Ua0.d("WebVRActivity.Oculus", "onBackPressed! " + this, new Object[0]);
        VrShellDelegate b = b();
        if (isFinishing()) {
            AbstractC1220Ua0.d("WebVRActivity.Oculus", "onBackPressed, isFinishing(), doing nothing", new Object[0]);
            return;
        }
        if (b != null && b.m(this)) {
            Tab tab = b.P;
            Tab tab2 = (tab == null || tab.x()) ? null : b.P;
            int i = b.Y;
            int j = b.j();
            if (j > i && tab2.h()) {
                AbstractC1220Ua0.d("WebVRActivity.Oculus", "going back, index %d, startidx %d", Integer.valueOf(j), Integer.valueOf(i));
                tab2.e();
                return;
            } else if (b.Z) {
                b.a("ovrweb://exit");
            } else {
                b.forceExitVR();
            }
        }
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AbstractC1220Ua0.d("WebVRActivity.Oculus", "onCreate! " + this, new Object[0]);
        TraceEvent.f0("WebVRActivity.create");
        if (a(getIntent())) {
            TraceEvent.Y("WebVRActivity.start", null);
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
            VrShellDelegate b = b();
            if (b != null) {
                if (b.l() && !b.m(this)) {
                    b.t(b.H, 5);
                    b.t(b.H, 6);
                }
                b.t(this, 1);
                if (b.R) {
                    b.Y = b.j();
                    b.w();
                } else if (b.L) {
                    b.u();
                } else {
                    AbstractC1220Ua0.d("WebVRActivity.Oculus", "not presenting, finishing...", new Object[0]);
                    finish();
                    TraceEvent.f0("WebVRActivity.start");
                }
            } else {
                AbstractC1220Ua0.d("WebVRActivity.Oculus", "onCreate: no vrshelldelegate, finishing", new Object[0]);
                finish();
            }
        }
    }

    public void onDestroy() {
        VrShellDelegate b = b();
        StringBuilder sb = new StringBuilder();
        sb.append("onDestroy! ");
        sb.append(this);
        sb.append(" delegate? ");
        boolean z = false;
        sb.append(b != null);
        AbstractC1220Ua0.d("WebVRActivity.Oculus", sb.toString(), new Object[0]);
        TraceEvent j0 = TraceEvent.j0("WebVRActivity.onDestroy");
        if (b != null) {
            try {
                b.t(this, 6);
                z = b.Z;
                b.forceExitVR();
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        if (!z) {
            d();
        }
        super.onDestroy();
        if (j0 != null) {
            j0.close();
            return;
        }
        return;
        throw th;
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        VrShellImpl vrShellImpl;
        Tab tab;
        VrShellDelegate b = b();
        boolean z = false;
        if (!(b == null || !b.m(this) || (vrShellImpl = b.K) == null || (tab = vrShellImpl.e) == null || tab.l() == null || !vrShellImpl.e.l().n0().d(motionEvent))) {
            z = true;
        }
        return !z ? super.onGenericMotionEvent(motionEvent) : z;
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AbstractC1220Ua0.d("WebVRActivity.Oculus", "onNewIntent! " + intent + " " + this, new Object[0]);
        a(intent);
    }

    public void onPause() {
        VrShellDelegate b = b();
        StringBuilder sb = new StringBuilder();
        sb.append("onPause! ");
        sb.append(this);
        sb.append(" delegate? ");
        sb.append(b != null);
        AbstractC1220Ua0.d("WebVRActivity.Oculus", sb.toString(), new Object[0]);
        TraceEvent j0 = TraceEvent.j0("WebVRActivity.onPause");
        if (b != null) {
            try {
                b.t(this, 4);
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        super.onPause();
        if (j0 != null) {
            j0.close();
            return;
        }
        return;
        throw th;
    }

    public void onRestart() {
        AbstractC1220Ua0.d("WebVRActivity.Oculus", "onRestart! " + this, new Object[0]);
        TraceEvent j0 = TraceEvent.j0("WebVRActivity.onRestart");
        try {
            VrShellDelegate b = b();
            if (b == null || !b.L) {
                AbstractC1220Ua0.d("WebVRActivity.Oculus", "not presenting, finishing...", new Object[0]);
                finish();
                if (b != null) {
                    b.t(this, 6);
                }
            }
            super.onRestart();
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

    public void onResume() {
        VrShellDelegate b = b();
        StringBuilder sb = new StringBuilder();
        sb.append("onResume! ");
        sb.append(this);
        sb.append(" delegate? ");
        sb.append(b != null);
        AbstractC1220Ua0.d("WebVRActivity.Oculus", sb.toString(), new Object[0]);
        TraceEvent j0 = TraceEvent.j0("WebVRActivity.onResume");
        try {
            super.onResume();
            if (b != null) {
                b.t(this, 3);
            }
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

    public void onStart() {
        VrShellDelegate b = b();
        StringBuilder sb = new StringBuilder();
        sb.append("onStart! ");
        sb.append(this);
        sb.append(" delegate? ");
        sb.append(b != null);
        AbstractC1220Ua0.d("WebVRActivity.Oculus", sb.toString(), new Object[0]);
        if (b != null) {
            b.t(this, 2);
        }
        super.onStart();
        TraceEvent.f0("WebVRActivity.start");
    }

    public void onStop() {
        VrShellDelegate b = b();
        StringBuilder sb = new StringBuilder();
        sb.append("onStop! ");
        sb.append(this);
        sb.append(" delegate? ");
        sb.append(b != null);
        AbstractC1220Ua0.d("WebVRActivity.Oculus", sb.toString(), new Object[0]);
        TraceEvent j0 = TraceEvent.j0("WebVRActivity.onStop");
        if (b != null) {
            try {
                b.t(this, 5);
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        super.onStop();
        if (j0 != null) {
            j0.close();
            return;
        }
        return;
        throw th;
    }

    @Override // defpackage.AbstractC3226ja1
    public boolean y() {
        Objects.requireNonNull((PanelApp) c());
        return false;
    }

    @Override // defpackage.AbstractC3226ja1
    public A61 z(boolean z, boolean z2) {
        return S(z);
    }
}
