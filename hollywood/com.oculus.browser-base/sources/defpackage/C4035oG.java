package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.chromium.chrome.browser.dom_distiller.DistilledPagePrefsView;

/* renamed from: oG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4035oG extends ArrayAdapter {
    public C4035oG(DistilledPagePrefsView distilledPagePrefsView, Context context, int i, CharSequence[] charSequenceArr) {
        super(context, i, charSequenceArr);
    }

    public final View a(View view, int i) {
        BR.a(i);
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (i == 2) {
                textView.setTypeface(Typeface.MONOSPACE);
            } else if (i == 0) {
                textView.setTypeface(Typeface.SANS_SERIF);
            } else if (i == 1) {
                textView.setTypeface(Typeface.SERIF);
            }
        }
        return view;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View dropDownView = super.getDropDownView(i, view, viewGroup);
        a(dropDownView, i);
        return dropDownView;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        a(view2, i);
        return view2;
    }
}
