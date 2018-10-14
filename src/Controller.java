import java.util.Collections;
import java.util.HashSet;

public class Controller extends Thread {

    private final Storage storage;

    public Controller(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (!storage.isStop()) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (storage) {
                HashSet<Long> helpSet = new HashSet<>();
                for (int i = 0; i < storage.getList().size(); i++) {
                    long l = storage.getList().get(i);
                    if (!helpSet.contains(l)) {
                        System.out.println("Количество раз числа " + l + " = " + Collections.frequency(storage.getList(), l));
                        if (Collections.frequency(storage.getList(), l) > 4) {
                            storage.setStop(true);
                            System.out.println("СТОП!");
                            break;
                        }
                    }
                    helpSet.add(l);
                }

                if (storage.isStop()) {
                    break;
                }

                try {
                    storage.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
