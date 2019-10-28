
public interface IMedia extends Comparable<IMedia>{
	/**
	 * 
	 * @return 's the names of medias.
	 */
	public String mediaName();
	/**
	 * 
	 * @return 's the classes of medias.(Book or Movie)
	 */
	public String mediaType();
	/**
	 * 
	 * @return 's the prices of medias.
	 */
	public int mediaPrice();
	/**
	 * 
	 * @return 's the years that medias published. 
	 */
	public int mediaYear(); 
	/**
	 * 
	 * @return 's the authors/directors of medias.
	 */
	public String mediaProducer();
	/**
	 * @return this method extends from comparable interface. 
	 * It take a media and it contrasts by their price.
	 */
	public int compareTo(IMedia media);
}
