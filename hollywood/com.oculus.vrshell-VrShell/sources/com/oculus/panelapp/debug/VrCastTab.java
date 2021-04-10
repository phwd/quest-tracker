package com.oculus.panelapp.debug;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.common.build.BuildConstants;
import com.oculus.panelapp.debug.DebugTabHost;

public class VrCastTab extends RelativeLayout implements DebugTabHost.DebugTab {
    private static final String CAST_OPTION_WWW = "cast_option_www";
    private static final String HORIZON_COMPONENT_NAME = "com.oculus.horizon.service_media.OVRMediaService";
    private static final String HORIZON_PACKAGE_NAME = "com.oculus.horizon";
    private static final String KEY_MESSAGE_TYPE = "message_type";
    private static final String START_LOCAL_STREAMING_HORIZON_ACTION = "com.oculus.horizon.START_LOCAL_STREAM";
    private static final String TAG = "VrCastTab";
    private VrCastManager mCastManager;

    public VrCastTab(@NonNull Context context) {
        this(context, null);
    }

    public VrCastTab(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow() called: Unbind VrCastService");
        this.mCastManager.unbindVrCastService();
    }

    @Override // com.oculus.panelapp.debug.DebugTabHost.DebugTab
    public void initialize(ShellDebugPanelApp shellDebugPanelApp, DebugTabHost debugTabHost) {
        if (!getContext().getPackageManager().hasSystemFeature(BuildConstants.PACKAGE_NAME_OVR_STANDALONE)) {
            Log.d(TAG, "Only standalone device supports VrCast.");
            return;
        }
        setVisibility(0);
        ((Button) findViewById(R.id.vrcast_start_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.VrCastTab.AnonymousClass1 */

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.oculus.horizon", VrCastTab.HORIZON_COMPONENT_NAME));
                intent.putExtra(VrCastTab.KEY_MESSAGE_TYPE, VrCastTab.START_LOCAL_STREAMING_HORIZON_ACTION);
                intent.putExtra(VrCastTab.CAST_OPTION_WWW, true);
                VrCastTab.this.getContext().startService(intent);
            }
        });
        ((Button) findViewById(R.id.vrcast_refresh_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.VrCastTab.AnonymousClass2 */

            public void onClick(View view) {
                VrCastTab.this.mCastManager.startDiscovery();
            }
        });
        ((Button) findViewById(R.id.vrcast_teardown_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.VrCastTab.AnonymousClass3 */

            public void onClick(View view) {
                VrCastTab.this.mCastManager.stopDiscovery();
            }
        });
        this.mCastManager = new VrCastManager(getContext());
        VrCastDevicesAdapter vrCastDevicesAdapter = new VrCastDevicesAdapter(getContext());
        vrCastDevicesAdapter.setManager(this.mCastManager);
        this.mCastManager.setDeviceAdapter(vrCastDevicesAdapter);
        this.mCastManager.setConnectView(new VrCastConnectView(getContext(), this.mCastManager, findViewById(R.id.vrcast_connect_view)));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.vrcast_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(vrCastDevicesAdapter);
    }
}
