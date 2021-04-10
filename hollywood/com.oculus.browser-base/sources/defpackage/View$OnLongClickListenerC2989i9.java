package defpackage;

import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: i9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnLongClickListenerC2989i9 implements View.OnLongClickListener {
    public final C4185p9 F;
    public final MenuItem G;

    public View$OnLongClickListenerC2989i9(C4185p9 p9Var, MenuItem menuItem) {
        this.F = p9Var;
        this.G = menuItem;
    }

    public boolean onLongClick(View view) {
        C4185p9 p9Var = this.F;
        MenuItem menuItem = this.G;
        View$OnKeyListenerC2476f9 f9Var = (View$OnKeyListenerC2476f9) p9Var.G;
        Objects.requireNonNull(f9Var);
        if (!menuItem.isEnabled()) {
            return false;
        }
        f9Var.V = true;
        CharSequence titleCondensed = menuItem.getTitleCondensed();
        if (TextUtils.isEmpty(titleCondensed)) {
            titleCondensed = menuItem.getTitle();
        }
        return C1184Ti1.c(ContextUtils.getApplicationContext(), view, titleCondensed);
    }
}
