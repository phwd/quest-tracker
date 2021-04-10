package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.Category;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsEnvironment;
import com.oculus.vrshell.util.HorizonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SettingsEnvironmentSection extends SettingsSection {
    private static final String TAG = LoggingUtil.tag(SettingsEnvironmentSection.class);
    private OVRLibrary.OnChangeListener mAppsChangeListener;
    private Context mContext;
    private final Map<String, App> mEnvironmentsProd = new HashMap();
    private final Map<String, App> mEnvironmentsTest = new HashMap();
    private AnytimeUIAndroidPanelAppV2 mPanelApp;
    private Runnable mRefreshView;

    public SettingsEnvironmentSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getString(R.string.anytime_tablet_settings_nav_virtual_environment_v2), null);
        Log.d(TAG, "Constructing");
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        this.mAppsChangeListener = new OVRLibrary.OnChangeListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsEnvironmentSection$TrArLntdFHBTlDCnvJw83wMGRaw */

            @Override // com.oculus.libraryapi.OVRLibrary.OnChangeListener
            public final void onChange(String str) {
                SettingsEnvironmentSection.this.lambda$new$368$SettingsEnvironmentSection(str);
            }
        };
        HorizonUtil.registerAppsChangeListener(this.mContext, this.mAppsChangeListener);
        loadApps();
        addEnvironmentsToSettingsItems();
    }

    public /* synthetic */ void lambda$new$368$SettingsEnvironmentSection(String str) {
        if (!TextUtils.isEmpty(str)) {
            loadApp(str);
        } else {
            loadApps();
        }
        addEnvironmentsToSettingsItems();
        this.mRefreshView.run();
    }

    private void loadApps() {
        this.mEnvironmentsProd.clear();
        this.mEnvironmentsTest.clear();
        for (App app : HorizonUtil.getApplications(this.mContext)) {
            setApp(app);
        }
        Log.d(TAG, String.format("Fetched all environments (%d prod, %d test)", Integer.valueOf(this.mEnvironmentsProd.size()), Integer.valueOf(this.mEnvironmentsTest.size())));
    }

    private void loadApp(String str) {
        setApp(HorizonUtil.getApplication(this.mContext, str));
    }

    private void setApp(App app) {
        if (app.category != Category.ENVIRONMENTS) {
            return;
        }
        if (app.isTest) {
            this.mEnvironmentsTest.put(app.packageName, app);
        } else {
            this.mEnvironmentsProd.put(app.packageName, app);
        }
    }

    private void addEnvironmentsToSettingsItems() {
        ArrayList arrayList = new ArrayList();
        for (App app : this.mEnvironmentsProd.values()) {
            arrayList.add(new SettingsEnvironment.Builder(this.mPanelApp).withEnvironment(app));
        }
        for (App app2 : this.mEnvironmentsTest.values()) {
            arrayList.add(new SettingsEnvironment.Builder(this.mPanelApp).withEnvironment(app2));
        }
        addSettingsItems(arrayList);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
        Log.d(TAG, "Destroying");
        HorizonUtil.unregisterAppsChangeListener(this.mContext, this.mAppsChangeListener);
        this.mPanelApp.releaseAndroidSettingsViewModel();
    }
}
