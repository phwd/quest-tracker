package com.oculus.panelapp.debug;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.debug.DebugTabHost;
import java.util.Timer;
import java.util.TimerTask;

class TrackingTab extends LinearLayout implements DebugTabHost.DebugTab, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String DEBUG_INFO_UNAVAILABLE = "Debug info is not currently available in this mode or device.";
    private static final String TAG = LoggingUtil.tag(TrackingTab.class);
    private final TrackingDebugInfo mDebugInfo;
    private final Handler mHandler;
    private boolean mTaskScheduled;
    private TextView mTextView;
    private final Timer mTimer;
    private UiThreadDispatch mTimerTask;
    private final Runnable mUiThreadRunnable;

    public TrackingTab(@NonNull Context context) {
        this(context, null);
    }

    public TrackingTab(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTaskScheduled = false;
        this.mHandler = new Handler();
        this.mUiThreadRunnable = new Runnable() {
            /* class com.oculus.panelapp.debug.TrackingTab.AnonymousClass1 */

            public void run() {
                String str = TrackingTab.this.mDebugInfo.get();
                if (TextUtils.isEmpty(str)) {
                    str = TrackingTab.DEBUG_INFO_UNAVAILABLE;
                }
                TrackingTab.this.mTextView.setText(str);
            }
        };
        this.mTimer = new Timer(true);
        this.mDebugInfo = new TrackingDebugInfo();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        this.mTextView = (TextView) findViewById(R.id.debug_tab_tracking_textview);
    }

    public synchronized void onGlobalLayout() {
        if (!this.mTaskScheduled && isShown()) {
            this.mDebugInfo.start();
            this.mTaskScheduled = true;
            this.mTimerTask = new UiThreadDispatch();
            this.mTimer.scheduleAtFixedRate(this.mTimerTask, 0, 250);
        } else if (this.mTaskScheduled && !isShown()) {
            this.mTimerTask.cancel();
            this.mTimer.purge();
            this.mDebugInfo.stop();
            this.mTaskScheduled = false;
        }
    }

    private class UiThreadDispatch extends TimerTask {
        private UiThreadDispatch() {
        }

        public void run() {
            TrackingTab.this.mHandler.post(TrackingTab.this.mUiThreadRunnable);
        }
    }
}
