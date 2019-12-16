package CargoElements;
import Facilities.DistrubutionCenter;
import Facilities.Factory;
import Facilities.Owner;
import Facilities.Warehouse;
import Items.*;
//The shipment processes are done in this class.
public class ARCargoTrack implements IARCargoTrack{
	public ARCargoTrack() {
		System.out.println("Your cargo will go out in 5 minutes!!");}
	
	public void toTheFactory(Warehouse<Ingredient> warehouse, Factory<Ingredient,Product> factory, Ingredient ingd, Owner sahip) {
		if(sahip.getWallet().getMoney()>=100) {//if owner has less than 100 TL we cannot do shipment because shipments costs 100 TL.
		if(ingd.getName().equals("Milk")  && !warehouse.getMilk().isEmpty()) {//For shipment processes, the ingd's storage at warehouse
			Milk tempMilk = new Milk(ingd.getName(),ingd.getAmount());   //must not be empty.
			if(10000-warehouse.getMilk().getCapacity()-tempMilk.getAmount()>=0) {//if we would take the ingd, storage's capacity must not be below zero.
				if(tempMilk.getAmount()<=1000) {//The wanted amount must be below zero because containers have 1000 lt capacity.
					ARCargo.tankIngredient.addItem(tempMilk);
					ARCargo.tankIngredient.removCap(tempMilk.getAmount());//why removing capacity because we are filling the capacities with items.
					if(factory.getMilk().lookFor(tempMilk.getAmount())>=0) {// The factory ingd's storage must not be full.
						factory.getMilk().removCap(tempMilk.getAmount());
						warehouse.getMilk().addCap(tempMilk.getAmount());
						sahip.getWallet().giveMoney(100);//shipment cost
						System.out.println("Your cargo has arrived!!");
						ARCargo.tankIngredient.empty();}
					else {
						System.out.println("We do not have enough space in Factory Milk Deposit!!");
						ARCargo.tankIngredient.empty();}}
				else {
					System.out.println("We do not enough space for our cargo container!!");}}
			else {
				System.out.println("We do not have that amount milk!!");}}
		else if(ingd.getName().equals("Yeast") && !warehouse.getYeast().isEmpty()) {// same as Milk block.
			Yeast tempYeast = new Yeast(ingd.getName(),ingd.getAmount());
			if(10000-warehouse.getYeast().getCapacity()-tempYeast.getAmount()>=0) {
			if(tempYeast.getAmount()<=1000) {
				ARCargo.openIngredient.addItem(tempYeast);
				ARCargo.openIngredient.removCap(tempYeast.getAmount());
				if(factory.getYeast().lookFor(tempYeast.getAmount())>=0) {
					factory.getYeast().removCap(tempYeast.getAmount());
					warehouse.getYeast().addCap(tempYeast.getAmount());
					sahip.getWallet().giveMoney(100);
					System.out.println("Your cargo has arrived!!");
					ARCargo.openIngredient.empty();}
				else {
					System.out.println("We do not have enough space in Factory Yeast Deposit!!");
					ARCargo.openIngredient.empty();}}
			else {
				System.out.println("We do not enough space for our cargo container!!");}}
		else {
			System.out.println("We do not have that amount yeast!!");}}
		else if(ingd.getName().equals("Cacao") && !warehouse.getCacao().isEmpty()) {// same as milk block.
			Cacao tempCacao = new Cacao("Cacao",ingd.getAmount());
			if(10000-warehouse.getCacao().getCapacity()-tempCacao.getAmount()>=0) {
			if(tempCacao.getAmount()<=1000) {
				ARCargo.openIngredient.addItem(tempCacao);
				ARCargo.openIngredient.removCap(tempCacao.getAmount());
				if(factory.getCacao().lookFor(tempCacao.getAmount())>=0) {
					factory.getCacao().removCap(tempCacao.getAmount());
					warehouse.getCacao().addCap(tempCacao.getAmount());
					sahip.getWallet().giveMoney(100);
					System.out.println("Your cargo has arrived!!");
					ARCargo.openIngredient.empty();}
				else {
					System.out.println("We do not have enough space in Factory Cacao Deposit!!");
					ARCargo.openIngredient.empty();}}
			else {
				System.out.println("We do not enough space for our cargo container!!");}}
		else {
			System.out.println("We do not have that amount cacao!!");}}
		else if(ingd.getName().equals("Cream") && !warehouse.getCream().isEmpty()) {// same as milk block.
			Cream tempCream = new Cream("Cream",ingd.getAmount());
			if(10000-warehouse.getCream().getCapacity()-tempCream.getAmount()>=0) {
			if(tempCream.getAmount()<=1000) {
				ARCargo.tankIngredient.addItem(tempCream);
				ARCargo.tankIngredient.removCap(tempCream.getAmount());
				if(factory.getCream().lookFor(tempCream.getAmount())>=0) {
					factory.getCream().removCap(tempCream.getAmount());
					warehouse.getCream().addCap(tempCream.getAmount());
					sahip.getWallet().giveMoney(100);
					System.out.println("Your cargo has arrived!!");
					ARCargo.tankIngredient.empty();}
				else {
					System.out.println("We do not have enough space in Factory Cream Deposit!!");
					ARCargo.tankIngredient.empty();}}
			else {
				System.out.println("We do not enough space for our cargo container!!");}}
		else {
			System.out.println("We do not have that amount yeast!!");}}
		else {
			System.out.println("We do not have this Ingredient that you are looking for");}}// user can enter non existing ingredient.
		else{
			System.out.println("You do not have enough money for shipment!!");}}//not have enough money for shipment.
	
	public void toTheDistCenter(DistrubutionCenter<Product> distcen, Factory<Ingredient,Product> factory, Object[] prd, Owner sahip) {
		if(sahip.getWallet().getMoney()>=100) {// same reason as above.
			double temp = 0;// all of their amounts.
			int index=prd.length-1;// when we reach 1000 capacity.
			for(int i=0;i<prd.length;i++) {
				Product prdct = (Product) prd[i];//why list because products are countable so we can fix them.
				temp+=prdct.getAmount();
				if(temp>1000) {
					temp-=prdct.getAmount();
					index=i;
					System.out.println("Because we have 1000 LT space we are going to ship that amount cargo!!");
					break;}}
			for(Object pr:prd) {
				Product pr1 = (Product) pr; //amount control for container's capacity. equality control for when we reach 1000 lt.
				if((prd.length-1==0 && pr1.getAmount()<=1000)|| !pr.equals(prd[index])) {// lenght control for 1 element lists.
					switch(pr1.getName()) {
					case "Boxed Milk":// save logic as above toTheFactory.
						BoxedMilk bxm = new BoxedMilk(pr1.getName(),pr1.getNumber());
						if(factory.getBoxedMilk().lookFor(bxm.getAmount())>=0) {
						ARCargo.productCon.addItem(bxm);
						ARCargo.productCon.removCap(bxm.getAmount());
						sahip.getWallet().giveMoney(100);
						if(distcen.getBoxedMilk().lookFor(bxm.getAmount())>=0) {
							distcen.getBoxedMilk().removCap(bxm.getAmount());
							factory.getBoxedMilk().addCap(bxm.getAmount());
							distcen.getBoxedMilk().addQ(bxm);
							System.out.println("Your cargo has arrived!!");}
						else {
							System.out.println("We do not have enough space in Distrubution Center Boxed Milk Deposit!!");
							ARCargo.productCon.empty();}}
						else {
							System.out.println("We do not have enough Boxed Milk to ship in Factory");}
						break;						
					case "Yogurt":
						Yogurt yrt = new Yogurt(pr1.getName(),pr1.getNumber()*2);// 1 yogurt product is 2lt.
						if(factory.getYogurt().lookFor(yrt.getAmount())>=0) {
							ARCargo.productCon.addItem(yrt);
							ARCargo.productCon.removCap(yrt.getAmount());
							sahip.getWallet().giveMoney(100);
							if(distcen.getYogurt().lookFor(yrt.getAmount())>=0) {
								distcen.getYogurt().removCap(yrt.getAmount());
								factory.getYogurt().addCap(yrt.getAmount());
								distcen.getYogurt().addQ(yrt);
								System.out.println("Your cargo has arrived!!");}
							else {
								System.out.println("We do not have enough space in Distrubution Center Yogurt Deposit!!");
								ARCargo.productCon.empty();}}
							else {
								System.out.println("We do not have enough Yogurt to ship in Factory");}
						break;
					case "Chocolate":
						Chocolate chlt = new Chocolate(pr1.getName(),pr1.getNumber()*0.5);// 1 choco product is 0.5 lt.
						if(factory.getChocolate().lookFor(chlt.getAmount())>=0) {
							ARCargo.productCon.addItem(chlt);
							ARCargo.productCon.removCap(chlt.getAmount());
							sahip.getWallet().giveMoney(100);
							if(distcen.getChocolate().lookFor(chlt.getAmount())>=0) {
								distcen.getChocolate().removCap(chlt.getAmount());
								factory.getChocolate().addCap(chlt.getAmount());
								distcen.getChocolate().addQ(chlt);
								System.out.println("Your cargo has arrived!!");}
							else {
								System.out.println("We do not have enough space in Distrubution Center Chocolate Deposit!!");
								ARCargo.productCon.empty();}}
							else {
								System.out.println("We do not have enough Chocolate to ship in Factory");}
						break;
					default:
						System.out.println("We do not have that kind of product!!");}}
				else {
					break;}}
			ARCargo.productCon.empty();}
		else{
			System.out.println("You do not have enough money for shipment!!");}}}


