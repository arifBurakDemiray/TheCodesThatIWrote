
public class Movie implements IMedia{
	private int price;
	private int year;
	private String name;
	private String directorName;
	private String actressName;
	private String actorName; 
	public Movie(int price, int year, String name, String directorname, String actress, String actor) {
		this.price=price;
		this.actorName=actor;
		this.directorName=directorname;
		this.actressName=actress;
		this.year=year;
		this.name=name;
	}
	public String mediaName() {
		return this.name;
	}

	public String mediaType() {
		return "Movie";
	}
	public int mediaPrice() {
		return this.price;
	}
	public int mediaYear() {
		return this.year;
	}
	public String getMovieActress() {
		return this.actressName;
	}
	public String getMovieActor() {
		return this.actorName;
	}
	public String mediaProducer() {
		return this.directorName;
	}
	public int compareTo(IMedia media) {
		int comparison = 0;
		if(this.mediaPrice()<media.mediaPrice())
			comparison = -1;
		else if(this.mediaPrice()>media.mediaPrice())
			comparison=1;
		else
			comparison=0;
		return comparison;
	}

}
