package defpackage;

import android.content.Context;
import android.view.KeyboardShortcutGroup;
import android.view.KeyboardShortcutInfo;

/* renamed from: i60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2981i60 {
    public static void a(Context context, KeyboardShortcutGroup keyboardShortcutGroup, int i, int i2, int i3) {
        keyboardShortcutGroup.addItem(new KeyboardShortcutInfo(context.getString(i), i2, i3));
    }
}
