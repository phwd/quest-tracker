package com.facebook.gk.sessionless;

import android.content.Context;
import com.facebook.gk.store.GatekeeperStore;
import com.facebook.gk.store.GatekeeperStoreConfig;
import com.facebook.gk.store.GatekeeperStoreImpl;
import com.facebook.gk.store.GatekeeperStoreLogger;
import com.facebook.gk.store.GatekeeperStoreManager;
import com.facebook.gk.store.GatekeeperWriter;
import com.facebook.gk.store.GkAccessorByName;
import com.facebook.gk.storelogger.TraceGatekeeperStoreLogger;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectableComponentWithoutContext;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.Lazy;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.Inject;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import javax.inject.Provider;

@DoNotStrip
@InjectorModule
@Nullsafe(Nullsafe.Mode.LOCAL)
public class GkSessionlessModule extends AbstractLibraryModule {
    private static volatile GatekeeperStoreConfig _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_LOCK = new Object();
    private static volatile GatekeeperStoreImpl _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_LOCK = new Object();
    private static volatile GatekeeperStoreLogger _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_LOCK = new Object();

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(GatekeeperStoreConfig.class, (Class<? extends Annotation>) Sessionless.class)));
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(GatekeeperStoreImpl.class, (Class<? extends Annotation>) Sessionless.class)));
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(GatekeeperStoreLogger.class, (Class<? extends Annotation>) Sessionless.class)));
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(GatekeeperStoreManager.class, (Class<? extends Annotation>) Sessionless.class)));
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(GatekeeperStore.class, (Class<? extends Annotation>) Sessionless.class)));
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(GatekeeperWriter.class, (Class<? extends Annotation>) Sessionless.class)));
        public static final int _UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(GkAccessorByName.class, (Class<? extends Annotation>) Sessionless.class)));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_gk_store_GatekeeperStoreConfig_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_gk_store_GatekeeperStoreConfig_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @Sessionless
    @ProviderMethod
    static GatekeeperStore provideSessionlessGatekeeperStore(@Sessionless GatekeeperStoreImpl gatekeeperStoreImpl) {
        return gatekeeperStoreImpl;
    }

    @Sessionless
    @ProviderMethod
    static GatekeeperStoreManager provideSessionlessGatekeeperStoreManager(@Sessionless GatekeeperStoreImpl gatekeeperStoreImpl) {
        return gatekeeperStoreImpl;
    }

    @Sessionless
    @ProviderMethod
    static GatekeeperWriter provideSessionlessGatekeeperWriter(@Sessionless GatekeeperStoreImpl gatekeeperStoreImpl) {
        return gatekeeperStoreImpl;
    }

    @Sessionless
    @ProviderMethod
    static GkAccessorByName provideSessionlessGkAccessorByName(@Sessionless GatekeeperStoreImpl gatekeeperStoreImpl) {
        return gatekeeperStoreImpl;
    }

    @AutoGeneratedBinder
    static class AutoGeneratedBindingsForGkSessionlessModule {
        AutoGeneratedBindingsForGkSessionlessModule() {
        }

        static void bind(Binder binder) {
            if (!UL.USE_STATIC_DI) {
                binder.require(BundledAndroidModule.class);
                binder.bind(GatekeeperStore.class).annotatedWith(Sessionless.class).toProvider(new GatekeeperStore_com_facebook_gk_sessionless_SessionlessMethodAutoProvider());
                binder.bind(GatekeeperStoreConfig.class).annotatedWith(Sessionless.class).toProvider(new GatekeeperStoreConfig_com_facebook_gk_sessionless_SessionlessMethodAutoProvider()).in(ApplicationScoped.class);
                binder.bind(GatekeeperStoreImpl.class).annotatedWith(Sessionless.class).toProvider(new GatekeeperStoreImpl_com_facebook_gk_sessionless_SessionlessMethodAutoProvider()).in(ApplicationScoped.class);
                binder.bind(GatekeeperStoreLogger.class).annotatedWith(Sessionless.class).toProvider(new GatekeeperStoreLogger_com_facebook_gk_sessionless_SessionlessMethodAutoProvider()).in(ApplicationScoped.class);
                binder.bind(GatekeeperStoreManager.class).annotatedWith(Sessionless.class).toProvider(new GatekeeperStoreManager_com_facebook_gk_sessionless_SessionlessMethodAutoProvider());
                binder.bind(GatekeeperWriter.class).annotatedWith(Sessionless.class).toProvider(new GatekeeperWriter_com_facebook_gk_sessionless_SessionlessMethodAutoProvider());
                binder.bind(GkAccessorByName.class).annotatedWith(Sessionless.class).toProvider(new GkAccessorByName_com_facebook_gk_sessionless_SessionlessMethodAutoProvider());
                binder.bindComponent(GkSessionlessModuleSelendroidInjector.class).toProvider(new GkSessionlessModule_GkSessionlessModuleSelendroidInjectorAutoProvider());
            }
        }
    }

    @AutoGeneratedAccessMethod
    public static final GatekeeperStoreConfig _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (GatekeeperStoreConfig) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final GatekeeperStoreConfig _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_LOCK) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE = provideGatekeeperStoreConfig();
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final GatekeeperStoreImpl _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (GatekeeperStoreImpl) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final GatekeeperStoreImpl _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_LOCK) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        InjectorLike applicationInjector = injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE = provideGatekeeperStore(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(applicationInjector), _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(applicationInjector), _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(applicationInjector));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final GatekeeperStoreLogger _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (GatekeeperStoreLogger) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final GatekeeperStoreLogger _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_LOCK) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE = provideGatekeeperStoreLogger();
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final GatekeeperStoreManager _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (GatekeeperStoreManager) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final GatekeeperStoreManager _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideSessionlessGatekeeperStoreManager(_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final GatekeeperStore _UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (GatekeeperStore) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final GatekeeperStore _UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideSessionlessGatekeeperStore(_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final GatekeeperWriter _UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (GatekeeperWriter) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final GatekeeperWriter _UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideSessionlessGatekeeperWriter(_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final GkAccessorByName _UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (GkAccessorByName) UL.factorymap.get(UL_id._UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final GkAccessorByName _UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideSessionlessGkAccessorByName(_UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_gk_store_GatekeeperStoreImpl_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_gk_store_GatekeeperStoreLogger_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_gk_store_GatekeeperStoreManager_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_gk_store_GatekeeperStore_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_gk_store_GatekeeperWriter_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_facebook_gk_store_GkAccessorByName_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UL_id._UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_gk_store_GatekeeperStoreImpl_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_gk_store_GatekeeperStoreLogger_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_gk_store_GatekeeperStoreManager_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_gk_store_GatekeeperStore_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_gk_store_GatekeeperWriter_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_facebook_gk_store_GkAccessorByName_ULGT__ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UL_id._UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, injectorLike);
    }

    @Sessionless
    @ApplicationScoped(enableScopeValidation = false)
    @ProviderMethod
    static GatekeeperStoreConfig provideGatekeeperStoreConfig() {
        return new SessionlessGKStoreConfig();
    }

    @Sessionless
    @ApplicationScoped
    @ProviderMethod
    static GatekeeperStoreImpl provideGatekeeperStore(@UnsafeContextInjection Context context, @Sessionless GatekeeperStoreConfig gatekeeperStoreConfig, @Sessionless GatekeeperStoreLogger gatekeeperStoreLogger) {
        return GatekeeperStoreImpl.forSessionless(context.getApplicationContext()).withConfig(gatekeeperStoreConfig).withLogger(gatekeeperStoreLogger).build();
    }

    @Sessionless
    @ApplicationScoped(enableScopeValidation = false)
    @ProviderMethod
    static GatekeeperStoreLogger provideGatekeeperStoreLogger() {
        return new TraceGatekeeperStoreLogger("SessionlessGatekeeperStore");
    }

    @DoNotStrip
    public static class GkSessionlessModuleSelendroidInjector implements InjectableComponentWithoutContext {
        private InjectionContext _UL_mInjectionContext;

        /* access modifiers changed from: package-private */
        @Inject
        public final void injectMe() {
        }

        private static final void _UL_injectMe(Context context, GkSessionlessModuleSelendroidInjector gkSessionlessModuleSelendroidInjector) {
            if (UL.USE_STATIC_DI) {
                _UL_staticInjectMe(FbInjector.get(context), gkSessionlessModuleSelendroidInjector);
            } else {
                FbInjector.injectMe(GkSessionlessModuleSelendroidInjector.class, (InjectableComponentWithoutContext) gkSessionlessModuleSelendroidInjector, context);
            }
        }

        public static final void _UL_staticInjectMe(InjectorLike injectorLike, GkSessionlessModuleSelendroidInjector gkSessionlessModuleSelendroidInjector) {
            gkSessionlessModuleSelendroidInjector._UL_mInjectionContext = new InjectionContext(0, injectorLike);
            gkSessionlessModuleSelendroidInjector.injectMe();
        }

        @DoNotStrip
        public GkSessionlessModuleSelendroidInjector(Context context) {
            _UL_injectMe(context, this);
        }

        @DoNotStrip
        public GatekeeperWriter getGatekeeperWriter() {
            return (GatekeeperWriter) FbInjector.localInstance(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, this._UL_mInjectionContext);
        }

        @Sessionless
        public GatekeeperStoreConfig getSessionlessGatekeeperStoreConfig() {
            return (GatekeeperStoreConfig) FbInjector.localInstance(UL_id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID, this._UL_mInjectionContext);
        }
    }
}
