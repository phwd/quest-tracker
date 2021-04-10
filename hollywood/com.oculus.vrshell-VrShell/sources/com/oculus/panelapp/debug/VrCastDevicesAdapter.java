package com.oculus.panelapp.debug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.vrcast.VrCastController;
import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: package-private */
public class VrCastDevicesAdapter extends RecyclerView.Adapter<VrCastViewHolder> {
    private static final String TAG = "VrCastDevicesAdapter";
    private VrCastManager mCastController;
    private final Context mContext;
    private List<VrCastController.VrShellCastDevice> mList = new ArrayList();

    public VrCastDevicesAdapter(Context context) {
        this.mContext = context;
    }

    public void setManager(VrCastManager vrCastManager) {
        this.mCastController = vrCastManager;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VrCastViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new VrCastViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.debug_vr_cast_item, viewGroup, false));
    }

    public void onBindViewHolder(VrCastViewHolder vrCastViewHolder, int i) {
        vrCastViewHolder.bind(this.mList.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    public void onDevicesFound(List<VrCastController.VrShellCastDevice> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    /* access modifiers changed from: package-private */
    public class VrCastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final Button mButton;
        private VrCastController.VrShellCastDevice mDevice;
        private final TextView mText;

        public VrCastViewHolder(View view) {
            super(view);
            this.mText = (TextView) view.findViewById(R.id.vrcast_item_text);
            this.mButton = (Button) view.findViewById(R.id.vrcast_item_button);
            this.mButton.setOnClickListener(this);
        }

        public void onClick(View view) {
            if (this.mDevice != null) {
                int i = AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State[this.mDevice.state.ordinal()];
                if (i == 1) {
                    VrCastDevicesAdapter.this.mCastController.startCast(this.mDevice);
                } else if (i == 2) {
                    VrCastDevicesAdapter.this.mCastController.stopCast(this.mDevice);
                }
            }
        }

        public void bind(VrCastController.VrShellCastDevice vrShellCastDevice) {
            this.mDevice = vrShellCastDevice;
            this.mText.setText(this.mDevice.name);
            int i = AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State[this.mDevice.state.ordinal()];
            if (i == 1) {
                this.mButton.setEnabled(true);
                this.mButton.setText("Connect");
            } else if (i == 2) {
                this.mButton.setEnabled(true);
                this.mButton.setText("Stop casting");
            } else if (i == 3) {
                this.mButton.setEnabled(false);
                this.mButton.setText("Connecting to peer");
            } else if (i == 4) {
                this.mButton.setEnabled(false);
                this.mButton.setText("Starting session");
            } else if (i == 5) {
                this.mButton.setEnabled(false);
                this.mButton.setText("Disconnecting");
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.debug.VrCastDevicesAdapter$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State = new int[VrCastController.VrShellCastDevice.State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State[] r0 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.debug.VrCastDevicesAdapter.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State = r0
                int[] r0 = com.oculus.panelapp.debug.VrCastDevicesAdapter.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.FOUND     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.debug.VrCastDevicesAdapter.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CASTING     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.debug.VrCastDevicesAdapter.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CONNECTING_TO_PEER     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.debug.VrCastDevicesAdapter.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.STARTING_SESSION     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.panelapp.debug.VrCastDevicesAdapter.AnonymousClass1.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.DISCONNECTING     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.debug.VrCastDevicesAdapter.AnonymousClass1.<clinit>():void");
        }
    }
}
