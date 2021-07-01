import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();

//		main.addNewData();
//		main.addNewPhoto();
//		main.removePhoto();
//		main.addNewAlbum();
//		main.removeAlbum();
//		main.removeUser();
//		main.likePhoto();
//		main.unlikePhoto();

		main.printUsers();
		System.out.println("");
		main.printLikes();
		
		main.close();
	}
	
	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
	
	private void addNewData() {

		User user1 = new User();
		user1.setUserName("U¿ytkownik 3");
		user1.setJoinDate("28-02-2021");
		Album buty = new Album();
		buty.setAlbumName("Buty");
		buty.setAlbumDescription("Album z butami");
		user1.addAlbum(buty);
		Photo but = new Photo();
		but.setPhotoName("Prawy");
		but.setPhotoDate("01-01-2021");
		buty.addPhoto(but);
		

		Transaction transaction = session.beginTransaction();
		session.save(user1);
		transaction.commit();
	}
	
	private void removeUser() {
		
		Query user = session.createQuery("FROM User WHERE id= :id");		
		user.setLong("id", 3);
		User user1 = (User) user.uniqueResult();
		
		Transaction transaction = session.beginTransaction();
		session.delete(user1);
		transaction.commit();
	}
	
	private void addNewAlbum() {
		
		Query query = session.createQuery("FROM User WHERE id= :id");		
		query.setLong("id", 1);
		User user1 = (User) query.uniqueResult();
		
		Album psy = new Album();
		psy.setAlbumName("Labradory");
		psy.setAlbumDescription("Najlepsze labradory");
		
		user1.addAlbum(psy);
		
		Transaction transaction = session.beginTransaction();
		session.save(user1);
		transaction.commit();
	}
	
	private void removeAlbum() {
		
		Query album = session.createQuery("FROM Album WHERE id= :id");		
		album.setLong("id", 3);
		Album buty = (Album) album.uniqueResult();

		Transaction transaction = session.beginTransaction();
		session.delete(buty);
		transaction.commit();
	}
	
	
	private void addNewPhoto() {
		
		Query query = session.createQuery("FROM Album WHERE id= :id");		
		query.setLong("id", 1);
		
		Album koty = (Album) query.uniqueResult();
		
		Photo kot = new Photo();
		
		kot.setPhotoName("Zielony");
		kot.setPhotoDate("20-02-2021");
		
		koty.addPhoto(kot);
		
		Transaction transaction = session.beginTransaction();
		session.save(kot);
		transaction.commit();
	}
	
	private void removePhoto() {
		
		Query photo = session.createQuery("FROM Photo WHERE id= :id");		
		photo.setLong("id", 5);
		Photo kot = (Photo) photo.uniqueResult();

		Transaction transaction = session.beginTransaction();
		session.delete(kot);
		
		transaction.commit();
	}
	
	private void likePhoto() {
		
		Query user = session.createQuery("FROM User WHERE id= :id");		
		user.setLong("id", 3);
		User user1 = (User) user.uniqueResult();
		
		Query photo = session.createQuery("FROM Photo WHERE id= :id");		
		photo.setLong("id", 2);
		Photo kot = (Photo) photo.uniqueResult();
		
		user1.likePhoto(kot);
		
		Transaction transaction = session.beginTransaction();
		session.save(user1);
		transaction.commit();
	}
	
	private void unlikePhoto() {
		
		Query user = session.createQuery("FROM User WHERE id= :id");		
		user.setLong("id", 3);
		User user1 = (User) user.uniqueResult();
		
		Query photo = session.createQuery("FROM Photo WHERE id= :id");		
		photo.setLong("id", 2);
		Photo kot = (Photo) photo.uniqueResult();
		
		user1.unlikePhoto(kot);
		
		Transaction transaction = session.beginTransaction();
		session.save(user1);
		transaction.commit();
	}
	
	private void printUsers() {
		Criteria crit = session.createCriteria(User.class);
		List<User> users = crit.list();

		System.out.println("### Users");

		for (User user : users) {
			System.out.println(user);
			System.out.println("   ###Albums");
			for (Album album : user.getAlbums()) {
				System.out.println("   " + album);
				System.out.println("       >Photos:");
				for (Photo photo : album.getPhotos()) {
					System.out.println("       " + photo);
				}
			}
		}
	}
	
	private void printLikes() {
		Criteria crit = session.createCriteria(User.class);
		List<User> users = crit.list();

		System.out.println("### Users and likes");
		for (User user : users) {
			System.out.println(user);
			
			for (Photo photo : user.getPhotos()) {
				System.out.println("    " + photo);
			}
		}
	}
	
	
//	public void printLikes() {
//		
//		Criteria crit = session.createCriteria(User.class);
//		List<Photo> likes = crit.list();
//		
//		for (Photo like : likes) {
//			System.out.println(like);
//		}
//		
//	}
}
