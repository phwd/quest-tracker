package android.support.v4.widget;

import android.os.Build;
import android.widget.TextView;

public final class TextViewCompat {
    public static void setTextAppearance(TextView textView, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            textView.setTextAppearance(i);
        } else {
            textView.setTextAppearance(textView.getContext(), i);
        }
    }
}
