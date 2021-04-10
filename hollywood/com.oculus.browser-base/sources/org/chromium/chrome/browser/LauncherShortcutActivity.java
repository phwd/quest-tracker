package org.chromium.chrome.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LauncherShortcutActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if (action.equals("chromium.shortcut.action.OPEN_NEW_TAB") || action.equals("chromium.shortcut.action.OPEN_NEW_INCOGNITO_TAB")) {
            Intent b = S20.b(this, action.equals("chromium.shortcut.action.OPEN_NEW_INCOGNITO_TAB"));
            b.putExtra("com.android.chrome.invoked_from_shortcut", true);
            startActivity(b);
            finish();
            return;
        }
        finish();
    }
}
