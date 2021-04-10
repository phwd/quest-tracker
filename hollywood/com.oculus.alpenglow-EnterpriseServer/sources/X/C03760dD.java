package X;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.oculus.alpenglow.R;

/* renamed from: X.0dD  reason: invalid class name and case insensitive filesystem */
public class C03760dD extends AnonymousClass0Ak<Boolean> {
    public C03760dD() {
        super(R.id.tag_accessibility_heading, Boolean.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Ak
    @RequiresApi(28)
    public final Boolean A01(View view) {
        return Boolean.valueOf(view.isAccessibilityHeading());
    }
}
