package entities.threadoperations;

import java.util.Random;

public class Connection {
    private final int CONNECTIONID;

    public Connection(int connectionId) {
        this.CONNECTIONID = connectionId;
    }

    public int getConnectionId() {
        return CONNECTIONID;
    }

    public String performTransaction() {
        return "Transaction done with the DB. Result is:: " + new Random().nextInt();
    }
}
