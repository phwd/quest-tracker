package com.oculus.directboot;

import com.google.inject.BindingAnnotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BindingAnnotation
@Retention(RetentionPolicy.CLASS)
public @interface ForDeviceProtectedStorage {
}
