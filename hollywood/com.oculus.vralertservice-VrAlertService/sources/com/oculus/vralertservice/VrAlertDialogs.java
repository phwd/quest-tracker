package com.oculus.vralertservice;

import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import oculus.internal.ui.VrAlertDialog;

/* access modifiers changed from: package-private */
public class VrAlertDialogs {
    static VrAlertDialog getCriticalOverheatDialog(Context context, int i) {
        Context applicationContext = context.getApplicationContext();
        return new VrAlertDialog.Builder(applicationContext, 16974916).setIconAttribute(16843605).setTitle((int) R.string.system_alert).setView(LayoutInflater.from(applicationContext).inflate(i, (ViewGroup) null)).create();
    }

    static VrAlertDialog getExtTempDialog(Context context, Runnable runnable) {
        return new VrAlertDialog.Builder(context.getApplicationContext(), 16974916).setIconAttribute(16843605).setTitle((int) R.string.system_alert).setMessage((int) R.string.external_temp_warning).setPositiveButton((int) R.string.notification_dismiss, new DialogInterface.OnClickListener(runnable) {
            /* class com.oculus.vralertservice.$$Lambda$VrAlertDialogs$Ao8Dl60Zw9tGhOklxcY_PIeNpYY */
            private final /* synthetic */ Runnable f$0;

            {
                this.f$0 = r1;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                VrAlertDialogs.lambda$getExtTempDialog$0(this.f$0, dialogInterface, i);
            }
        }).create();
    }

    static /* synthetic */ void lambda$getExtTempDialog$0(Runnable runnable, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (runnable != null) {
            runnable.run();
        }
    }

    static VrAlertDialog getFanMalfunctionDialog(Context context, Runnable runnable) {
        Context applicationContext = context.getApplicationContext();
        View inflate = LayoutInflater.from(applicationContext).inflate(R.layout.fan_malfunction_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.message);
        SpannableString spannableString = new SpannableString((String) textView.getText());
        Linkify.addLinks(spannableString, 1);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        return new VrAlertDialog.Builder(applicationContext, 16974916).setIconAttribute(16843605).setTitle((int) R.string.system_alert).setView(inflate).setPositiveButton((int) R.string.notification_dismiss, new DialogInterface.OnClickListener(runnable) {
            /* class com.oculus.vralertservice.$$Lambda$VrAlertDialogs$W04QM7cgE9IYcAvl4tBlvO3t1s4 */
            private final /* synthetic */ Runnable f$0;

            {
                this.f$0 = r1;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                VrAlertDialogs.lambda$getFanMalfunctionDialog$1(this.f$0, dialogInterface, i);
            }
        }).create();
    }

    static /* synthetic */ void lambda$getFanMalfunctionDialog$1(Runnable runnable, DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        if (runnable != null) {
            runnable.run();
        }
    }
}
