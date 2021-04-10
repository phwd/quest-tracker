package X;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import com.facebook.cameracore.threading.ThreadPool$LifeStatus;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

@TargetApi(4)
/* renamed from: X.1mY  reason: invalid class name and case insensitive filesystem */
public final class C09541mY {
    public static final C09541mY A03 = new C09541mY();
    public final Map<Handler, HandlerThread> A00 = new HashMap();
    public final Map<HandlerThread, ThreadPool$LifeStatus> A01 = new HashMap();
    public final C09551ma A02 = new C09551ma();

    public static Handler A00(C09541mY r5, String str, @Nullable Handler.Callback callback) {
        String str2;
        Map<HandlerThread, ThreadPool$LifeStatus> map;
        boolean z;
        HandlerThread handlerThread;
        if (str == null || str.isEmpty()) {
            str2 = "Thread name cannot be empty";
        } else {
            synchronized (r5) {
                map = r5.A01;
                Iterator<HandlerThread> it = map.keySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getName().equals(str)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    AnonymousClass0NO.A0F("ThreadPool", "Thread name already exists %s", str);
                }
                handlerThread = new HandlerThread("DO_NOT_USE_thread", 0);
                handlerThread.setName(str);
            }
            handlerThread.start();
            if (handlerThread.isAlive()) {
                synchronized (r5) {
                    map.put(handlerThread, ThreadPool$LifeStatus.AVAILABLE);
                }
                Handler handler = new Handler(handlerThread.getLooper(), callback);
                r5.A00.put(handler, handlerThread);
                synchronized (r5) {
                    map.put(handlerThread, ThreadPool$LifeStatus.TAKEN);
                }
                map.size();
                return handler;
            }
            str2 = "Thread start was unsuccessful";
        }
        throw new RuntimeException(str2);
    }

    public static void A01(@Nullable Handler handler, boolean z, boolean z2) {
        Map<HandlerThread, ThreadPool$LifeStatus> map;
        C09541mY r4 = A03;
        if (handler != null) {
            Map<Handler, HandlerThread> map2 = r4.A00;
            HandlerThread handlerThread = map2.get(handler);
            if (handlerThread == null) {
                AnonymousClass0NO.A09("ThreadPool", "Trying to quit thread not managed by ThreadPool - abort");
                return;
            }
            map2.remove(handler);
            synchronized (r4) {
                map = r4.A01;
                ThreadPool$LifeStatus threadPool$LifeStatus = map.get(handlerThread);
                if (threadPool$LifeStatus == ThreadPool$LifeStatus.TAKEN) {
                    ThreadPool$LifeStatus threadPool$LifeStatus2 = ThreadPool$LifeStatus.AVAILABLE;
                    map.put(handlerThread, threadPool$LifeStatus2);
                    handlerThread.setName("CameraCore_Available_Thread");
                    if (map.get(handlerThread) == threadPool$LifeStatus2) {
                        map.put(handlerThread, ThreadPool$LifeStatus.QUITTING);
                        if (!z2) {
                            handlerThread.quitSafely();
                        } else {
                            handlerThread.quit();
                        }
                        if (z) {
                            try {
                                if (Thread.currentThread() != handlerThread) {
                                    handlerThread.join(1000);
                                }
                            } catch (InterruptedException unused) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        map.remove(handlerThread);
                    } else {
                        AnonymousClass0NO.A09("ThreadPool", "Trying to kill thread that is not AVAILABLE");
                    }
                } else {
                    AnonymousClass0NO.A0F("ThreadPool", "Trying to quit thread that is not TAKEN but in %s", threadPool$LifeStatus);
                }
            }
            map.size();
        }
    }
}
