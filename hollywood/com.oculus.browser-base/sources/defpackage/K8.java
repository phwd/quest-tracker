package defpackage;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* renamed from: K8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class K8 {

    /* renamed from: a  reason: collision with root package name */
    public TextView f8346a;
    public TextClassifier b;

    public K8(TextView textView) {
        this.f8346a = textView;
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f8346a.getContext().getSystemService(TextClassificationManager.class);
        if (textClassificationManager != null) {
            return textClassificationManager.getTextClassifier();
        }
        return TextClassifier.NO_OP;
    }
}
