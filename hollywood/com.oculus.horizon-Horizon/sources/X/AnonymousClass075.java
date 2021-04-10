package X;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.075  reason: invalid class name */
public final class AnonymousClass075 {
    public static Field A00;
    public static Method A01;
    public static boolean A02;
    public static boolean A03;

    public static boolean A00(@NonNull AnonymousClass074 r8, @Nullable View view, @Nullable Window.Callback callback, @NonNull KeyEvent keyEvent) {
        KeyEvent.DispatcherState dispatcherState;
        KeyEvent.DispatcherState dispatcherState2;
        if (Build.VERSION.SDK_INT >= 28) {
            return r8.superDispatchKeyEvent(keyEvent);
        }
        if (callback instanceof Activity) {
            Activity activity = (Activity) callback;
            activity.onUserInteraction();
            Window window = activity.getWindow();
            if (window.hasFeature(8)) {
                Object actionBar = activity.getActionBar();
                if (keyEvent.getKeyCode() == 82 && actionBar != null) {
                    if (!A02) {
                        try {
                            A01 = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
                        } catch (NoSuchMethodException unused) {
                        }
                        A02 = true;
                    }
                    Method method = A01;
                    if (method != null) {
                        try {
                            if (((Boolean) method.invoke(actionBar, keyEvent)).booleanValue()) {
                                return true;
                            }
                        } catch (IllegalAccessException | InvocationTargetException unused2) {
                        }
                    }
                }
            }
            if (window.superDispatchKeyEvent(keyEvent)) {
                return true;
            }
            View decorView = window.getDecorView();
            if (C001507f.A00(decorView, keyEvent)) {
                return true;
            }
            if (decorView != null) {
                dispatcherState2 = decorView.getKeyDispatcherState();
            } else {
                dispatcherState2 = null;
            }
            return keyEvent.dispatch(activity, dispatcherState2, activity);
        } else if (!(callback instanceof Dialog)) {
            return (view != null && C001507f.A00(view, keyEvent)) || r8.superDispatchKeyEvent(keyEvent);
        } else {
            Dialog dialog = (Dialog) callback;
            if (!A03) {
                try {
                    Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                    A00 = declaredField;
                    declaredField.setAccessible(true);
                } catch (NoSuchFieldException unused3) {
                }
                A03 = true;
            }
            Field field = A00;
            if (field != null) {
                try {
                    DialogInterface.OnKeyListener onKeyListener = (DialogInterface.OnKeyListener) field.get(dialog);
                    if (onKeyListener != null && onKeyListener.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
                        return true;
                    }
                } catch (IllegalAccessException unused4) {
                }
            }
            Window window2 = dialog.getWindow();
            if (window2.superDispatchKeyEvent(keyEvent)) {
                return true;
            }
            View decorView2 = window2.getDecorView();
            if (C001507f.A00(decorView2, keyEvent)) {
                return true;
            }
            if (decorView2 != null) {
                dispatcherState = decorView2.getKeyDispatcherState();
            } else {
                dispatcherState = null;
            }
            return keyEvent.dispatch(dialog, dispatcherState, dialog);
        }
    }
}
