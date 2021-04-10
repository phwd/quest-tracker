package com.facebook.common.time;

import com.facebook.proguard.annotations.DoNotStrip;
import com.google.inject.BindingAnnotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@DoNotStrip
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface ElapsedAwakeTimeSinceBoot {
}
