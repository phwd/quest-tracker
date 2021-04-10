package com.oculus.horizon.cloudstorage2.task;

import com.oculus.horizon.cloudstorage2.Reportable;
import com.oculus.horizon.cloudstorage2.Reporter;

public abstract class AsyncWork<T> extends Reportable {
    public AsyncWork(Reporter reporter) {
        super(reporter);
    }
}
