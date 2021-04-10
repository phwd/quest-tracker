package X;

import android.view.View;
import androidx.annotation.RequiresApi;
import com.oculus.horizon.R;

/* renamed from: X.0sT  reason: invalid class name */
public class AnonymousClass0sT extends AnonymousClass07T<Boolean> {
    public AnonymousClass0sT() {
        super(R.id.tag_screen_reader_focusable, Boolean.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass07T
    @RequiresApi(28)
    public final Boolean A01(View view) {
        return Boolean.valueOf(view.isScreenReaderFocusable());
    }
}
