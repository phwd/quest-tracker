package X;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.oculus.alpenglow.R;

/* renamed from: X.0dF  reason: invalid class name and case insensitive filesystem */
public class C03780dF extends AnonymousClass0Ak<Boolean> {
    public C03780dF() {
        super(R.id.tag_screen_reader_focusable, Boolean.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Ak
    @RequiresApi(28)
    public final Boolean A01(View view) {
        return Boolean.valueOf(view.isScreenReaderFocusable());
    }
}
