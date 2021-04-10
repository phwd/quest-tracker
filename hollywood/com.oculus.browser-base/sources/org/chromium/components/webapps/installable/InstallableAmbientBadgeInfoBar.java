package org.chromium.components.webapps.installable;

import J.N;
import android.graphics.Bitmap;
import android.view.View;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.webapps.WebappsIconUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InstallableAmbientBadgeInfoBar extends InfoBar implements View.OnClickListener {
    public boolean I;

    public InstallableAmbientBadgeInfoBar(int i, Bitmap bitmap, String str, String str2) {
        super(i, 0, null, bitmap);
    }

    public static InfoBar show(int i, Bitmap bitmap, String str, String str2, boolean z) {
        if (z && WebappsIconUtils.a()) {
            bitmap = WebappsIconUtils.b(bitmap);
        }
        return new InstallableAmbientBadgeInfoBar(i, bitmap, str, str2);
    }

    public void onClick(View view) {
        long j = this.H;
        if (j != 0 && !this.I) {
            N.MzHO1MxZ(j, this);
        }
    }
}
