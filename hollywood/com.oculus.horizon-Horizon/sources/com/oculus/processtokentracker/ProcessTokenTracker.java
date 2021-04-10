package com.oculus.processtokentracker;

import X.C07490ss;
import android.os.IBinder;
import androidx.annotation.VisibleForTesting;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Dependencies({})
@ApplicationScoped
public class ProcessTokenTracker {
    public static volatile ProcessTokenTracker _UL__ULSEP_com_oculus_processtokentracker_ProcessTokenTracker_ULSEP_INSTANCE;
    public final Set<ProcessListener> mProcessListeners = new HashSet();
    public final Map<String, Map<Integer, ProcessDeathRecipient>> mTokenRegistry = Collections.synchronizedMap(new C07490ss());

    @VisibleForTesting
    public class ProcessDeathRecipient implements IBinder.DeathRecipient {
        public final String packageName;
        public final int processId;
        public final IBinder token;

        public ProcessDeathRecipient(String str, int i, IBinder iBinder) {
            this.packageName = str;
            this.processId = i;
            this.token = iBinder;
        }

        public final void binderDied() {
            ProcessTokenTracker.A00(ProcessTokenTracker.this, this.packageName, this.processId);
            for (ProcessListener processListener : ProcessTokenTracker.this.mProcessListeners) {
                processListener.onDeath(this.packageName, this.processId);
            }
        }
    }

    public interface ProcessListener {
        void onDeath(String str, int i);

        void onRegister(String str, int i);
    }

    public static void A00(ProcessTokenTracker processTokenTracker, String str, int i) {
        Integer valueOf;
        ProcessDeathRecipient processDeathRecipient;
        Map<Integer, ProcessDeathRecipient> map = processTokenTracker.mTokenRegistry.get(str);
        if (map != null && (processDeathRecipient = map.get((valueOf = Integer.valueOf(i)))) != null) {
            map.remove(valueOf);
            processDeathRecipient.token.unlinkToDeath(processDeathRecipient, 0);
            if (map.size() == 0) {
                processTokenTracker.mTokenRegistry.remove(str);
            }
        }
    }
}
