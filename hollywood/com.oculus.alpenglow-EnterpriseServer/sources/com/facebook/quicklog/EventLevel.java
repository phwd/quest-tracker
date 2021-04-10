package com.facebook.quicklog;

import com.facebook.proguard.annotations.DoNotStrip;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@DoNotStrip
@Retention(RetentionPolicy.CLASS)
public @interface EventLevel {
}
