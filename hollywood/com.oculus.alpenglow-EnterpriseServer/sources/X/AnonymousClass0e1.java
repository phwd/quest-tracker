package X;

import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.widget.TextView;
import androidx.annotation.RequiresApi;

@RequiresApi(23)
/* renamed from: X.0e1  reason: invalid class name */
public class AnonymousClass0e1 extends AnonymousClass04V {
    @Override // X.AnonymousClass04V
    public void A00(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection((TextDirectionHeuristic) AnonymousClass04W.A00(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
    }
}
