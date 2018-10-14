public class RandonGenerator extends Thread {

    private final Storage storage;

    public RandonGenerator(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (!storage.isStop()) {
            synchronized (storage) {
                long g = (long) (100 * Math.random());
                if (!storage.isStop()) {
                    System.out.println("Рандомное число: " + g);
                    storage.getList().add(g);
                    storage.notify();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
