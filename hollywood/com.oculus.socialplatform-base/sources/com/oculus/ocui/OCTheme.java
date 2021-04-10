package com.oculus.ocui;

import android.content.Context;
import androidx.annotation.StyleRes;
import com.oculus.socialplatform.R;

public class OCTheme {
    @StyleRes
    public static final int DEFAULT_PANEL_THEME = 2131755125;

    @StyleRes
    public static int getThemeId(Context context) {
        int i = context.getResources().getConfiguration().uiMode & 48;
        if (i == 16) {
            return 2131755126;
        }
        if (i != 32) {
            return DEFAULT_PANEL_THEME;
        }
        return R.style.PanelAppTheme;
    }
}
