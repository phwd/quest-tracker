package com.facebook.ultralight;

import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ReturnsOwnership;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Ultralight {
    @ReturnsOwnership
    public static <T> T inject() {
        throw Assertions.assertUnreachable();
    }
}
