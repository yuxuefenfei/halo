# Halo Controller

用于控制协调器（Reconciler）的调度运行。

默认的实现类是`run.halo.app.extension.controller.DefaultController`，其构造方法为：

```java
public DefaultController(String name,
        Reconciler<R> reconciler,
        RequestQueue<R> queue,
        Synchronizer<R> synchronizer,
        Supplier<Instant> nowSupplier,
        Duration minDelay,
        Duration maxDelay,
        ExecutorService executor, int workerCount) {
        Assert.isTrue(workerCount > 0, "Worker count must not be less than 1");
        // 控制器的名称
        this.name = name;
        // 控制器持有的协调器
        this.reconciler = reconciler;
        this.nowSupplier = nowSupplier;
        this.queue = queue;
        this.synchronizer = synchronizer;
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
        this.executor = executor;
        this.workerCount = workerCount;
        this.workerCounter = new AtomicLong();
}
```

