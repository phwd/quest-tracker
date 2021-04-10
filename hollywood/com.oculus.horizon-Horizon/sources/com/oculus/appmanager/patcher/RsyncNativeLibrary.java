package com.oculus.appmanager.patcher;

import com.facebook.inject.ApplicationScoped;
import com.facebook.soloader.NativeLibrary;
import com.facebook.ultralight.Dependencies;
import java.util.Arrays;
import javax.inject.Inject;

@Dependencies({})
@ApplicationScoped
public class RsyncNativeLibrary extends NativeLibrary {
    public static volatile RsyncNativeLibrary _UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_INSTANCE;

    @Inject
    public RsyncNativeLibrary() {
        super(Arrays.asList("rsync", "rsync-jni"));
    }

    public static native void patch(String str, String str2, String str3);
}
