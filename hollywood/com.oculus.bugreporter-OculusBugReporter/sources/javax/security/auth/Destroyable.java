package javax.security.auth;

public interface Destroyable {
    default void destroy() throws DestroyFailedException {
        throw new DestroyFailedException();
    }

    default boolean isDestroyed() {
        return false;
    }
}
