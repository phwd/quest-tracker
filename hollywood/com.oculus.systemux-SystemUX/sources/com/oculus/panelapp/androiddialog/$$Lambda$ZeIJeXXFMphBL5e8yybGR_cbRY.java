package com.oculus.panelapp.androiddialog;

import android.os.SystemClock;
import java.util.function.LongSupplier;

/* renamed from: com.oculus.panelapp.androiddialog.-$$Lambda$ZeIJ-eXXFMphBL5e8yybGR_cbRY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ZeIJeXXFMphBL5e8yybGR_cbRY implements LongSupplier {
    public static final /* synthetic */ $$Lambda$ZeIJeXXFMphBL5e8yybGR_cbRY INSTANCE = new $$Lambda$ZeIJeXXFMphBL5e8yybGR_cbRY();

    private /* synthetic */ $$Lambda$ZeIJeXXFMphBL5e8yybGR_cbRY() {
    }

    public final long getAsLong() {
        return SystemClock.elapsedRealtime();
    }
}
