package defpackage;

import android.text.style.ClickableSpan;
import android.view.MenuItem;
import org.chromium.ui.widget.TextViewWithClickableSpans;

/* renamed from: Ng1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MenuItem$OnMenuItemClickListenerC0812Ng1 implements MenuItem.OnMenuItemClickListener {
    public final /* synthetic */ ClickableSpan F;
    public final /* synthetic */ TextViewWithClickableSpans G;

    public MenuItem$OnMenuItemClickListenerC0812Ng1(TextViewWithClickableSpans textViewWithClickableSpans, ClickableSpan clickableSpan) {
        this.G = textViewWithClickableSpans;
        this.F = clickableSpan;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        this.F.onClick(this.G);
        return true;
    }
}
