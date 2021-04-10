package X;

import android.text.TextUtils;

/* renamed from: X.0uw  reason: invalid class name */
public class AnonymousClass0uw implements AbstractC09610zk<String> {
    public final /* synthetic */ String A00;

    public AnonymousClass0uw(String str) {
        this.A00 = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC09610zk
    public final String get() {
        String str = this.A00;
        if (TextUtils.isEmpty(str)) {
            return "unset";
        }
        return str;
    }
}
