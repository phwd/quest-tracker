package com.oculus.vrshell.home;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;
import com.oculus.deviceconfigclient.shared.Constants;
import com.oculus.vrshell.home.R;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Localization {
    private static String getLocalizedString(Context context, String name) {
        String outString = "";
        String packageName = context.getPackageName();
        Resources resources = context.getResources();
        int id = resources.getIdentifier(name, Constants.STRING_TYPE_JSON_VALUE, packageName);
        if (id == 0) {
            return outString;
        }
        if (id != 0) {
            outString = resources.getText(id).toString();
        }
        return outString;
    }

    public static List<Pair<String, String>> getStrings(Context context) {
        List<Pair<String, String>> list = new ArrayList<>();
        Field[] fields = R.string.class.getFields();
        for (Field field : fields) {
            list.add(new Pair<>(field.getName(), getLocalizedString(context, field.getName())));
        }
        return list;
    }
}
