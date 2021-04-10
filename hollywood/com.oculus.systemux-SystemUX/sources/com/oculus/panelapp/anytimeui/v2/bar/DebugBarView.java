package com.oculus.panelapp.anytimeui.v2.bar;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCButton;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.databinding.AnytimeBarDebugViewV2Binding;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.destinationui.DestinationUIViewModel;
import com.oculus.tablet.logging.ClickEventButtonId;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.InputFrame;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public final class DebugBarView extends ConstraintLayout implements AndroidPanelApp.PanelFrameCallback {
    private static final long DEBUG_BAR_DISMISS_INTERVAL_MILLIS = 1000;
    private static final long DEBUG_BAR_DISMISS_INTERVAL_MILLIS_AUTOMATION = 5000;
    private static final long SYSTEM_STATS_UPDATE_INTERVAL_MILLIS = 2013;
    private static final String TAG = LoggingUtil.tag(DebugBarView.class);
    private AnytimeBarDebugViewV2Binding mBinding;
    private DestinationUIViewModel mDestinationUIViewModel;
    private boolean mIsHovered;
    private long mLastSystemStatsUpdateTimeMillis;
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private long mPrevCpuProcessTicks;
    private long mPrevCpuTotalTicks;
    private long mPrevFrameMillis;
    private long mTimeToLiveMillis;

    public DebugBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, AnytimeBarDebugViewV2Binding anytimeBarDebugViewV2Binding) {
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mBinding = anytimeBarDebugViewV2Binding;
        this.mDestinationUIViewModel = anytimeUIAndroidPanelAppV2.acquireDestinationUIViewModel();
        this.mTimeToLiveMillis = getBarDismissInterval();
        initializeInternalSettingsButton();
        initializeBugReportButton();
        initializeDogfoodingButton();
        initializeSystemStatsIndicators();
    }

    public void destroy() {
        Log.d(TAG, "Destroying DebugBarView");
        this.mPanelApp.releaseDestinationUIViewModel();
        this.mPanelApp.removePanelFrameCallback(this);
    }

    private void initializeInternalSettingsButton() {
        Log.d(TAG, "Initializing internal settings button");
        OCButton oCButton = this.mBinding.settingsButton;
        oCButton.setEventHandler(this.mPanelApp);
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$DebugBarView$wFKEaz7SjJaDK2z14ci74wcx3Ng */

            public final void onClick(View view) {
                DebugBarView.this.lambda$initializeInternalSettingsButton$92$DebugBarView(view);
            }
        });
        Log.d(TAG, "Initialized internal settings button");
    }

    public /* synthetic */ void lambda$initializeInternalSettingsButton$92$DebugBarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_DEBUG_INTERNAL_SETTINGS);
        this.mPanelApp.actionNavigate(SystemUXRoute.AUI_INTERNAL_SETTINGS, "");
    }

    private void initializeBugReportButton() {
        Log.d(TAG, "Initializing bug report button");
        OCButton oCButton = this.mBinding.bugButton;
        oCButton.setEventHandler(this.mPanelApp);
        oCButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$DebugBarView$ZlFXvbRitD0wybPFy9ldTvz43a4 */

            public final void onClick(View view) {
                DebugBarView.this.lambda$initializeBugReportButton$93$DebugBarView(view);
            }
        });
        Log.d(TAG, "Initialized bug report button");
    }

    public /* synthetic */ void lambda$initializeBugReportButton$93$DebugBarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_DEBUG_BUGREPORT);
        this.mPanelApp.actionNavigate(SystemUXRoute.BUG_REPORT, "");
    }

    private void initializeDogfoodingButton() {
        Log.d(TAG, "Initializing dogfooding button");
        OCButton oCButton = this.mBinding.dogfoodButton;
        oCButton.setEventHandler(this.mPanelApp);
        if (this.mPanelApp.isGKEnabled(Gatekeeper.QUEST_DOGFOODING_UPDATE)) {
            oCButton.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$DebugBarView$j80Fl6AKjCnaUp7_AjdwHI_WDU */

                public final void onClick(View view) {
                    DebugBarView.this.lambda$initializeDogfoodingButton$94$DebugBarView(view);
                }
            });
        } else {
            oCButton.setVisibility(8);
        }
        Log.d(TAG, "Initialized dogfooding button");
    }

    public /* synthetic */ void lambda$initializeDogfoodingButton$94$DebugBarView(View view) {
        this.mPanelApp.logButtonClick(ClickEventButtonId.AUIV2_BAR_DEBUG_DOGFOODING);
        this.mPanelApp.actionNavigate("com.oculus.vrshell/com.oculus.panelapp.dogfood.DogfoodPanelService", "");
    }

    private void initializeSystemStatsIndicators() {
        this.mPanelApp.addPanelFrameCallback(this);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp.PanelFrameCallback
    public void onBeginFrame(InputFrame inputFrame) {
        long timeMillis = inputFrame.getTimeMillis();
        long j = this.mPrevFrameMillis;
        long j2 = 0;
        if (j != 0) {
            j2 = timeMillis - j;
        }
        this.mPrevFrameMillis = timeMillis;
        if (!this.mIsHovered) {
            this.mTimeToLiveMillis -= j2;
        }
        if (timeMillis - this.mLastSystemStatsUpdateTimeMillis > SYSTEM_STATS_UPDATE_INTERVAL_MILLIS) {
            this.mLastSystemStatsUpdateTimeMillis = timeMillis;
            updateSystemStats();
        }
    }

    public boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 9) {
            this.mIsHovered = true;
        } else if (motionEvent.getActionMasked() == 10) {
            this.mIsHovered = false;
            this.mTimeToLiveMillis = getBarDismissInterval();
        }
        return super.onInterceptHoverEvent(motionEvent);
    }

    private long getBarDismissInterval() {
        if (this.mPanelApp.isOCShellAutomationEnabled()) {
            return 5000;
        }
        return DEBUG_BAR_DISMISS_INTERVAL_MILLIS;
    }

    public boolean isRequestingDismiss() {
        return !this.mIsHovered && this.mTimeToLiveMillis <= 0;
    }

    private void updateSystemStats() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$DebugBarView$Fd19Y0WH5ata3RHAMR4pp3bK5Lw */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DebugBarView.this.lambda$updateSystemStats$96$DebugBarView();
            }
        });
    }

    public /* synthetic */ Object lambda$updateSystemStats$96$DebugBarView() throws Exception {
        try {
            UiThreadExecutor.getInstance().execute(new Runnable(getProcessCPUUsage(), getProcessTotalPSSMebibytes()) {
                /* class com.oculus.panelapp.anytimeui.v2.bar.$$Lambda$DebugBarView$uKVLOF149itHp9X3CJxbAgkEZ2o */
                private final /* synthetic */ float f$1;
                private final /* synthetic */ long f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    DebugBarView.this.lambda$null$95$DebugBarView(this.f$1, this.f$2);
                }
            });
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Cannot determine SystemUX resource usage", e);
            return null;
        }
    }

    public /* synthetic */ void lambda$null$95$DebugBarView(float f, long j) {
        this.mBinding.systemUsage.setText(this.mPanelApp.getContext().getResources().getString(R.string.anytime_bar_debug_system_stats_text, Float.valueOf(f), Long.valueOf(j)));
    }

    private float getProcessCPUUsage() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/stat"));
        String[] split = bufferedReader.readLine().split(" +");
        long parseLong = Long.parseLong(split[1]) + Long.parseLong(split[2]) + Long.parseLong(split[3]) + Long.parseLong(split[4]) + Long.parseLong(split[5]) + Long.parseLong(split[6]) + Long.parseLong(split[7]) + Long.parseLong(split[8]) + Long.parseLong(split[9]) + Long.parseLong(split[10]);
        bufferedReader.close();
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader("/proc/" + Process.myPid() + "/stat"));
        String[] split2 = bufferedReader2.readLine().split(" ");
        long parseLong2 = Long.parseLong(split2[13]) + Long.parseLong(split2[14]);
        bufferedReader2.close();
        float availableProcessors = (((((float) Runtime.getRuntime().availableProcessors()) * 100.0f) * ((float) (parseLong2 - this.mPrevCpuProcessTicks))) / ((float) (parseLong - this.mPrevCpuTotalTicks))) / 2.0f;
        this.mPrevCpuProcessTicks = parseLong2;
        this.mPrevCpuTotalTicks = parseLong;
        return availableProcessors;
    }

    private long getProcessTotalPSSMebibytes() {
        return (long) (((ActivityManager) this.mPanelApp.getContext().getSystemService("activity")).getProcessMemoryInfo(new int[]{Process.myPid()})[0].getTotalPss() / 1024);
    }
}
