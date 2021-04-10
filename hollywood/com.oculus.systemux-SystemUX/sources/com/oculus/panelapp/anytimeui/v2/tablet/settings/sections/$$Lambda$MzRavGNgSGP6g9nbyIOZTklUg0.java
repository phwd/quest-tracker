package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import com.oculus.vrshell.util.DeviceProperties;
import java.util.function.Supplier;

/* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.-$$Lambda$MzRavGNgS-GP6g9nbyIOZTklUg0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MzRavGNgSGP6g9nbyIOZTklUg0 implements Supplier {
    public static final /* synthetic */ $$Lambda$MzRavGNgSGP6g9nbyIOZTklUg0 INSTANCE = new $$Lambda$MzRavGNgSGP6g9nbyIOZTklUg0();

    private /* synthetic */ $$Lambda$MzRavGNgSGP6g9nbyIOZTklUg0() {
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        return Boolean.valueOf(DeviceProperties.supportsDisablingOculusButtonDuringFastMovement());
    }
}
