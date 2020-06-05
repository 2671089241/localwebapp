package cn.pzj.test;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import cn.pzj.pojo.Dept;

public class HibernateTest {

	@Test
	public void testHibernate(){
		//1.创建配置对象
		Configuration configuration=new Configuration().configure("hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		Transaction ts=session.beginTransaction();
		
		SQLQuery query=session.createSQLQuery("select empno,ename from emp");
		
		List<Object[]> list=query.list();
		for (Object[] oa : list) {
			for (int i = 0; i < oa.length; i++) {
				System.out.println(oa[i].toString());
			}
		}
		
		ts.commit();
	}
	
	@Test
	public void testDept(){
		//1.创建配置对象，不指定配置文件，那么它会寻找classpath下面的默认配置文件命名：hibernate.cfg.xml
				Configuration configuration=new Configuration().configure("hibernate.cfg.xml");
				//2.通过配置对象创建SessionFactoy
				SessionFactory factory = configuration.buildSessionFactory();
				//3.使用factory对象获取一个Session
				Session session = factory.getCurrentSession();
				//4.开始一个事务
				Transaction ts=session.beginTransaction();
		
				Dept dept=(Dept) session.get(Dept.class, 10);
				
				System.out.println(dept.getName());
				
				System.out.println(dept.getLoc());
				
				ts.commit();
		
	}
	
}
