package com.oculus.bugreporter;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class SystemInfoUtils {
    private static final HashSet<String> CORE_APPS = new HashSet<>(Arrays.asList("com.oculus.vrshell", "com.oculus.horizon", "com.oculus.updater", "com.oculus.ocms", "com.oculus.tv", "com.oculus.browser", "com.oculus.mediaplayer", "com.oculus.vrshell.home", "com.oculus.systemdriver", "com.oculus.systemactivities"));
    private static final String TAG = "SystemInfoUtils";
    private final Context mContext;

    public SystemInfoUtils(Context context) {
        this.mContext = context;
    }

    public String getPackagesInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Package          Version          Version Code Info\n");
        Iterator<String> it = CORE_APPS.iterator();
        while (it.hasNext()) {
            String packageName = it.next();
            try {
                PackageInfo info = this.mContext.getPackageManager().getPackageInfo(packageName, 0);
                sb.append(packageName);
                sb.append("\t");
                sb.append(info.versionName);
                sb.append("\t");
                sb.append(info.versionCode);
                sb.append("\n");
            } catch (Exception e) {
                Log.w(TAG, "Couldn't get info for package", e);
            }
        }
        for (PackageInfo info2 : this.mContext.getPackageManager().getInstalledPackages(0)) {
            if (!CORE_APPS.contains(info2.packageName)) {
                sb.append(info2.packageName);
                sb.append("\t");
                sb.append(info2.versionName);
                sb.append("\t");
                sb.append(info2.versionCode);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public String getSystemDetails() {
        return "Build Version: " + Build.FINGERPRINT + "\n";
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getComponentAppId(String app) {
        char c;
        switch (app.hashCode()) {
            case -323082994:
                if (app.equals("360Photos")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1364563883:
                if (app.equals("OculusTV")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1468337970:
                if (app.equals("Gallery")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1815593736:
                if (app.equals("Browser")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return "1916519981771802";
        }
        if (c == 1) {
            return "838122072929207";
        }
        if (c == 2) {
            return "922519877806282";
        }
        if (c != 3) {
            return "1517832211847102";
        }
        return "1783939024969036";
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static String getComponentCategoryId(String app) {
        char c;
        switch (app.hashCode()) {
            case -1850654380:
                if (app.equals("Report")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1822469688:
                if (app.equals("Search")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -861171010:
                if (app.equals("Assistant")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -690413761:
                if (app.equals("Scoreboards")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -576091972:
                if (app.equals("Sharing")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 2255103:
                if (app.equals("Home")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 2404337:
                if (app.equals("Move")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 63613878:
                if (app.equals("Audio")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 76517104:
                if (app.equals("Other")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 80218305:
                if (app.equals("Store")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 82833682:
                if (app.equals("Voice")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 355504755:
                if (app.equals("Explore")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 886463248:
                if (app.equals("Cornerstone")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1064558965:
                if (app.equals("Friends")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1355227529:
                if (app.equals("Profile")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1364563883:
                if (app.equals("OculusTV")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1447271275:
                if (app.equals("Infinite Office Platform")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 1468337970:
                if (app.equals("Gallery")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1815593736:
                if (app.equals("Browser")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1830861979:
                if (app.equals("Library")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2071315656:
                if (app.equals("Notifications")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 2087505209:
                if (app.equals("Events")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return "1645131112370468";
            case 1:
                return "208661596294288";
            case 2:
                return "1870816053237558";
            case 3:
                return "291338521333692";
            case 4:
                return "1907936829475117";
            case 5:
                return "299098534079674";
            case 6:
                return "493182581368648";
            case 7:
                return "480777592860512";
            case '\b':
                return "191123448754281";
            case '\t':
                return "806991799798308";
            case '\n':
                return "776289276181812";
            case 11:
                return "533287180610100";
            case '\f':
                return "213046719727669";
            case '\r':
                return "592678981297738";
            case 14:
                return "2425648914353612";
            case 15:
                return "2490079111234786";
            case 16:
                return "1124679407891870";
            case 17:
                return "321565932595351";
            case 18:
                return "719029768649341";
            case 19:
                return "793062804509072";
            case 20:
                return "548722229320297";
            case 21:
                return "270076201038635";
            default:
                return "1581534861914814";
        }
    }
}
