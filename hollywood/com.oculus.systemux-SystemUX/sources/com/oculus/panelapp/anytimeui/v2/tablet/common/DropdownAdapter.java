package com.oculus.panelapp.anytimeui.v2.tablet.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.panelapp.anytimeui.R;

public class DropdownAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private Integer[] mIcons;
    private String[] mStrings;

    public DropdownAdapter(Context context, String[] strArr, Integer[] numArr) {
        super(context, R.layout.anytime_tablet_dropdown_item, R.id.dropdown_text, strArr);
        this.mContext = context;
        this.mStrings = strArr;
        this.mIcons = numArr;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    private View getCustomView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.anytime_tablet_dropdown_item, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.dropdown_text)).setText(this.mStrings[i]);
        ((ImageView) inflate.findViewById(R.id.dropdown_icon)).setImageResource(this.mIcons[i].intValue());
        return inflate;
    }
}
