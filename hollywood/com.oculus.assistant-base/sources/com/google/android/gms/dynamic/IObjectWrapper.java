package com.google.android.gms.dynamic;

import android.os.IInterface;
import com.google.android.gms.internal.common.zza;

public interface IObjectWrapper extends IInterface {

    public class Stub extends zza implements IObjectWrapper {
        public Stub() {
            super("com.google.android.gms.dynamic.IObjectWrapper");
        }
    }
}
