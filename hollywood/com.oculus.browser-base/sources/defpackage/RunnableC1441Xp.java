package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkBridge;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Xp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1441Xp implements Runnable {
    public final ChromeActivity F;
    public final Tab G;

    public RunnableC1441Xp(ChromeActivity chromeActivity, Tab tab) {
        this.F = chromeActivity;
        this.G = tab;
    }

    public void run() {
        ChromeActivity chromeActivity = this.F;
        Tab tab = this.G;
        Objects.requireNonNull(chromeActivity);
        AbstractC1243Ui.b(tab.n(), tab.getTitle(), chromeActivity.U(), (BookmarkBridge) chromeActivity.r0.H, chromeActivity);
    }
}
