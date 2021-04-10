package oculus.internal;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.ActivityThread;
import android.app.IActivityTaskManager;
import android.app.ProfilerInfo;
import android.app.TaskStackListener;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.hardware.input.InputManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.view.InputEvent;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.SurfaceSession;
import java.util.concurrent.atomic.AtomicBoolean;

public class VrDesktopContainer implements VrDesktopContainerInterface {
    private static final String DISPLAY_NAME = "VrDesktopDisplay";
    private static final String TAG = VrDesktopContainer.class.getSimpleName();
    private final AtomicBoolean mActive = new AtomicBoolean(true);
    private final IActivityTaskManager mActivityTaskManager;
    private SurfaceControl mBackgroundSurface;
    private final Context mContext;
    private final int mDisplayId;
    private final int mHeight;
    private final SurfaceSession mSurfaceSession = new SurfaceSession();
    private final TaskStackListener mTaskStackListener;
    private VirtualDisplay mVirtualDisplay;
    private final int mWidth;

    public VrDesktopContainer(Context context, Surface surface, int width, int height, int displayDensity) throws RemoteException {
        this.mContext = context;
        this.mWidth = width;
        this.mHeight = height;
        this.mActivityTaskManager = ActivityTaskManager.getService();
        this.mTaskStackListener = new TaskStackListenerImpl();
        this.mVirtualDisplay = ((DisplayManager) context.getSystemService(DisplayManager.class)).createVirtualDisplay("VrDesktopDisplay@" + System.identityHashCode(this), width, height, displayDensity, surface, 269);
        this.mDisplayId = this.mVirtualDisplay.getDisplay().getDisplayId();
        this.mActivityTaskManager.registerTaskStackListener(this.mTaskStackListener);
        String str = TAG;
        Log.d(str, "Created a new virtual display: id=" + this.mDisplayId);
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public synchronized int startActivity(Intent intent) throws RemoteException {
        if (this.mVirtualDisplay == null) {
            return -1;
        }
        ActivityOptions options = ActivityOptions.makeBasic();
        options.setLaunchDisplayId(this.mDisplayId);
        int res = this.mActivityTaskManager.startActivityAsUser(ActivityThread.currentActivityThread().getApplicationThread(), this.mContext.getBasePackageName(), intent, intent.resolveTypeIfNeeded(this.mContext.getContentResolver()), (IBinder) null, (String) null, 0, 268435456, (ProfilerInfo) null, options.toBundle(), UserHandle.CURRENT.getIdentifier());
        this.mActive.set(res >= 0);
        return res;
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public synchronized boolean injectEvent(InputEvent event) throws RemoteException {
        if (this.mVirtualDisplay == null) {
            return false;
        }
        InputManager im = InputManager.getInstance();
        event.setDisplayId(this.mDisplayId);
        return im.injectInputEvent(event, 0);
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public void release() throws RemoteException {
        if (this.mVirtualDisplay != null) {
            this.mActivityTaskManager.unregisterTaskStackListener(this.mTaskStackListener);
            this.mVirtualDisplay.release();
            this.mVirtualDisplay = null;
            this.mActive.set(false);
        }
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public void setInputFocusEnabled(boolean enabled) throws RemoteException {
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public void suspendActivities() throws RemoteException {
        if (this.mVirtualDisplay != null) {
            this.mActivityTaskManager.suspendActivities(this.mDisplayId);
        }
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public void resumeTopActivity() throws RemoteException {
        if (this.mVirtualDisplay != null) {
            this.mActivityTaskManager.resumeTopActivity(this.mDisplayId);
        }
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public void setVolume(float volume) throws RemoteException {
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public void clear() throws RemoteException {
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public synchronized void addBackgroundSurface() throws RemoteException {
        if (this.mVirtualDisplay != null) {
            SurfaceControl.openTransaction();
            try {
                this.mBackgroundSurface = new SurfaceControl.Builder(this.mSurfaceSession).setName("BackgroundSurface").setBufferSize(this.mWidth, this.mHeight).setOpaque(true).setFlags(131076).build();
                this.mBackgroundSurface.setLayerStack(this.mDisplayId);
                this.mBackgroundSurface.setAlpha(1.0f);
                this.mBackgroundSurface.setLayer(0);
                this.mBackgroundSurface.show();
                String str = TAG;
                Log.d(str, "Added " + this.mBackgroundSurface);
            } finally {
                SurfaceControl.closeTransaction();
            }
        }
    }

    @Override // oculus.internal.VrDesktopContainerInterface
    public boolean isActive() {
        return this.mActive.get();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkDisplayState() throws RemoteException {
        for (ActivityManager.StackInfo si : this.mActivityTaskManager.getAllStackInfos()) {
            if (si.displayId == this.mDisplayId && si.visible) {
                return;
            }
        }
        this.mActive.set(false);
    }

    private class TaskStackListenerImpl extends TaskStackListener {
        private TaskStackListenerImpl() {
        }

        public void onTaskRemoved(int taskId) throws RemoteException {
            VrDesktopContainer.this.checkDisplayState();
        }
    }
}
