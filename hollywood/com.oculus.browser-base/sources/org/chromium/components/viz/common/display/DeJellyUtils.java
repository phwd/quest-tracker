package org.chromium.components.viz.common.display;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.provider.Settings;
import android.view.Display;
import java.lang.reflect.Field;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DeJellyUtils implements DisplayManager.DisplayListener, ComponentCallbacks {
    public static DeJellyUtils F;
    public boolean G;
    public boolean H;
    public float I;

    /* renamed from: J  reason: collision with root package name */
    public DisplayManager f10902J;
    public Field K;

    public DeJellyUtils() {
        DisplayManager displayManager = (DisplayManager) ContextUtils.getApplicationContext().getSystemService("display");
        this.f10902J = displayManager;
        displayManager.registerDisplayListener(this, null);
        ContextUtils.getApplicationContext().registerComponentCallbacks(this);
        onDisplayChanged(0);
        onConfigurationChanged(ContextUtils.getApplicationContext().getResources().getConfiguration());
        try {
            this.K = Configuration.class.getDeclaredField("semDisplayDeviceType");
        } catch (Exception unused) {
        }
    }

    public static float screenWidth() {
        if (F == null) {
            F = new DeJellyUtils();
        }
        return F.I;
    }

    public static boolean useDeJelly() {
        if (F == null) {
            F = new DeJellyUtils();
        }
        DeJellyUtils deJellyUtils = F;
        if (Settings.Global.getInt(ContextUtils.getApplicationContext().getContentResolver(), "sem_support_scroll_filter", 1) == 0) {
            return false;
        }
        return deJellyUtils.G && deJellyUtils.H;
    }

    public void onConfigurationChanged(Configuration configuration) {
        Field field = this.K;
        if (field != null) {
            try {
                this.H = field.getInt(configuration) != 5;
                return;
            } catch (Exception unused) {
            }
        }
        this.H = true;
    }

    public void onDisplayAdded(int i) {
    }

    public void onDisplayChanged(int i) {
        if (i == 0) {
            Display display = this.f10902J.getDisplay(i);
            this.G = display.getRotation() == 0;
            Point point = new Point();
            display.getRealSize(point);
            this.I = (float) point.x;
        }
    }

    public void onDisplayRemoved(int i) {
    }

    public void onLowMemory() {
    }
}
