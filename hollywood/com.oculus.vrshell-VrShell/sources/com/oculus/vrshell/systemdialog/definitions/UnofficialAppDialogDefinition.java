package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import android.net.UrlQuerySanitizer;
import android.text.Html;
import androidx.annotation.Nullable;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;
import com.oculus.vrshell.util.HorizonUtil;

public final class UnofficialAppDialogDefinition extends CustomSystemDialogDefinition {
    private static final String ACTION_BLOCK = "BLOCK";
    private static final String ACTION_WARN = "WARN";
    public static final String EVENT_INSTALL = "install";
    public static final String EVENT_LAUNCH = "launch";
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_PACKAGE_NAME = "package_name";

    public UnofficialAppDialogDefinition(Context context, String str, String str2) {
        super(build(context, str, str2));
    }

    private static DialogDefinitionCustom setAdditionalButtons(Context context, DialogDefinitionCustom dialogDefinitionCustom, String str, @Nullable String str2) {
        if ("install".equals(str)) {
            return dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.unofficial_app_close_button)));
        }
        if (str2 != null) {
            char c = 65535;
            int hashCode = str2.hashCode();
            if (hashCode != 2656902) {
                if (hashCode == 63294573 && str2.equals(ACTION_BLOCK)) {
                    c = 0;
                }
            } else if (str2.equals(ACTION_WARN)) {
                c = 1;
            }
            if (c == 0) {
                return dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("quit", context.getResources().getString(R.string.unofficial_app_close_button)));
            }
            if (c == 1) {
                return dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("continue", context.getResources().getString(R.string.unofficial_app_launched_open_button))).setTertiaryButton(new DialogButtonText("quit", context.getResources().getString(R.string.unofficial_app_close_button)));
            }
            throw new UnsupportedOperationException("Unknown unofficial app launch action");
        }
        throw new UnsupportedOperationException("Unknown unofficial app launch action");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.oculus.vrshell.SystemUXRoute getRoute(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = -1109843021(0xffffffffbdd923b3, float:-0.10602512)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 1957569947(0x74ae259b, float:1.1037871E32)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "install"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0024
            r3 = 0
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "launch"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0024
            r3 = r2
            goto L_0x0025
        L_0x0024:
            r3 = -1
        L_0x0025:
            if (r3 == 0) goto L_0x0034
            if (r3 != r2) goto L_0x002c
            com.oculus.vrshell.SystemUXRoute r3 = com.oculus.vrshell.SystemUXRoute.UNOFFICIAL_APP_LAUNCHED_DIALOG
            return r3
        L_0x002c:
            java.lang.UnsupportedOperationException r3 = new java.lang.UnsupportedOperationException
            java.lang.String r0 = "unsupported unofficial app dialog event"
            r3.<init>(r0)
            throw r3
        L_0x0034:
            com.oculus.vrshell.SystemUXRoute r3 = com.oculus.vrshell.SystemUXRoute.UNOFFICIAL_APP_INSTALLED_DIALOG
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.systemdialog.definitions.UnofficialAppDialogDefinition.getRoute(java.lang.String):com.oculus.vrshell.SystemUXRoute");
    }

    private static DialogDefinitionCustom build(Context context, String str, String str2) {
        String str3;
        UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer(str);
        String value = urlQuerySanitizer.getValue("package_name");
        String value2 = urlQuerySanitizer.getValue("action");
        try {
            str3 = HorizonUtil.getSafeApplicationName(context, value);
        } catch (IllegalArgumentException unused) {
            str3 = "{App Name}";
        }
        return setAdditionalButtons(context, new DialogDefinitionCustom(getRoute(str2).path(), context.getResources().getString(R.string.unofficial_app_title), Html.toHtml(Html.fromHtml(context.getResources().getString(R.string.unofficial_app_body, str3), 0), 0)).setPrimaryButton(new DialogButtonText(SystemDialogActions.UNOFFICIAL_APP_DIALOG_RESTORE_APP_ACTION, context.getResources().getString(R.string.unofficial_app_restore_button))).setControllerBackButton(new DialogButton("close")), str2, value2);
    }
}
