package com.oculus.vrshell.panels;

import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Iterator;

public class AndroidPanelWindowManager implements WindowManager {
    public static final String TAG = "AndroidPanelWindowManager";
    public final WindowManager mParentWindowManager;
    public final ArrayList<View> mViews = new ArrayList<>();

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mParentWindowManager.addView(view, layoutParams);
        this.mViews.add(view);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        Iterator<View> it = this.mViews.iterator();
        while (it.hasNext()) {
            View next = it.next();
            if (next.dispatchGenericMotionEvent(offsetMotionEvent(next, motionEvent))) {
                return true;
            }
        }
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Iterator<View> it = this.mViews.iterator();
        while (it.hasNext()) {
            View next = it.next();
            if (next.dispatchTouchEvent(offsetMotionEvent(next, motionEvent))) {
                return true;
            }
        }
        return false;
    }

    public Display getDefaultDisplay() {
        return this.mParentWindowManager.getDefaultDisplay();
    }

    public void removeView(View view) {
        this.mParentWindowManager.removeView(view);
        this.mViews.remove(view);
    }

    public void removeViewImmediate(View view) {
        this.mParentWindowManager.removeViewImmediate(view);
        this.mViews.remove(view);
    }

    public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
        this.mParentWindowManager.updateViewLayout(view, layoutParams);
    }

    public AndroidPanelWindowManager(WindowManager windowManager) {
        this.mParentWindowManager = windowManager;
    }

    private MotionEvent offsetMotionEvent(View view, MotionEvent motionEvent) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof WindowManager.LayoutParams)) {
            return motionEvent;
        }
        WindowManager.LayoutParams layoutParams2 = (WindowManager.LayoutParams) layoutParams;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation((float) (-layoutParams2.x), (float) (-layoutParams2.y));
        return obtain;
    }
}
