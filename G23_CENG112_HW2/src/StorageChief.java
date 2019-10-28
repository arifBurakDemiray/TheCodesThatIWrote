
public class StorageChief<T>{
	
	public Stack<T> stackRAM= new Stack<T>();
	public Stack<T> stackGraph= new Stack<T>();
	public Stack<T> stackCAC= new Stack<T>();
	public Stack<T> stackMOT= new Stack<T>();
	public Stack<T> stackCPU= new Stack<T>();
	
	public StorageChief() {
		
	}


	public void sentStack(ProductQueue<T> queue) throws NullPointerException {
		if(!queue.isEmpty()) {
		T product = queue.dequeue();
		String nameOfP=((Product)product).getName();
		if(nameOfP=="RAM") {
			stackRAM.push(product);
			System.out.println("Storage Chief storing "+nameOfP+" , SUCCESS , "+nameOfP+" in "+nameOfP+" warehouse");
		}
		else if(nameOfP=="Cache") {
			stackCAC.push(product);
			System.out.println("Storage Chief storing "+nameOfP+" , SUCCESS , "+nameOfP+" in "+nameOfP+" warehouse");
		}
		else if(nameOfP=="Motherboard") {
			stackMOT.push(product);
			System.out.println("Storage Chief storing "+nameOfP+" , SUCCESS , "+nameOfP+" in "+nameOfP+" warehouse");
		}
		else if(nameOfP=="Graphics Card") {
			stackGraph.push(product);
			System.out.println("Storage Chief storing "+nameOfP+" , SUCCESS , "+nameOfP+" in "+nameOfP+" warehouse");
		}
		else if(nameOfP=="CPU") {
			stackCPU.push(product);
			System.out.println("Storage Chief storing "+nameOfP+" , SUCCESS , "+nameOfP+" in "+nameOfP+" warehouse");
		}
		else
			System.out.println("");
		}
		else
			System.out.println("Storage Chief FAILED because factory line is Empty");}
		int ram=0;
		int cac=0;
		int gr=0;
		int cpu=0;
		int mot=0;
		public void reportW() {
			for(int i=0;i<stackRAM.getStack().length;i++) {
				if(stackRAM.getStack()[i]!=null)
					ram++;
				if(stackCAC.getStack()[i]!=null)
					cac++;
				if(stackGraph.getStack()[i]!=null)
					gr++;
				if(stackMOT.getStack()[i]!=null)
					mot++;
				if(stackCPU.getStack()[i]!=null)
					cpu++;
				
			}
			System.out.println("Amount of Ram in Ram Warehouse: "+ram);
			System.out.println("Amount of CPU in CPU Warehouse: "+cpu);
			System.out.println("Amount of Graphic Cards in Graphic Cards Warehouse: "+gr);
			System.out.println("Amount of Motherboard in Motherboard Warehouse: "+mot);
			System.out.println("Amount of Cache in Cache Warehouse: "+cac);
			
		}



}