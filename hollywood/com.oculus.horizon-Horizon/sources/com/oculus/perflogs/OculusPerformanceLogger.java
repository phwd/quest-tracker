package com.oculus.perflogs;

import com.facebook.inject.RequiresBinding;
import javax.annotation.concurrent.ThreadSafe;

@RequiresBinding
@ThreadSafe
public interface OculusPerformanceLogger {
    void markerEnd(int i, short s);

    void markerStart(int i);
}
