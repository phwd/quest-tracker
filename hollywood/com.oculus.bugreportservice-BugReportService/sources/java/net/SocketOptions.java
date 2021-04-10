package java.net;

public interface SocketOptions {
    Object getOption(int i);

    void setOption(int i, Object obj);
}
