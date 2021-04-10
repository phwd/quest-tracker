package com.bumptech.glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import java.util.Set;

/* access modifiers changed from: package-private */
public abstract class GeneratedAppGlideModule extends AppGlideModule {
    /* access modifiers changed from: package-private */
    @NonNull
    public abstract Set<Class<?>> getExcludedModuleClasses();

    /* access modifiers changed from: package-private */
    @Nullable
    public RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
        return null;
    }

    GeneratedAppGlideModule() {
    }
}
