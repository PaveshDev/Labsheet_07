package application.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Very small singleton that mimics access to shared in-memory data.
 * All repositories request their collections through this class to
 * demonstrate controlled global access.
 */
public final class DatabaseConnection {
    private static DatabaseConnection instance;
    private final Map<String, List<?>> tables = new HashMap<>();

    private DatabaseConnection() {
        System.out.println("[Singleton] DatabaseConnection initialized");
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public synchronized <T> List<T> getTable(String tableName) {
        return (List<T>) tables.computeIfAbsent(tableName, key -> new ArrayList<>());
    }
}
