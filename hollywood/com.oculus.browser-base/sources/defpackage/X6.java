package defpackage;

import android.content.Context;
import android.text.style.MetricAffectingSpan;
import android.text.style.TextAppearanceSpan;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.util.List;
import org.chromium.components.omnibox.SuggestionAnswer;

/* renamed from: X6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X6 extends W6 {
    public final boolean g;
    public final int h;

    public X6(Context context, int i, SuggestionAnswer.ImageLine imageLine, boolean z) {
        super(context);
        this.g = z;
        this.h = i;
        List list = imageLine.f10865a;
        boolean z2 = false;
        int i2 = 0;
        while (true) {
            boolean z3 = true;
            if (i2 >= list.size()) {
                break;
            }
            a(((SuggestionAnswer.TextField) list.get(i2)).b, b(((SuggestionAnswer.TextField) list.get(i2)).f10866a));
            if (((SuggestionAnswer.TextField) list.get(i2)).d == -1 ? false : z3) {
                this.f = Math.max(this.f, Math.min(3, ((SuggestionAnswer.TextField) list.get(i2)).d));
            }
            i2++;
        }
        if (imageLine.b != null) {
            this.c.append((CharSequence) "  ");
            SuggestionAnswer.TextField textField = imageLine.b;
            a(textField.b, b(textField.f10866a));
        }
        if (imageLine.c != null ? true : z2) {
            this.c.append((CharSequence) "  ");
            SuggestionAnswer.TextField textField2 = imageLine.c;
            a(textField2.b, b(textField2.f10866a));
        }
        this.d = this.c.toString();
    }

    public MetricAffectingSpan[] b(int i) {
        if (this.g) {
            int i2 = this.h;
            int i3 = R.style.f71850_resource_name_obfuscated_RES_2132017758;
            if (i2 == 1 || i2 == 2) {
                if (!(i == 3 || i == 8 || i == 13)) {
                    if (i == 5) {
                        i3 = R.style.f71640_resource_name_obfuscated_RES_2132017737;
                    } else if (i != 6) {
                        switch (i) {
                            case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                            case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                            case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                                break;
                            case Version.VERSION_20 /*{ENCODED_INT: 20}*/:
                                i3 = R.style.f72260_resource_name_obfuscated_RES_2132017799;
                                break;
                            default:
                                AbstractC1220Ua0.f("AnswerTextNewLayout", AbstractC2531fV.w("Unknown answer type: ", i), new Object[0]);
                                break;
                        }
                    } else {
                        i3 = R.style.f71650_resource_name_obfuscated_RES_2132017738;
                    }
                }
                return new TextAppearanceSpan[]{new TextAppearanceSpan(this.f9128a, i3)};
            }
            return new TextAppearanceSpan[]{new TextAppearanceSpan(this.f9128a, R.style.f71850_resource_name_obfuscated_RES_2132017758)};
        }
        return new TextAppearanceSpan[]{new TextAppearanceSpan(this.f9128a, R.style.f72010_resource_name_obfuscated_RES_2132017774)};
    }

    public X6(Context context, String str, boolean z) {
        super(context);
        this.g = z;
        this.h = 0;
        a(str, b(8));
    }
}
