package X;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/* renamed from: X.Rk  reason: case insensitive filesystem */
public abstract class AbstractDialogInterface$OnClickListenerC0330Rk implements DialogInterface.OnClickListener {
    public final void onClick(DialogInterface dialogInterface, int i) {
        try {
            C1100sW sWVar = (C1100sW) this;
            Intent intent = sWVar.A02;
            if (intent != null) {
                sWVar.A01.startActivityForResult(intent, sWVar.A00);
            }
        } catch (ActivityNotFoundException e) {
            String str = "Failed to start resolution intent.";
            if (Build.FINGERPRINT.contains("generic")) {
                str = str.concat(" This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store.");
            }
            Log.e("DialogRedirect", str, e);
        } catch (Throwable th) {
            dialogInterface.dismiss();
            throw th;
        }
        dialogInterface.dismiss();
    }
}
