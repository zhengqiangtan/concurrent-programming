package book2.chapter1;

/**
 * @Desc
 * @Created by tzq 2018/7/28 18:48
 **/
public class MainRun {
    public static void main(String[] args) {
        ListPool listPool = new ListPool();
       MyThread[] myThreads = new MyThread[12];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread(listPool);
        }
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i].start();

        }
    }
}
