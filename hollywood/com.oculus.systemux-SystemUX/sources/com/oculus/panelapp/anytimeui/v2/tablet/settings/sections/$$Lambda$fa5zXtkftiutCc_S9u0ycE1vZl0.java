package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import com.oculus.vrshell.util.DeviceProperties;
import java.util.function.Supplier;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.-$$Lambda$fa5zXtkftiutCc_S9u0ycE1vZl0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$fa5zXtkftiutCc_S9u0ycE1vZl0 implements Supplier {
    public static final /* synthetic */ $$Lambda$fa5zXtkftiutCc_S9u0ycE1vZl0 INSTANCE = new $$Lambda$fa5zXtkftiutCc_S9u0ycE1vZl0();

    private /* synthetic */ $$Lambda$fa5zXtkftiutCc_S9u0ycE1vZl0() {
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Boolean.valueOf(DeviceProperties.supportsExperimental120hzRefreshRate());
    }
}
