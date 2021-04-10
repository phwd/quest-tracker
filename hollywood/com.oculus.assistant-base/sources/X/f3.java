package X;

import android.content.ComponentName;
import android.content.Context;
import com.facebook.analytics2.logger.GooglePlayUploadService;

public final class f3 extends AnonymousClass7u {
    public final Context A00;
    public final ComponentName A01;

    public f3(Context context) {
        this.A00 = context;
        this.A01 = new ComponentName(context, GooglePlayUploadService.class);
    }
}
