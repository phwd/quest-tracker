package libcore.net.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NetworkEventDispatcher {
    private static final NetworkEventDispatcher instance = new NetworkEventDispatcher();
    private final List listeners = new CopyOnWriteArrayList();

    public static NetworkEventDispatcher getInstance() {
        return instance;
    }

    protected NetworkEventDispatcher() {
    }

    public void addListener(NetworkEventListener networkEventListener) {
        if (networkEventListener != null) {
            this.listeners.add(networkEventListener);
            return;
        }
        throw new NullPointerException("toAdd == null");
    }
}
