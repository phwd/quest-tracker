package defpackage;

import android.view.View;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: DE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DE implements View.OnClickListener {
    public final EE F;

    public DE(EE ee) {
        this.F = ee;
    }

    public void onClick(View view) {
        EE ee = this.F;
        Objects.requireNonNull(ee);
        AbstractC3535lK0.a("SharingHubAndroid.SendTabToSelf.ChromeSettingsClicked");
        ee.O.b(ContextUtils.getApplicationContext(), null, null);
    }
}
