package book2.chapter1;

import chapter3.ReenterLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Desc 1.1.14
 * @Created by tzq 2018/7/28 18:23
 **/
public class ListPool {

    private Semaphore semaphore = new Semaphore(5);
    private List<String> list = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public ListPool() {
        super();
        for (int i = 0; i < 3; i++) {
            list.add("aaa" + (i+1));
        }
    }


    public  String get() {
        String str = null;
        try {
            semaphore.acquire();
            lock.lock();
            while (list.size() == 0) {
                condition.await();
            }
            str = list.remove(0);
            lock.unlock();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return str;
    }

    public void put(String  str) {
        lock.lock();
        list.add(str);
        condition.signalAll();
        lock.unlock();
        semaphore.release();

    }

}
class MyThread extends Thread {
    private ListPool listPool;
    public  MyThread(ListPool listPool) {
        super();
        this.listPool = listPool;
    }



    @Override
    public void run() {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = listPool.get();
            System.out.println(Thread.currentThread().getName() + "  get value: " + str);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listPool.put(str);
        }
    }
}