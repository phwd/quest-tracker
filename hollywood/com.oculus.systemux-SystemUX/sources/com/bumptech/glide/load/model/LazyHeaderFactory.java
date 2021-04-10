package com.bumptech.glide.load.model;

import androidx.annotation.Nullable;

public interface LazyHeaderFactory {
    @Nullable
    String buildHeader();
}
