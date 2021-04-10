package java.sql;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public class DriverManager {
    static final SQLPermission SET_LOG_PERMISSION = new SQLPermission("setLog");
    private static volatile PrintStream logStream = null;
    private static final Object logSync = new Object();
    private static volatile PrintWriter logWriter = null;
    private static volatile int loginTimeout = 0;
    private static final CopyOnWriteArrayList<DriverInfo> registeredDrivers = new CopyOnWriteArrayList<>();

    static {
        loadInitialDrivers();
        println("JDBC DriverManager initialized");
    }

    private DriverManager() {
    }

    public static PrintWriter getLogWriter() {
        return logWriter;
    }

    public static void setLogWriter(PrintWriter out) {
        SecurityManager sec = System.getSecurityManager();
        if (sec != null) {
            sec.checkPermission(SET_LOG_PERMISSION);
        }
        logStream = null;
        logWriter = out;
    }

    @CallerSensitive
    public static Connection getConnection(String url, Properties info) throws SQLException {
        return getConnection(url, info, Reflection.getCallerClass());
    }

    @CallerSensitive
    public static Connection getConnection(String url, String user, String password) throws SQLException {
        Properties info = new Properties();
        if (user != null) {
            info.put("user", user);
        }
        if (password != null) {
            info.put("password", password);
        }
        return getConnection(url, info, Reflection.getCallerClass());
    }

    @CallerSensitive
    public static Connection getConnection(String url) throws SQLException {
        return getConnection(url, new Properties(), Reflection.getCallerClass());
    }

    @CallerSensitive
    public static Driver getDriver(String url) throws SQLException {
        println("DriverManager.getDriver(\"" + url + "\")");
        Class<?> callerClass = Reflection.getCallerClass();
        Iterator<DriverInfo> it = registeredDrivers.iterator();
        while (it.hasNext()) {
            DriverInfo aDriver = it.next();
            if (isDriverAllowed(aDriver.driver, callerClass)) {
                try {
                    if (aDriver.driver.acceptsURL(url)) {
                        println("getDriver returning " + aDriver.driver.getClass().getName());
                        return aDriver.driver;
                    }
                } catch (SQLException e) {
                }
            } else {
                println("    skipping: " + aDriver.driver.getClass().getName());
            }
        }
        println("getDriver: no suitable driver");
        throw new SQLException("No suitable driver", "08001");
    }

    public static synchronized void registerDriver(Driver driver) throws SQLException {
        synchronized (DriverManager.class) {
            if (driver != null) {
                registeredDrivers.addIfAbsent(new DriverInfo(driver));
                println("registerDriver: " + ((Object) driver));
            } else {
                throw new NullPointerException();
            }
        }
    }

    @CallerSensitive
    public static synchronized void deregisterDriver(Driver driver) throws SQLException {
        synchronized (DriverManager.class) {
            if (driver != null) {
                println("DriverManager.deregisterDriver: " + ((Object) driver));
                DriverInfo aDriver = new DriverInfo(driver);
                if (!registeredDrivers.contains(aDriver)) {
                    println("    couldn't find driver to unload");
                } else if (isDriverAllowed(driver, Reflection.getCallerClass())) {
                    registeredDrivers.remove(aDriver);
                } else {
                    throw new SecurityException();
                }
            }
        }
    }

    @CallerSensitive
    public static Enumeration<Driver> getDrivers() {
        Vector<Driver> result = new Vector<>();
        Class<?> callerClass = Reflection.getCallerClass();
        Iterator<DriverInfo> it = registeredDrivers.iterator();
        while (it.hasNext()) {
            DriverInfo aDriver = it.next();
            if (isDriverAllowed(aDriver.driver, callerClass)) {
                result.addElement(aDriver.driver);
            } else {
                println("    skipping: " + aDriver.getClass().getName());
            }
        }
        return result.elements();
    }

    public static void setLoginTimeout(int seconds) {
        loginTimeout = seconds;
    }

    public static int getLoginTimeout() {
        return loginTimeout;
    }

    @Deprecated
    public static void setLogStream(PrintStream out) {
        SecurityManager sec = System.getSecurityManager();
        if (sec != null) {
            sec.checkPermission(SET_LOG_PERMISSION);
        }
        logStream = out;
        if (out != null) {
            logWriter = new PrintWriter(out);
        } else {
            logWriter = null;
        }
    }

    @Deprecated
    public static PrintStream getLogStream() {
        return logStream;
    }

    public static void println(String message) {
        synchronized (logSync) {
            if (logWriter != null) {
                logWriter.println(message);
                logWriter.flush();
            }
        }
    }

    private static boolean isDriverAllowed(Driver driver, Class<?> caller) {
        return isDriverAllowed(driver, caller != null ? caller.getClassLoader() : null);
    }

    private static boolean isDriverAllowed(Driver driver, ClassLoader classLoader) {
        if (driver == null) {
            return false;
        }
        Class<?> aClass = null;
        boolean result = true;
        try {
            aClass = Class.forName(driver.getClass().getName(), true, classLoader);
        } catch (Exception e) {
        }
        if (aClass != driver.getClass()) {
            result = false;
        }
        return result;
    }

    private static void loadInitialDrivers() {
        String drivers;
        try {
            drivers = (String) AccessController.doPrivileged(new PrivilegedAction<String>() {
                /* class java.sql.DriverManager.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public String run() {
                    return System.getProperty("jdbc.drivers");
                }
            });
        } catch (Exception e) {
            drivers = null;
        }
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class java.sql.DriverManager.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                Iterator driversIterator = ServiceLoader.load(Driver.class).iterator();
                while (driversIterator.hasNext()) {
                    try {
                        driversIterator.next();
                    } catch (Throwable th) {
                        return null;
                    }
                }
                return null;
            }
        });
        println("DriverManager.initialize: jdbc.drivers = " + drivers);
        if (!(drivers == null || drivers.equals(""))) {
            String[] driversList = drivers.split(":");
            println("number of Drivers:" + driversList.length);
            for (String aDriver : driversList) {
                try {
                    println("DriverManager.Initialize: loading " + aDriver);
                    Class.forName(aDriver, true, ClassLoader.getSystemClassLoader());
                } catch (Exception ex) {
                    println("DriverManager.Initialize: load failed: " + ((Object) ex));
                }
            }
        }
    }

    private static Connection getConnection(String url, Properties info, Class<?> caller) throws SQLException {
        ClassLoader callerCL = caller != null ? caller.getClassLoader() : null;
        synchronized (DriverManager.class) {
            if (callerCL == null) {
                callerCL = Thread.currentThread().getContextClassLoader();
            }
        }
        if (url != null) {
            println("DriverManager.getConnection(\"" + url + "\")");
            SQLException reason = null;
            Iterator<DriverInfo> it = registeredDrivers.iterator();
            while (it.hasNext()) {
                DriverInfo aDriver = it.next();
                if (isDriverAllowed(aDriver.driver, callerCL)) {
                    try {
                        println("    trying " + aDriver.driver.getClass().getName());
                        Connection con = aDriver.driver.connect(url, info);
                        if (con != null) {
                            println("getConnection returning " + aDriver.driver.getClass().getName());
                            return con;
                        }
                    } catch (SQLException ex) {
                        if (reason == null) {
                            reason = ex;
                        }
                    }
                } else {
                    println("    skipping: " + aDriver.getClass().getName());
                }
            }
            if (reason != null) {
                println("getConnection failed: " + ((Object) reason));
                throw reason;
            }
            println("getConnection: no suitable driver found for " + url);
            throw new SQLException("No suitable driver found for " + url, "08001");
        }
        throw new SQLException("The url cannot be null", "08001");
    }
}
