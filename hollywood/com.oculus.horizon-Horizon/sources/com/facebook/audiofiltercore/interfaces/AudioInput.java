package com.facebook.audiofiltercore.interfaces;

import com.facebook.proguard.annotations.DoNotStrip;
import java.io.IOException;

@DoNotStrip
public interface AudioInput {
    @DoNotStrip
    void close();

    @DoNotStrip
    int read(short[] sArr, int i) throws IOException;
}
