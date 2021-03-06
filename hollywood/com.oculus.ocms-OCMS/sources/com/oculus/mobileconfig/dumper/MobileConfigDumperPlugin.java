package com.oculus.mobileconfig.dumper;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.mobileconfig.MobileConfigManagerHolder;
import com.facebook.mobileconfig.dumper.MobileConfigDumperBase;
import com.facebook.mobileconfig.factory.MobileConfig;
import com.facebook.mobileconfig.impl.MobileConfigDebugUtil;
import com.facebook.mobileconfig.impl.MobileConfigManagerSingletonHolder;
import com.facebook.mobileconfig.impl.MobileConfigValueUtil;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfig.metadata.ParamsMapFactory;
import com.facebook.mobileconfig.metadata.ParamsMapList;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.installer.dumper.InstallerServiceDumper;
import com.oculus.mobileconfig.dumper.MobileConfigDumperModule;
import com.oculus.mobileconfig.init.MobileConfigConfiguration;
import com.oculus.mobileconfig.init.MobileConfigInitModule;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigDebugUtil_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_BINDING_ID"})
public class MobileConfigDumperPlugin extends MobileConfigDumperBase {
    private final Context mContext;
    private final MobileConfigConfiguration mMobileConfigConfiguration;
    private final MobileConfigDebugUtil mMobileConfigDebugUtil;
    private final Provider<MobileConfig> mMobileConfigFactoryProvider;
    private final Provider<MobileConfigManagerSingletonHolder> mMobileConfigManagerHolderProvider;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(MobileConfigDumperModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final MobileConfigDumperPlugin _UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (MobileConfigDumperPlugin) UL.factorymap.get(MobileConfigDumperModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final MobileConfigDumperPlugin _UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new MobileConfigDumperPlugin(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike), MobileConfigInitModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_factory_MobileConfig_ULGT__ULSEP_ACCESS_METHOD(injectorLike), MobileConfigFactoryImplModule._UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULGT__ULSEP_ACCESS_METHOD(injectorLike), MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigDebugUtil_ULSEP_ACCESS_METHOD(injectorLike), MobileConfigConfiguration._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(MobileConfigDumperModule.UL_id._UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_BINDING_ID, injectorLike);
    }

    @Inject
    public MobileConfigDumperPlugin(@UnsafeContextInjection Context context, Provider<MobileConfig> provider, Provider<MobileConfigManagerSingletonHolder> provider2, MobileConfigDebugUtil mobileConfigDebugUtil, MobileConfigConfiguration mobileConfigConfiguration) {
        this.mContext = context;
        this.mMobileConfigDebugUtil = mobileConfigDebugUtil;
        this.mMobileConfigManagerHolderProvider = provider2;
        this.mMobileConfigFactoryProvider = provider;
        this.mMobileConfigConfiguration = mobileConfigConfiguration;
    }

    @Override // com.facebook.stetho.dumpapp.DumperPlugin
    public void dump(DumperContext dumperContext) throws DumpException {
        initialize(dumperContext);
        if ("update".equals(this.mCommandName)) {
            doUpdate();
        } else if (InstallerServiceDumper.CMD_PRINT.equals(this.mCommandName)) {
            doPrint(this.mMobileConfigDebugUtil, getParamsMapList());
        } else if ("status".equals(this.mCommandName)) {
            doStatus(this.mMobileConfigManagerHolderProvider.get());
        } else if ("schema".equals(this.mCommandName)) {
            doPrintSchema(this.mMobileConfigManagerHolderProvider.get());
        } else if ("clear".equals(this.mCommandName)) {
            doClearUserData(this.mMobileConfigManagerHolderProvider.get());
        } else if ("override".equals(this.mCommandName)) {
            doOverride(getParamsMapList(), new MobileConfigValueUtil(this.mMobileConfigManagerHolderProvider.get(), this.mMobileConfigFactoryProvider.get(), this.mMobileConfigFactoryProvider.get()));
        } else if ("help".equals(this.mCommandName)) {
            doUsage();
        } else {
            doUsage();
            dumperContext.getStdout().println();
            if (TextUtils.isEmpty(this.mCommandName)) {
                throw new DumpUsageException("Missing command");
            }
            throw new DumpUsageException("Unsupported command: " + this.mCommandName);
        }
    }

    private void doStatus(MobileConfigManagerHolder mobileConfigManagerHolder) {
        doStatus(mobileConfigManagerHolder, null);
    }

    private void doClearUserData(MobileConfigManagerHolder mobileConfigManagerHolder) {
        doClearUserData(mobileConfigManagerHolder, null);
    }

    private ParamsMapList getParamsMapList() {
        return new ParamsMapList(ParamsMapEntry.parseParamsMapResource(ParamsMapFactory.readParamsMapResource(this.mContext, false)));
    }

    private void doUpdate() throws DumpUsageException {
        MobileConfigManagerSingletonHolder mobileConfigManagerSingletonHolder = this.mMobileConfigManagerHolderProvider.get();
        if (mobileConfigManagerSingletonHolder == null || !mobileConfigManagerSingletonHolder.isValid()) {
            this.mWriter.println("MobileConfig manager is not available.");
            return;
        }
        this.mWriter.println(this.mMobileConfigConfiguration.updateConfigs(mobileConfigManagerSingletonHolder) ? "Update succeeded!" : "Update failed.");
    }
}
