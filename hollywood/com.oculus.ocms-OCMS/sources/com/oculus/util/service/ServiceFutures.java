package com.oculus.util.service;

import android.content.Intent;
import android.os.Process;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.SettableFuture;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.errorreporting.interfaces.InterfacesModule;
import com.oculus.util.service.ServiceModule;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID"})
@ApplicationScoped(enableScopeValidation = false)
public class ServiceFutures {
    private static final String EXTRA_FUTURE_ID = "future_id";
    private static final String EXTRA_FUTURE_PROCESS_ID = "future_process_id";
    private static final String TAG = "ServiceFutures";
    private static volatile ServiceFutures _UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_INSTANCE;
    private final IErrorReporter mErrorReporter;
    private final AtomicLong mFutureCounter = new AtomicLong();
    private final Map<Long, SettableFuture<?>> mFutures = Maps.newConcurrentMap();

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_util_service_ServiceFutures_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(ServiceModule.UL_id._UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_util_service_ServiceFutures_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(ServiceModule.UL_id._UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final ServiceFutures _UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ServiceFutures) UL.factorymap.get(ServiceModule.UL_id._UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ServiceFutures _UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_INSTANCE == null) {
            synchronized (ServiceFutures.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_INSTANCE = new ServiceFutures(InterfacesModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_ACCESS_METHOD(injectorLike.getApplicationInjector()));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_INSTANCE;
    }

    private static final class SoftErrors {
        static final String INCOMPLETE_FUTURE_DISPOSAL = "SERVICE_FUTURES_INCOMPLETE_FUTURE_DISPOSED";
        static final String MISSING_FUTURE = "SERVICE_FUTURES_CONTRACT_MISSING_FUTURE";
        static final String PREFIX = "SERVICE_FUTURES_";

        private SoftErrors() {
        }
    }

    @Inject
    public ServiceFutures(IErrorReporter iErrorReporter) {
        this.mErrorReporter = iErrorReporter;
    }

    public synchronized <T> SettableFuture<T> createFuture(Intent intent) {
        SettableFuture<T> create;
        long incrementAndGet = this.mFutureCounter.incrementAndGet();
        int myPid = Process.myPid();
        create = SettableFuture.create();
        this.mFutures.put(Long.valueOf(incrementAndGet), create);
        intent.putExtra("future_id", incrementAndGet);
        intent.putExtra(EXTRA_FUTURE_PROCESS_ID, myPid);
        return create;
    }

    public long getFutureId(Intent intent) {
        return intent.getLongExtra("future_id", -1);
    }

    public int getFuturePid(Intent intent) {
        return intent.getIntExtra(EXTRA_FUTURE_PROCESS_ID, -1);
    }

    @Nullable
    public synchronized <T> SettableFuture<T> getFuture(Intent intent) {
        return getFuture(intent.getLongExtra("future_id", -1), intent.getIntExtra(EXTRA_FUTURE_PROCESS_ID, -1));
    }

    @Nullable
    public synchronized <T> SettableFuture<T> getFuture(long j, int i) {
        if (i != Process.myPid()) {
            BLog.i(TAG, "Requested future %d from different PID %d (current PID %d)", Long.valueOf(j), Integer.valueOf(i), Integer.valueOf(Process.myPid()));
            return null;
        }
        return (SettableFuture<T>) this.mFutures.get(Long.valueOf(j));
    }

    public synchronized void disposeFuture(Intent intent) {
        long longExtra = intent.getLongExtra("future_id", -1);
        if (intent.getIntExtra(EXTRA_FUTURE_PROCESS_ID, -1) == Process.myPid()) {
            SettableFuture<?> remove = this.mFutures.remove(Long.valueOf(longExtra));
            if (remove != null && !remove.isDone()) {
                String format = String.format(null, "Disposed of incomplete future %d", Long.valueOf(longExtra));
                remove.setException(new IllegalStateException(format));
                this.mErrorReporter.softError("SERVICE_FUTURES_INCOMPLETE_FUTURE_DISPOSED", format);
            }
        }
    }

    public synchronized boolean hasFuture(Intent intent) {
        long longExtra = intent.getLongExtra("future_id", -1);
        if (intent.getIntExtra(EXTRA_FUTURE_PROCESS_ID, -1) != Process.myPid()) {
            return false;
        }
        return this.mFutures.containsKey(Long.valueOf(longExtra));
    }
}
