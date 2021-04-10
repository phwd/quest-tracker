package defpackage;

import J.N;
import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: OY0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OY0 extends N31 {
    public String[] W = new String[0];

    public OY0(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        super(context, textSuggestionHost, windowAndroid, view);
    }

    @Override // defpackage.N31
    public void a(int i) {
        TextSuggestionHost textSuggestionHost = this.G;
        N.M7RnYR2r(textSuggestionHost.F, textSuggestionHost, this.W[i]);
    }

    @Override // defpackage.N31
    public Object b(int i) {
        return this.W[i];
    }

    @Override // defpackage.N31
    public SpannableString c(int i) {
        return new SpannableString(this.W[i]);
    }

    @Override // defpackage.N31
    public int d() {
        return this.W.length;
    }
}
