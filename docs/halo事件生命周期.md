# Halo事件分析

## run.halo.app.infra.SchemeInitializer

对ApplicationContextInitializedEvent事件进行监听，用于创建SchemeManager和SchemeWatcherManager对象：

- SchemeManager：管理Scheme，包含注册、注销、获取Scheme；
- SchemeWatcherManager：管理SchemeWatcher，包含注册、注销、获取SchemeWatcher集合。当程序中的Scheme被注册/注销时，会遍历SchemeWatcherManager中的SchemeWatcher列表，逐个进行事件推送；

SchemeInitializer监听器是在容器尚未调用AbstractApplicationContext::refresh方法前需要被执行的，此时容器尚未初始化程序内@Componemt注解标记的Bean对象，因此需要在spring.factories文件中手动指定监听器来监听ApplicationContextInitializedEvent事件：

  ```properties
  # 用于监听ApplicationContextInitializedEvent事件
  org.springframework.context.ApplicationListener=run.halo.app.infra.SchemeInitializer
  ```

## run.halo.app.infra.ExtensionResourceInitializer

对ApplicationStartedEvent事件进行监听，用于加载类路径下extensions文件中的资源文件，并在成功完成资源加载后发布ExtensionInitializedEvent事件：

- 读取类路径下所有/extensions/*.y[a]ml配置文件，并解析为Unstructured资源

- 将读取的Unstructured资源与数据库中的资源对比：进行更新/保存操作

- 处理完所有的Unstructured资源后发布ExtensionInitializedEvent事件：通知DefaultControllerManager、GcControllerInitializer监听器进行后置处置：

  - 先调用GcControllerInitializer

  - 再调用DefaultControllerManager，按如下顺序启动协调器：

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

run.halo.app.event.post.PostPublishedEvent

run.halo.app.plugin.event.HaloPluginLoadedEvent

run.halo.app.plugin.event.HaloPluginStateChangedEvent

run.halo.app.plugin.event.HaloPluginStartedEvent

run.halo.app.plugin.event.PluginCreatedEvent