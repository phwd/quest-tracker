package com.oculus.panelapp.debug;

import android.content.Context;
import android.os.Handler;
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

public class XrspTab extends LinearLayout implements DebugTabHost.DebugTab, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = LoggingUtil.tag(XrspTab.class);
    private final Handler mHandler;
    private boolean mTaskScheduled;
    private TextView mTextView;
    private final Timer mTimer;
    private UiThreadDispatch mTimerTask;
    private final Runnable mUiThreadRunnable;

    public XrspTab(@NonNull Context context) {
        this(context, null);
    }

    public XrspTab(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTaskScheduled = false;
        this.mHandler = new Handler();
        this.mUiThreadRunnable = new Runnable() {
            /* class com.oculus.panelapp.debug.XrspTab.AnonymousClass1 */

            public void run() {
                TextView textView = XrspTab.this.mTextView;
                textView.setText("Status: " + XrspDebugInfo.getStateString().toString());
            }
        };
        this.mTimer = new Timer(true);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        this.mTextView = (TextView) findViewById(R.id.debug_tab_xrsp_textview);
    }

    public synchronized void onGlobalLayout() {
        if (!this.mTaskScheduled && isShown()) {
            this.mTaskScheduled = true;
            this.mTimerTask = new UiThreadDispatch();
            this.mTimer.scheduleAtFixedRate(this.mTimerTask, 0, 1000);
        } else if (this.mTaskScheduled && !isShown()) {
            this.mTimerTask.cancel();
            this.mTimer.purge();
            this.mTaskScheduled = false;
        }
    }

    private class UiThreadDispatch extends TimerTask {
        private UiThreadDispatch() {
        }

        public void run() {
            XrspTab.this.mHandler.post(XrspTab.this.mUiThreadRunnable);
        }
    }
}
