package defpackage;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.browsing_data.ConfirmImportantSitesDialogFragment;
import org.chromium.url.GURL;

/* renamed from: tx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5000tx extends ArrayAdapter implements AdapterView.OnItemClickListener {
    public final String[] F;
    public final int G;
    public KN0 H;
    public final /* synthetic */ ConfirmImportantSitesDialogFragment I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5000tx(ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment, String[] strArr, String[] strArr2, Resources resources, DialogInterface$OnClickListenerC4660rx rxVar) {
        super(confirmImportantSitesDialogFragment.u(), (int) R.layout.f37390_resource_name_obfuscated_RES_2131624048, strArr);
        this.I = confirmImportantSitesDialogFragment;
        this.F = strArr;
        confirmImportantSitesDialogFragment.O0 = strArr2;
        this.G = resources.getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);
        Resources I2 = confirmImportantSitesDialogFragment.I();
        int dimensionPixelSize = I2.getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);
        this.H = new KN0(dimensionPixelSize, dimensionPixelSize, I2.getDimensionPixelSize(R.dimen.f18010_resource_name_obfuscated_RES_2131165420), I2.getColor(R.color.f11180_resource_name_obfuscated_RES_2131099808), (float) I2.getDimensionPixelSize(R.dimen.f18020_resource_name_obfuscated_RES_2131165421));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.I.u()).inflate(R.layout.f37390_resource_name_obfuscated_RES_2131624048, viewGroup, false);
            C5170ux uxVar = new C5170ux(null);
            uxVar.f11448a = (CheckBox) view.findViewById(R.id.icon_row_checkbox);
            uxVar.b = (ImageView) view.findViewById(R.id.icon_row_image);
            view.setTag(uxVar);
        }
        C5170ux uxVar2 = (C5170ux) view.getTag();
        String str = this.F[i];
        uxVar2.f11448a.setChecked(((Boolean) this.I.P0.get(str)).booleanValue());
        uxVar2.f11448a.setText(str);
        ConfirmImportantSitesDialogFragment confirmImportantSitesDialogFragment = this.I;
        String str2 = confirmImportantSitesDialogFragment.O0[i];
        C4830sx sxVar = new C4830sx(this, uxVar2, str2);
        uxVar2.c = sxVar;
        X60 x60 = confirmImportantSitesDialogFragment.S0;
        int i2 = this.G;
        Objects.requireNonNull(x60);
        x60.b(new GURL(str2), i2, sxVar);
        return view;
    }

    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        String str = this.F[i];
        boolean booleanValue = ((Boolean) this.I.P0.get(str)).booleanValue();
        this.I.P0.put(str, Boolean.valueOf(!booleanValue));
        ((C5170ux) view.getTag()).f11448a.setChecked(!booleanValue);
    }
}
