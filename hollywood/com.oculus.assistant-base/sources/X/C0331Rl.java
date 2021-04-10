package X;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.aidl.OVRServiceInterface;
import com.oculus.assistant.R;
import java.util.Locale;

/* renamed from: X.Rl  reason: case insensitive filesystem */
public final class C0331Rl {
    public static Locale A00;
    public static final AnonymousClass0m A01 = new AnonymousClass0m();

    public static String A03(Context context, String str) {
        AnonymousClass1V A012;
        Resources resources;
        String str2;
        String str3;
        AnonymousClass0m r3 = A01;
        synchronized (r3) {
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= 24) {
                A012 = AnonymousClass1V.A00(configuration.getLocales());
            } else {
                A012 = AnonymousClass1V.A01(configuration.locale);
            }
            Locale A2F = A012.A00.A2F(0);
            if (!A2F.equals(A00)) {
                r3.clear();
                A00 = A2F;
            }
            String str4 = (String) r3.get(str);
            if (str4 == null) {
                try {
                    resources = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
                } catch (PackageManager.NameNotFoundException unused) {
                    resources = null;
                }
                str4 = null;
                if (resources != null) {
                    int identifier = resources.getIdentifier(str, "string", "com.google.android.gms");
                    if (identifier == 0) {
                        str2 = "GoogleApiAvailability";
                        String valueOf = String.valueOf(str);
                        if (valueOf.length() != 0) {
                            str3 = "Missing resource: ".concat(valueOf);
                        } else {
                            str3 = new String("Missing resource: ");
                        }
                    } else {
                        String string = resources.getString(identifier);
                        if (TextUtils.isEmpty(string)) {
                            str2 = "GoogleApiAvailability";
                            String valueOf2 = String.valueOf(str);
                            if (valueOf2.length() != 0) {
                                str3 = "Got empty resource: ".concat(valueOf2);
                            } else {
                                str3 = new String("Got empty resource: ");
                            }
                        } else {
                            r3.put(str, string);
                            return string;
                        }
                    }
                    Log.w(str2, str3);
                }
            }
            return str4;
        }
    }

    public static String A00(Context context) {
        String packageName = context.getPackageName();
        try {
            Context context2 = S8.A00(context).A00;
            return context2.getPackageManager().getApplicationLabel(context2.getPackageManager().getApplicationInfo(packageName, 0)).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            if (TextUtils.isEmpty(str)) {
                return packageName;
            }
            return str;
        }
    }

    public static String A01(Context context, int i) {
        String str;
        int i2;
        String str2;
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                i2 = R.string.common_google_play_services_install_title;
                return resources.getString(i2);
            case 2:
                i2 = R.string.common_google_play_services_update_title;
                return resources.getString(i2);
            case 3:
                i2 = R.string.common_google_play_services_enable_title;
                return resources.getString(i2);
            case 4:
            case 6:
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipMicrophoneMuted /*{ENCODED_INT: 18}*/:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                str = "common_google_play_services_invalid_account_title";
                return A03(context, str);
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                str = "common_google_play_services_network_error_title";
                return A03(context, str);
            case 8:
                str2 = "Internal error occurred. Please see logs for detailed information";
                Log.e("GoogleApiAvailability", str2);
                return null;
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                str2 = "Google Play services is invalid. Cannot recover.";
                Log.e("GoogleApiAvailability", str2);
                return null;
            case 10:
                str2 = "Developer error occurred. Please see logs for detailed information";
                Log.e("GoogleApiAvailability", str2);
                return null;
            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                str2 = "The application is not licensed to the user.";
                Log.e("GoogleApiAvailability", str2);
                return null;
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
            case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
            case OVRServiceInterface.Stub.TRANSACTION_startPartyChat /*{ENCODED_INT: 19}*/:
            default:
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unexpected error code ");
                sb.append(i);
                str2 = sb.toString();
                Log.e("GoogleApiAvailability", str2);
                return null;
            case 16:
                str2 = "One of the API components you attempted to connect to is not available.";
                Log.e("GoogleApiAvailability", str2);
                return null;
            case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipStatus /*{ENCODED_INT: 17}*/:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                str = "common_google_play_services_sign_in_failed_title";
                return A03(context, str);
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                str = "common_google_play_services_restricted_profile_title";
                return A03(context, str);
        }
    }

    public static String A02(Context context, int i) {
        int i2;
        String str;
        Resources resources = context.getResources();
        String A002 = A00(context);
        if (i == 1) {
            i2 = R.string.common_google_play_services_install_text;
        } else if (i == 2) {
            boolean A003 = S5.A00(context);
            i2 = R.string.common_google_play_services_update_text;
            if (A003) {
                return resources.getString(R.string.common_google_play_services_wear_update_text);
            }
        } else if (i != 3) {
            if (i == 5) {
                str = "common_google_play_services_invalid_account_text";
            } else if (i == 7) {
                str = "common_google_play_services_network_error_text";
            } else if (i == 9) {
                i2 = R.string.common_google_play_services_unsupported_text;
            } else if (i != 20) {
                switch (i) {
                    case 16:
                        str = "common_google_play_services_api_unavailable_text";
                        break;
                    case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipStatus /*{ENCODED_INT: 17}*/:
                        str = "common_google_play_services_sign_in_failed_text";
                        break;
                    case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipMicrophoneMuted /*{ENCODED_INT: 18}*/:
                        i2 = R.string.common_google_play_services_updating_text;
                        break;
                    default:
                        i2 = R.string.common_google_play_services_unknown_issue;
                        break;
                }
            } else {
                str = "common_google_play_services_restricted_profile_text";
            }
            return A04(context, str, A002);
        } else {
            i2 = R.string.common_google_play_services_enable_text;
        }
        return resources.getString(i2, A002);
    }

    public static String A04(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String A03 = A03(context, str);
        if (A03 == null) {
            A03 = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, A03, str2);
    }
}
