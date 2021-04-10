package com.oculus.panelapp.bugreporter;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCEventHandler;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import com.oculus.vrshell.panels.SoundType;
import com.oculus.vrshell.panels.telemetry.LoggingApi;
import java.util.Map;

public final class BugReporterPanelApp extends AndroidPanelApp implements OCEventHandler {
    private static final int MAIN_LAYER_HEIGHT = 570;
    private static final int MAIN_LAYER_WIDTH = 512;
    private static final String TAG = LoggingUtil.tag(BugReporterPanelApp.class);
    private LoggingApi mLoggingApi = new LoggingApi(this);
    private BugReporterView mMainView;

    private native long nativeCreateInstance(long j, long j2);

    public BugReporterPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        showMainLayer();
    }

    private void showMainLayer() {
        ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, 512, MAIN_LAYER_HEIGHT, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.LandscapeCylinder, R.style.PanelAppTheme), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.bugreporter.BugReporterPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "bug_reporter#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return BugReporterPanelApp.this.createMainLayerView(context);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mMainView == null) {
            this.mMainView = (BugReporterView) LayoutInflater.from(context).inflate(R.layout.bugreporter_panel, (ViewGroup) null);
            this.mMainView.initialize(this);
            attachDefaultKeyboardHandler(this.mMainView);
            return this.mMainView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onForeground() {
        super.onForeground();
        Log.i(TAG, "BugReporter foregrounded");
        this.mMainView.setVisibility(0);
    }

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onBackground() {
        super.onBackground();
        Log.i(TAG, "BugReporter backgrounded");
        this.mMainView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(512, 570);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unknown layerName: " + str);
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by getLayerSpec.", new Object[0]));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public View createViewForLayer(String str, int i, Context context) {
        String str2 = TAG;
        Log.d(str2, "Inflating view for viewIdentifier = " + i);
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unknown layerName: " + str);
        }
        throw new IllegalArgumentException(String.format("Layer %s is not supported by createViewForLayer.", new Object[0]));
    }

    @Override // com.oculus.vrshell.panels.GenericInputListener, com.oculus.vrshell.panels.AndroidPanelApp
    public boolean onBackButton() {
        if (!this.mMainView.onControllerBackButton()) {
            return true;
        }
        getCommandChannel().playAudio(SoundType.CLOSE);
        return true;
    }

    public void actionQuitAndHide() {
        this.mMainView.setVisibility(8);
        getCommandChannel().quit();
    }

    public void actionNavigate(SystemUXRoute systemUXRoute, String str) {
        actionNavigate(systemUXRoute.path(), str);
    }

    public void actionNavigate(String str, String str2) {
        String str3 = TAG;
        Log.i(str3, "actionNavigate - App:  " + str + ", Uri:  " + str2);
        getCommandChannel().launch(str, str2);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onButtonClick() {
        getCommandChannel().playAudio(SoundType.SELECT);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onButtonEnter() {
        getCommandChannel().playAudio(SoundType.HOVER);
    }

    @Override // com.oculus.ocui.OCEventHandler
    public void onBackButtonClick() {
        getCommandChannel().playAudio(SoundType.CLOSE);
    }

    public LoggingApi getLogger() {
        return this.mLoggingApi;
    }
}
