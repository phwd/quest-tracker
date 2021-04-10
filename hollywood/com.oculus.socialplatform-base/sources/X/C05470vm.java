package X;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.oculus.socialplatform.R;

/* renamed from: X.0vm  reason: invalid class name and case insensitive filesystem */
public class C05470vm extends AnonymousClass07T<Boolean> {
    public C05470vm() {
        super(R.id.tag_screen_reader_focusable, Boolean.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass07T
    @RequiresApi(28)
    public final Boolean A01(View view) {
        return Boolean.valueOf(view.isScreenReaderFocusable());
    }
}
