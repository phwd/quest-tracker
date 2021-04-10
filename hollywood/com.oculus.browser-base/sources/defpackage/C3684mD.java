package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.content.browser.picker.DateTimeSuggestion;

/* renamed from: mD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3684mD extends ArrayAdapter {
    public final Context F;

    public C3684mD(Context context, List list) {
        super(context, (int) R.layout.f37790_resource_name_obfuscated_RES_2131624088, list);
        this.F = context;
    }

    public int getCount() {
        return super.getCount() + 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.F).inflate(R.layout.f37790_resource_name_obfuscated_RES_2131624088, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.date_time_suggestion_value);
        TextView textView2 = (TextView) view.findViewById(R.id.date_time_suggestion_label);
        if (i == getCount() - 1) {
            textView.setText(this.F.getText(R.string.f50700_resource_name_obfuscated_RES_2131952387));
            textView2.setText("");
        } else {
            textView.setText(((DateTimeSuggestion) getItem(i)).b);
            textView2.setText(((DateTimeSuggestion) getItem(i)).c);
        }
        return view;
    }
}
