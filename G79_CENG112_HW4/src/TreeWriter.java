
public class TreeWriter implements ITreeWriter{
	public TreeWriter() {}
	private void findLowestBook(BNode<IMedia> rootNode,BNode<IMedia> node, String name) {
		BNode<IMedia> tempNode=node;
		tempNode.setData(node.getData());
		if(rootNode!=null) {
			findLowestBook(rootNode.getLeft(),tempNode,name);
			findLowestBook(rootNode.getRight(),tempNode,name);
			if(rootNode.getData().mediaType()=="Book" && rootNode.getData().mediaProducer().equals(name) && rootNode.getData().mediaPrice()<tempNode.getData().mediaPrice()) {
				tempNode.setData(rootNode.getData());
			System.out.println(tempNode.getData().mediaName());}
		}}
	private void findLowestMovie(BNode<IMedia> rootNode,BNode<IMedia> node, String name) {
		BNode<IMedia> tempNode=node;
		tempNode.setData(node.getData());
		if(rootNode!=null) {
			findLowestMovie(rootNode.getLeft(),tempNode,name);
			findLowestMovie(rootNode.getRight(),tempNode,name);
			if(rootNode.getData().mediaType().equals("Movie") && rootNode.getData().mediaProducer().equals(name) && rootNode.getData().mediaPrice()<tempNode.getData().mediaPrice()) {
				tempNode.setData(rootNode.getData());
			System.out.println(tempNode.getData().mediaName());}
		}}
	private void findHighestBook(BNode<IMedia> rootNode,BNode<IMedia> node, String name) {
		BNode<IMedia> tempNode=node;
		tempNode.setData(node.getData());
		if(rootNode!=null) {
			findHighestBook(rootNode.getRight(),tempNode,name);
			findHighestBook(rootNode.getLeft(),tempNode,name);
			if(rootNode.getData().mediaType().equals("Book") && rootNode.getData().mediaProducer().equals(name) && rootNode.getData().mediaPrice()>tempNode.getData().mediaPrice()) {
				tempNode.setData(rootNode.getData());
			System.out.println(tempNode.getData().mediaName());}
		}}
	private void findHighestMovie(BNode<IMedia> rootNode,BNode<IMedia> node, String name) {
		BNode<IMedia> tempNode=node;
		tempNode.setData(node.getData());
		if(rootNode!=null) {
			findHighestMovie(rootNode.getRight(),tempNode,name);
			findHighestMovie(rootNode.getLeft(),tempNode,name);
			if(rootNode.getData().mediaType().equals("Movie") && rootNode.getData().mediaProducer().equals(name) && rootNode.getData().mediaPrice()>tempNode.getData().mediaPrice()) {
				tempNode.setData(rootNode.getData());
			System.out.println(tempNode.getData().mediaName());}
		}}
	private void lowPrice(int price,BNode<IMedia> node) {
		if(node!=null) {
			lowPrice(price,node.getLeft());
			if(node.getData().mediaPrice()<=price)
				System.out.println(node.getData().mediaName());
			lowPrice(price,node.getRight());
		}
	}
	private void upPrice(int price,BNode<IMedia> node) {
		if(node!=null) {
			upPrice(price,node.getLeft());
			if(node.getData().mediaPrice()>price)
				System.out.println(node.getData().mediaName());
			upPrice(price,node.getRight());
		}
	}
	private void ascendingOrder(BNode<IMedia> node) {
		if(node!=null) {
			ascendingOrder(node.getLeft());
			System.out.println(node.getData().mediaName());
			ascendingOrder(node.getRight());
		}
	}
	private void ascendingOrderBook(BNode<IMedia> node) {
		if(node!=null) {
			ascendingOrderBook(node.getLeft());
			if(node.getData().mediaType()=="Book")
				System.out.println(node.getData().mediaName());
			ascendingOrderBook(node.getRight());
		}
	}
	private void ascendingOrderMovie(BNode<IMedia> node) {
		if(node!=null) {
			ascendingOrderMovie(node.getLeft());
			if(node.getData().mediaType()=="Movie")
				System.out.println(node.getData().mediaName());
			ascendingOrderMovie(node.getRight());
		}
	}
	private void descendingOrder(BNode<IMedia> node) {
		if(node!=null) {
			descendingOrder(node.getRight());
			System.out.println(node.getData().mediaName());
			descendingOrder(node.getLeft());
		}
	}
	private void descendingOrderBook(BNode<IMedia> node) {
		if(node!=null) {
			descendingOrderBook(node.getRight());
			if(node.getData().mediaType()=="Book")
				System.out.println(node.getData().mediaName());
			descendingOrderBook(node.getLeft());
		}
	}
	private void descendingOrderMovie(BNode<IMedia> node) {
		if(node!=null) {
			descendingOrderMovie(node.getRight());
			if(node.getData().mediaType()=="Movie")
				System.out.println(node.getData().mediaName());
			descendingOrderMovie(node.getLeft());
		}
	}
	public void desasPrinter(IBinaryST<IMedia> treeOfMedias) {
		System.out.println("----------------------------------------------------");
		System.out.println("All medias in descending order in terms of the price");
		System.out.println("----------------------------------------------------");
		descendingOrder(treeOfMedias.getRoot());
		System.out.println("----------------------------------------------------");
		System.out.println("All medias in ascending order in terms of the price");
		System.out.println("----------------------------------------------------");
		ascendingOrder(treeOfMedias.getRoot());
		System.out.println("----------------------------------------------------");
		System.out.println("All books in descending order in terms of the price");
		System.out.println("----------------------------------------------------");
		ascendingOrderBook(treeOfMedias.getRoot());
		System.out.println("----------------------------------------------------");
		System.out.println("All books in ascending order in terms of the price");
		System.out.println("----------------------------------------------------");
		descendingOrderBook(treeOfMedias.getRoot());
		System.out.println("----------------------------------------------------");
		System.out.println("All movies in descending order in terms of the price");
		System.out.println("----------------------------------------------------");
		ascendingOrderMovie(treeOfMedias.getRoot());
		System.out.println("----------------------------------------------------");
		System.out.println("All movies in ascending order in terms of the price");
		System.out.println("----------------------------------------------------");
		descendingOrderMovie(treeOfMedias.getRoot());
	}
	public void limitPrice(IBinaryST<IMedia> treeOfMedias,int price) {
		System.out.println("----------------------------------------------------");
		System.out.println("All below medias by limited price that you enter");
		System.out.println("----------------------------------------------------");
		lowPrice(price,treeOfMedias.getRoot());
		System.out.println("----------------------------------------------------");
		System.out.println("All above medias by limited price that you enter");
		System.out.println("----------------------------------------------------");
		upPrice(price,treeOfMedias.getRoot());
	}
	public void theMostMedias(IBinaryST<IMedia> treeOfMedias,String name,String messenger) {
		IMedia temp = new Book(1000,0,"name","aut");
		BNode<IMedia> temp1 = new BNode<IMedia>(temp);
		int controller=0;
		if(messenger=="b") {
			System.out.println("----------------------------------------------------");
			System.out.println(name+"'s book that is the minimum priced book");
			System.out.println("----------------------------------------------------");
			System.out.println();
			findLowestBook(treeOfMedias.getRoot(),temp1,name);
			System.out.println();
			System.out.println("----------------------------------------------------");
			System.out.println(name+"'s book that is the maximum priced book");
			System.out.println("----------------------------------------------------");
			if(controller==0) {
				temp = new Book(0,0,"name","aut");
				System.out.println();
				findHighestBook(treeOfMedias.getRoot(),temp1,name);
				}
		}
		else {
			System.out.println("----------------------------------------------------");
			System.out.println(name+"'s movie that is the minimum priced movie");
			System.out.println("----------------------------------------------------");
			System.out.println();
			findLowestMovie(treeOfMedias.getRoot(),temp1,name);
			System.out.println();
			System.out.println("----------------------------------------------------");
			System.out.println(name+"'s movie that is the maximum priced movie");
			System.out.println("----------------------------------------------------");
			if(controller==0) {
				temp = new Book(0,0,"name","aut");
				System.out.println();
				findHighestMovie(treeOfMedias.getRoot(),temp1,name);
				}
		}
		
	}
}
