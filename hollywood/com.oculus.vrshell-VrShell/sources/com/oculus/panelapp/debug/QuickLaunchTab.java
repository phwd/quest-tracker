package com.oculus.panelapp.debug;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.packageutils.PackageHelpers;
import com.oculus.panelapp.debug.DebugTabHost;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.definitions.UnofficialAppDialogDefinition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class QuickLaunchTab extends FrameLayout implements DebugTabHost.DebugTab {
    private QuickLaunchAdapter mAdapter;
    private ShellDebugPanelApp mPanelApp;

    public QuickLaunchTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        this.mPanelApp = shellDebugPanelApp;
        debugTabHost.addTab(UnofficialAppDialogDefinition.EVENT_LAUNCH, "Launch", R.id.debug_tab_quick_launch);
        this.mAdapter = new QuickLaunchAdapter(getContext());
        this.mAdapter.refresh();
        ((ListView) findViewById(R.id.quick_launch_list)).setAdapter((ListAdapter) this.mAdapter);
    }

    /* access modifiers changed from: private */
    public static abstract class QuickListItem implements Comparable<QuickListItem> {
        private final String mLabel;

        /* access modifiers changed from: package-private */
        public abstract String getLaunchString();

        public QuickListItem(String str) {
            this.mLabel = str;
        }

        /* access modifiers changed from: package-private */
        public String getLabel() {
            return this.mLabel;
        }
    }

    /* access modifiers changed from: private */
    public static class QuickApp extends QuickListItem {
        private final String mPackageName;

        public QuickApp(String str, String str2) {
            super(String.format("%s (%s)", str, str2));
            this.mPackageName = str2;
        }

        @Override // com.oculus.panelapp.debug.QuickLaunchTab.QuickListItem
        public String getLaunchString() {
            return this.mPackageName;
        }

        public int compareTo(@NonNull QuickListItem quickListItem) {
            if (quickListItem.getClass() == getClass()) {
                return getLabel().toLowerCase().compareTo(quickListItem.getLabel().toLowerCase());
            }
            return -1;
        }
    }

    /* access modifiers changed from: private */
    public static class QuickPanel extends QuickListItem {
        private final String mComponentName;
        private final String mPackageName;

        public QuickPanel(String str, String str2, String str3) {
            super(String.format("%s (/%s)", str, str3));
            this.mPackageName = str2;
            this.mComponentName = str3;
        }

        @Override // com.oculus.panelapp.debug.QuickLaunchTab.QuickListItem
        public String getLaunchString() {
            return this.mPackageName + "/" + this.mComponentName;
        }

        public int compareTo(@NonNull QuickListItem quickListItem) {
            if (quickListItem.getClass() == getClass()) {
                return getLabel().toLowerCase().compareTo(quickListItem.getLabel().toLowerCase());
            }
            return quickListItem.getClass() == QuickApp.class ? 1 : -1;
        }
    }

    /* access modifiers changed from: private */
    public static class QuickSystemUX extends QuickListItem {
        private final String mUri;

        public QuickSystemUX(String str) {
            super(str);
            this.mUri = str;
        }

        @Override // com.oculus.panelapp.debug.QuickLaunchTab.QuickListItem
        public String getLaunchString() {
            return this.mUri;
        }

        public int compareTo(@NonNull QuickListItem quickListItem) {
            if (quickListItem.getClass() == getClass()) {
                return getLabel().toLowerCase().compareTo(quickListItem.getLabel().toLowerCase());
            }
            return (quickListItem.getClass() == QuickApp.class || quickListItem.getClass() == QuickPanel.class) ? 1 : -1;
        }
    }

    /* access modifiers changed from: private */
    public class QuickLaunchAdapter extends ArrayAdapter<String> {
        private final List<QuickListItem> mItems = new ArrayList();

        public QuickLaunchAdapter(Context context) {
            super(context, R.layout.quick_launch_item, R.id.item_app_name);
        }

        public int getCount() {
            return this.mItems.size();
        }

        @Override // android.widget.ArrayAdapter
        public String getItem(int i) {
            return this.mItems.get(i).getLabel();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private QuickListItem getQuickListItem(int i) {
            return this.mItems.get(i);
        }

        private boolean isHeaderPosition(int i) {
            if (i == 0 && getCount() > 0) {
                return true;
            }
            if (getQuickListItem(i - 1).getClass() != getQuickListItem(i).getClass()) {
                return true;
            }
            return false;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = super.getView(i, view, viewGroup);
            }
            QuickListItem quickListItem = getQuickListItem(i);
            TextView textView = (TextView) view.findViewById(R.id.item_app_header);
            TextView textView2 = (TextView) view.findViewById(R.id.item_app_name);
            if (isHeaderPosition(i)) {
                textView.setVisibility(0);
                Class<?> cls = getQuickListItem(i).getClass();
                if (cls == QuickApp.class) {
                    textView.setText("VR Packages");
                } else if (cls == QuickPanel.class) {
                    textView.setText("VrShell Panel Services");
                } else if (cls == QuickSystemUX.class) {
                    textView.setText("System UX");
                } else {
                    textView.setText("Unexpected content type: " + cls.getSimpleName());
                }
            } else {
                textView.setVisibility(8);
            }
            textView2.setText(quickListItem.getLabel());
            textView2.setOnClickListener(new View.OnClickListener() {
                /* class com.oculus.panelapp.debug.QuickLaunchTab.QuickLaunchAdapter.AnonymousClass1 */

                public void onClick(View view) {
                    QuickLaunchTab.this.mPanelApp.getCommandChannel().launch(QuickLaunchTab.this.mAdapter.getQuickListItem(i).getLaunchString());
                }
            });
            return view;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void refresh() {
            PackageManager packageManager = getContext().getPackageManager();
            this.mItems.clear();
            for (SystemUXRoute systemUXRoute : SystemUXRoute.values()) {
                this.mItems.add(new QuickSystemUX(systemUXRoute.path()));
            }
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(128);
            int size = installedApplications.size();
            for (int i = 0; i < size; i++) {
                ApplicationInfo applicationInfo = installedApplications.get(i);
                if (PackageHelpers.isVrApp(packageManager, applicationInfo)) {
                    this.mItems.add(new QuickApp(applicationInfo.loadLabel(packageManager).toString(), applicationInfo.packageName));
                }
                List<ResolveInfo> queryPanelServices = PackageHelpers.queryPanelServices(packageManager, applicationInfo.packageName);
                if (queryPanelServices != null) {
                    for (ResolveInfo resolveInfo : queryPanelServices) {
                        this.mItems.add(new QuickPanel(applicationInfo.loadLabel(packageManager).toString(), applicationInfo.packageName, resolveInfo.serviceInfo.name));
                    }
                }
            }
            Collections.sort(this.mItems);
            notifyDataSetChanged();
        }
    }
}
