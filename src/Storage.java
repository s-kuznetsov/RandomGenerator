import java.util.ArrayList;

public class Storage {

    private ArrayList<Long> list = new ArrayList<>();
    private volatile boolean stop;

    public ArrayList<Long> getList() {
        return list;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
