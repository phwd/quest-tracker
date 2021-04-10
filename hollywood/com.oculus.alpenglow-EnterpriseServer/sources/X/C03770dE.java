package X;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.oculus.alpenglow.R;

/* renamed from: X.0dE  reason: invalid class name and case insensitive filesystem */
public class C03770dE extends AnonymousClass0Ak<CharSequence> {
    public C03770dE() {
        super(R.id.tag_accessibility_pane_title, CharSequence.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Ak
    @RequiresApi(28)
    public final CharSequence A01(View view) {
        return view.getAccessibilityPaneTitle();
    }
}
