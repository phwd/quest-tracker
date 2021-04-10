package org.chromium.chrome.browser.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.ui.widget.ChromeImageButton;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HomeButton extends ChromeImageButton implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
    public Callback H;
    public Q31 I;

    public HomeButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Object obj = K2.f8337a;
        setImageDrawable(context.getDrawable(R.drawable.f28660_resource_name_obfuscated_RES_2131230906));
    }

    /* renamed from: e */
    public final void d() {
        if (!((Boolean) this.I.get()).booleanValue()) {
            setOnCreateContextMenuListener(this);
            return;
        }
        setOnCreateContextMenuListener(null);
        setLongClickable(false);
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.add(0, 0, 0, R.string.f56810_resource_name_obfuscated_RES_2131952998).setOnMenuItemClickListener(this);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent j0 = TraceEvent.j0("HomeButton.onLayout");
        try {
            super.onLayout(z, i, i2, i3, i4);
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

    public void onMeasure(int i, int i2) {
        TraceEvent j0 = TraceEvent.j0("HomeButton.onMeasure");
        try {
            super.onMeasure(i, i2);
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

    public boolean onMenuItemClick(MenuItem menuItem) {
        this.H.onResult(getContext());
        return true;
    }
}
