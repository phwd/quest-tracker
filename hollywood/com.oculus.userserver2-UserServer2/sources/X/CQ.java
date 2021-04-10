package X;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class CQ extends TY {
    public static final Class<?>[] A05 = {Application.class, C6.class};
    public static final Class<?>[] A06 = {C6.class};
    public final Application A00;
    public final Bundle A01;
    public final AbstractC0041Bq A02;
    public final CO A03;
    public final Ds A04;

    @SuppressLint({"LambdaLast"})
    public CQ(@NonNull Application application, @NonNull TM tm, @Nullable Bundle bundle) {
        this.A04 = tm.getSavedStateRegistry();
        this.A02 = tm.getLifecycle();
        this.A01 = bundle;
        this.A00 = application;
        CO co = CO.A01;
        if (co == null) {
            co = new CO(application);
            CO.A01 = co;
        }
        this.A03 = co;
    }
}
