package com.oculus.userserver.managerservice;

import com.google.inject.BindingAnnotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@BindingAnnotation
@Retention(RetentionPolicy.CLASS)
public @interface ForDeviceProtectedStorage {
}
