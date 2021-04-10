package com.oculus.vrshell.uicommon;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.oculus.common.vrshellutil.R;
import com.oculus.vrshell.panels.ViewState;

public class MainNavTabHost extends TabHost {
    public MainNavTabHost(Context context) {
        super(context);
    }

    public MainNavTabHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MainNavTabHost(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setup() {
        super.setup();
        getTabWidget().setDividerPadding(0);
        getTabWidget().setDividerDrawable(R.drawable.main_nav_tab_button_divider);
        getTabWidget().setShowDividers(2);
        getTabWidget().setBackgroundColor(getResources().getColor(R.color.deprecated_oc_gray_1_fix_me));
    }

    public void addTab(String str, String str2, int i) {
        TabHost.TabSpec newTabSpec = newTabSpec(str);
        newTabSpec.setContent(i);
        Context context = getContext();
        TextView textView = new TextView(context);
        textView.setTextAppearance(context, R.style.MainNavTabText);
        textView.setGravity(16);
        textView.setText(str2);
        newTabSpec.setIndicator(textView);
        addTab(newTabSpec);
    }

    public void addTab(TabHost.TabSpec tabSpec) {
        super.addTab(tabSpec);
        TabWidget tabWidget = getTabWidget();
        final int tabCount = tabWidget.getTabCount() - 1;
        tabWidget.setWeightSum((float) tabWidget.getTabCount());
        View childTabViewAt = tabWidget.getChildTabViewAt(tabCount);
        childTabViewAt.setBackgroundResource(R.drawable.main_nav_tab_button_background);
        childTabViewAt.setLayoutParams(new LinearLayout.LayoutParams(-1, 49, 1.0f));
        childTabViewAt.setPadding(15, 0, 15, 0);
        ViewState.setViewStateListeners(childTabViewAt, ViewState.findInParents(childTabViewAt), new View.OnClickListener() {
            /* class com.oculus.vrshell.uicommon.MainNavTabHost.AnonymousClass1 */

            public void onClick(View view) {
                MainNavTabHost.this.setCurrentTab(tabCount);
            }
        }, null);
    }
}
