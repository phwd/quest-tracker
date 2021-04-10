package X;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;
import okhttp3.internal.ws.WebSocketProtocol;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1FE  reason: invalid class name */
public final class AnonymousClass1FE {
    public final Context A00;
    public final Rect A01 = new Rect();
    public final View A02;
    public final WindowManager.LayoutParams A03 = new WindowManager.LayoutParams();
    public final TextView A04;
    public final int[] A05 = new int[2];
    public final int[] A06 = new int[2];

    public AnonymousClass1FE(@NonNull Context context) {
        this.A00 = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.abc_tooltip, (ViewGroup) null);
        this.A02 = inflate;
        this.A04 = (TextView) inflate.findViewById(R.id.message);
        this.A03.setTitle(getClass().getSimpleName());
        this.A03.packageName = this.A00.getPackageName();
        WindowManager.LayoutParams layoutParams = this.A03;
        layoutParams.type = WebSocketProtocol.CLOSE_PROTOCOL_EXCEPTION;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 2131755011;
        layoutParams.flags = 24;
    }
}
