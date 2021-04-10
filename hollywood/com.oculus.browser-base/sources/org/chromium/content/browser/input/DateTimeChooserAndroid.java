package org.chromium.content.browser.input;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;
import java.util.Arrays;
import org.chromium.base.ContextUtils;
import org.chromium.content.browser.picker.DateTimeSuggestion;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DateTimeChooserAndroid {

    /* renamed from: a  reason: collision with root package name */
    public final long f10926a;
    public final P10 b;

    public DateTimeChooserAndroid(Context context, long j) {
        this.f10926a = j;
        this.b = new P10(context, new C3342kD(this));
    }

    public static DateTimeChooserAndroid createDateTimeChooser(WindowAndroid windowAndroid, long j, int i, double d, double d2, double d3, double d4, DateTimeSuggestion[] dateTimeSuggestionArr) {
        Context context = (Context) windowAndroid.f11022J.get();
        if (context == null || ContextUtils.a(context) == null) {
            return null;
        }
        DateTimeChooserAndroid dateTimeChooserAndroid = new DateTimeChooserAndroid(context, j);
        P10 p10 = dateTimeChooserAndroid.b;
        p10.a();
        if (dateTimeSuggestionArr == null) {
            p10.c(i, d, d2, d3, d4);
            return dateTimeChooserAndroid;
        }
        ListView listView = new ListView(p10.f8661a);
        C3684mD mDVar = new C3684mD(p10.f8661a, Arrays.asList(dateTimeSuggestionArr));
        listView.setAdapter((ListAdapter) mDVar);
        listView.setOnItemClickListener(new F10(p10, mDVar, i, d, d2, d3, d4));
        AlertDialog create = new AlertDialog.Builder(p10.f8661a).setTitle(i == 12 ? R.string.f63440_resource_name_obfuscated_RES_2131953661 : (i == 9 || i == 10) ? R.string.f50730_resource_name_obfuscated_RES_2131952390 : i == 11 ? R.string.f55080_resource_name_obfuscated_RES_2131952825 : i == 13 ? R.string.f65750_resource_name_obfuscated_RES_2131953892 : R.string.f50720_resource_name_obfuscated_RES_2131952389).setView(listView).setNegativeButton(p10.f8661a.getText(17039360), new G10(p10)).create();
        p10.c = create;
        create.setOnDismissListener(new H10(p10));
        p10.b = false;
        p10.c.show();
        return dateTimeChooserAndroid;
    }

    public static DateTimeSuggestion[] createSuggestionsArray(int i) {
        return new DateTimeSuggestion[i];
    }

    public static void setDateTimeSuggestionAt(DateTimeSuggestion[] dateTimeSuggestionArr, int i, double d, String str, String str2) {
        dateTimeSuggestionArr[i] = new DateTimeSuggestion(d, str, str2);
    }
}
