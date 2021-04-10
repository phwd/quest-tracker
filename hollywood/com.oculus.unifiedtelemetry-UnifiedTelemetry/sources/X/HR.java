package X;

import android.os.Bundle;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;

public class HR {
    @Nullable
    public final String A00;

    public HR(Bundle bundle) {
        this.A00 = bundle.getString(ServiceContract.EXTRA_USER_ID);
    }

    public HR(@Nullable String str) {
        this.A00 = str;
    }
}
