package okhttp3.internal;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.connection.StreamAllocation;

public abstract class Internal {
    public static Internal instance;

    public abstract void addLenient(Headers.Builder builder, String str);

    public abstract void addLenient(Headers.Builder builder, String str, String str2);

    public abstract void apply(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z);

    public abstract int code(Response.Builder builder);

    public abstract boolean connectionBecameIdle(ConnectionPool connectionPool, RealConnection realConnection);

    public abstract Socket deduplicate(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation);

    public abstract boolean equalsNonHost(Address address, Address address2);

    public abstract RealConnection get(ConnectionPool connectionPool, Address address, StreamAllocation streamAllocation, Route route);

    public abstract void put(ConnectionPool connectionPool, RealConnection realConnection);

    public abstract RouteDatabase routeDatabase(ConnectionPool connectionPool);

    public abstract IOException timeoutExit(Call call, IOException iOException);
}
