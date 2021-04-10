package com.oculus.panelapp.gauntlettest;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.vrshellutil.R;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Map;

public final class GauntletTestPanelApp extends AndroidPanelApp {
    private static final AndroidPanelLayer.Spec MAIN_LAYER_SPEC = new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, 1000, 500, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.LandscapeCylinder, R.style.PanelAppTheme);
    private static String TAG = LoggingUtil.tag(GauntletTestPanelApp.class);
    private GauntletTestView mMainView;

    private native long nativeCreateInstance(long j, long j2);

    public GauntletTestPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance((long) MAIN_LAYER_SPEC.width, (long) MAIN_LAYER_SPEC.height);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        return MAIN_LAYER_SPEC;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        String str2 = TAG;
        Log.d(str2, "Inflating view for viewIdentifier = " + i);
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unknown layerName: " + str);
        } else if (this.mMainView == null) {
            this.mMainView = (GauntletTestView) LayoutInflater.from(context).inflate(R.layout.gauntlet_test_panel, (ViewGroup) null);
            this.mMainView.initialize(this);
            attachDefaultKeyboardHandler(this.mMainView);
            return this.mMainView;
        } else {
            throw new UnsupportedOperationException("trying to recreate main layer!");
        }
    }
}
