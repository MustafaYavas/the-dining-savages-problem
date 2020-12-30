import java.util.concurrent.Semaphore;

public class Savages extends Thread{
    private MutableInteger remainingServing;
    private Semaphore mutex;
    private Semaphore emptyPot;
    private Semaphore fullPot;
    private String savageName;

    public Savages(MutableInteger remainingServing, Semaphore mutex, Semaphore emptyPot, Semaphore fullPot, String savageName){
        this.remainingServing = remainingServing;
        this.mutex = mutex;
        this.emptyPot = emptyPot;
        this.fullPot = fullPot;
        this.savageName = savageName;
    }

    public void run(){
        while (true){
        System.out.println(savageName+" is checking whether there is meal");
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(remainingServing.getServingCount() == 0){
            emptyPot.release();     // no food in pot. signal the cook
            try {
                fullPot.acquire();  // dinner is ready
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            remainingServing.setServingCount(remainingServing.getServingCount());
        }

        getServingFromPot();

        mutex.release();

        eat();
        }
    }

    public void getServingFromPot(){
        remainingServing.setServingCount(remainingServing.getServingCount()-1);
        System.out.println(savageName+" is preparing serving for yourself - remaining servings in pot: "+remainingServing.getServingCount());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eat(){
        System.out.println(savageName+" is eating");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
