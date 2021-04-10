package com.oculus.messengervr.oc;

import com.facebook.infer.annotation.Nullsafe;
import com.oculus.messengervr.oc.OcMessengerManagerUpdateResult;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Constants {
    public static final String LOG_TAG = "OC_VR_MESSENGER_API";
    public static final Set<OcMessengerManagerUpdateResult.UpdateResultType> MESSAGE_UPDATE_RESULT_TYPES = new HashSet(Arrays.asList(OcMessengerManagerUpdateResult.UpdateResultType.NEW_MESSAGE));
    public static final int PROFILE_PICS_PATCHING_PREFETCH_SIZE = 5;
    public static final int THREADLIST_SNIPPET_MESSAGE_COUNT = 1;
    public static final int THREADLIST_THREAD_PROFILE_PICS_MAX_COUNT = 2;
    public static final Set<OcMessengerManagerUpdateResult.UpdateResultType> THREAD_LIST_UPDATE_RESULT_TYPES;
    public static final Set<OcMessengerManagerUpdateResult.UpdateResultType> THREAD_UPDATE_RESULT_TYPES;
    public static final int THROTTLE_TIMEOUT = 250;

    static {
        OcMessengerManagerUpdateResult.UpdateResultType updateResultType = OcMessengerManagerUpdateResult.UpdateResultType.THREAD_DELETION;
        OcMessengerManagerUpdateResult.UpdateResultType updateResultType2 = OcMessengerManagerUpdateResult.UpdateResultType.THREAD_UPDATE;
        THREAD_LIST_UPDATE_RESULT_TYPES = new HashSet(Arrays.asList(updateResultType, updateResultType2, OcMessengerManagerUpdateResult.UpdateResultType.THREAD_UNREAD_COUNT));
        THREAD_UPDATE_RESULT_TYPES = new HashSet(Arrays.asList(updateResultType2));
    }
}
