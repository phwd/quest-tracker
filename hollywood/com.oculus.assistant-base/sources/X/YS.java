package X;

import android.app.Application;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.oculus.assistant.R;
import java.util.LinkedHashMap;
import java.util.Locale;

public final class YS {
    public static String A00() {
        String property = System.getProperty("http.agent");
        C1396yX yXVar = new C1396yX();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("FBAN", BX.A00().getString(R.string.fb_mobile_app_name));
        linkedHashMap.put("FBAV", yXVar.A01());
        linkedHashMap.put("FBLC", Locale.getDefault().toString());
        linkedHashMap.put("FBBV", Integer.toString(C1396yX.A00()));
        linkedHashMap.put("FBMF", Build.MANUFACTURER);
        linkedHashMap.put("FBBD", Build.BRAND);
        linkedHashMap.put("FBDV", Build.MODEL);
        linkedHashMap.put("FBSV", Build.DISPLAY);
        StringBuilder sb = new StringBuilder();
        for (String str : Build.SUPPORTED_ABIS) {
            sb.append(A01(str));
            sb.append(":");
        }
        linkedHashMap.put("FBCA", sb.substring(0, sb.length() - 1));
        Application A00 = BX.A00();
        DisplayMetrics displayMetrics = A00.getResources().getDisplayMetrics();
        Point point = new Point();
        ((WindowManager) A00.getSystemService("window")).getDefaultDisplay().getSize(point);
        StringBuilder sb2 = new StringBuilder("{density=");
        sb2.append(displayMetrics.density);
        sb2.append(",width=");
        sb2.append(point.x);
        sb2.append(",height=");
        sb2.append(point.y);
        sb2.append("}");
        linkedHashMap.put("FBDM", sb2.toString());
        linkedHashMap.put("FB_FW", "1");
        linkedHashMap.put("FBAS", Integer.toString(Build.VERSION.SDK_INT));
        StringBuilder sb3 = new StringBuilder(" [");
        for (Object obj : linkedHashMap.keySet()) {
            sb3.append(String.format(null, "%s/%s;", obj, A01((String) linkedHashMap.get(obj))));
        }
        sb3.append("]");
        return AnonymousClass08.A04(property, sb3.toString());
    }

    public static String A01(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '&') {
                sb.append("&amp;");
            } else if (charAt < ' ' || charAt > '~') {
                sb.append("&#");
                sb.append(Integer.toString(charAt));
                sb.append(";");
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString().replace("/", "-").replace(";", "-");
    }
}
