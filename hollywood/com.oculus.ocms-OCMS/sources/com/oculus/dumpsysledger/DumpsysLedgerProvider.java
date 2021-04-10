package com.oculus.dumpsysledger;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractAssistedProvider;
import com.facebook.inject.InjectorLike;

@Generated({"By: InjectorProcessor"})
public class DumpsysLedgerProvider extends AbstractAssistedProvider<DumpsysLedger> {
    public DumpsysLedgerProvider(InjectorLike injectorLike) {
        super(injectorLike);
    }

    public DumpsysLedger get(String str) {
        return new DumpsysLedger(this, str, DumpsysLedgerMapper._UL__ULSEP_com_oculus_dumpsysledger_DumpsysLedgerMapper_ULSEP_ACCESS_METHOD(this));
    }
}
