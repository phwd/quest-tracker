package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.ThreadSafe;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.IOException;

@DoNotStrip
@ThreadSafe
public interface TreeJsonSerializer {
    <T extends Tree> T deserializeFromJson(String str, long j, Class<T> cls, int i, String str2) throws IOException;
}
