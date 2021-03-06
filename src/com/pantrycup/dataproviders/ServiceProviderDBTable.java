package com.pantrycup.dataproviders;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.pantrycup.entities.Bookings;
import com.pantrycup.entities.CustomerUser;
import com.pantrycup.entities.ServiceProviderUser;

public class ServiceProviderDBTable
{
	private SessionFactory sessionFactory;
	
	public ServiceProviderDBTable()
	{
		Configuration configuration=new Configuration().configure();
		sessionFactory=configuration.buildSessionFactory();
	}
	
	public List<ServiceProviderUser> fetchAllServiceProviders()
	{
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery("from ServiceProviderUser");
		List<ServiceProviderUser> serviceProviderList=query.list();
		session.close();
		return serviceProviderList; 
	}
	public void saveServiceProvider(ServiceProviderUser serviceProviderUser)
	{
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.save(serviceProviderUser);
		transaction.commit();
		session.close();
	}

	public ServiceProviderUser findByUserName(String username) 
		{
			Session session=sessionFactory.openSession();
			Transaction transaction=session.beginTransaction();
			String hql = "select c from ServiceProviderUser c INNER JOIN c.userCredentials u WHERE u.no=c.no and u.username = '"+username+"'";
			System.out.println(hql);
			Query<ServiceProviderUser> query = session.createQuery(hql);
			List<ServiceProviderUser> results = query.list();
			if(results.size()==0)
			{
				session.close();
				return null;
			}
			else
			{
				session.close();
				return results.get(0);
			}
		}

	public ServiceProviderUser getServiceProviderByNo(long no)
	{
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		ServiceProviderUser serviceProviderUser =  (ServiceProviderUser) session.get(ServiceProviderUser.class, no);
		session.close();
		return serviceProviderUser; 
	}

	public boolean checkServiceProviderBookingAvailability(long no, LocalDateTime fromDateTime, LocalDateTime toDateTime)
	{
		boolean availability = true;
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		ServiceProviderUser serviceProviderUser =  (ServiceProviderUser) session.get(ServiceProviderUser.class, no);
		List<Bookings> bookingsList = serviceProviderUser.getBookings();
		for(Bookings booking: bookingsList)
		{
			if(availability && (fromDateTime.isAfter(booking.getFromDateTime()) || fromDateTime.isEqual(booking.getFromDateTime())))
			{
				if(toDateTime.isBefore(booking.getToDateTime()) || toDateTime.isEqual(booking.getToDateTime()))
				availability = false;
			}
		}
		session.close();
		return availability;
	}

	
	
	
}


