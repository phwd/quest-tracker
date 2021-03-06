package com.oculus.util.thread;

import android.os.Handler;
import android.os.Looper;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.oculus.util.thread.ThreadModule;
import javax.inject.Provider;

@Dependencies({})
public class ThreadUtils {
    private static final String CANT_BE_UI_THREAD = "This operation must not be run on UI thread.";
    private static final String MUST_BE_UI_THREAD = "This operation must be run on UI thread.";

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_util_thread_ThreadUtils_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final ThreadUtils _UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (ThreadUtils) UL.factorymap.get(ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final ThreadUtils _UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new ThreadUtils();
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_util_thread_ThreadUtils_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(ThreadModule.UL_id._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID, injectorLike);
    }

    @Deprecated
    public static void assertOnUiThread(String str) {
        checkState(isUiThread(), str);
    }

    @Deprecated
    public static void assertOnUiThread() {
        assertOnUiThread(MUST_BE_UI_THREAD);
    }

    @Deprecated
    public static void assertOnNonUiThread(String str) {
        checkState(!isUiThread(), str);
    }

    @Deprecated
    public static void assertOnNonUiThread() {
        assertOnNonUiThread(CANT_BE_UI_THREAD);
    }

    @Deprecated
    public static void runOnUiThread(Runnable runnable) {
        if (isUiThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static boolean isUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    private static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public void assertIsUiThread(String str) {
        checkState(isUiThread(), str);
    }

    public void assertIsUiThread() {
        assertIsUiThread(MUST_BE_UI_THREAD);
    }

    public void assertIsNonUiThread(String str) {
        checkState(!isUiThread(), str);
    }

    public void assertIsNonUiThread() {
        assertIsNonUiThread(CANT_BE_UI_THREAD);
    }
}
