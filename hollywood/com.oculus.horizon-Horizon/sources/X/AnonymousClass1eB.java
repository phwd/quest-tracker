package X;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import com.oculus.deviceconfigclient.DeviceConfigTelemetryLogger;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1eB  reason: invalid class name */
public class AnonymousClass1eB extends ContentObserver {
    public final DeviceConfigClient.ChangeListener A00;
    public final String A01;

    public AnonymousClass1eB(String str, DeviceConfigClient.ChangeListener changeListener, Context context) {
        super(new Handler(context.getMainLooper()));
        this.A01 = str;
        this.A00 = changeListener;
    }

    public final void onChange(boolean z, Uri uri) {
        boolean addAll;
        super.onChange(z);
        DeviceConfigClient.ChangeListener changeListener = this.A00;
        String str = this.A01;
        Set<String> set = DeviceConfigClient.this.mParamNamesByConfig.get(str);
        if (set != null) {
            int size = set.size();
            String[] strArr = new String[size];
            int i = 0;
            for (String str2 : set) {
                strArr[i] = str2;
                i++;
            }
            DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
            try {
                HashSet hashSet = new HashSet();
                for (int i2 = 0; i2 < size; i2++) {
                    String str3 = strArr[i2];
                    AnonymousClass1am r0 = deviceConfigClient.mParamsMapEntries.get(str3);
                    if (r0 != null) {
                        str3 = DeviceConfigClient.A02(deviceConfigClient, str3, r0.A04);
                    } else {
                        DeviceConfigTelemetryLogger.A08(deviceConfigClient.mContext, "Expected to fail with getMultiple()", str3);
                    }
                    hashSet.add(str3);
                }
                synchronized (deviceConfigClient.mParamsLock) {
                    addAll = deviceConfigClient.mParamsToPrefetch.addAll(hashSet);
                }
                if (addAll) {
                    DeviceConfigClient.A03(deviceConfigClient);
                }
            } catch (Exception e) {
                DeviceConfigTelemetryLogger.A05(deviceConfigClient.mContext, e);
            }
        } else {
            DeviceConfigTelemetryLogger.A07(DeviceConfigClient.this.mContext, "Config could not be found in params_map.txt", str);
        }
    }
}
