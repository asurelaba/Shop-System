package entities.threadoperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class AccessConnection {
    public static final Logger LOGGER = LogManager.getLogger(AccessConnection.class);
    ConnectionPool connectionPool = new ConnectionPool();

    //Callable that does the same thing as runnable but with calling method performTransaction which results in a String
    class InnerCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            try {
                Connection connection = connectionPool.getConnection();
                LOGGER.info("Callable Thread " + "::" + Thread.currentThread().getName() + " got the connection " + connection.getConnectionId());
                Thread.sleep(100);
                String result = connection.performTransaction();
                connectionPool.releaseConnection(connection);
                return result;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void connectionWithThreads() {
        threadWithRunnable();
        threadWithExecutorService();
        useCompletableFuture();
    }

    public void threadWithRunnable() {

        Runnable runnable = () -> { //Runnable task that does not sleep
            try {
                LOGGER.info("Thread " + "::" + Thread.currentThread().getName()
                        + " got the connection " + connectionPool.getConnection().getConnectionId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        //Runnable task that sleeps for 10 seconds
        Runnable runnableWait = () -> {
            Connection connection = null;
            try {
                connection = connectionPool.getConnection();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LOGGER.info("Thread " + "::" + Thread.currentThread().getName() + " got the connection " + connection.getConnectionId());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        };

        //Using the new Thread () to create threads. The program is handling the thread management. Their status info is
        //hard to get.
        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            threadList.add(new Thread(runnableWait, "threadConnection" + i));
        }
        for (Thread thread : threadList) {
            thread.start();
        }
        ExecutorService e = Executors.newSingleThreadExecutor();
        e.execute(runnableWait);
        e.shutdown();
    }

    //Using Executor service to handle threads. Futures will hold the status and thier results of thread excecution.
    public void threadWithExecutorService() {
        ExecutorService es1 = Executors.newFixedThreadPool(7);
        Collection<InnerCallable> callableList = new ArrayList<>();
        for (int i : IntStream.range(0, 7).toArray()) {
            callableList.add(new InnerCallable());
        }
        try {
            LOGGER.info("Callable list size:: " + callableList.size());
            List<Future<String>> futuresList = es1.invokeAll(callableList);
            for (Future<String> future : futuresList) {
                LOGGER.info(future.get());
            }
            es1.shutdown();
            LOGGER.info("Executor service shutdown" + Thread.activeCount());
        } catch (InterruptedException interruptedException) {
            LOGGER.error(interruptedException.getMessage());
        } catch (ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void useCompletableFuture() {
        //List<CompletableFuture<String>> completableFutures = es1.invokeAll(callableList);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Connection connection = connectionPool.getConnection();
                LOGGER.info("Callable Thread " + "::" + Thread.currentThread().getName() + " got the connection " + connection.getConnectionId());
                Thread.sleep(100);
                String result = connection.performTransaction();
                connectionPool.releaseConnection(connection);
                return result;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        LOGGER.info("Result from completable future" + completableFuture.join());
    }
}
