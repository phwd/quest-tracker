package com.google.android.play.core.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PlayCoreDialogWrapperActivity extends Activity {
    public ResultReceiver F;

    public void onActivityResult(int i, int i2, Intent intent) {
        ResultReceiver resultReceiver;
        int i3;
        Bundle bundle;
        super.onActivityResult(i, i2, intent);
        if (i == 0 && (resultReceiver = this.F) != null) {
            if (i2 == -1) {
                i3 = 1;
                bundle = new Bundle();
            } else if (i2 == 0) {
                i3 = 2;
                bundle = new Bundle();
            }
            resultReceiver.send(i3, bundle);
        }
        finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.F = (ResultReceiver) getIntent().getParcelableExtra("result_receiver");
            try {
                startIntentSenderForResult(((PendingIntent) getIntent().getExtras().get("confirmation_intent")).getIntentSender(), 0, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException unused) {
                ResultReceiver resultReceiver = this.F;
                if (resultReceiver != null) {
                    resultReceiver.send(3, new Bundle());
                }
                finish();
            }
        } else {
            this.F = (ResultReceiver) bundle.getParcelable("result_receiver");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("result_receiver", this.F);
    }
}
