import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name="albums")
public class Album implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String albumName;
	
	@Column
	private String albumDescription;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="album_id")
	private Set<Photo> photos = new HashSet<Photo>();

	public Album() {
	}

	public Album(String albumName) {
		this.albumName = albumName;
	}
	
	public Album(String albumName, String albumDescription) {
		super();
		this.albumName = albumName;
		this.albumDescription = albumDescription;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumDescription() {
		return albumDescription;
	}

	public void setAlbumDescription(String albumDescritpion) {
		this.albumDescription = albumDescritpion;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	
	public Set<Photo> getPhotos(){
		return photos;
	}

	public void addPhoto(Photo photo) {
		photos.add(photo);
	}

	public void removePhoto(Photo photo) {
		photos.remove(photo);
	}
	
	public String toString() {
		return "Album: " + getAlbumName() + " - " + getAlbumDescription();
	}

}
