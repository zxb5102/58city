package dbtest;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class DbTest {

	@Test
	public void f2(){
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = managerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Student student = new Student();
		student.setDate(new Date());
		student.setName("xm");
		Other other = new Other();
		other.setCity("Beijing");
		other.setHobby("swing");
		student.setOther(other);
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Test
	public void f(){
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Student student = new Student();
		student.setDate(new Date());
		student.setName("xm");
		Other other = new Other();
		other.setCity("BeiJing");
		other.setHobby("swing");
//		student.setOther(other);
		session.save(student);
		
		session.getTransaction().commit();
	}
}
