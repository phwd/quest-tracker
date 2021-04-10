package com.oculus.horizon.auth.shared_datastore;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface HorizonAuthDatastoreSharedPrefs {
}
