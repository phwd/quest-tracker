package org.chromium.components.signin.base;

import android.graphics.Bitmap;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountInfo extends CoreAccountInfo {
    public final Bitmap d;

    public AccountInfo(CoreAccountId coreAccountId, String str, String str2, String str3, String str4, Bitmap bitmap) {
        super(coreAccountId, str, str2);
        this.d = bitmap;
    }
}
