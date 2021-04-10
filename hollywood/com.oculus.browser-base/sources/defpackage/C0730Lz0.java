package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.FadingShadowView;

/* renamed from: Lz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0730Lz0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8452a;
    public final View b;
    public final TextView c;
    public final TextView d;
    public final ProgressBar e;
    public final ImageView f;
    public final View g;

    public C0730Lz0(Context context) {
        this.f8452a = context.getResources().getDimensionPixelSize(R.dimen.f25040_resource_name_obfuscated_RES_2131166123);
        context.getResources().getDimensionPixelSize(R.dimen.f16490_resource_name_obfuscated_RES_2131165268);
        View inflate = LayoutInflater.from(context).inflate(R.layout.f41470_resource_name_obfuscated_RES_2131624456, (ViewGroup) null);
        this.b = inflate;
        this.c = (TextView) inflate.findViewById(R.id.origin);
        this.d = (TextView) inflate.findViewById(R.id.title);
        this.e = (ProgressBar) inflate.findViewById(R.id.progress_bar);
        this.f = (ImageView) inflate.findViewById(R.id.security_icon);
        this.g = inflate.findViewById(R.id.close);
        inflate.findViewById(R.id.open_in_new_tab).setVisibility(8);
        inflate.findViewById(R.id.favicon).setVisibility(8);
        ((FadingShadowView) inflate.findViewById(R.id.shadow)).a(context.getResources().getColor(R.color.f15370_resource_name_obfuscated_RES_2131100227), 0);
    }
}
