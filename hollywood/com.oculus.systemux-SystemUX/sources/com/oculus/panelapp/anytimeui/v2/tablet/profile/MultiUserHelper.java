package com.oculus.panelapp.anytimeui.v2.tablet.profile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.LockscreenManager;
import com.oculus.os.Version;
import com.oculus.userserver.api.OculusUserManager;

public class MultiUserHelper {
    private static final String TAG = LoggingUtil.tag(MultiUserHelper.class);
    private LockscreenStateChangeReceiver lockscreenStateChangeReceiver = new LockscreenStateChangeReceiver();
    private final Context mContext;
    private View mLockButton;
    private boolean mShowUserSwitcher;

    public MultiUserHelper(Context context) {
        this.mContext = context;
        this.lockscreenStateChangeReceiver.register(context);
    }

    public void close() {
        this.lockscreenStateChangeReceiver.unregister(this.mContext);
    }

    public void initSwitchUserButton(View view) {
        initButton(view, true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initButton(View view, boolean z) {
        this.mLockButton = view;
        this.mShowUserSwitcher = z;
        view.setVisibility(8);
        if (OculusUserManager.isMultiUserEnabled() && Version.CURRENT_SDK_VERSION >= 29) {
            checkAndMaybeEnableLockScreenButton();
        }
    }

    private void checkAndMaybeEnableLockScreenButton() {
        boolean z;
        LockscreenManager lockscreenManager = new LockscreenManager(this.mContext);
        try {
            if (this.mShowUserSwitcher) {
                z = lockscreenManager.canShowUserSwitcher();
            } else {
                z = lockscreenManager.canShowLockscreen();
            }
            if (z) {
                this.mLockButton.setVisibility(0);
                this.mLockButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.profile.$$Lambda$MultiUserHelper$SxlN0eDqh6wr5ycIomUTtVmYhGI */

                    public final void onClick(View view) {
                        MultiUserHelper.this.lambda$checkAndMaybeEnableLockScreenButton$72$MultiUserHelper(view);
                    }
                });
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error invoking LockscreenManager", e);
        }
    }

    public /* synthetic */ void lambda$checkAndMaybeEnableLockScreenButton$72$MultiUserHelper(View view) {
        onLockButtonClicked();
    }

    private void onLockButtonClicked() {
        LockscreenManager lockscreenManager = new LockscreenManager(this.mContext);
        try {
            if (this.mShowUserSwitcher) {
                lockscreenManager.showUserSwitcher();
            } else {
                lockscreenManager.showLockscreen();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error invoking LockscreenManager", e);
        }
    }

    public boolean canShowUserSwitcher() {
        try {
            return new LockscreenManager(this.mContext).canShowUserSwitcher();
        } catch (RemoteException e) {
            Log.e(TAG, "Error invoking LockscreenManager", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public class LockscreenStateChangeReceiver extends BroadcastReceiver {
        private static final String ACTION_STATE_CHANGED = "oculus.lockscreen.action.STATE_CHANGED";

        LockscreenStateChangeReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            MultiUserHelper multiUserHelper = MultiUserHelper.this;
            multiUserHelper.initButton(multiUserHelper.mLockButton, MultiUserHelper.this.mShowUserSwitcher);
        }

        public void register(Context context) {
            context.registerReceiver(this, new IntentFilter(ACTION_STATE_CHANGED));
        }

        public void unregister(Context context) {
            context.unregisterReceiver(this);
        }
    }
}
