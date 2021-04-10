package com.facebook.cameracore.threading;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum ThreadPool$LifeStatus {
    AVAILABLE,
    TAKEN,
    QUITTING
}
