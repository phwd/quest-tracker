package com.facebook.common.time;

import com.facebook.proguard.annotations.DoNotStrip;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@DoNotStrip
@Retention(RetentionPolicy.RUNTIME)
public @interface ElapsedRealtimeSinceBoot {
}
