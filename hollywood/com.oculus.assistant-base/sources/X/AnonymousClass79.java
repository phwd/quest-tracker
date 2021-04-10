package X;

import android.os.Bundle;
import com.facebook.common.gcmcompat.OneoffTask;

/* renamed from: X.79  reason: invalid class name */
public final class AnonymousClass79 {
    public final int A00;
    public final OneoffTask A01;

    public AnonymousClass79(int i, OneoffTask oneoffTask) {
        this.A00 = i;
        this.A01 = oneoffTask;
    }

    public AnonymousClass79(Bundle bundle) {
        int i = bundle.getInt("job_id", -1);
        if (i != -1) {
            this.A00 = i;
            OneoffTask oneoffTask = (OneoffTask) bundle.getParcelable("task");
            if (oneoffTask != null) {
                this.A01 = oneoffTask;
                return;
            }
            throw new AnonymousClass7D("Missing task");
        }
        StringBuilder sb = new StringBuilder("Invalid job_id: ");
        sb.append(bundle.get("job_id"));
        throw new AnonymousClass7D(sb.toString());
    }
}
