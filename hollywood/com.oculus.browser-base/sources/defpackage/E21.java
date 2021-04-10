package defpackage;

import android.content.Context;
import android.view.View;
import org.chromium.chrome.browser.omnibox.status.StatusView;

/* renamed from: E21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E21 implements View.OnLongClickListener {
    public final /* synthetic */ StatusView F;

    public E21(StatusView statusView) {
        this.F = statusView;
    }

    public boolean onLongClick(View view) {
        StatusView statusView = this.F;
        if (statusView.R == 0) {
            return false;
        }
        Context context = statusView.getContext();
        return C1184Ti1.c(context, view, context.getResources().getString(this.F.R));
    }
}
