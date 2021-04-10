package com.oculus.horizon.vr_lifecycle;

import java.util.Timer;
import javax.annotation.Nullable;

public class MountStatusPoller extends MountStatusBase {
    public static final int POLL_INTERVAL_MS = 250;
    public static final String TAG = "MountStatusPoller";
    public boolean mLastMountStatus = false;
    @Nullable
    public Timer mMountStatusPoller = null;
}
