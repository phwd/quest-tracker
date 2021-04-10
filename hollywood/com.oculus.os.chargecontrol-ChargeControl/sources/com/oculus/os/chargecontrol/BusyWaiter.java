package com.oculus.os.chargecontrol;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;
import com.oculus.os.VrApiManager;
import java.util.Random;

public class BusyWaiter {
    private static final byte[] JUNK = new byte[8388608];
    public static final BusyWaiter instance = new BusyWaiter();
    private volatile boolean running_ = false;
    private final Thread[] waiters_ = new Thread[Runtime.getRuntime().availableProcessors()];
    private PowerManager.WakeLock wakeLock_;

    static {
        new Random().nextBytes(JUNK);
    }

    public synchronized void start(Context context) {
        if (!this.running_) {
            this.running_ = true;
            Log.d("ChargeControl", "Starting " + this.waiters_.length + " copy threads");
            configureCpuClocks(true);
            this.wakeLock_ = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "battery_discharge");
            this.wakeLock_.acquire();
            for (int i = 0; i < this.waiters_.length; i++) {
                this.waiters_[i] = new Thread(new Runnable(i) {
                    /* class com.oculus.os.chargecontrol.$$Lambda$BusyWaiter$zPVnPADyta6LJyFmT88shHxVA */
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        BusyWaiter.this.lambda$start$0$BusyWaiter(this.f$1);
                    }
                });
                this.waiters_[i].setPriority(1);
                this.waiters_[i].start();
            }
        }
    }

    public /* synthetic */ void lambda$start$0$BusyWaiter(int i) {
        byte[] bArr = new byte[JUNK.length];
        Random random = new Random();
        while (true) {
            long j = 0;
            long j2 = 0;
            while (this.running_) {
                int nextInt = (131072 * i) + random.nextInt(16);
                long nanoTime = System.nanoTime();
                byte[] bArr2 = JUNK;
                System.arraycopy(bArr2, nextInt, bArr, 0, bArr2.length - nextInt);
                j += System.nanoTime() - nanoTime;
                j2 += (long) (JUNK.length - nextInt);
                if (j2 >= 1099511627776L) {
                    Log.d("ChargeControl", "Waiter[" + i + "]: " + (j2 / (j / 1000)) + " MB/sec");
                }
            }
            return;
        }
    }

    public synchronized void stop() {
        if (this.running_) {
            this.wakeLock_.release();
            this.running_ = false;
            for (int i = 0; i < this.waiters_.length; i++) {
                try {
                    this.waiters_[i].join();
                } catch (InterruptedException unused) {
                }
            }
            configureCpuClocks(false);
        }
    }

    private void configureCpuClocks(boolean z) {
        try {
            VrApiManager instance2 = VrApiManager.getInstance();
            if (z) {
                instance2.lockVrPerformance(3, 0);
            } else {
                instance2.releaseVrPerformance();
            }
        } catch (Exception e) {
            Log.e("ChargeControl", "Unable to configure CPU clocks", e);
        }
    }

    private BusyWaiter() {
    }
}
