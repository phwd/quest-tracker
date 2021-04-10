package org.chromium.chrome.browser.share.send_tab_to_self;

import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SendTabToSelfAndroidBridge {
    public static void addToGuidList(List list, String str) {
        list.add(str);
    }

    public static void addToTargetDeviceInfoList(List list, TargetDeviceInfo targetDeviceInfo) {
        list.add(targetDeviceInfo);
    }
}
