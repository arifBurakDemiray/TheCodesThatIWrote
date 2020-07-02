
public interface ITreeWriter {
	/**
	 * @param treeOfMedias it takes a tree. It writes decreasing and increasing order of medias,movies and books.
	 * It has 6 methods in it.
	 */
	public void desasPrinter(IBinaryST<IMedia> treeOfMedias);
	/**
	 * @param treeOfMedias it takes a tree. 
	 * @param price it takes price and look for the limit. 
	 * This method writes below and above limits at the same time.
	 */
	public void limitPrice(IBinaryST<IMedia> treeOfMedias,int price);
	/**
	 * 
	 * @param treeOfMedias it takes a tree
	 * @param name it takes author/director name.
	 * @param messenger in loop it takes this because it can alter whatever the client wants.(Book or Movie)
	 */
	public void theMostMedias(IBinaryST<IMedia> treeOfMedias,String name,String messenger);
}
