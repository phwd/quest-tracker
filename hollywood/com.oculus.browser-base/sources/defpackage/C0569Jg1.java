package defpackage;

import J.N;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.content.browser.input.SuggestionInfo;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: Jg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0569Jg1 extends N31 {
    public SuggestionInfo[] W;
    public TextAppearanceSpan X;
    public TextAppearanceSpan Y;

    public C0569Jg1(Context context, TextSuggestionHost textSuggestionHost, WindowAndroid windowAndroid, View view) {
        super(context, textSuggestionHost, windowAndroid, view);
        this.X = new TextAppearanceSpan(context, R.style.f71760_resource_name_obfuscated_RES_2132017749);
        this.Y = new TextAppearanceSpan(context, R.style.f71760_resource_name_obfuscated_RES_2132017749);
    }

    @Override // defpackage.N31
    public void a(int i) {
        SuggestionInfo suggestionInfo = this.W[i];
        TextSuggestionHost textSuggestionHost = this.G;
        N.MIADbBhq(textSuggestionHost.F, textSuggestionHost, suggestionInfo.f10928a, suggestionInfo.b);
    }

    @Override // defpackage.N31
    public Object b(int i) {
        return this.W[i];
    }

    @Override // defpackage.N31
    public SpannableString c(int i) {
        SuggestionInfo suggestionInfo = this.W[i];
        SpannableString spannableString = new SpannableString(suggestionInfo.c + suggestionInfo.d + suggestionInfo.e);
        spannableString.setSpan(this.X, 0, suggestionInfo.c.length(), 33);
        TextAppearanceSpan textAppearanceSpan = this.Y;
        int length = suggestionInfo.d.length() + suggestionInfo.c.length();
        int length2 = suggestionInfo.c.length();
        spannableString.setSpan(textAppearanceSpan, length, suggestionInfo.e.length() + suggestionInfo.d.length() + length2, 33);
        return spannableString;
    }

    @Override // defpackage.N31
    public int d() {
        return this.W.length;
    }
}
