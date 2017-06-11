package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.collect.specific.Specific;
import com.collect.specific.bean.HouseMsg;

public class TestDb {

	@Test
	public void f() throws IOException{
		String url = "http://nc.58.com/zufang/30303771936427x.shtml?psid=113561031196235696843122979&ClickID=2&cookie=|||&PGTID=0d300008-0029-d1b9-6e43-5c1af3880976&apptype=0&entinfo=30303771936427_0&fzbref=0&iuType=gz_2&pubid=12927423&local=669&trackkey=30303771936427_8a1810dc-4eb0-4008-b6b1-8a81dcebe72e_20170611133921_1497159561267&fcinfotype=gz";
		ArrayList<String> list = new ArrayList<String>();
		list.add(url);
		Specific specific = new Specific();
		ArrayList<HouseMsg> execute = specific.execute(list);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Iterator<HouseMsg> iterator = execute.iterator();
		while(iterator.hasNext()){
			HouseMsg next = iterator.next();
			entityManager.persist(next);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
