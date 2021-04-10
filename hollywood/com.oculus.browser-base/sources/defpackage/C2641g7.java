package defpackage;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* renamed from: g7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2641g7 {
    public static Bundle a(int i) {
        ActivityOptions makeBasic = ActivityOptions.makeBasic();
        makeBasic.setLaunchDisplayId(i);
        return makeBasic.toBundle();
    }

    public static void b(TextView textView) {
        textView.setTextClassifier(TextClassifier.NO_OP);
    }

    public static void c(Intent intent, String str) {
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", str);
    }
}
