package com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing;

import java.util.Timer;
import java.util.TimerTask;

/* access modifiers changed from: package-private */
public class ControllerPairingRemainingTimeCounter {
    public static final int COUNTER_DELAY_MILLISECONDS = 800;
    public static final int COUNTER_PERIOD_MILLISECONDS = 200;
    private RemainingTimeListener mListener;
    private long mPairingStartTime;
    private Timer mPairingTimer;
    private TimerTask mPairingTimerTask;

    /* access modifiers changed from: package-private */
    public interface RemainingTimeListener {
        void onRemainingTime(long j);
    }

    public synchronized void stop() {
        if (this.mPairingTimer != null) {
            this.mPairingTimerTask.cancel();
            this.mPairingTimer.purge();
            this.mPairingTimerTask = null;
            this.mPairingTimer = null;
        }
    }

    ControllerPairingRemainingTimeCounter(RemainingTimeListener remainingTimeListener) {
        this.mListener = remainingTimeListener;
    }

    public void start(long j) {
        this.mPairingStartTime = j;
        this.mPairingTimerTask = new TimerTask() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingRemainingTimeCounter.AnonymousClass1 */

            public void run() {
                long j = ControllerPairingRemainingTimeCounter.this.mPairingStartTime + 30000;
                long min = j - Math.min(j, System.currentTimeMillis());
                if (ControllerPairingRemainingTimeCounter.this.mListener != null) {
                    ControllerPairingRemainingTimeCounter.this.mListener.onRemainingTime(min);
                }
                if (min == 0) {
                    ControllerPairingRemainingTimeCounter.this.stop();
                }
            }
        };
        this.mPairingTimer = new Timer();
        this.mPairingTimer.scheduleAtFixedRate(this.mPairingTimerTask, 800, 200);
    }
}
