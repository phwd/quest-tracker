package com.oculus.horizon.cast;

import X.AbstractC09411eb;
import X.AnonymousClass1em;

public class CastWebSocket extends AbstractC09411eb {
    public final String TAG = "CastWebSocket";
    public final SocketMessageListener mListener;

    public interface SocketMessageListener {
        void A5h(String str, String str2);

        void A61(String str, String str2);

        void A64(String str);

        void A6x(String str, VideoSpec videoSpec, boolean z, boolean z2);

        void A71(String str);

        void onClose();
    }

    /* renamed from: com.oculus.horizon.cast.CastWebSocket$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$cast$Message$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.horizon.cast.Message$Type[] r0 = com.oculus.horizon.cast.Message.Type.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.cast.CastWebSocket.AnonymousClass1.$SwitchMap$com$oculus$horizon$cast$Message$Type = r2
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.START     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.ANSWER     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.STOP     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.ERROR     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cast.CastWebSocket.AnonymousClass1.<clinit>():void");
        }
    }

    public CastWebSocket(SocketMessageListener socketMessageListener, AnonymousClass1em r3) {
        super(r3);
        this.mListener = socketMessageListener;
    }
}
