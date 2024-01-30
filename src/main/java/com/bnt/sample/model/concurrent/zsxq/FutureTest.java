package com.bnt.sample.model.concurrent.zsxq;

import java.util.*;
import java.util.concurrent.*;

/**
 * future
 *
 * @author bnt
 * @version 1.0.0
 * @create 2024/1/29 15:14 bnt
 * @history
 */
public class FutureTest {

    /**
     * 描述：演示一个 Future 的使用方法
     */
    public static class OneFuture {
        public static void main(String[] args) {
            ExecutorService service = Executors.newFixedThreadPool(10);
            Future<Integer> future = service.submit(new CallableTask());
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            service.shutdown();
        }

    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            return new Random().nextInt();
        }
    }

    /**
     * 描述：演示get方法
     */
    public static class GetException {
        public static void main(String[] args) {
            ExecutorService service = Executors.newFixedThreadPool(20);
            Future<Integer> future = service.submit(new CallableTask2());
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println(i);
                    Thread.sleep(500);
                }
                System.out.println(future.isDone());
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class CallableTask2 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("Callable抛出异常");
        }
    }

    /**
     * 描述：演示 FutureTask 的用法 创建future
     */
    static class FutureTaskDemo {
        public static void main(String[] args) {
            Task task = new Task();
            FutureTask<Integer> integerFutureTask = new FutureTask<>(task);
            new Thread(integerFutureTask).start();
            try {
                System.out.println("运行结果:" + integerFutureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程正在计算");
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        }
    }

    /**
     * for循环批量获取future的结果容易block
     */
    static class FutureDemo {
        public static void main(String[] args) {
            ExecutorService service = Executors.newFixedThreadPool(10);
            ArrayList<Future> allFutures = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                if (i == 0 || i == 1) {
                    allFutures.add(service.submit(new SlowTask()));
                } else {
                    allFutures.add(service.submit(new FastTask()));
                }
            }

            for (int i = 0; i < 4; i++) {
                try {
                    System.out.println(allFutures.get(i).get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            service.shutdown();
        }
    }

    static class SlowTask implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            return "速度慢的任务";
        }
    }

    static class FastTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "速度快的任务";
        }
    }

    /**
     * 线程池实现“旅游平台”问题
     */
    static class ThreadPoolDemo {
        static ExecutorService threadPool = Executors.newFixedThreadPool(3);

        public static void main(String[] args) throws InterruptedException {
            ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();
            System.out.println(threadPoolDemo.getPrices());
        }

        private static Set<Integer> getPrices() throws InterruptedException {
            Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
            threadPool.submit(new Task(123, prices));
            threadPool.submit(new Task(456, prices));
            threadPool.submit(new Task(789, prices));
            // 超时等待3s
            Thread.sleep(3000);
            return prices;
        }

        private static class Task implements Runnable {
            Integer productId;

            Set<Integer> prices;

            public Task(Integer productId, Set<Integer> prices) {
                this.productId = productId;
                this.prices = prices;
            }

            @Override
            public void run() {
                int price = 0;

                try {
                    // 模拟获取平台价格耗时
                    Thread.sleep((long) (Math.random() * 4000));
                    price = (int) (Math.random() * 4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prices.add(price);
            }
        }
    }

    /**
     * CountDownLatchDemo实现“旅游平台”问题
     */
    static class CountDownLatchDemo {
        static ExecutorService threadPool = Executors.newFixedThreadPool(3);

        public static void main(String[] args) throws InterruptedException {
            CountDownLatchDemo countDownLatchDemo = new CountDownLatchDemo();
            System.out.println(countDownLatchDemo.getPrices());
        }

        private static Set<Integer> getPrices() throws InterruptedException {
            Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
            CountDownLatch countDownLatch = new CountDownLatch(3);
            threadPool.submit(new Task(123, prices, countDownLatch));
            threadPool.submit(new Task(456, prices, countDownLatch));
            threadPool.submit(new Task(789, prices, countDownLatch));
            // 如果线程已经执行了 countDown 方法，那么这个 await 方法就会立刻返回，不需要傻等到 3 秒钟
            countDownLatch.await(3, TimeUnit.SECONDS);
            return prices;
        }

        private static class Task implements Runnable {
            Integer productId;
            Set<Integer> prices;
            CountDownLatch countDownLatch;

            private Task(Integer productId, Set<Integer> prices, CountDownLatch countDownLatch) {
                this.productId = productId;
                this.prices = prices;
                this.countDownLatch = countDownLatch;
            }

            @Override
            public void run() {
                int price = 0;
                try {
                    Thread.sleep((long) (Math.random() * 4000));
                    price = (int) (Math.random() * 4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prices.add(price);
                countDownLatch.countDown();
            }
        }
    }

    /**
     * CompletableFuture 实现 “旅游平台”问题
     */
    static class CompletableFutureDemo {
        static ExecutorService threadPool = Executors.newFixedThreadPool(3);

        public static void main(String[] args) {
            CompletableFutureDemo completableFutureDemo = new CompletableFutureDemo();
            System.out.println(completableFutureDemo.getPrices());
        }

        private static Set<Integer> getPrices() {
            Set<Integer> integers = Collections.synchronizedSet(new HashSet<Integer>());
            CompletableFuture<Void> task1 = CompletableFuture.runAsync(new Task(123, integers));
            CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task(456, integers));
            CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task(789, integers));
            CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);
            try {
                // 如果在 3 秒钟之内这 3 个任务都可以顺利返回，则正常执行，否则抛异常
                allTasks.get(3, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return integers;
        }

        private static class Task implements Runnable {
            Integer productId;

            Set<Integer> prices;

            public Task(Integer productId, Set<Integer> prices) {
                this.productId = productId;
                this.prices = prices;
            }

            @Override
            public void run() {
                int price = 0;

                try {
                    // 模拟获取平台价格耗时
                    Thread.sleep((long) (Math.random() * 4000));
                    price = (int) (Math.random() * 4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prices.add(price);
            }
        }
    }

}
