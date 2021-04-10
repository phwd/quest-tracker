package com.oculus.panelapp.dogfood.tabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dogfood.DogfoodPanelApp;
import com.oculus.panelapp.dogfood.R;
import com.oculus.panelapp.dogfood.tabs.DogfoodDeveloperSettingsTab;
import com.oculus.panelapp.dogfood.tabs.DogfoodTabHost;
import com.oculus.vrshell.panels.views.ShellButton;

public class DogfoodDeveloperSettingsTab extends LinearLayout implements DogfoodTabHost.DogfoodTab {
    private static final String ACTION_ADB_ENABLED = "companion.ADB_MODE";
    private static final String CS_PACKAGE = "com.oculus.companion.server";
    private static final String CS_SERVICE = "com.oculus.companion.server.CompanionService";
    private static String TAG = LoggingUtil.tag(DogfoodDeveloperSettingsTab.class);
    private DogfoodPanelApp mPanelApp;

    public DogfoodDeveloperSettingsTab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.oculus.panelapp.dogfood.tabs.DogfoodTabHost.DogfoodTab
    public void initialize(DogfoodPanelApp dogfoodPanelApp, DogfoodTabHost dogfoodTabHost) {
        this.mPanelApp = dogfoodPanelApp;
        dogfoodTabHost.addTab("devsettings", "Developer Settings", R.id.dogfood_developer_settings_tab);
        final TextView textView = (TextView) findViewById(R.id.dogfood_developer_settings_adb);
        final ShellButton shellButton = (ShellButton) findViewById(R.id.dogfood_developer_settings_adb_toggle);
        shellButton.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodDeveloperSettingsTab.AnonymousClass1 */

            public void onClick(View view) {
                DogfoodDeveloperSettingsTab.this.fetchAndSetAdbStatus(textView, shellButton, shellButton.getText() == "Enable" ? 1 : 0);
            }
        });
        final TextView textView2 = (TextView) findViewById(R.id.dogfood_developer_settings_mtp);
        final ShellButton shellButton2 = (ShellButton) findViewById(R.id.dogfood_developer_settings_mtp_toggle);
        shellButton2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodDeveloperSettingsTab.AnonymousClass2 */

            public void onClick(View view) {
                DogfoodDeveloperSettingsTab.this.mPanelApp.getSettingsManager().setBoolean("mtp_dialog_enabled", shellButton2.getText() == "Enable");
                DogfoodDeveloperSettingsTab.this.updateMtpState(textView2, shellButton2);
            }
        });
        updateMtpState(textView2, shellButton2);
        fetchAndSetAdbStatus(textView, shellButton, -1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateMtpState(TextView textView, ShellButton shellButton) {
        if (this.mPanelApp.getSettingsManager().getBoolean("mtp_dialog_enabled", true)) {
            textView.setText("On");
            shellButton.setText("Disable");
        } else {
            textView.setText("Off");
            shellButton.setText("Enable");
        }
        shellButton.setEnabled(true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void fetchAndSetAdbStatus(final TextView textView, final ShellButton shellButton, int i) {
        AnonymousClass3 r0 = new ResultReceiver(null) {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodDeveloperSettingsTab.AnonymousClass3 */

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                new Handler(DogfoodDeveloperSettingsTab.this.mPanelApp.getContext().getMainLooper()).post(new Runnable(bundle, textView, shellButton) {
                    /* class com.oculus.panelapp.dogfood.tabs.$$Lambda$DogfoodDeveloperSettingsTab$3$MlaFBr9z8ChFAKYb5Mg0lX2iplM */
                    private final /* synthetic */ Bundle f$0;
                    private final /* synthetic */ TextView f$1;
                    private final /* synthetic */ ShellButton f$2;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        DogfoodDeveloperSettingsTab.AnonymousClass3.lambda$onReceiveResult$0(this.f$0, this.f$1, this.f$2);
                    }
                });
            }

            static /* synthetic */ void lambda$onReceiveResult$0(Bundle bundle, TextView textView, ShellButton shellButton) {
                if (bundle.getInt("CS_RESULT", -1) == 1) {
                    textView.setText("On");
                    shellButton.setText("Disable");
                } else {
                    textView.setText("Off");
                    shellButton.setText("Enable");
                }
                shellButton.setEnabled(true);
            }
        };
        Parcel obtain = Parcel.obtain();
        r0.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        obtain.recycle();
        Intent intent = new Intent();
        intent.setAction(ACTION_ADB_ENABLED);
        intent.putExtra("RESULT_RECEIVER", (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(obtain));
        intent.putExtra("MODE", i);
        intent.setClassName(CS_PACKAGE, CS_SERVICE);
        this.mPanelApp.getContext().startService(intent);
    }
}
