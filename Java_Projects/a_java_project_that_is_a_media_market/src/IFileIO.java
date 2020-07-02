import java.io.IOException;

public interface IFileIO {
	/**
	 * @return 's tree which has all medias from txt file by increasing order because it is binary search tree.
	 * @throws IOException because maybe our file input can be null.
	 */
	public IBinaryST<IMedia> readInventory() throws IOException;
}
