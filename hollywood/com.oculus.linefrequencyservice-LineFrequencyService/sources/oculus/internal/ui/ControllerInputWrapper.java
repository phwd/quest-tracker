package oculus.internal.ui;

public class ControllerInputWrapper extends VrUiWrapper {
    public ControllerInputWrapper() {
    }

    public ControllerInputWrapper(NativeKeyEventListener listener) {
        super(listener);
    }

    public void setRecenterCallback(Runnable recenterRunnable) {
        VrBase.setRecenterCallback(recenterRunnable);
    }
}
