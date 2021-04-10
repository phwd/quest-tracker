package com.oculus.panelapp.messenger.views;

import com.oculus.socialplatform.R;
import java.util.HashMap;
import java.util.Map;

public enum ThreadViewContextMenuItem {
    BLOCK(R.string.anytime_tablet_messenger_thread_context_menu_block, R.string.anytime_tablet_messenger_group_thread_context_menu_block),
    UNBLOCK(R.string.anytime_tablet_messenger_thread_context_menu_unblock, R.string.anytime_tablet_messenger_thread_context_menu_unblock),
    REPORT(R.string.anytime_tablet_messenger_thread_context_menu_report, R.string.anytime_tablet_messenger_group_thread_context_menu_report),
    LEAVE_CHAT(R.string.anytime_tablet_messenger_thread_context_menu_leave_chat, R.string.anytime_tablet_messenger_thread_context_menu_leave_chat);
    
    public static final Map<ThreadViewContextMenuItem, Integer> GROUP_THREAD_CONTEXT_MENU_ITEM_TITLE_MAP = new HashMap();
    public static final Map<ThreadViewContextMenuItem, Integer> THREAD_CONTEXT_MENU_ITEM_TITLE_MAP = new HashMap();
    public final int mGroupThreadStringId;
    public final int mStringId;

    private int getGroupThreadStringId() {
        return this.mGroupThreadStringId;
    }

    private int getStringId() {
        return this.mStringId;
    }

    public static Map<ThreadViewContextMenuItem, Integer> getTitleMap(boolean z) {
        if (z) {
            return THREAD_CONTEXT_MENU_ITEM_TITLE_MAP;
        }
        return GROUP_THREAD_CONTEXT_MENU_ITEM_TITLE_MAP;
    }

    /* access modifiers changed from: public */
    static {
        ThreadViewContextMenuItem[] values = values();
        for (ThreadViewContextMenuItem threadViewContextMenuItem : values) {
            THREAD_CONTEXT_MENU_ITEM_TITLE_MAP.put(threadViewContextMenuItem, Integer.valueOf(threadViewContextMenuItem.getStringId()));
            GROUP_THREAD_CONTEXT_MENU_ITEM_TITLE_MAP.put(threadViewContextMenuItem, Integer.valueOf(threadViewContextMenuItem.getGroupThreadStringId()));
        }
    }

    /* access modifiers changed from: public */
    ThreadViewContextMenuItem(int i, int i2) {
        this.mStringId = i;
        this.mGroupThreadStringId = i2;
    }
}
