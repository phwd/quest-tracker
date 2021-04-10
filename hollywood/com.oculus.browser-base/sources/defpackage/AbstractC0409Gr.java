package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;

/* renamed from: Gr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0409Gr {
    public static Intent a(int i) {
        Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        Intent intent = new Intent(applicationContext, Lr.class);
        intent.putExtra("com.android.browser.application_id", applicationContext.getPackageName());
        intent.putExtra("BRING_TAB_TO_FRONT", i);
        return intent;
    }
}
