package defpackage;

import android.view.View;
import androidx.appcompat.widget.SearchView;
import java.util.Objects;

/* renamed from: VQ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VQ0 implements View.OnFocusChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchView f9082a;

    public VQ0(SearchView searchView) {
        this.f9082a = searchView;
    }

    public void onFocusChange(View view, boolean z) {
        Objects.requireNonNull(this.f9082a);
    }
}
