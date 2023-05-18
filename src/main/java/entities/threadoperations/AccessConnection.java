package entities.threadoperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AccessConnection {
    public static final Logger LOGGER = LogManager.getLogger(AccessConnection.class);

    public void connectionWithThreads() {
        ConnectionPool connectionPool = new ConnectionPool();
        Runnable runnable = () -> {
            try {
                LOGGER.info("Thread " + "::" + Thread.currentThread().getName()
                        + " got the connection " + connectionPool.getConnection().getConnectionId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable runnableWait = () -> {
            Connection connection = null;
            try {
                connection = connectionPool.getConnection();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LOGGER.info("Thread " + "::" + Thread.currentThread().getName() + " got the connection " + connection.getConnectionId());
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        };
        Thread threadConnection1 = new Thread(runnableWait, "threadConnection1");
        threadConnection1.start();
        Thread threadConnection2 = new Thread(runnableWait, "threadConnection2");
        threadConnection2.start();
        Thread threadConnection3 = new Thread(runnableWait, "threadConnection3");
        threadConnection3.start();
        Thread threadConnection4 = new Thread(runnableWait, "threadConnection4");
        threadConnection4.start();
        Thread threadConnection5 = new Thread(runnableWait, "threadConnection5");
        threadConnection5.start();
        Thread threadConnection6 = new Thread(runnable, "threadConnection6");
        threadConnection6.start();
        Thread threadConnection7 = new Thread(runnable, "threadConnection7");
        threadConnection7.start();
        Thread threadConnection8 = new Thread(runnable, "threadConnection8");
        threadConnection8.start();
        LOGGER.info("In main thread");
    }
}
