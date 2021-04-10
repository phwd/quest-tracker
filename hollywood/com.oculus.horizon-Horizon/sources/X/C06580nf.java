package X;

import android.text.TextUtils;
import com.oculus.config.updater.ConfigUpdaterDumperPlugin;

/* renamed from: X.0nf  reason: invalid class name and case insensitive filesystem */
public class C06580nf implements AnonymousClass0WY<String> {
    public final /* synthetic */ String A00;

    public C06580nf(String str) {
        this.A00 = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0WY
    public final String get() {
        String str = this.A00;
        if (TextUtils.isEmpty(str)) {
            return ConfigUpdaterDumperPlugin.COMMAND_UNSET;
        }
        return str;
    }
}
