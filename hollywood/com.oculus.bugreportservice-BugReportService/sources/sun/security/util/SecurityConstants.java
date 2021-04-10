package sun.security.util;

import java.net.NetPermission;
import java.net.SocketPermission;
import java.security.AllPermission;
import java.security.SecurityPermission;

public final class SecurityConstants {
    public static final AllPermission ALL_PERMISSION = new AllPermission();
    public static final RuntimePermission CHECK_MEMBER_ACCESS_PERMISSION = new RuntimePermission("accessDeclaredMembers");
    public static final SecurityPermission CREATE_ACC_PERMISSION = new SecurityPermission("createAccessControlContext");
    public static final RuntimePermission CREATE_CLASSLOADER_PERMISSION = new RuntimePermission("createClassLoader");
    public static final RuntimePermission GET_CLASSLOADER_PERMISSION = new RuntimePermission("getClassLoader");
    public static final SecurityPermission GET_COMBINER_PERMISSION = new SecurityPermission("getDomainCombiner");
    public static final NetPermission GET_COOKIEHANDLER_PERMISSION = new NetPermission("getCookieHandler");
    public static final RuntimePermission GET_PD_PERMISSION = new RuntimePermission("getProtectionDomain");
    public static final SecurityPermission GET_POLICY_PERMISSION = new SecurityPermission("getPolicy");
    public static final NetPermission GET_PROXYSELECTOR_PERMISSION = new NetPermission("getProxySelector");
    public static final NetPermission GET_RESPONSECACHE_PERMISSION = new NetPermission("getResponseCache");
    public static final RuntimePermission GET_STACK_TRACE_PERMISSION = new RuntimePermission("getStackTrace");
    public static final SocketPermission LOCAL_LISTEN_PERMISSION = new SocketPermission("localhost:0", "listen");
    public static final RuntimePermission MODIFY_THREADGROUP_PERMISSION = new RuntimePermission("modifyThreadGroup");
    public static final RuntimePermission MODIFY_THREAD_PERMISSION = new RuntimePermission("modifyThread");
    public static final NetPermission SET_COOKIEHANDLER_PERMISSION = new NetPermission("setCookieHandler");
    public static final NetPermission SET_PROXYSELECTOR_PERMISSION = new NetPermission("setProxySelector");
    public static final NetPermission SET_RESPONSECACHE_PERMISSION = new NetPermission("setResponseCache");
    public static final NetPermission SPECIFY_HANDLER_PERMISSION = new NetPermission("specifyStreamHandler");
    public static final RuntimePermission STOP_THREAD_PERMISSION = new RuntimePermission("stopThread");
}
