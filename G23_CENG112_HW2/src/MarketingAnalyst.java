public class MarketingAnalyst<T> implements IProduct{
	private int sizeOfQ;
	private int previousBackIndex;
	ProductQueue<T> pqu = new ProductQueue<T>();
		public void toQueue(T kam) {
			if(sizeOfQ!=pqu.getBack()) {
			previousBackIndex=pqu.getBack();
			pqu.enqueue(kam);}
			if(isManufactured() || pqu.getBack()==0)
				System.out.println("Marketing Analyst requesting "+((Product) kam).getName()+" , SUCCESS , "+((Product) kam).getName()+" manufactured");
			else
				System.out.println("Marketing Analyst requesting "+((Product) kam).getName()+" , FAIL , "+((Product) kam).getName()+" cannot manufactured");}
	public void setSize(int size) {
		sizeOfQ=size;}
	public boolean isStored() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isSold() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isManufactured() {
		if(previousBackIndex<pqu.getBack())
			return true;
		else
			return false;
	}

	public void reportQ() {
		int ram=0;
		int cac=0;
		int gr=0;
		int cpu=0;
		int mot=0;
		for(int i=0;i<pqu.getQu().length;i++) {
			if(pqu.getQu()[i]!=null) {
				Product looker = (Product) pqu.dequeue();
				if(looker.getName()=="RAM")
					ram++;
				if(looker.getName()=="Cache")
					cac++;
				if(looker.getName()=="Graphics Card")
					gr++;
				if(looker.getName()=="CPU")
					cpu++;
				if(looker.getName()=="Motherboard")
					mot++;
			}}
		System.out.println("Amount of Ram in factory line: "+ram);
		System.out.println("Amount of CPU in factory line: "+cpu);
		System.out.println("Amount of Graphics Card in factory line: "+gr);
		System.out.println("Amount of Motherboard in factory line: "+mot);
		System.out.println("Amount of Cache in factory line: "+cac);
		
	}

}
