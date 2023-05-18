package entities.threadoperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConnectionPool {
    public static final Logger LOGGER = LogManager.getLogger(AccessConnection.class);
    private static CopyOnWriteArrayList<Connection> pool;
    private int maxConnections;

    public ConnectionPool() {
        maxConnections = 5;
    }

    public synchronized Connection getConnection() throws InterruptedException {
        //System.out.println(Thread.currentThread().getName() + " is trying to obtain connection");
        if (pool == null) {
            pool = new CopyOnWriteArrayList<Connection>();
        }
        if (pool.size() == maxConnections) {
            List<Connection> freeConnections = pool.stream()
                    .filter(conn -> !conn.isInUse())
                    .toList();

            if (freeConnections.isEmpty()) {
                LOGGER.info(Thread.currentThread().getName() + " is waiting for connection");
                wait(1000);
                freeConnections = pool.stream()
                        .filter(conn -> !conn.isInUse())
                        .toList();
            }
            freeConnections.get(0).setInUse(true);
            return freeConnections.get(0);

        } else {
            Connection connection = new Connection(pool.size() + 1);
            pool.add(connection);
            return connection;
        }
    }

    public void releaseConnection(Connection connection) {
        connection.setInUse(false);
    }
}
