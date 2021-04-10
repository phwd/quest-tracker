package com.oculus.dumpsysledger;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class DumpsysLedgerMapperAutoProvider extends AbstractProvider<DumpsysLedgerMapper> {
    @Override // javax.inject.Provider
    public DumpsysLedgerMapper get() {
        return new DumpsysLedgerMapper(this);
    }
}
