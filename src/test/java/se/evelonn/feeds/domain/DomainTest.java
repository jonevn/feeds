package se.evelonn.feeds.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

public abstract class DomainTest {

	protected Session session;
	private Transaction transaction;
	private SessionFactory sessionFactory;

	@Before
	public void setup() {
		sessionFactory = createSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}

	@After
	public void after() {
		transaction.rollback();
		session.close();
		sessionFactory.close();
	}

	private SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		return configuration
				.addAnnotatedClass(AtomCategory.class)
				.addAnnotatedClass(AtomCommonAttributes.class)
				.addAnnotatedClass(AtomEntry.class)
				.addAnnotatedClass(AtomFeed.class)
				.addAnnotatedClass(AtomGenerator.class)
				.addAnnotatedClass(AtomLink.class)
				.addAnnotatedClass(AtomPerson.class)
				.addAnnotatedClass(AtomSource.class)
				.addAnnotatedClass(Event.class)
				.setProperty("hibernate.hbm2ddl.auto", "create")
				.buildSessionFactory(
						new ServiceRegistryBuilder()
								.applySetting("hibernate.dialect",
										"org.hibernate.dialect.H2Dialect")
								.applySetting(
										"hibernate.connection.driver_class",
										"org.h2.Driver")
								.applySetting("hibernate.connection.url",
										"jdbc:h2:mem:")
								.applySetting("hibernate.hbm2ddl.auto",
										"create")
								.applySetting(
										"hibernate.archive.autodetection",
										"class").buildServiceRegistry());
	}
}