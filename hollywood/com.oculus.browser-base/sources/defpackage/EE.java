package defpackage;

import J.N;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.share.send_tab_to_self.SendTabToSelfEntry;
import org.chromium.chrome.browser.share.send_tab_to_self.TargetDeviceInfo;
import org.chromium.ui.widget.ButtonCompat;

/* renamed from: EE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EE extends AbstractC4277pj implements AdapterView.OnItemClickListener {
    public final Context F;
    public final AbstractC4448qj G;
    public ViewGroup H;
    public ViewGroup I;

    /* renamed from: J  reason: collision with root package name */
    public final CE f7946J;
    public final Profile K;
    public final String L;
    public final String M;
    public final long N;
    public final C2528fT0 O;
    public final boolean P;

    public EE(Context context, String str, String str2, long j, AbstractC4448qj qjVar, C2528fT0 ft0, boolean z) {
        this.F = context;
        this.G = qjVar;
        Profile b = Profile.b();
        this.K = b;
        CE ce = new CE(b);
        this.f7946J = ce;
        this.L = str;
        this.M = str2;
        this.N = j;
        this.O = ft0;
        this.P = z;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41410_resource_name_obfuscated_RES_2131624450, (ViewGroup) null);
        this.H = viewGroup;
        ((TextView) viewGroup.findViewById(R.id.device_picker_toolbar)).setText(R.string.f61290_resource_name_obfuscated_RES_2131953446);
        ArrayList arrayList = new ArrayList();
        N.MVujpkId(b, arrayList);
        if (!z) {
            AbstractC3535lK0.a("SharingHubAndroid.SendTabToSelf.NotSyncing");
            this.I = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41420_resource_name_obfuscated_RES_2131624451, (ViewGroup) null);
            this.H.setVisibility(8);
            if (ft0 != null) {
                ButtonCompat buttonCompat = (ButtonCompat) this.I.findViewById(R.id.chrome_settings);
                buttonCompat.setVisibility(0);
                buttonCompat.setOnClickListener(new DE(this));
            }
        } else if (arrayList.isEmpty()) {
            AbstractC3535lK0.a("SharingHubAndroid.SendTabToSelf.NoTargetDevices");
            this.I = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41420_resource_name_obfuscated_RES_2131624451, (ViewGroup) null);
            this.H.setVisibility(8);
            ((TextView) this.I.findViewById(R.id.enable_sync_text_field)).setText(context.getResources().getString(R.string.f61670_resource_name_obfuscated_RES_2131953484));
        } else {
            ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41400_resource_name_obfuscated_RES_2131624449, (ViewGroup) null);
            this.I = viewGroup2;
            ListView listView = (ListView) viewGroup2.findViewById(R.id.device_picker_list);
            listView.setAdapter((ListAdapter) ce);
            listView.setOnItemClickListener(this);
        }
    }

    @Override // defpackage.AbstractC4277pj
    public void f() {
    }

    @Override // defpackage.AbstractC4277pj
    public View g() {
        return this.I;
    }

    @Override // defpackage.AbstractC4277pj
    public float h() {
        return -1.0f;
    }

    @Override // defpackage.AbstractC4277pj
    public int j() {
        return -2;
    }

    @Override // defpackage.AbstractC4277pj
    public int k() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public int l() {
        return R.string.f61260_resource_name_obfuscated_RES_2131953443;
    }

    @Override // defpackage.AbstractC4277pj
    public int m() {
        return R.string.f61180_resource_name_obfuscated_RES_2131953435;
    }

    @Override // defpackage.AbstractC4277pj
    public int n() {
        return R.string.f61270_resource_name_obfuscated_RES_2131953444;
    }

    @Override // defpackage.AbstractC4277pj
    public int o() {
        return R.string.f61280_resource_name_obfuscated_RES_2131953445;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        AbstractC3364kK0.g("SendTabToSelf.AndroidShareSheet.ClickResult", 1, 3);
        TargetDeviceInfo targetDeviceInfo = (TargetDeviceInfo) this.f7946J.F.get(i);
        SendTabToSelfEntry sendTabToSelfEntry = (SendTabToSelfEntry) N.M$fvB7ud(this.K, this.L, this.M, this.N, targetDeviceInfo.f10760a);
        C1184Ti1.b(this.F, this.F.getResources().getString(R.string.f61300_resource_name_obfuscated_RES_2131953447, targetDeviceInfo.c), 0).b.show();
        ((C5638xj) this.G).p(this, true, 0);
    }

    @Override // defpackage.AbstractC4277pj
    public View p() {
        return this.H;
    }

    @Override // defpackage.AbstractC4277pj
    public int q() {
        return 0;
    }

    @Override // defpackage.AbstractC4277pj
    public boolean v() {
        return true;
    }
}
