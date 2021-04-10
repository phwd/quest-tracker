package defpackage;

import android.content.Context;
import android.os.Build;
import android.os.LocaleList;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;

/* renamed from: cY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2026cY0 extends AbstractC2032cb {
    public final TextClassifier i;
    public final int j;
    public final CharSequence k;
    public final int l;
    public final int m;
    public final Context n;
    public final /* synthetic */ C2197dY0 o;

    public C2026cY0(C2197dY0 dy0, TextClassifier textClassifier, int i2, CharSequence charSequence, int i3, int i4, Context context) {
        this.o = dy0;
        this.i = textClassifier;
        this.j = i2;
        this.k = charSequence;
        this.l = i3;
        this.m = i4;
        this.n = context;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        TextSelection textSelection;
        int i2 = this.l;
        int i3 = this.m;
        if (this.j == 1) {
            TextSelection suggestSelection = this.i.suggestSelection(this.k, i2, i3, LocaleList.getAdjustedDefault());
            int max = Math.max(0, suggestSelection.getSelectionStartIndex());
            int min = Math.min(this.k.length(), suggestSelection.getSelectionEndIndex());
            if (h()) {
                return new C2355eS0();
            }
            textSelection = suggestSelection;
            i2 = max;
            i3 = min;
        } else {
            textSelection = null;
        }
        TextClassification classifyText = this.i.classifyText(this.k, i2, i3, LocaleList.getAdjustedDefault());
        C2355eS0 es0 = new C2355eS0();
        es0.f9855a = i2 - this.l;
        es0.b = i3 - this.m;
        es0.c = classifyText.getLabel();
        es0.d = classifyText.getIcon();
        es0.e = classifyText.getIntent();
        es0.f = classifyText.getOnClickListener();
        es0.h = textSelection;
        es0.g = classifyText;
        if (Build.VERSION.SDK_INT >= 28) {
            es0.i = H3.e(this.n, classifyText);
        }
        return es0;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.o.f9789a.a((C2355eS0) obj);
    }
}
