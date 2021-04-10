package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressLint({"CatchGeneralException", "DefaultLocale"})
/* renamed from: X.0it  reason: invalid class name and case insensitive filesystem */
public final class C05240it {
    public static C05240it A01;
    public AbstractC04970iB A00 = new C02810ar();

    @VisibleForTesting(otherwise = 2)
    public static List<String> A00(Context context, String str) {
        List<String> emptyList = Collections.emptyList();
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(str, 128).metaData;
            if (bundle != null && bundle.size() > 0) {
                emptyList = new ArrayList<>();
                for (String str2 : bundle.keySet()) {
                    if (str2.matches(".*\\.fbpermission\\..*")) {
                        emptyList.add(str2);
                    }
                }
            }
            return emptyList;
        } catch (PackageManager.NameNotFoundException unused) {
            return Collections.emptyList();
        }
    }
}
