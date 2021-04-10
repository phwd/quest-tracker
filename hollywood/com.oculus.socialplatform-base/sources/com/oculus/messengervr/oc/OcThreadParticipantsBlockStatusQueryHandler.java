package com.oculus.messengervr.oc;

import android.annotation.TargetApi;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Map;
import java.util.function.Consumer;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface OcThreadParticipantsBlockStatusQueryHandler {
    public static final String FETCH_THREAD_PARTICIPANTS_BLOCK_STATUSES_QUERY = "query FetchThreadParticipantsBlockStatusesQuery($id:ID!) {\n  message_thread(id: $id) {\n    participants_as_user_or_blocked_users {\n      is_blocked_by_viewer\n      possibly_blocked_real_user_id\n    }\n  }\n}";

    void queryParticipantBlockStatuses(String str, Consumer<Map<String, Boolean>> consumer, Consumer<Throwable> consumer2);
}
