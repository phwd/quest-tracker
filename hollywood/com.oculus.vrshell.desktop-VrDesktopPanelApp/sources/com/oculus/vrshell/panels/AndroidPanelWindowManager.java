package com.oculus.vrshell.panels;

import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Iterator;

/* access modifiers changed from: package-private */
public class AndroidPanelWindowManager implements WindowManager {
    private static final String TAG = AndroidPanelWindowManager.class.getSimpleName();
    private final WindowManager mParentWindowManager;
    private final ArrayList<View> mViews = new ArrayList<>();

    public AndroidPanelWindowManager(WindowManager parent) {
        this.mParentWindowManager = parent;
    }

    public Display getDefaultDisplay() {
        return this.mParentWindowManager.getDefaultDisplay();
    }

    public void removeViewImmediate(View view) {
        this.mParentWindowManager.removeViewImmediate(view);
        this.mViews.remove(view);
        Log.d(TAG, "View removed.");
    }

    public void addView(View view, ViewGroup.LayoutParams params) {
        this.mParentWindowManager.addView(view, params);
        this.mViews.add(view);
        Log.d(TAG, "View added.");
    }

    public void removeView(View view) {
        this.mParentWindowManager.removeView(view);
        this.mViews.remove(view);
        Log.d(TAG, "View removed.");
    }

    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
        this.mParentWindowManager.updateViewLayout(view, params);
    }

    public boolean dispatchGenericMotionEvent(MotionEvent e) {
        Iterator<View> it = this.mViews.iterator();
        while (it.hasNext()) {
            View v = it.next();
            if (v.dispatchGenericMotionEvent(offsetMotionEvent(v, e))) {
                return true;
            }
        }
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent e) {
        Iterator<View> it = this.mViews.iterator();
        while (it.hasNext()) {
            View v = it.next();
            if (v.dispatchTouchEvent(offsetMotionEvent(v, e))) {
                return true;
            }
        }
        return false;
    }

    private MotionEvent offsetMotionEvent(View v, MotionEvent e) {
        ViewGroup.LayoutParams viewParams = v.getLayoutParams();
        if (!(viewParams instanceof WindowManager.LayoutParams)) {
            return e;
        }
        WindowManager.LayoutParams windowParams = (WindowManager.LayoutParams) viewParams;
        MotionEvent clone = MotionEvent.obtain(e);
        clone.offsetLocation((float) (-windowParams.x), (float) (-windowParams.y));
        return clone;
    }
}
