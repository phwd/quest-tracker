package android.support.v4.os;

import android.content.Context;
import android.os.UserManager;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.GROUP_ID})
public class UserManagerCompatApi24 {
    public static boolean isUserUnlocked(Context context) {
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }
}
