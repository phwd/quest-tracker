package com.oculus.panelapp.dogfood.tabs;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.oculus.auth.service.contract.ServiceContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dogfood.DogfoodPanelApp;
import com.oculus.panelapp.dogfood.GraphQLRequester;
import com.oculus.panelapp.dogfood.OTAUpdateHelper;
import com.oculus.panelapp.dogfood.R;
import com.oculus.panelapp.dogfood.tabs.DogfoodTabHost;
import com.oculus.vrshell.panels.views.ShellProgressBarView;
import com.oculus.vrshell.util.AndroidSystemProperties;
import org.json.JSONException;
import org.json.JSONObject;

public class DogfoodOTATab extends LinearLayout implements DogfoodTabHost.DogfoodTab, GraphQLRequester {
    private static final String TAG = LoggingUtil.tag(DogfoodPanelApp.class);
    private TextView mDogfoodChannelText;
    private DogfoodPanelApp mPanelApp;

    @Override // com.oculus.panelapp.dogfood.GraphQLRequester
    public void handleFailedGraphQlResponse(String str) {
    }

    public DogfoodOTATab(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void getDeviceInformation() {
        this.mPanelApp.makeGraphQLRequest(this, String.format(GraphQLRequester.GRAPHQL_ASSIGNMENTS, Build.SERIAL, Build.VERSION.INCREMENTAL));
    }

    @Override // com.oculus.panelapp.dogfood.GraphQLRequester
    public void handleGraphQlResponse(JSONObject jSONObject) {
        try {
            Log.d(TAG, jSONObject.toString());
            this.mDogfoodChannelText.setText(jSONObject.getJSONObject("channel").getString(ServiceContract.EXTRA_NAME));
        } catch (JSONException e) {
            String str = TAG;
            Log.e(str, "Received Invalid JSON response: " + jSONObject + " - " + e.getMessage());
            this.mDogfoodChannelText.setText("Unknown Error. Check Wifi.");
        }
    }

    @Override // com.oculus.panelapp.dogfood.tabs.DogfoodTabHost.DogfoodTab
    public void initialize(DogfoodPanelApp dogfoodPanelApp, DogfoodTabHost dogfoodTabHost) {
        this.mPanelApp = dogfoodPanelApp;
        dogfoodTabHost.addTab("ota", "Summary", R.id.dogfood_ota_tab);
        getDeviceInformation();
        this.mDogfoodChannelText = (TextView) findViewById(R.id.dogfood_channel_text);
        this.mDogfoodChannelText.setText("Updating...");
        ((TextView) findViewById(R.id.dogfood_device_serial)).setText(Build.SERIAL);
        ((TextView) findViewById(R.id.dogfood_current_build_display)).setText(Build.DISPLAY);
        ((TextView) findViewById(R.id.dogfood_current_build_version)).setText(Build.VERSION.INCREMENTAL);
        ((TextView) findViewById(R.id.dogfood_current_build_date)).setText(AndroidSystemProperties.getSystemPropertyString("ro.build.date", "<unknown>"));
        ((Button) findViewById(R.id.dogfood_no_update_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodOTATab.AnonymousClass1 */

            public void onClick(View view) {
                DogfoodOTATab.this.findViewById(R.id.dogfood_primary_panel).setVisibility(0);
                DogfoodOTATab.this.findViewById(R.id.dogfood_update_ota_panel).setVisibility(8);
                ((TextView) DogfoodOTATab.this.findViewById(R.id.dogfood_update_panel_text)).setText(R.string.dogfood_waiting_for_update);
                DogfoodOTATab.this.findViewById(R.id.dogfood_no_update_button).setVisibility(4);
                ((ShellProgressBarView) DogfoodOTATab.this.findViewById(R.id.dogfood_ota_update_progress_bar)).setProgressRatio(0.0f);
            }
        });
        Button button = (Button) findViewById(R.id.dogfood_ota_exit_button);
        button.setText(R.string.dogfood_reject_button_text);
        button.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodOTATab.AnonymousClass2 */

            public void onClick(View view) {
                DogfoodOTATab.this.mPanelApp.getCommandChannel().sendCommand("quitAndHide");
            }
        });
        Button button2 = (Button) findViewById(R.id.dogfood_ota_update_button);
        button2.setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.dogfood.tabs.DogfoodOTATab.AnonymousClass3 */

            public void onClick(View view) {
                if (OTAUpdateHelper.tryOTAUpdate(DogfoodOTATab.this.mPanelApp.getContext(), false)) {
                    DogfoodOTATab.this.findViewById(R.id.dogfood_primary_panel).setVisibility(8);
                    DogfoodOTATab.this.findViewById(R.id.dogfood_update_ota_panel).setVisibility(0);
                }
            }
        });
        TextView textView = (TextView) findViewById(R.id.dogfood_ota_status);
        textView.setText("Checking for OTA Status.");
        button2.setEnabled(false);
        OTAUpdateHelper.tryOTACheck(this.mPanelApp.getContext(), true, new OTAUpdateHelper.OTACheckResult(textView, button2) {
            /* class com.oculus.panelapp.dogfood.tabs.$$Lambda$DogfoodOTATab$H6msd862puqkjJJ2oXBNRPjUc7E */
            private final /* synthetic */ TextView f$1;
            private final /* synthetic */ Button f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.panelapp.dogfood.OTAUpdateHelper.OTACheckResult
            public final void onOtaResult(int i, boolean z, Bundle bundle) {
                DogfoodOTATab.this.lambda$initialize$1$DogfoodOTATab(this.f$1, this.f$2, i, z, bundle);
            }
        });
        ((TextView) findViewById(R.id.dogfood_ota_shell_or_shellenv)).setText(isShellEnvRunning(this.mPanelApp.getContext()) ? "Active. (ShellEnv rendering scene)" : "Inactive. (VrShell rendering scene)");
    }

    public /* synthetic */ void lambda$initialize$1$DogfoodOTATab(TextView textView, Button button, int i, boolean z, Bundle bundle) {
        new Handler(this.mPanelApp.getContext().getMainLooper()).post(new Runnable(i, bundle, textView, z, button) {
            /* class com.oculus.panelapp.dogfood.tabs.$$Lambda$DogfoodOTATab$Nnq5f49ieFza86yirWIABJ53K78 */
            private final /* synthetic */ int f$0;
            private final /* synthetic */ Bundle f$1;
            private final /* synthetic */ TextView f$2;
            private final /* synthetic */ boolean f$3;
            private final /* synthetic */ Button f$4;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void run() {
                DogfoodOTATab.lambda$initialize$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
    }

    static /* synthetic */ void lambda$initialize$0(int i, Bundle bundle, TextView textView, boolean z, Button button) {
        StringBuilder sb = new StringBuilder("OTA Result: " + (i == 0 ? "Success" : String.format("Error Code %d", Integer.valueOf(i))) + "\n");
        for (String str : bundle.keySet()) {
            sb.append("\n");
            sb.append(str);
            sb.append(" = \"");
            sb.append(bundle.get(str));
            sb.append("\"");
        }
        textView.setText(sb);
        if (z) {
            button.setText("Update Now");
        } else {
            button.setText("No Update");
        }
        button.setEnabled(z);
    }

    private boolean isShellEnvRunning(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if ("com.oculus.shellenv".equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    class ChannelDetails {
        public String channelName;
        public String gatekeeperName;

        public ChannelDetails(String str, String str2) {
            this.gatekeeperName = str;
            this.channelName = str2;
        }
    }
}
