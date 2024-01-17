1. run.halo.app.infra.SchemeInitializer
  对应用程序初始化过程中发布的ApplicationContextInitializedEvent事件进行监听。
  此时容器尚未调用AbstractApplicationContext::refresh方法初始化程序内@Componemt注解标记的Bean对象，因此需要在spring.factories文件中手动指定监听器来监听ApplicationContextInitializedEvent事件：

  ```properties
  # 用于监听ApplicationContextInitializedEvent事件
  org.springframework.context.ApplicationListener=run.halo.app.infra.SchemeInitializer
  ```

  SchemeInitializer用于创建SchemeManager和SchemeWatcherManager对象：

  SchemeManager：管理程序中的Scheme，包含注册、注销、获取Scheme；

  SchemeWatcherManager：管理程序中的SchemeWatcher，包含注册、注销、获取SchemeWatcher集合；当程序中的Scheme被注册、注销时，会遍历SchemeWatcherManager中持有的SchemeWatcher列表，逐个进行事件推送；

2. run.halo.app.infra.ExtensionInitializedEvent

  初始化扩展资源完毕后发布的事件。

  ExtensionResourceInitializer对ApplicationStartedEvent事件进行监听，在成功完成扩展资源加载后触发ExtensionInitializedEvent事件，其主要的功能是：

  

3. run.halo.app.event.post.PostPublishedEvent

4. run.halo.app.plugin.event.HaloPluginLoadedEvent

5. run.halo.app.plugin.event.HaloPluginStateChangedEvent

6. run.halo.app.plugin.event.HaloPluginStartedEvent

7. run.halo.app.plugin.event.HaloPluginStateChangedEvent

8. run.halo.app.plugin.event.PluginCreatedEvent

9. run.halo.app.plugin.event.HaloPluginLoadedEvent

10. run.halo.app.plugin.event.HaloPluginStateChangedEvent

11. run.halo.app.plugin.event.HaloPluginStartedEvent

12. run.halo.app.plugin.event.HaloPluginStateChangedEvent

13. run.halo.app.plugin.event.HaloPluginLoadedEvent

14. run.halo.app.plugin.event.HaloPluginStateChangedEvent

15. run.halo.app.plugin.event.HaloPluginStartedEvent

16. run.halo.app.plugin.event.HaloPluginStateChangedEvent

17. run.halo.app.plugin.event.HaloPluginLoadedEvent

18. run.halo.app.plugin.event.HaloPluginStateChangedEvent

19. run.halo.app.plugin.event.HaloPluginStartedEvent

20. run.halo.app.plugin.event.HaloPluginStateChangedEvent

21. run.halo.app.plugin.event.HaloPluginLoadedEvent

22. run.halo.app.plugin.event.HaloPluginStateChangedEvent

23. run.halo.app.plugin.event.HaloPluginStartedEvent

24. run.halo.app.plugin.event.HaloPluginStateChangedEvent

25. run.halo.app.plugin.event.HaloPluginLoadedEvent

26. run.halo.app.plugin.event.HaloPluginStateChangedEvent

27. run.halo.app.plugin.event.HaloPluginStartedEvent

27. run.halo.app.plugin.event.HaloPluginStateChangedEvent