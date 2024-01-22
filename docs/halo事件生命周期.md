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

  ExtensionResourceInitializer对ApplicationStartedEvent事件进行监听，在成功完成扩展资源加载后触发ExtensionInitializedEvent事件，ExtensionResourceInitializer逻辑：

- 读取类路径下所有/extensions/*.y[a]ml配置文件，并解析为Unstructured资源

- 将读取的Unstructured资源与数据库中的资源对比，不存在的资源入库保存，已存在的资源入库更新

- 处理完所有的Unstructured资源后发布ExtensionInitializedEvent事件，通知DefaultControllerManager、GcControllerInitializer监听器进行后置处置DefaultControllerManager负责对ExtensionInitializedEvent事件进行监听，在扩展资源初始化完成后，启动垃圾收集器控制器（默认启动10个）
  DefaultControllerManager负责加载通用的控制器，默认包含如下：

  `run.halo.app.core.extension.reconciler.RoleBindingReconciler`
  `run.halo.app.core.extension.reconciler.attachment.AttachmentReconciler`
  `run.halo.app.core.extension.reconciler.SystemSettingReconciler`
  `run.halo.app.core.extension.reconciler.MenuItemReconciler`
  `run.halo.app.core.extension.reconciler.CommentReconciler`
  `run.halo.app.core.extension.reconciler.PluginReconciler`
  `run.halo.app.migration.BackupReconciler`
  `run.halo.app.core.extension.reconciler.CategoryReconciler`
  `run.halo.app.core.extension.reconciler.PostReconciler`
  `run.halo.app.core.extension.reconciler.AnnotationSettingReconciler`
  `run.halo.app.core.extension.reconciler.UserReconciler`
  `run.halo.app.core.extension.reconciler.ReverseProxyReconciler`
  `run.halo.app.core.extension.reconciler.TagReconciler`
  `run.halo.app.core.extension.reconciler.ReplyReconciler`
  `run.halo.app.notification.NotificationTrigger`
  `run.halo.app.theme.router.SinglePageRoute`
  `run.halo.app.core.extension.reconciler.SinglePageReconciler`
  `run.halo.app.core.extension.reconciler.ThemeReconciler`
  `run.halo.app.core.extension.reconciler.RoleReconciler`
  `run.halo.app.core.extension.reconciler.AuthProviderReconciler`

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