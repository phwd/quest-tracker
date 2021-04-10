package com.bumptech.glide.module;

import android.content.Context;
import androidx.annotation.NonNull;
import com.bumptech.glide.GlideBuilder;

@Deprecated
interface AppliesOptions {
    void applyOptions(@NonNull Context context, @NonNull GlideBuilder glideBuilder);
}
