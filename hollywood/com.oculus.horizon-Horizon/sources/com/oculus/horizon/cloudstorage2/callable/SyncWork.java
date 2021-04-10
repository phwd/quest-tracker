package com.oculus.horizon.cloudstorage2.callable;

import com.oculus.horizon.cloudstorage2.Reportable;
import com.oculus.horizon.cloudstorage2.Reporter;
import java.util.concurrent.Callable;

public abstract class SyncWork<T> extends Reportable implements Callable<T> {
    public SyncWork(Reporter reporter) {
        super(reporter);
    }
}
