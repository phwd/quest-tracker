package defpackage;

import J.N;
import android.graphics.Rect;
import java.util.Objects;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: r81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4525r81 implements Runnable {
    public final TabImpl F;
    public final WebContents G;
    public final Rect H;
    public final Rect I;

    public RunnableC4525r81(TabImpl tabImpl, WebContents webContents, Rect rect, Rect rect2) {
        this.F = tabImpl;
        this.G = webContents;
        this.H = rect;
        this.I = rect2;
    }

    public void run() {
        TabImpl tabImpl = this.F;
        WebContents webContents = this.G;
        Rect rect = this.H;
        Rect rect2 = this.I;
        Objects.requireNonNull(tabImpl);
        webContents.p0(rect.width(), rect.height());
        if (rect2 != null) {
            N.MzfONDmc(tabImpl.F, webContents, rect2.right, rect2.bottom);
        }
        webContents.O();
        tabImpl.b0(webContents);
    }
}
