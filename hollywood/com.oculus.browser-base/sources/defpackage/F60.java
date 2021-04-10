package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import org.chromium.chrome.browser.language.settings.AddLanguageFragment;

/* renamed from: F60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class F60 implements View.OnClickListener {
    public final C4338q3 F;
    public final B60 G;

    public F60(C4338q3 q3Var, B60 b60) {
        this.F = q3Var;
        this.G = b60;
    }

    public void onClick(View view) {
        C4338q3 q3Var = this.F;
        B60 b60 = this.G;
        Activity activity = q3Var.f11111a;
        int i = AddLanguageFragment.y0;
        Intent intent = new Intent();
        intent.putExtra("AddLanguageFragment.SelectedLanguages", b60.f7716a);
        activity.setResult(-1, intent);
        activity.finish();
    }
}
