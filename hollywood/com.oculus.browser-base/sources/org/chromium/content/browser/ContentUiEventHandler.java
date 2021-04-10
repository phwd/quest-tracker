package org.chromium.content.browser;

import J.N;
import android.os.SystemClock;
import android.view.KeyEvent;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContentUiEventHandler extends Pr1 {
    public final WebContentsImpl F;
    public AbstractC2432eu1 G;
    public long H;

    public ContentUiEventHandler(WebContents webContents) {
        this.F = (WebContentsImpl) webContents;
        this.H = N.MXL6itCa(this, webContents);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0070 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r8) {
        /*
        // Method dump skipped, instructions count: 244
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.ContentUiEventHandler.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0187, code lost:
        r0 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0068 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ad A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r23) {
        /*
        // Method dump skipped, instructions count: 393
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.ContentUiEventHandler.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        return ((AbstractViewGroup$OnHierarchyChangeListenerC1520Yy) this.G).h(i, keyEvent);
    }

    public final void scrollBy(float f, float f2) {
        if (f != 0.0f || f2 != 0.0f) {
            long uptimeMillis = SystemClock.uptimeMillis();
            if (GestureListenerManagerImpl.s0(this.F).M) {
                N.MW$k83qi(this.H, this, uptimeMillis);
            }
            N.M_1sgTVt(this.H, this, uptimeMillis, f, f2);
        }
    }

    public final void scrollTo(float f, float f2) {
        scrollBy(f - this.F.M.c(), f2 - this.F.M.d());
    }
}
