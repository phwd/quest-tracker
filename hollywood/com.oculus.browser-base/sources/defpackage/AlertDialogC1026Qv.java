package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.embedder_support.delegate.ColorPickerAdvanced;
import org.chromium.components.embedder_support.delegate.ColorPickerSimple;
import org.chromium.components.embedder_support.delegate.ColorSuggestion;

/* renamed from: Qv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AlertDialogC1026Qv extends AlertDialog implements AbstractC0656Ks0 {
    public final ColorPickerAdvanced F;
    public final ColorPickerSimple G;
    public final Button H;
    public final View I;

    /* renamed from: J  reason: collision with root package name */
    public final View f8793J;
    public final AbstractC0656Ks0 K;
    public final int L;
    public int M;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlertDialogC1026Qv(Context context, AbstractC0656Ks0 ks0, int i, ColorSuggestion[] colorSuggestionArr) {
        super(context, 0);
        this.K = ks0;
        this.L = i;
        this.M = i;
        View a2 = AbstractC2471f70.a(context, R.layout.f37360_resource_name_obfuscated_RES_2131624045, null);
        setCustomTitle(a2);
        this.f8793J = a2.findViewById(R.id.selected_color_view);
        ((TextView) a2.findViewById(R.id.title)).setText(R.string.f49310_resource_name_obfuscated_RES_2131952248);
        setButton(-1, context.getString(R.string.f49280_resource_name_obfuscated_RES_2131952245), new DialogInterface$OnClickListenerC0782Mv(this));
        setButton(-2, context.getString(R.string.f49220_resource_name_obfuscated_RES_2131952239), new DialogInterface$OnClickListenerC0843Nv(this));
        setOnCancelListener(new DialogInterface$OnCancelListenerC0904Ov(this));
        View a3 = AbstractC2471f70.a(context, R.layout.f37350_resource_name_obfuscated_RES_2131624044, null);
        this.I = a3;
        setView(a3);
        Button button = (Button) a3.findViewById(R.id.more_colors_button);
        this.H = button;
        button.setOnClickListener(new View$OnClickListenerC0965Pv(this));
        ColorPickerAdvanced colorPickerAdvanced = (ColorPickerAdvanced) a3.findViewById(R.id.color_picker_advanced);
        this.F = colorPickerAdvanced;
        colorPickerAdvanced.setVisibility(8);
        ColorPickerSimple colorPickerSimple = (ColorPickerSimple) a3.findViewById(R.id.color_picker_simple);
        this.G = colorPickerSimple;
        colorPickerSimple.H = this;
        if (colorSuggestionArr == null) {
            int length = ColorPickerSimple.F.length;
            colorSuggestionArr = new ColorSuggestion[length];
            for (int i2 = 0; i2 < length; i2++) {
                colorSuggestionArr[i2] = new ColorSuggestion(ColorPickerSimple.F[i2], colorPickerSimple.getContext().getString(ColorPickerSimple.G[i2]));
            }
        }
        View$OnClickListenerC1209Tv tv = new View$OnClickListenerC1209Tv(colorPickerSimple.getContext(), colorSuggestionArr);
        tv.H = colorPickerSimple;
        colorPickerSimple.setAdapter((ListAdapter) tv);
        int i3 = this.L;
        this.M = i3;
        View view = this.f8793J;
        if (view != null) {
            view.setBackgroundColor(i3);
        }
    }

    public static void b(AlertDialogC1026Qv qv, int i) {
        AbstractC0656Ks0 ks0 = qv.K;
        if (ks0 != null) {
            ks0.a(i);
        }
    }

    @Override // defpackage.AbstractC0656Ks0
    public void a(int i) {
        this.M = i;
        View view = this.f8793J;
        if (view != null) {
            view.setBackgroundColor(i);
        }
    }
}
