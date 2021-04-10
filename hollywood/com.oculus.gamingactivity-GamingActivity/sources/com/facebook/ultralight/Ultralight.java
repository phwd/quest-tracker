package com.facebook.ultralight;

import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.ReturnsOwnership;

public class Ultralight {
    @ReturnsOwnership
    public static <T> T inject() {
        throw Assertions.assertUnreachable();
    }
}
