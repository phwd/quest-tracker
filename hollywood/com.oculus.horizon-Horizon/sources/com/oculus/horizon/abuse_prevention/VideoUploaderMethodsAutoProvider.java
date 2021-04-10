package com.oculus.horizon.abuse_prevention;

import X.AnonymousClass0J3;
import com.facebook.annotations.Generated;
import com.oculus.http.core.ApiModule;
import com.oculus.util.thread.ThreadUtils;

@Generated({"By: InjectorProcessor"})
public class VideoUploaderMethodsAutoProvider extends AnonymousClass0J3<VideoUploaderMethods> {
    public final Object get() {
        return new VideoUploaderMethods(ApiModule.A0C(this), ThreadUtils.A01(this));
    }
}
