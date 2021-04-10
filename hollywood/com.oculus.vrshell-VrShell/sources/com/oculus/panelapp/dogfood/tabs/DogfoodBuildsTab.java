package com.oculus.panelapp.dogfood.tabs;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.common.packageutils.PackageHelpers;
import com.oculus.panelapp.dogfood.DogfoodPanelApp;
import com.oculus.panelapp.dogfood.R;
import com.oculus.panelapp.dogfood.tabs.DogfoodTabHost;
import com.oculus.vrshell.SystemUXRoute;

public class DogfoodBuildsTab extends LinearLayout implements DogfoodTabHost.DogfoodTab {
    private static final String FB_BUILDS_URI = "https://our.intern.facebook.com/intern/mobile_builds/";
    private static final String PACKAGE_NAME_ASSISTANT = "com.oculus.assistant";
    private static final String PACKAGE_NAME_OCULUS_EXPLORE = "com.oculus.explore";
    private static final String PACKAGE_NAME_OCULUS_STORE = "com.oculus.store";
    private static final BuildInfo[] mBuildItems = {new BuildInfo("VR Shell", "com.oculus.vrshell", "https://our.intern.facebook.com/intern/mobile_builds/oculus_vrshell"), new BuildInfo("Home", "com.oculus.vrshell.home", "https://our.intern.facebook.com/intern/mobile_builds/oculus_home_shell_for_mobile"), new BuildInfo("Oculus Browser", "com.oculus.browser", "https://our.intern.facebook.com/intern/mobile_builds/oculus_browser"), new BuildInfo("Oculus TV", "com.oculus.tv", ""), new BuildInfo("Oculus Link", PackageHelpers.PACKAGE_NAME_XRSTREAMING_CLIENT, ""), new BuildInfo("Assistant", "com.oculus.assistant", ""), new BuildInfo("ShellEnv", "com.oculus.shellenv", "https://our.intern.facebook.com/intern/mobile_builds/oculus_shellenv"), new BuildInfo("Social Platform", "com.oculus.socialplatform", ""), new BuildInfo("Oculus Store", "com.oculus.store", ""), new BuildInfo("Oculus Explore", "com.oculus.explore", "")};
    private BuildInfoAdapter mAdapter;
    private DogfoodPanelApp mPanelApp;

    /* access modifiers changed from: private */
    public static class BuildInfo {
        public final String mAppName;
        public final String mMobileBuildEndpoint;
        public final String mPackageName;
        public String mStatus = "";
        public String mVersionCode = "";
        public String mVersionName = "";

        public BuildInfo(String str, String str2, String str3) {
            this.mAppName = str;
            this.mPackageName = str2;
            this.mMobileBuildEndpoint = str3;
        }

        public void setVersionInfo(String str, String str2) {
            this.mVersionName = str;
            this.mVersionCode = str2;
        }
    }

    public DogfoodBuildsTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.dogfood.tabs.DogfoodTabHost.DogfoodTab
    public void initialize(DogfoodPanelApp dogfoodPanelApp, DogfoodTabHost dogfoodTabHost) {
        this.mPanelApp = dogfoodPanelApp;
        dogfoodTabHost.addTab("builds", "All Build #'s", R.id.dogfood_builds_tab);
        populatePackages();
        this.mAdapter = new BuildInfoAdapter(dogfoodPanelApp.getContext());
        ((ListView) findViewById(R.id.dogfood_builds_list)).setAdapter((ListAdapter) this.mAdapter);
        ((Button) findViewById(R.id.dogfood_builds_refresh_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodBuildsTab.AnonymousClass1 */

            public void onClick(View view) {
                for (BuildInfo buildInfo : DogfoodBuildsTab.mBuildItems) {
                    DogfoodBuildsTab.this.updatePackageStatus(buildInfo);
                }
                DogfoodBuildsTab.this.mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void populatePackages() {
        new Thread(new Runnable() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodBuildsTab.AnonymousClass2 */

            public void run() {
                BuildInfo[] buildInfoArr = DogfoodBuildsTab.mBuildItems;
                for (BuildInfo buildInfo : buildInfoArr) {
                    DogfoodBuildsTab.this.populatePackageInfo(buildInfo);
                    DogfoodBuildsTab.this.updatePackageStatus(buildInfo);
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void populatePackageInfo(BuildInfo buildInfo) {
        String str;
        String str2 = "";
        try {
            PackageInfo packageInfo = this.mPanelApp.getContext().getPackageManager().getPackageInfo(buildInfo.mPackageName, 0);
            str2 = String.format("%s", Integer.valueOf(packageInfo.versionCode));
            str = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            str = "Not Installed";
        }
        buildInfo.setVersionInfo(str, str2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updatePackageStatus(BuildInfo buildInfo) {
        String str = "";
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.mPanelApp.getContext().getSystemService("activity")).getRunningAppProcesses()) {
            if (buildInfo.mPackageName.equals(runningAppProcessInfo.processName)) {
                str = str + "Running";
                if (runningAppProcessInfo.importance == 100) {
                    str = str + " | Foreground";
                }
            }
        }
        buildInfo.mStatus = str;
    }

    /* access modifiers changed from: private */
    public class BuildInfoAdapter extends ArrayAdapter<BuildInfo> {
        public BuildInfoAdapter(@NonNull Context context) {
            super(context, R.layout.build_info_item, R.id.dogfood_build_info_app_name);
        }

        public int getCount() {
            return DogfoodBuildsTab.mBuildItems.length;
        }

        @Override // android.widget.ArrayAdapter
        @Nullable
        public BuildInfo getItem(int i) {
            return DogfoodBuildsTab.mBuildItems[i];
        }

        @NonNull
        public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
            if (view == null) {
                view = super.getView(i, view, viewGroup);
            }
            final BuildInfo buildInfo = DogfoodBuildsTab.mBuildItems[i];
            TextView textView = (TextView) view.findViewById(R.id.dogfood_build_info_app_name);
            TextView textView2 = (TextView) view.findViewById(R.id.dogfood_build_info_version_name);
            TextView textView3 = (TextView) view.findViewById(R.id.dogfood_build_info_version_code);
            TextView textView4 = (TextView) view.findViewById(R.id.dogfood_build_info_status);
            ImageButton imageButton = (ImageButton) view.findViewById(R.id.dogfood_open_tab_button);
            if (buildInfo.mMobileBuildEndpoint.isEmpty()) {
                imageButton.setVisibility(8);
            } else {
                imageButton.setVisibility(0);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    /* class com.oculus.panelapp.dogfood.tabs.DogfoodBuildsTab.BuildInfoAdapter.AnonymousClass1 */

                    public void onClick(View view) {
                        DogfoodBuildsTab.this.mPanelApp.actionNavigate(SystemUXRoute.DEFAULT_BROWSER.path(), buildInfo.mMobileBuildEndpoint);
                    }
                });
            }
            textView.setText(buildInfo.mAppName);
            textView2.setText(buildInfo.mVersionName);
            textView3.setText(buildInfo.mVersionCode);
            textView4.setText(buildInfo.mStatus);
            return view;
        }
    }
}
