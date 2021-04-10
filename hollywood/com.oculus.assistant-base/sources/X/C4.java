package X;

import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.common.gcmcompat.Task;

public abstract class C4 {
    public Bundle A00 = null;
    public String A01 = null;
    public String A02 = null;
    public boolean A03 = false;
    public boolean A04 = false;

    public void A00() {
        if (this.A01 != null) {
            String str = this.A02;
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("Must provide a valid tag.");
            } else if (str.length() > 100) {
                throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
            } else if (this.A03) {
                Task.A00(this.A00);
            }
        } else {
            throw new IllegalArgumentException("Must provide an endpoint for this task by calling setService(ComponentName).");
        }
    }
}
