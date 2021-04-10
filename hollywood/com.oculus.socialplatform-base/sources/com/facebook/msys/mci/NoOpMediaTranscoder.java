package com.facebook.msys.mci;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class NoOpMediaTranscoder implements MediaTranscoder {
    public static final MediaTranscoder A00 = new NoOpMediaTranscoder();
}
