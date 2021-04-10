package com.facebook.debug.tracer;

import com.facebook.common.build.BuildConstants;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DefaultTracerConfigCallback implements TracerConfigCallback {
    @Override // com.facebook.debug.tracer.TracerConfigCallback
    public boolean shouldCollectTracer() {
        return BuildConstants.isInternalBuild();
    }
}
