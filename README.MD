我这里jdk设置成1.8会报错，所以我设置成了1.7
执行者Receiver是以XML配置进行依赖注入，其它的是注解依赖注入
<bean id ="receiver" class="com.command.receiver.Receiver">
		<property name="filmService" ref="filmServiceImpl"></property>
</bean>

模拟命令窗口 以命令运行
1.show 2.update 3.delete 4.insert 5.exit
单个插入   insert -single {字段名1：值1，字段名2：值2}包含id 如果数据库无该条记录 则能操作成功，否则失败，并提示 
批量插入   insert -batch  [{title ：值1，languageId：值2，description：值3},{title ：值1，languageId：值2，description：值3},...]  字段必须位 title languageId description

模糊查询   show -like XXX  只写了模糊查询title
更据id查询 show -id XX

更据id修改 update -id {字段名1：值1，字段名2：值2}必需包含id 不然修改失败 并提示

删除       delete -id XX

第一步：mvn compile
第二步：mvn exec:java -Dexec.mainClass="com.MainApp"


不用命令执行 步骤：
第一步：mvn compile
第二步：mvn exec:java -Dexec.mainClass="com.App"

