package com.oculus.vralertservice;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Slog;
import com.oculus.vralertservice.AlertApplication;
import oculus.internal.ui.VrAlertDialog;

abstract class CriticalOverheatActivity extends Activity {
    private static final String TAG = "CriticalOverheatActivity";
    private VrAlertDialog mDialog;
    private final Handler mHandler = new Handler();
    private final int mLayoutId;
    private final AlertApplication.AlertLevel mLevel;
    private final String mShutdownReason;

    protected CriticalOverheatActivity(int i, String str, AlertApplication.AlertLevel alertLevel) {
        this.mLayoutId = i;
        this.mShutdownReason = str;
        this.mLevel = alertLevel;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDialog = VrAlertDialogs.getCriticalOverheatDialog(this, this.mLayoutId);
        this.mDialog.getWindow().setType(2009);
        this.mDialog.create();
        if (AlertApplication.setCurrentDialog(this.mLevel, this.mDialog)) {
            this.mHandler.postDelayed(new Runnable(playOverheatRing()) {
                /* class com.oculus.vralertservice.$$Lambda$CriticalOverheatActivity$8sKdoonVP0KlR2pWWBYr_JmcDPo */
                private final /* synthetic */ MediaPlayer f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    CriticalOverheatActivity.this.lambda$onCreate$0$CriticalOverheatActivity(this.f$1);
                }
            }, 30000);
        }
        finish();
    }

    public /* synthetic */ void lambda$onCreate$0$CriticalOverheatActivity(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        ((PowerManager) getSystemService("power")).shutdown(false, this.mShutdownReason, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0058, code lost:
        if (r4 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005e, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        r1.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0062, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.media.MediaPlayer playOverheatRing() {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vralertservice.CriticalOverheatActivity.playOverheatRing():android.media.MediaPlayer");
    }

    static /* synthetic */ boolean lambda$playOverheatRing$2(MediaPlayer mediaPlayer, int i, int i2) {
        String str = TAG;
        Slog.e(str, "Failed to play overheat_ring what=" + i + " extra=" + i2);
        if (mediaPlayer == null) {
            return true;
        }
        mediaPlayer.release();
        return true;
    }
}
