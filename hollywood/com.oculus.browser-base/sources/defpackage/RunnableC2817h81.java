package defpackage;

import org.chromium.chrome.browser.tasks.tab_management.TabGroupUiToolbarView;

/* renamed from: h81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2817h81 implements Runnable {
    public final /* synthetic */ C3493l60 F;
    public final /* synthetic */ TabGroupUiToolbarView G;

    public RunnableC2817h81(TabGroupUiToolbarView tabGroupUiToolbarView, C3493l60 l60) {
        this.G = tabGroupUiToolbarView;
        this.F = l60;
    }

    public void run() {
        this.F.i(this.G.L);
    }
}
