package com.oculus.horizon.util.string;

import android.content.Context;
import com.oculus.socialplatform.R;
import java.util.List;

public class StringUtils {
    public static String getFormattedList(Context context, List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        String str = list.get(0);
        if (list.size() > 1) {
            int size = list.size();
            for (int i = 1; i < size; i++) {
                str = context.getString(R.string.common_list_separator_chars_with_two_vars, str, list.get(i));
            }
        }
        return str;
    }
}
