package Program;
import Facilities.DistrubutionCenter;
import Facilities.Factory;
import Facilities.Owner;
import Facilities.Warehouse;
import Items.BoxedMilk;
import Items.Cacao;
import Items.Chocolate;
import Items.Cream;
import Items.Ingredient;
import Items.Milk;
import Items.Product;
import Items.Yeast;
import Items.Yogurt;

public class Operations implements IOperations{
	public Operations() {}
	public void buyOperation(Warehouse<Ingredient> warehouse, Owner sahip, Ingredient alınan) {
		if(alınan.getName().equals("Milk")) {//If bought one milk, adds to the warehouse milk deposit. 
			Milk tempAl = new Milk(alınan.getName(),alınan.getAmount());
			warehouse.getMilk().addQ(tempAl);
			warehouse.getMilk().removCap(tempAl.getAmount());//because added.
			if(warehouse.getMilk().getCapacity()<0) {//if we do not have enough space. take back the actions.
				warehouse.getMilk().removQ();
				warehouse.getMilk().addCap(tempAl.getAmount());
				sahip.getWallet().addMoney(tempAl.calculateCost());
				System.out.println("We do not enough space for this Ingredient!!");
			}
			else {
				System.out.println("You have bought succesfully!!");
				sahip.getWallet().giveMoney(tempAl.calculateCost());}
		}
		else if(alınan.getName().equals("Yeast")) {//same as above.
			Yeast tempAl = new Yeast(alınan.getName(),alınan.getAmount());
			warehouse.getYeast().addQ(tempAl);
			warehouse.getYeast().removCap(tempAl.getAmount());
			if(warehouse.getYeast().getCapacity()<0) {
				warehouse.getYeast().removQ();
				warehouse.getYeast().addCap(tempAl.getAmount());
				sahip.getWallet().addMoney(tempAl.calculateCost());
				System.out.println("We do not enough space for this Ingredient!!");
			}
			else {
				System.out.println("You have bought succesfully!!");
				sahip.getWallet().giveMoney(tempAl.calculateCost());}
		}
		else if(alınan.getName().equals("Cacao")) {//same as above.
			Cacao tempAl = new Cacao(alınan.getName(),alınan.getAmount());
			warehouse.getCacao().addQ(tempAl);
			warehouse.getCacao().removCap(tempAl.getAmount());
			if(warehouse.getCacao().getCapacity()<0) {
				warehouse.getCacao().addCap(tempAl.getAmount());
				warehouse.getCacao().removQ();
				sahip.getWallet().addMoney(tempAl.calculateCost());
				System.out.println("We do not enough space for this Ingredient!!");
			}
			else {
				System.out.println("You have bought succesfully!!");
				sahip.getWallet().giveMoney(tempAl.calculateCost());}
		}
		else if(alınan.getName().equals("Cream")) {//same as above.
			Cream tempAl = new Cream(alınan.getName(),alınan.getAmount());
			warehouse.getCream().addQ(tempAl);
			warehouse.getCream().removCap(tempAl.getAmount());
			if(warehouse.getCream().getCapacity()<0) {
				warehouse.getCream().addCap(tempAl.getAmount());
				warehouse.getCream().removQ();
				sahip.getWallet().addMoney(tempAl.calculateCost());
				System.out.println("We do not enough space for this Ingredient!!");
			}
			else {
				System.out.println("You have bought succesfully!!");
				sahip.getWallet().giveMoney(tempAl.calculateCost());}//takes the money of the costed item.
		}
		else {
			System.out.println("We do not have a storage tank for that Ingredient!!");
		}

	}
	public void sellOperation(DistrubutionCenter<Product> dtCenter, Owner sahip, Product satılan) {
		if(satılan.getName().equals("Boxed Milk")) {//same logic as above.
			BoxedMilk tempPrd = new BoxedMilk(satılan.getName(),satılan.getNumber());
			dtCenter.getBoxedMilk().addCap(tempPrd.getAmount());
			if(dtCenter.getBoxedMilk().isEmpty() || dtCenter.getBoxedMilk().getCapacity()<0) {//for selling we must have that product.
				dtCenter.getBoxedMilk().removCap(tempPrd.getAmount());
				sahip.getWallet().giveMoney(tempPrd.calculateCost());
				System.out.println("We do not have that amount Product!!");
			}
			else {
				System.out.println("You have sold succesfully!!");
				sahip.getWallet().addMoney(tempPrd.calculateCost());}
		}
		else if(satılan.getName().equals("Yogurt")) {
			Yogurt tempPrd = new Yogurt(satılan.getName(),satılan.getNumber()*2);
			dtCenter.getYogurt().addCap(tempPrd.getAmount());
			if(dtCenter.getYogurt().isEmpty() || dtCenter.getYogurt().getCapacity()<0) {
				dtCenter.getYogurt().removCap(tempPrd.getAmount());
				sahip.getWallet().giveMoney(tempPrd.calculateCost());
				System.out.println("We do not have that amount Product!!");
			}
			else {
				System.out.println("You have sold succesfully!!");
				sahip.getWallet().addMoney(tempPrd.calculateCost());}

		}
		else if(satılan.getName().equals("Chocolate")) {
			Chocolate tempPrd = new Chocolate(satılan.getName(),satılan.getNumber()*0.5);
			dtCenter.getChocolate().addCap(tempPrd.getAmount());
			if(dtCenter.getChocolate().isEmpty() || dtCenter.getChocolate().getCapacity()<0) {
				dtCenter.getChocolate().removCap(tempPrd.getAmount());
				sahip.getWallet().giveMoney(tempPrd.calculateCost());
				System.out.println("We do not have that amount Product!!");
			}
			else {
				System.out.println("You have sold succesfully!!");
				sahip.getWallet().addMoney(tempPrd.calculateCost());}

		}
		else {
			System.out.println("We do not have that kind of Product!!");
		}
	}
	public void produce(Product product, Factory<Ingredient,Product> factory){
	
		if(product.getName().equals("Boxed Milk")) {
			BoxedMilk tempPrd = new BoxedMilk(product.getName(),product.getNumber()*1);
			double lt = tempPrd.getAmount();
			if(factory.getMilk().getCapacity()+ lt > 10000) {//controls for ingredient deposits.
				System.out.println("We don't have enough milk for boxed milk.");
			}
			else if(factory.getBoxedMilk().getCapacity() -lt <0) {
				System.out.println("We don't have enough space for boxed milk.");
			}
			else {
				factory.getBoxedMilk().removCap(lt);
				factory.getMilk().addCap(lt);
				System.out.println("You have produced succesfully!!");
			}
		}
		
		
			else if(product.getName().equals("Yogurt")) {//2.2857142857142857142857142857143 milk+yeast
				//2.1428571428571428571428571428572 milk ,0.1428571428571428571428571428571‬ yeast
				//0.93750000000000000000000000000002‬ milk oran
				//0.06249999999999999999999999999998 yeast oran
				Yogurt tempPrd = new Yogurt(product.getName(),product.getNumber()*2); 
				double lt = tempPrd.getAmount();
				if((factory.getMilk().getCapacity()+ (0.9375)*lt >10000) || (factory.getYeast().getCapacity() + (0.06249)*lt >10000)) {
					System.out.println("We don't have enough milk or yeast for yogurt.");
				}
				else if(factory.getYogurt().getCapacity() - lt <0) {
					System.out.println("We don't have enough space for yogurt.");
				}
				else {
					factory.getYogurt().removCap(lt);
					factory.getMilk().addCap((0.9375)*lt);
					factory.getYeast().addCap((0.06249)*lt);
					System.out.println("You have produced succesfully!!");
				}
			}
		
				else if(product.getName().equals("Chocolate")) {
					Chocolate tempPrd = new Chocolate(product.getName(),product.getNumber()*0.5);
					double lt = tempPrd.getAmount();
					//0.625 orantı,   milk oran = 0.3125 ,   cacao oran = 0.1875‬ ,     crema oran = 0.125‬‬
					if((factory.getCream().getCapacity()+(0.1250)*lt >10000))
					{
						System.out.println("We don't have enough cream for Chocolate .");
					}
					else if((factory.getMilk().getCapacity()+(0.3125)*lt >10000)) {
						System.out.println("We don't have enough milk for Chocolate .");
					}
					else if((factory.getCacao().getCapacity()+(0.1875)*lt >10000 )) {
						System.out.println("We don't have enough cacao for Chocolate .");
					}
					else if(factory.getChocolate().getCapacity() -lt <0) {
						System.out.println("We don't have enough space for chocolate .");
					}
					else {
	
						factory.getChocolate().removCap(lt);
						factory.getMilk().addCap((0.3125)*lt);
						factory.getCacao().addCap((0.1875)*lt);
						factory.getCream().addCap((0.1250)*lt);
						System.out.println("You have produced succesfully!!");
					}}
				else {
					System.out.println("We do not have that like blueprint of the product that you are looking for!!");
				}	
		}
}
