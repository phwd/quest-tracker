package com.oculus.panelapp.debug;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.vrshellutil.R;
import com.oculus.os.PreferencesManager;
import com.oculus.systemdialog.DialogManager;
import com.oculus.vrshell.panels.AndroidPanelApp;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public final class ShellDebugPanelApp extends AndroidPanelApp {
    private static final int MAIN_LAYER_HEIGHT = 540;
    private static final int MAIN_LAYER_WIDTH = 960;
    private static String TAG = LoggingUtil.tag(ShellDebugPanelApp.class);
    private ArrayList<IShellToPanelCommandHandler> mCommandHandlers = new ArrayList<>();
    private final DialogManager mDialogManager = new DialogManager();
    private ShellDebugView mMainView;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();

    private native long nativeCreateInstance(long j, long j2);

    public ShellDebugPanelApp(Application application, Context context, Surface surface, Map<String, Surface> map, Bundle bundle) {
        super(application, context, surface, map, bundle);
        showMainLayer();
    }

    private void showMainLayer() {
        ensurePanelLayer(AndroidPanelApp.MAIN_LAYER, new AndroidPanelLayer.Spec(AndroidPanelApp.MAIN_LAYER, MAIN_LAYER_WIDTH, MAIN_LAYER_HEIGHT, AndroidPanelLayer.ResizeBehavior.NONE, AndroidPanelLayer.HitTestingBehavior.HIT_TESTABLE, AndroidPanelLayer.Shape.LandscapeCylinder, R.style.PanelAppTheme), new AndroidPanelApp.ViewCreator() {
            /* class com.oculus.panelapp.debug.ShellDebugPanelApp.AnonymousClass1 */

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public String name() {
                return "shell_debug_#main";
            }

            @Override // com.oculus.vrshell.panels.AndroidPanelApp.ViewCreator
            public View createView(Context context) {
                return ShellDebugPanelApp.this.createMainLayerView(context);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private View createMainLayerView(Context context) {
        if (this.mMainView == null) {
            this.mMainView = (ShellDebugView) LayoutInflater.from(context).inflate(R.layout.debug_panel, (ViewGroup) null);
            this.mMainView.initialize(this);
            attachDefaultKeyboardHandler(this.mMainView);
            return this.mMainView;
        }
        throw new UnsupportedOperationException("Trying to recreate main layer!");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public long createNativeInstance() {
        return nativeCreateInstance(960, 540);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public AndroidPanelLayer.Spec getLayerSpec(String str, int i) {
        if (((str.hashCode() == 35667036 && str.equals(AndroidPanelApp.MAIN_LAYER)) ? (char) 0 : 65535) != 0) {
            throw new IllegalArgumentException("Unknown layer: " + str);
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

    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void frame(String str, float f, float f2, long j, long j2, float f3, float f4, float f5) {
        super.frame(str, f, f2, j, j2, f3, f4, f5);
        String dialogIPC = this.mDialogManager.getDialogIPC();
        if (!TextUtils.isEmpty(dialogIPC)) {
            getCommandChannel().sendCommand(dialogIPC);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public void onSystemDialogResult(String str) {
        this.mDialogManager.handleSystemDialogResult(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.panels.AndroidPanelApp
    public boolean handleGenericMessage(String str) {
        try {
            new Scanner(str).next();
            this.mCommandHandlers.removeIf($$Lambda$krkSunIY_pL2xgRJfnkO9sKiRUE.INSTANCE);
            Iterator<IShellToPanelCommandHandler> it = this.mCommandHandlers.iterator();
            while (it.hasNext()) {
                it.next().handleGenericMessage(str);
            }
        } catch (InputMismatchException e) {
            String str2 = TAG;
            Log.e(str2, "Received Invalid response: " + e.getMessage());
        }
        return super.handleGenericMessage(str);
    }

    public DialogManager getDialogManager() {
        return this.mDialogManager;
    }

    public PreferencesManager getPreferencesManager() {
        return this.mPreferencesManager;
    }

    public void registerCommandHandler(IShellToPanelCommandHandler iShellToPanelCommandHandler) {
        this.mCommandHandlers.add(iShellToPanelCommandHandler);
    }

    public void unregisterCommandHandler(IShellToPanelCommandHandler iShellToPanelCommandHandler) {
        this.mCommandHandlers.remove(iShellToPanelCommandHandler);
    }
}
