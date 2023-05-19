package entities.threadoperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionPool {
    public static final Logger LOGGER = LogManager.getLogger(AccessConnection.class);
    private ArrayBlockingQueue<Connection> pool;
    private ArrayBlockingQueue<Connection> inUseConnections;
    private int maxConnections;

    public ConnectionPool() {
        maxConnections = 5;
    }

    public synchronized Connection getConnection() throws InterruptedException {
        //System.out.println(Thread.currentThread().getName() + " is trying to obtain connection");
        if (pool == null) {
            pool = new ArrayBlockingQueue<>(maxConnections);
            for (int i = 0; i < maxConnections; i++) {
                Connection connection = new Connection(i);
                pool.add(connection);
            }
        }
        Connection connection = pool.take();
        if (inUseConnections == null) {
            inUseConnections = new ArrayBlockingQueue<>(maxConnections);
        }
        inUseConnections.add(connection);
        return connection;
    }

    public void releaseConnection(Connection connection) {
        inUseConnections.remove(connection);
        pool.add(connection);
        LOGGER.info(connection.getConnectionId() + "is released");
    }
}
