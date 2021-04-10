package org.chromium.chrome.browser.complex_tasks;

import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TaskTabHelper {
    public static long getParentRootTaskId(Tab tab) {
        Long l = (Long) Y51.c(tab).e("ParentRootTaskId");
        if (l == null) {
            return -1;
        }
        return l.longValue();
    }

    public static long getParentTaskId(Tab tab) {
        Long l = (Long) Y51.c(tab).e("ParentTaskId");
        if (l == null) {
            return -1;
        }
        return l.longValue();
    }
}
