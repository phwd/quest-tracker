package X;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.oculus.socialplatform.R;

/* renamed from: X.0vl  reason: invalid class name and case insensitive filesystem */
public class C05460vl extends AnonymousClass07T<CharSequence> {
    public C05460vl() {
        super(R.id.tag_accessibility_pane_title, CharSequence.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass07T
    @RequiresApi(28)
    public final CharSequence A01(View view) {
        return view.getAccessibilityPaneTitle();
    }
}
