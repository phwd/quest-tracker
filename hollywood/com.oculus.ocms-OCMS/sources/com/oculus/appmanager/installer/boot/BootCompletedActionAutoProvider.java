package com.oculus.appmanager.installer.boot;

import com.facebook.inject.AbstractComponentProvider;

public class BootCompletedActionAutoProvider extends AbstractComponentProvider<BootCompletedAction> {
    public void inject(BootCompletedAction bootCompletedAction) {
        BootCompletedAction._UL_staticInjectMe(this, bootCompletedAction);
    }

    public boolean equals(Object obj) {
        return obj instanceof BootCompletedActionAutoProvider;
    }
}
