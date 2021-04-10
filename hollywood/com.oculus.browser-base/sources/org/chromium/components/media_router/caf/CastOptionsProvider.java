package org.chromium.components.media_router.caf;

import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.CastOptions;
import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CastOptionsProvider {
    public List a() {
        return null;
    }

    public CastOptions b() {
        ArrayList arrayList = new ArrayList();
        new LaunchOptions();
        LaunchOptions launchOptions = new LaunchOptions();
        launchOptions.F = true;
        return new CastOptions(null, arrayList, true, launchOptions, false, null, false, 0.05000000074505806d, false);
    }
}
