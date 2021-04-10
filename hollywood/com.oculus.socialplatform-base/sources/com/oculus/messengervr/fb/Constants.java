package com.oculus.messengervr.fb;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Constants {
    public static final String FOLDER_INBOX = "inbox";
    public static final String LOG_TAG = "VR_MESSENGER_API";
    public static final int THREAD_LIST_PAGE_SIZE = 10;
    public static final int THREAD_LIST_PATCHING_PREFETCH_SIZE = 5;
    public static final String THREAD_LIST_STORED_PROCEDURE_NAME = "thread_list";
    public static final int THROTTLE_TIMEOUT = 250;
}
