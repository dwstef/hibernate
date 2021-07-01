import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class User implements java.io.Serializable {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String userName;
	
	@Column
	private String joinDate;
//	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private Set<Album> albums = new HashSet<Album>();

	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	name="users_photos",
	joinColumns=@JoinColumn(name="user_id"),
	inverseJoinColumns=@JoinColumn(name="photo_id")
	)
	Set<Photo> likes = new HashSet<Photo>();

	public User() {
//		this.joinDate = formatter.format(LocalDateTime.now());
	}
	
	public User(String userName, String joinDate) {
		this.userName = userName;
		this.joinDate = joinDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void addAlbum(Album album) {
		albums.add(album);
	}

	public void removeAlbum(Album album) {
		albums.remove(album);
	}
	
	public Set<Photo> getPhotos() {
		return likes;
	}

	public void setPhotos(Set<Photo> photos) {
		this.likes = photos;
	}

	public void likePhoto(Photo photo) {
		likes.add(photo);
	}

	public void unlikePhoto(Photo photo) {
		likes.remove(photo);
	}
	
	public String toString() {
		return "User: " + getUserName() + " (Join date: " + getJoinDate() + ")";
	}

}
