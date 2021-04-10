package com.oculus.multiuser;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class UserClassifierAutoProvider extends AbstractProvider<UserClassifier> {
    @Override // javax.inject.Provider
    public UserClassifier get() {
        return new UserClassifier(this);
    }
}
