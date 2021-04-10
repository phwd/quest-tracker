package com.oculus.horizon.logging;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class QPLogEventBuilderAutoProvider extends AbstractProvider<QPLogEventBuilder> {
    @Override // javax.inject.Provider
    public QPLogEventBuilder get() {
        return new QPLogEventBuilder(this);
    }
}
