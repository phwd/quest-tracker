package X;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.oculus.socialplatform.R;

/* renamed from: X.0vk  reason: invalid class name and case insensitive filesystem */
public class C05450vk extends AnonymousClass07T<Boolean> {
    public C05450vk() {
        super(R.id.tag_accessibility_heading, Boolean.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass07T
    @RequiresApi(28)
    public final Boolean A01(View view) {
        return Boolean.valueOf(view.isAccessibilityHeading());
    }
}
