package com.oculus.executors;

import android.os.Looper;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class Looper_com_oculus_executors_ForUiThreadMethodAutoProvider extends AbstractProvider<Looper> {
    @Override // javax.inject.Provider
    public Looper get() {
        return ExecutorsModule.provideUiThreadLooper();
    }
}
