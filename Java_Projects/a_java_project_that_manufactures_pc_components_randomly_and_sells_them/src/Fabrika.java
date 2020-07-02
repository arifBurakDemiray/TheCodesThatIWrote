import java.util.*;
public class Fabrika {

	public static void main(String[] args) throws NullPointerException {
		@SuppressWarnings("resource")
		Scanner want = new Scanner(System.in);
		System.out.print("Enter the number of random request cycles: ");
		int istek= want.nextInt();
		Random rand = new Random();
		MarketingAnalyst<Product> analiz = new MarketingAnalyst<Product>();
		StorageChief<Product> depocu = new StorageChief<Product>();
		Customer<Product> al�c� = new Customer<Product>();
		analiz.setSize(istek);
		Product product = null;
		Product RAM = new Product("RAM");
		Product Motherboard = new Product("Motherboard");
		Product CPU = new Product("CPU");
		Product Graphics_Card = new Product("Graphics Card");
		Product Cache = new Product("Cache");
		Object[] products = {RAM,Motherboard,CPU,Graphics_Card,Cache};
		int say�c�=1;
		for(int i=0;i<istek;i++) {
			int var1 = rand.nextInt(3);
			if(var1==0) {
				int var2 = rand.nextInt(5);
				product = (Product) products[var2];
				System.out.print(say�c�+".  ");
				say�c�++;
				analiz.toQueue(product);	
				
}
			else if(var1==1) {	
				System.out.print(say�c�+".  ");
				say�c�++;
				depocu.sentStack(analiz.pqu);
	
			}
			else if(var1==2) {
				int var2 = rand.nextInt(5);
				System.out.print(say�c�+".  ");
				say�c�++;
				
				product = (Product) products[var2];
				al�c�.buy(depocu, product);
			}
			else
				product = null;
		
		}
		System.out.println("");
		System.out.println("REPORT:");
		System.out.println("");
		analiz.reportQ();
		System.out.println("");
		depocu.reportW();
		System.out.println("");
		al�c�.reportC();
	}

}
