package defpackage;

import J.N;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: WH  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WH extends C3696mH implements View.OnClickListener {
    public WH(Context context, AbstractC3525lH lHVar) {
        super(context, lHVar);
    }

    @Override // defpackage.C3696mH
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.f38080_resource_name_obfuscated_RES_2131624117, (ViewGroup) null);
        }
        view.setTag(Integer.valueOf(i));
        view.setOnClickListener(this);
        RadioButton radioButton = (RadioButton) view.findViewById(R.id.radio_button);
        radioButton.setChecked(this.G == i);
        radioButton.setTag(Integer.valueOf(i));
        radioButton.setOnClickListener(this);
        if (getCount() <= 1) {
            radioButton.setVisibility(8);
        }
        view.setEnabled(isEnabled(i));
        LF lf = (LF) getItem(i);
        if (lf == null) {
            return view;
        }
        TextView textView = (TextView) view.findViewById(R.id.title);
        textView.setText(lf.f8414a);
        TextView textView2 = (TextView) view.findViewById(R.id.description);
        if (isEnabled(i)) {
            String a2 = R21.a(getContext(), lf.c);
            textView2.setText(a2);
            StringBuilder sb = new StringBuilder();
            sb.append(lf.f8414a);
            sb.append(" ");
            sb.append(a2);
            radioButton.setContentDescription(sb);
        } else {
            radioButton.setEnabled(false);
            textView.setEnabled(false);
            textView2.setEnabled(false);
            if (this.M.isEmpty()) {
                textView2.setText(getContext().getText(R.string.f51210_resource_name_obfuscated_RES_2131952438));
            } else {
                textView2.setVisibility(8);
            }
        }
        return view;
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        LF lf = (LF) getItem(intValue);
        if (lf != null) {
            N.MQzHQbrF(lf.b);
            this.G = intValue;
            AbstractC3525lH lHVar = this.f10410J;
            if (lHVar != null) {
                lHVar.a();
            }
            AbstractC3364kK0.g("MobileDownload.Location.Setting.DirectoryType", lf.e, 3);
            notifyDataSetChanged();
        }
    }
}
