package defpackage;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.util.Log;

/* renamed from: XE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class XE implements DialogInterface.OnClickListener {
    public abstract void a();

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            a();
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } finally {
            dialogInterface.dismiss();
        }
    }
}
