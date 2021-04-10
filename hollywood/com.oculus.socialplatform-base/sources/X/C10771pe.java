package X;

import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.widget.TextView;

/* renamed from: X.1pe  reason: invalid class name and case insensitive filesystem */
public class C10771pe {
    public boolean A01(TextView textView) {
        return ((Boolean) C10761pd.A00(textView, "getHorizontallyScrolling", false)).booleanValue();
    }

    public void A00(StaticLayout.Builder builder, TextView textView) {
        if (this instanceof C10781pf) {
            builder.setTextDirection((TextDirectionHeuristic) C10761pd.A00(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }
}
