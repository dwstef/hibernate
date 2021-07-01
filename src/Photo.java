import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name="photos")
public class Photo implements java.io.Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String photoName;
	
	@Column
	private String photoDate;
//	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	
	public Photo() {
//		this.photoDate = formatter.format(LocalDateTime.now());
	}
	
	public Photo(String photoName, String photoDate) {
		this.photoName = photoName;
		this.photoDate = photoDate;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoDate() {
		return photoDate;
	}

	public void setPhotoDate(String photoDate) {
		this.photoDate = photoDate;
	}
	
	public String toString() {
		return "Photo: " + getPhotoName() + " (Photo date: " + getPhotoDate() + ")";
	}
	
	

}
