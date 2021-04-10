package com.facebook.msys.mci;

import X.AnonymousClass0IR;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DefaultUUID implements UUID {
    public static final UUID A00 = new DefaultUUID();

    @Override // com.facebook.msys.mci.UUID
    @DoNotStrip
    public String createString() {
        return AnonymousClass0IR.A00().toString();
    }
}
