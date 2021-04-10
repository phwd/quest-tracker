package X;

import android.content.SharedPreferences;

public final class Z4 {
    public static final SharedPreferences A00;
    public static final Z4 A01 = new Z4();

    public static final void A00() {
        A00.edit().putBoolean("show_mini_nux", false).apply();
    }

    static {
        SharedPreferences sharedPreferences = BX.A00().getSharedPreferences("AssistantServiceConfig", 0);
        C0514bB.A01(sharedPreferences, "ApplicationHolder.get().…ME, Context.MODE_PRIVATE)");
        A00 = sharedPreferences;
    }

    public static final void A01(Integer num) {
        String str;
        SharedPreferences.Editor edit = A00.edit();
        if (num != null) {
            switch (num.intValue()) {
                case 1:
                    str = "STORE_SEARCH";
                    break;
                case 2:
                    str = "TAKE_A_PHOTO";
                    break;
                case 3:
                    str = "BATTERY";
                    break;
                default:
                    str = "TIME";
                    break;
            }
        } else {
            str = null;
        }
        edit.putString("current_voice_command_id", str).apply();
    }

    public static final boolean A02() {
        return A00.getBoolean("user_in_nux", false);
    }
}
