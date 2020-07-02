
public class Customer<T> {
	private Stack<Product> bought = new Stack<Product>();
	private Stack<T> temp = new Stack<T>();
	public Customer() {}
	public void buy(StorageChief<T> depo,Product product) {
		String nameOfP=((Product)product).getName();
		if(nameOfP=="RAM")
			temp=depo.stackRAM;
		if(nameOfP=="Cache")
			temp=depo.stackCAC;
		if(nameOfP=="CPU")
			temp=depo.stackCPU;
		if(nameOfP=="Motherboard")
			temp=depo.stackMOT;
		if(nameOfP=="Graphics Card")
			temp=depo.stackGraph;
		if(!temp.isEmpty()) {
			Product gone=(Product) temp.pop();
			bought.push(gone);
			System.out.println("Customer buying "+nameOfP+" , SUCCESS, Customer bought "+nameOfP);
		}
		else
			System.out.println("Customer buying "+nameOfP+" , FAIL, "+nameOfP+" warehouse is empty");
	}


	public void reportC() {
		int ram=0;
		int cac=0;
		int gr=0;
		int cpu=0;
		int mot=0;
		for(int i=-1;i>-(temp.getStack().length);i--) {
			if(!bought.isEmpty()) {
			Product looker = (Product) bought.pop();
				if(looker!=null) {
					if(looker.getName()=="RAM")
						ram++;
					if(looker.getName()=="Cache")
						cac++;
					if(looker.getName()=="Graphics Card")
						gr++;
					if(looker.getName()=="CPU")
						cpu++;
					if(looker.getName()=="Motherboard")
						mot++;}
				}}
			System.out.println("Amount of Ram sold: "+ram);
			System.out.println("Amount of CPU sold: "+cpu);
			System.out.println("Amount of Graphics Card sold: "+gr);
			System.out.println("Amount of Motherboard sold: "+mot);
			System.out.println("Amount of Cache sold: "+cac);
			
		}
	
}
