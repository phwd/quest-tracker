package com.facebook.common.android;

import android.os.Handler;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class HandlerMethodAutoProvider extends AbstractProvider<Handler> {
    @Override // javax.inject.Provider
    public Handler get() {
        return AndroidModule.provideHandler();
    }
}
