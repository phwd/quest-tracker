package com.oculus.ocms.testing;

import com.facebook.inject.AbstractComponentProvider;

public class TestProviderAutoProvider extends AbstractComponentProvider<TestProvider> {
    public void inject(TestProvider testProvider) {
        TestProvider._UL_staticInjectMe(this, testProvider);
    }

    public boolean equals(Object obj) {
        return obj instanceof TestProviderAutoProvider;
    }
}
