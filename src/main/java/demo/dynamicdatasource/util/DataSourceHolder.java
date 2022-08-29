package demo.dynamicdatasource.util;

public class DataSourceHolder {

    private final static ThreadLocal<String> contextDataSourceNameThreadLocal = new ThreadLocal<>();

    public static void setContextDataSourceName(String contextDataSourceName) {
        contextDataSourceNameThreadLocal.set(contextDataSourceName);
    }

    public static String getContextDataSourceName() {
        return contextDataSourceNameThreadLocal.get();
    }
}
