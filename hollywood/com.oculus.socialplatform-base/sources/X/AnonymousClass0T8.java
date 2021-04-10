package X;

import android.app.Application;
import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import com.oculus.deviceconfigclient.shared.Constants;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.0T8  reason: invalid class name */
public final class AnonymousClass0T8 {
    public final Context A00;
    public final AnonymousClass0T6 A01;
    public final AnonymousClass0T9 A02;
    public final Map<String, List<AnonymousClass0T7>> A03 = new HashMap();

    private final Cursor A00(String str, String str2, String str3, AnonymousClass0Sp r13) {
        String str4;
        ContentResolver contentResolver = this.A00.getContentResolver();
        Uri uri = AnonymousClass0TE.A03;
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        if (acquireUnstableContentProviderClient == null) {
            try {
                AnonymousClass0MD.A04("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                return null;
            } catch (Throwable unused) {
            }
        } else {
            if (r13.A01) {
                str4 = "request_value_source";
            } else {
                str4 = "";
            }
            Cursor query = acquireUnstableContentProviderClient.query(uri, new String[]{str3, str2}, str, new String[]{str4}, null);
            if (query == null || !query.moveToFirst()) {
                AnonymousClass0MD.A09("MobileConfigBaseClient", "no results returned for %s", str);
                acquireUnstableContentProviderClient.close();
                return null;
            }
            try {
                acquireUnstableContentProviderClient.close();
                return query;
            } catch (RemoteException | SecurityException e) {
                AnonymousClass0MD.A07("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
                return null;
            }
        }
        throw th;
    }

    @Nullable
    public static AnonymousClass0T7 A01(AnonymousClass0T8 r1, String str, DeviceConfigClient.ChangeListener changeListener) {
        Map<String, List<AnonymousClass0T7>> map = r1.A03;
        if (!map.containsKey(str)) {
            return null;
        }
        for (AnonymousClass0T7 r12 : map.get(str)) {
            if (r12.A00.equals(changeListener)) {
                return r12;
            }
        }
        return null;
    }

    @Nullable
    public static String A02(AnonymousClass0T8 r4) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            return (String) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentProcessName", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            AnonymousClass0MD.A07("MobileConfigBaseClient", "Could not retrieve process name, package name will be used instead.", e);
            return r4.A00.getPackageName();
        }
    }

    public final double A03(String str, double d, AnonymousClass0Sp r12) {
        Closeable closeable = null;
        try {
            if (str.isEmpty()) {
                this.A02.A00("getDouble", str, false, AnonymousClass0Sq.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                r12.A00 = AnonymousClass0Sq.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                return d;
            }
            AnonymousClass0Sp A002 = AnonymousClass0Sp.A00(r12);
            A002.A01 = true;
            Cursor A003 = A00(str, Double.toString(d), Constants.DOUBLE_TYPE_JSON_VALUE, A002);
            if (A003 == null) {
                this.A02.A00("getDouble", str, false, AnonymousClass0Sq.DEFAULT__SERVICE_NOT_FOUND.toString());
                A002.A00 = AnonymousClass0Sq.DEFAULT__SERVICE_NOT_FOUND;
                return d;
            }
            double d2 = A003.getDouble(0);
            AnonymousClass0Sq fromInt = AnonymousClass0Sq.fromInt(A003.getInt(1));
            A002.A00 = fromInt;
            this.A02.A00("getDouble", str, true, fromInt.toString());
            try {
                A003.close();
            } catch (Exception unused) {
            }
            return d2;
        } catch (Exception e) {
            AnonymousClass0MD.A07("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (Exception unused2) {
                }
            }
            return d;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
    }

    public final long A04(String str, long j, AnonymousClass0Sp r12) {
        Closeable closeable = null;
        try {
            if (str.isEmpty()) {
                this.A02.A00("getLong", str, false, AnonymousClass0Sq.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                r12.A00 = AnonymousClass0Sq.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                return j;
            }
            AnonymousClass0Sp A002 = AnonymousClass0Sp.A00(r12);
            A002.A01 = true;
            Cursor A003 = A00(str, Long.toString(j), Constants.LONG_TYPE_JSON_VALUE, A002);
            if (A003 == null) {
                this.A02.A00("getLong", str, false, AnonymousClass0Sq.DEFAULT__SERVICE_NOT_FOUND.toString());
                A002.A00 = AnonymousClass0Sq.DEFAULT__SERVICE_NOT_FOUND;
                return j;
            }
            long j2 = A003.getLong(0);
            AnonymousClass0Sq fromInt = AnonymousClass0Sq.fromInt(A003.getInt(1));
            A002.A00 = fromInt;
            this.A02.A00("getLong", str, true, fromInt.toString());
            try {
                A003.close();
            } catch (Exception unused) {
            }
            return j2;
        } catch (Exception e) {
            AnonymousClass0MD.A07("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (Exception unused2) {
                }
            }
            return j;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
    }

    public final String A05() {
        AssetManager assets = this.A00.getAssets();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assets.open("params_map.txt")));
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
            AnonymousClass0MD.A07("MobileConfigBaseClient", "IOException while trying to read params map", e);
        }
        return sb.toString();
        throw th;
    }

    public final String A06(String str, String str2, AnonymousClass0Sp r10) {
        Closeable closeable = null;
        try {
            if (str.isEmpty()) {
                this.A02.A00("getString", str, false, AnonymousClass0Sq.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                r10.A00 = AnonymousClass0Sq.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                return str2;
            }
            AnonymousClass0Sp A002 = AnonymousClass0Sp.A00(r10);
            A002.A01 = true;
            Cursor A003 = A00(str, str2, Constants.STRING_TYPE_JSON_VALUE, A002);
            if (A003 == null) {
                this.A02.A00("getString", str, false, AnonymousClass0Sq.DEFAULT__SERVICE_NOT_FOUND.toString());
                A002.A00 = AnonymousClass0Sq.DEFAULT__SERVICE_NOT_FOUND;
                return str2;
            }
            String string = A003.getString(0);
            AnonymousClass0Sq fromInt = AnonymousClass0Sq.fromInt(A003.getInt(1));
            A002.A00 = fromInt;
            this.A02.A00("getString", str, true, fromInt.toString());
            try {
                A003.close();
            } catch (Exception unused) {
            }
            return string;
        } catch (Exception e) {
            AnonymousClass0MD.A07("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (Exception unused2) {
                }
            }
            return str2;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
    }

    public final boolean A07(String str, boolean z, AnonymousClass0Sp r10) {
        Closeable closeable = null;
        try {
            boolean z2 = false;
            if (str.isEmpty()) {
                this.A02.A00("getBoolean", str, false, AnonymousClass0Sq.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                r10.A00 = AnonymousClass0Sq.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                return z;
            }
            AnonymousClass0Sp A002 = AnonymousClass0Sp.A00(r10);
            A002.A01 = true;
            Cursor A003 = A00(str, Boolean.toString(z), "bool", A002);
            if (A003 == null) {
                this.A02.A00("getBoolean", str, false, AnonymousClass0Sq.DEFAULT__SERVICE_NOT_FOUND.toString());
                A002.A00 = AnonymousClass0Sq.DEFAULT__SERVICE_NOT_FOUND;
                return z;
            }
            if (A003.getInt(0) > 0) {
                z2 = true;
            }
            A002.A00 = AnonymousClass0Sq.fromInt(A003.getInt(1));
            A003.close();
            this.A02.A00("getBoolean", str, true, A002.A00.toString());
            try {
                A003.close();
            } catch (Exception unused) {
            }
            return z2;
        } catch (Exception e) {
            AnonymousClass0MD.A07("MobileConfigBaseClient", "Cannot retrieve MC value", e);
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (Exception unused2) {
                }
            }
            return z;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
    }

    public AnonymousClass0T8(String str, Context context) {
        this.A00 = context;
        AnonymousClass0T6 r3 = new AnonymousClass0T6(this, str);
        this.A01 = r3;
        this.A02 = new AnonymousClass0T9();
        try {
            ComponentName componentName = new ComponentName(r3.A00, "com.facebook.mobileconfigservice.service.MobileConfigService");
            Intent intent = new Intent();
            intent.setComponent(componentName);
            if (!r3.A03.A00.bindService(intent, r3, 1)) {
                throw new RuntimeException("Error calling bindService to MobileConfigService.");
            }
        } catch (RuntimeException e) {
            AnonymousClass0MD.A0C("MobileConfigBaseClient", e, "Failed to connect to MobileConfig Service");
        }
    }
}
