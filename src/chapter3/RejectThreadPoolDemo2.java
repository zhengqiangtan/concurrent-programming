package chapter3;

import java.util.concurrent.*;

/**
 * 自定义线程池拒绝策略
 *
 * 我们知道，使用shutdownNow方法，可能会引起报错，使用shutdown方法可能会导致线程关闭不了。
 *
 * 所以当我们使用shutdownNow方法关闭线程池时，一定要对任务里进行异常捕获。
 *
 * 当我们使用shuwdown方法关闭线程池时，一定要确保任务里不会有永久阻塞等待的逻辑，否则线程池就关闭不了。
 *
 * 最后，一定要记得，shutdownNow和shuwdown调用完，线程池并不是立马就关闭了，要想等待线程池关闭，还需调用awaitTermination方法来阻塞等待。
 *
 *
 */
public class RejectThreadPoolDemo2 {

    static class MyTask implements Runnable {

        private String name;
        public MyTask(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("--- " + name);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[])  {


        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 2, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3)
              );


        pool.execute(new MyTask("task1"));
        pool.execute(new MyTask("task2"));
        pool.execute(new MyTask("task3"));
        pool.execute(new MyTask("task4"));
        pool.execute(new MyTask("task5"));
        pool.execute(new MyTask("task6"));


        try {
            System.out.println("正在关闭线程池！");
            pool.shutdown();

            if (!pool.awaitTermination(1,TimeUnit.SECONDS)){
                pool.shutdownNow();
            }
            System.out.println("线程池已关闭！");
        }catch (Exception e) {
            pool.shutdownNow();
            e.printStackTrace();
        }


    }
}
