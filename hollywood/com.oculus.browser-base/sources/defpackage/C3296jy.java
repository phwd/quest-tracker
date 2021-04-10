package defpackage;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: jy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3296jy {

    /* renamed from: a  reason: collision with root package name */
    public final Resources f10252a;
    public final Map b = new HashMap();

    public C3296jy(Resources resources) {
        this.f10252a = resources;
    }

    public final void a(View view, ArrayList arrayList) {
        if (view.getVisibility() == 0) {
            arrayList.add(view);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    a(viewGroup.getChildAt(i), arrayList);
                }
            }
        }
    }

    public void b(TextView textView, int i, PV0 pv0) {
        CharSequence text = this.f10252a.getText(i);
        textView.setText(text);
        this.b.put(textView, new C3125iy(text.toString(), i));
    }

    public void c(TextView textView, CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        textView.setText(charSequence);
        this.b.put(textView, new C3125iy(charSequence.toString(), 0));
    }
}
