package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.cast.framework.R;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HelpTextView extends LinearLayout {
    public TextView F;
    public TextView G;

    public HelpTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public View asView() {
        return this;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.cast_featurehighlight_help_text_header_view);
        Objects.requireNonNull(textView);
        this.F = textView;
        TextView textView2 = (TextView) findViewById(R.id.cast_featurehighlight_help_text_body_view);
        Objects.requireNonNull(textView2);
        this.G = textView2;
    }

    public void setText(CharSequence charSequence, CharSequence charSequence2) {
        TextView textView = this.F;
        textView.setText(charSequence);
        int i = 8;
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        TextView textView2 = this.G;
        textView2.setText(charSequence2);
        if (!TextUtils.isEmpty(charSequence2)) {
            i = 0;
        }
        textView2.setVisibility(i);
    }
}
