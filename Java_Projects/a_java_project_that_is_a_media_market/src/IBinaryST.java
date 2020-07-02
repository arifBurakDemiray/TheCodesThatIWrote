
public interface IBinaryST<T extends Comparable<? super T>> {
	/** It changes the data of the root
	 * @param rootData that is going to change the root.
	 */
	public void setTree(T rootData);
	/** this function changes the tree.
	 * @param rootData that is going to change the root.
	 * @param right that is going to change the right side of the tree.
	 * @param left that is going to change the left side of the tree.
	 */
	public void setTree(T rootData,BinaryST<T> right,BinaryST<T> left);
	/**
	 * 
	 * @return 's the data of the root node.
	 */
	public T getRootData();
	/**
	 * It looks for emptiness.
	 * @return 's true if the  tree empty or return's false if it is not.
	 */
	public boolean isEmpty();
	/**
	 * It clears the tree.
	 */
	public void clear();
	/**
	 * Gets a specific entry.
	 * @param entry that is going to be retrieved.
	 * @return it returns null if not found or return the found item.
	 */
	public T getEntry(T entry);
	/**
	 * It looks for a specific entry.
	 * @param entry that is going to be found
	 * @return 's  true if it is found.
	 */
	public boolean contains(T entry);
	/***
	 * 
	 * @param entry enters this method and it will be added by its attribute to the binary search tree 
	 * @return 's the existing object which is replaced by entry if they are same. Otherwise, it returns null.
	 */
	public T add(T entry);
	/**
	 * 
	 * @return 's the root of the tree.
	 */
	public BNode<T> getRoot();
	/**
	 * 
	 * @param root it alters the tree's root to the this root.
	 */
	public void setRoot(BNode<T> root);
	/**
	 * This method inorder traverses the tree.
	 */
	public void inorderTraverse();
}
