package com.oculus.oculustestsettings;

import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.oculus.oculustestsettings.OculusTestSettings;
import com.oculus.oculustestsettings.OculusTestSettingsModule;
import java.util.Collections;
import java.util.List;
import javax.inject.Provider;

@Dependencies({})
public class OculusTestSettingsDefaultImpl implements OculusTestSettings {
    @Override // com.oculus.oculustestsettings.OculusTestSettings
    public boolean getBooleanTestSetting(OculusTestSettings.SettingsKey settingsKey) {
        return false;
    }

    @Override // com.oculus.oculustestsettings.OculusTestSettings
    public void setBooleanTestSetting(OculusTestSettings.SettingsKey settingsKey, boolean z) {
    }

    @AutoGeneratedAccessMethod
    public static final OculusTestSettingsDefaultImpl _UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OculusTestSettingsDefaultImpl) UL.factorymap.get(OculusTestSettingsModule.UL_id._UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OculusTestSettingsDefaultImpl _UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new OculusTestSettingsDefaultImpl();
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(OculusTestSettingsModule.UL_id._UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(OculusTestSettingsModule.UL_id._UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULSEP_BINDING_ID, injectorLike);
    }

    @Override // com.oculus.oculustestsettings.OculusTestSettings
    public List<String> getAllTestSettings() {
        return Collections.EMPTY_LIST;
    }
}