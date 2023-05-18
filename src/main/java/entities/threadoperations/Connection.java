package entities.threadoperations;

public class Connection {
    private final int CONNECTIONID;
    private boolean inUse;

    public Connection(int connectionId) {
        this.CONNECTIONID = connectionId;
        this.inUse = true;
    }

    public int getConnectionId() {
        return CONNECTIONID;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
