package org.chromium.chrome.browser.share.send_tab_to_self;

import java.util.ArrayList;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SendTabToSelfModelObserverBridge {
    public final void addToEntryList(List list, SendTabToSelfEntry sendTabToSelfEntry) {
        list.add(sendTabToSelfEntry);
    }

    public final void addToGuidList(List list, String str) {
        list.add(str);
    }

    public final List createEmptyJavaEntryList() {
        return new ArrayList();
    }

    public final List createEmptyJavaGuidList() {
        return new ArrayList();
    }

    public final void entriesAddedRemotely(List list) {
        throw null;
    }

    public final void entriesRemovedRemotely(List list) {
        throw null;
    }

    public final void modelLoaded() {
        throw null;
    }
}
