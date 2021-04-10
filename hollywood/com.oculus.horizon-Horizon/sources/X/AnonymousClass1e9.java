package X;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import com.oculus.mobileconfig.init.MobileConfigManagerUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.1e9  reason: invalid class name */
public final class AnonymousClass1e9 {
    public final Context A00;
    public final AnonymousClass1fg A01;
    public final AnonymousClass1fY A02;
    public final Map<String, List<AnonymousClass1eB>> A03 = new HashMap();

    @Nullable
    public static String A00(AnonymousClass1e9 r4) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            AnonymousClass0NO.A0B("MobileConfigBaseClient", "Could not retrieve process name, package name will be used instead.", e);
            return r4.A00.getPackageName();
        }
    }

    public final String A01() {
        AssetManager assets = this.A00.getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assets.open(MobileConfigManagerUtil.PARAMS_MAP_PATH)));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append('\n');
                } catch (Throwable unused) {
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            AnonymousClass0NO.A0B("MobileConfigBaseClient", "IOException while trying to read params map", e);
        }
        return sb.toString();
        throw th;
    }

    public AnonymousClass1e9(String str, Context context) {
        this.A00 = context;
        AnonymousClass1fg r3 = new AnonymousClass1fg(this, str);
        this.A01 = r3;
        this.A02 = new AnonymousClass1fY();
        try {
            ComponentName componentName = new ComponentName(r3.A00, "com.facebook.mobileconfigservice.service.MobileConfigService");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            if (!r3.A03.A00.bindService(intent, r3, 1)) {
                throw new RuntimeException("Error calling bindService to MobileConfigService.");
            }
        } catch (RuntimeException e) {
            AnonymousClass0NO.A0H("MobileConfigBaseClient", e, "Failed to connect to MobileConfig Service");
        }
    }
}
