package org.chromium.chrome.browser.browsing_data;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearBrowsingDataCheckBoxPreference extends ChromeBaseCheckBoxPreference {
    public Runnable A0;
    public boolean B0;
    public View z0;

    public ClearBrowsingDataCheckBoxPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.Preference
    public void T(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        if (!charSequence2.contains("<link>") || !charSequence2.contains("</link>")) {
            super.T(charSequence);
        } else if (C0283Ep.h().d()) {
            super.T(charSequence2.replaceAll("</?link>", ""));
        } else {
            SpannableString a2 = FY0.a(charSequence2, new EY0("<link>", "</link>", new C4467qp0(this.F.getResources(), new C0354Fu(this))));
            this.B0 = true;
            super.T(a2);
        }
    }

    public final /* synthetic */ boolean j0(TextView textView, MotionEvent motionEvent) {
        if (!this.B0) {
            return false;
        }
        int offsetForPosition = textView.getOffsetForPosition(motionEvent.getX(), motionEvent.getY());
        CharSequence text = textView.getText();
        if (!(text instanceof Spanned)) {
            return false;
        }
        ClickableSpan[] clickableSpanArr = (ClickableSpan[]) ((Spanned) text).getSpans(offsetForPosition, offsetForPosition, ClickableSpan.class);
        if (clickableSpanArr.length <= 0) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            for (ClickableSpan clickableSpan : clickableSpanArr) {
                clickableSpan.onClick(textView);
            }
        }
        return true;
    }

    public final /* synthetic */ void k0() {
        Runnable runnable = this.A0;
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // androidx.preference.Preference, org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        View view = tf0.G;
        this.z0 = view;
        TextView textView = (TextView) view.findViewById(16908304);
        textView.setOnTouchListener(new View$OnTouchListenerC0293Eu(this, textView));
    }
}
