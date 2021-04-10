package X;

import android.text.StaticLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;

@RequiresApi(29)
/* renamed from: X.0Mj  reason: invalid class name */
public class AnonymousClass0Mj extends AnonymousClass0e1 {
    @Override // X.AnonymousClass04V, X.AnonymousClass0e1
    public final void A00(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection(textView.getTextDirectionHeuristic());
    }

    @Override // X.AnonymousClass04V
    public final boolean A01(TextView textView) {
        return textView.isHorizontallyScrollable();
    }
}
