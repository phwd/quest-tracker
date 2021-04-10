package X;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class GO {
    public final Context A00;
    public final GM A01;
    public final GP A02;
    public final Map A03 = new HashMap();

    public GO(Context context) {
        this.A00 = context;
        GM gm = new GM(this);
        this.A01 = gm;
        this.A02 = new GP();
        try {
            Log.d("MobileConfigBaseClient", "connect()");
            ComponentName componentName = new ComponentName("com.oculus.horizon", "com.facebook.mobileconfigservice.service.MobileConfigService");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            if (!gm.A02.A00.bindService(intent, gm, 1)) {
                throw new RuntimeException("Error calling bindService to MobileConfigService.");
            }
        } catch (RuntimeException e) {
            C0139Dd.A0S("MobileConfigBaseClient", e, "Failed to connect to MobileConfig Service");
        }
    }

    public static String A00(GO go) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            C0139Dd.A0L("MobileConfigBaseClient", "Could not retrieve process name, package name will be used instead.", e);
            return go.A00.getPackageName();
        }
    }

    public final String A01() {
        AssetManager assets = this.A00.getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assets.open("params_map.txt")));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        sb.append('\n');
                    } else {
                        bufferedReader.close();
                        return sb.toString();
                    }
                } catch (Throwable unused) {
                }
            }
            throw th;
        } catch (IOException e) {
            C0139Dd.A0L("MobileConfigBaseClient", "IOException while trying to read params map", e);
            return null;
        }
    }
}
