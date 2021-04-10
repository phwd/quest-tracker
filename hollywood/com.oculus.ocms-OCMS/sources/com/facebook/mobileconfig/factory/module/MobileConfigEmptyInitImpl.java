package com.facebook.mobileconfig.factory.module;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.interfaces.MobileConfigInitInterface;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigEmptyInitImpl implements MobileConfigInitInterface {
    @Override // com.facebook.mobileconfig.interfaces.MobileConfigInitInterface
    public void init() {
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigInitInterface
    public boolean initNetwork(MobileConfigManagerHolder mobileConfigManagerHolder) {
        return false;
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigInitInterface
    public void login(String str) {
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigInitInterface
    public void logout() {
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigInitInterface
    public boolean shouldEnableMobileConfig(String str) {
        return false;
    }

    @Override // com.facebook.mobileconfig.interfaces.MobileConfigInitInterface
    public boolean updateMobileConfigManagerHolderIfNecessary() {
        return false;
    }
}
