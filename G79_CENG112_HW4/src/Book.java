
public class Book implements IMedia{
	private String authorName;
	private int price;
	private int year;
	private String name;
	public Book(int price, int year, String name, String author) {
		this.price=price;
		this.authorName=author;
		this.year=year;
		this.name=name;
	}
	public String mediaName() {
		return this.name;
	}
	public String mediaType() {
		return "Book";
	}
	public int mediaPrice() {
		return this.price;
	}
	public int mediaYear() {
		return this.year;
	}
	public String mediaProducer() {
		return this.authorName;
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
