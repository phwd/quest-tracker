package X;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.oculus.horizon.R;

/* renamed from: X.0sS  reason: invalid class name and case insensitive filesystem */
public class C07410sS extends AnonymousClass07T<CharSequence> {
    public C07410sS() {
        super(R.id.tag_accessibility_pane_title, CharSequence.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass07T
    @RequiresApi(28)
    public final CharSequence A01(View view) {
        return view.getAccessibilityPaneTitle();
    }
}
