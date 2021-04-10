package com.oculus.horizon.abuse_prevention.interfaces;

import com.facebook.inject.RequiresBinding;
import java.io.File;
import javax.annotation.Nullable;

@RequiresBinding
public interface AbuseCaptureProvider {
    boolean A9F(@Nullable String str, File file, String str2);

    boolean A9R(String str);
}
