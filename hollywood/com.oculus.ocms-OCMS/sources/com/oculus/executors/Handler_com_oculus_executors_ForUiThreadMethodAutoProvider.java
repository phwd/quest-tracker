package com.oculus.executors;

import android.os.Handler;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class Handler_com_oculus_executors_ForUiThreadMethodAutoProvider extends AbstractProvider<Handler> {
    @Override // javax.inject.Provider
    public Handler get() {
        return ExecutorsModule.provideUiThreadHandler(ExecutorsModule._UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_ACCESS_METHOD(this));
    }
}
