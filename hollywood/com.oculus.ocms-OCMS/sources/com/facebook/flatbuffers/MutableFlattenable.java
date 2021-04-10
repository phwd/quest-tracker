package com.facebook.flatbuffers;

import javax.annotation.Nullable;

public interface MutableFlattenable extends Flattenable {
    @Nullable
    MutableFlatBuffer getMutableFlatBuffer();

    int getPositionInMutableFlatBuffer();
}
