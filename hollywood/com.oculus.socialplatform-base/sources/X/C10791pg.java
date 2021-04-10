package X;

import android.text.StaticLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;

@RequiresApi(29)
/* renamed from: X.1pg  reason: invalid class name and case insensitive filesystem */
public class C10791pg extends C10781pf {
    @Override // X.C10771pe
    public final void A00(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection(textView.getTextDirectionHeuristic());
    }

    @Override // X.C10771pe
    public final boolean A01(TextView textView) {
        return textView.isHorizontallyScrollable();
    }
}
