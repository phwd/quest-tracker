package defpackage;

import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: g00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2621g00 {
    public C2621g00(View view, C2792h00 h00, Runnable runnable) {
        TextView textView = (TextView) view.findViewById(R.id.incognito_interstitial_message);
        textView.setText(FY0.a(textView.getText().toString(), new EY0("<b1>", "</b1>", new StyleSpan(1)), new EY0("<b2>", "</b2>", new StyleSpan(1))));
        ZH0.a(new C3304k00(h00, runnable).f10253a, view, new C2450f00());
    }
}
