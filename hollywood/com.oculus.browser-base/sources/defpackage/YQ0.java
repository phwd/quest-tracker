package defpackage;

import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import java.util.Objects;

/* renamed from: YQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YQ0 implements View.OnKeyListener {
    public final /* synthetic */ SearchView F;

    public YQ0(SearchView searchView) {
        this.F = searchView;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Objects.requireNonNull(this.F);
        return false;
    }
}
