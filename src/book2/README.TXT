JAVA 并发编程核心方法与框架  不建议购买此书，凑字数很严重

2018年07月28日 - 08月02日
第一章  Semaphore & Exchanger的使用






常见的非阻塞队列：
1.ConcurrentHashMap & HashTable
    1）都支持并发操作，但是有什么不同？
       HashTable不支持在循环中remove元素

2. ConcurrentSkipListMap：并发安全性和支持排序
3. ConcurrentSkipListSet：支持排序且不允许重复
4. ConcurrentLinkedQueue：仅支持对列头进行操作，poll(),element(),peek()
5. ConcurrentLinkedDeque: 支持队列头尾双向操作
6. CopyOnWriteArrayList：线程安全的List
7. CopyOnWriteArraySet：解决多线程下HashSet的不安全问题


常用的阻塞队列：
1. ArrayBlockingQueue: 有界阻塞队列
2. PriorityBlockingQueue: 优先级队列
3. LinkedBlockingQueue: 无界队列
4. LinkedBlockingDeQue: 无界，支持对双端节点的操作
5. SynchronousQueue : 异步队列，没有容量（插入操作必须等待移除操作，反之亦然），用于多个线程间传输数据使用。
6. DelayQueue : 延时执行任务的队列。
