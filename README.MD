������jdk���ó�1.8�ᱨ�������������ó���1.7
ִ����Receiver����XML���ý�������ע�룬��������ע������ע��
<bean id ="receiver" class="com.command.receiver.Receiver">
		<property name="filmService" ref="filmServiceImpl"></property>
</bean>

ģ������� ����������
1.show 2.update 3.delete 4.insert 5.exit
��������   insert -single {�ֶ���1��ֵ1���ֶ���2��ֵ2}����id ������ݿ��޸�����¼ ���ܲ����ɹ�������ʧ�ܣ�����ʾ 
��������   insert -batch  [{title ��ֵ1��languageId��ֵ2��description��ֵ3},{title ��ֵ1��languageId��ֵ2��description��ֵ3},...]  �ֶα���λ title languageId description

ģ����ѯ   show -like XXX  ֻд��ģ����ѯtitle
����id��ѯ show -id XX

����id�޸� update -id {�ֶ���1��ֵ1���ֶ���2��ֵ2}�������id ��Ȼ�޸�ʧ�� ����ʾ

ɾ��       delete -id XX

��һ����mvn compile
�ڶ�����mvn exec:java -Dexec.mainClass="com.MainApp"


��������ִ�� ���裺
��һ����mvn compile
�ڶ�����mvn exec:java -Dexec.mainClass="com.App"
