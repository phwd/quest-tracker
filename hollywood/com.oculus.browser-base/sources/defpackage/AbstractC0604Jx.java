package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.browser.R;
import com.oculus.os.Version;

/* renamed from: Jx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0604Jx {

    /* renamed from: a  reason: collision with root package name */
    public static final BW0 f8329a = new BW0();

    public static String a(Context context) {
        String packageName = context.getPackageName();
        try {
            C3969nu0 a2 = C5858yz1.a(context);
            return a2.f10518a.getPackageManager().getApplicationLabel(a2.f10518a.getPackageManager().getApplicationInfo(packageName, 0)).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    public static String b(Context context, int i) {
        Resources resources = context.getResources();
        String a2 = a(context);
        if (i == 1) {
            return resources.getString(R.string.f49390_resource_name_obfuscated_RES_2131952256, a2);
        } else if (i != 2) {
            if (i == 3) {
                return resources.getString(R.string.f49360_resource_name_obfuscated_RES_2131952253, a2);
            } else if (i == 5) {
                return e(context, "common_google_play_services_invalid_account_text", a2);
            } else {
                if (i == 7) {
                    return e(context, "common_google_play_services_network_error_text", a2);
                }
                if (i == 9) {
                    return resources.getString(R.string.f49440_resource_name_obfuscated_RES_2131952261, a2);
                } else if (i == 20) {
                    return e(context, "common_google_play_services_restricted_profile_text", a2);
                } else {
                    switch (i) {
                        case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                            return e(context, "common_google_play_services_api_unavailable_text", a2);
                        case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                            return e(context, "common_google_play_services_sign_in_failed_text", a2);
                        case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                            return resources.getString(R.string.f49480_resource_name_obfuscated_RES_2131952265, a2);
                        default:
                            return resources.getString(R.string.f49430_resource_name_obfuscated_RES_2131952260, a2);
                    }
                }
            }
        } else if (FE.b(context)) {
            return resources.getString(R.string.f49490_resource_name_obfuscated_RES_2131952266);
        } else {
            return resources.getString(R.string.f49460_resource_name_obfuscated_RES_2131952263, a2);
        }
    }

    public static String c(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.f49400_resource_name_obfuscated_RES_2131952257);
            case 2:
                return resources.getString(R.string.f49470_resource_name_obfuscated_RES_2131952264);
            case 3:
                return resources.getString(R.string.f49370_resource_name_obfuscated_RES_2131952254);
            case 4:
            case 6:
            case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return d(context, "common_google_play_services_invalid_account_title");
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return d(context, "common_google_play_services_network_error_title");
            case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
            case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
            case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
            case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
            case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
            default:
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unexpected error code ");
                sb.append(i);
                Log.e("GoogleApiAvailability", sb.toString());
                return null;
            case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return d(context, "common_google_play_services_sign_in_failed_title");
            case Version.VERSION_20 /*{ENCODED_INT: 20}*/:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return d(context, "common_google_play_services_restricted_profile_title");
        }
    }

    public static String d(Context context, String str) {
        Resources resources;
        BW0 bw0 = f8329a;
        synchronized (bw0) {
            String str2 = (String) bw0.getOrDefault(str, null);
            if (str2 != null) {
                return str2;
            }
            int i = AbstractC3558lW.c;
            try {
                resources = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
            } catch (PackageManager.NameNotFoundException unused) {
                resources = null;
            }
            if (resources == null) {
                return null;
            }
            int identifier = resources.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                String valueOf = String.valueOf(str);
                Log.w("GoogleApiAvailability", valueOf.length() != 0 ? "Missing resource: ".concat(valueOf) : new String("Missing resource: "));
                return null;
            }
            String string = resources.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                String valueOf2 = String.valueOf(str);
                Log.w("GoogleApiAvailability", valueOf2.length() != 0 ? "Got empty resource: ".concat(valueOf2) : new String("Got empty resource: "));
                return null;
            }
            f8329a.put(str, string);
            return string;
        }
    }

    public static String e(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String d = d(context, str);
        if (d == null) {
            d = resources.getString(R.string.f49430_resource_name_obfuscated_RES_2131952260);
        }
        return String.format(resources.getConfiguration().locale, d, str2);
    }
}
