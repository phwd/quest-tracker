package org.chromium.chrome.browser;

import android.accounts.Account;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import java.io.File;
import java.util.List;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class IntentHelper {
    public static void sendEmail(String str, String str2, String str3, String str4, String str5) {
        Uri uri;
        List n;
        if (TextUtils.isEmpty(str) && (n = AccountManagerFacadeProvider.getInstance().n()) != null && n.size() == 1 && Patterns.EMAIL_ADDRESS.matcher(((Account) n.get(0)).name).matches()) {
            str = ((Account) n.get(0)).name;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        }
        intent.putExtra("android.intent.extra.SUBJECT", str2);
        intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(str3));
        if (!TextUtils.isEmpty(str5)) {
            File file = new File(str5);
            try {
                uri = ContentUriUtils.b(file);
            } catch (IllegalArgumentException unused) {
                uri = Uri.fromFile(file);
            }
            intent.putExtra("android.intent.extra.STREAM", uri);
        }
        try {
            Intent createChooser = Intent.createChooser(intent, str4);
            createChooser.addFlags(268435456);
            ContextUtils.getApplicationContext().startActivity(createChooser);
        } catch (ActivityNotFoundException unused2) {
        }
    }
}
