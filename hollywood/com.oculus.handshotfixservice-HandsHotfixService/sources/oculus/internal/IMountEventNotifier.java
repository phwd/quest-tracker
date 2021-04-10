package oculus.internal;

public interface IMountEventNotifier {

    public interface Listener {
        void onSensorChanged(State state);
    }

    public enum State {
        DOFF,
        DON
    }

    boolean isListening();

    void registerListener(Listener listener);

    void startListening();

    void stopListening();
}
