package com.oculus.android.os.internal.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.android.os.internal.UserHandleInternal;

@Generated({"By: InjectorProcessor"})
public class UserHandleInternalMethodAutoProvider extends AbstractProvider<UserHandleInternal> {
    @Override // javax.inject.Provider
    public UserHandleInternal get() {
        return InternalModule.provideUserHandleInternal();
    }
}
