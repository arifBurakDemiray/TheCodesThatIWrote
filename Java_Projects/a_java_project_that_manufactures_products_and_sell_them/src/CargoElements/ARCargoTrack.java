package CargoElements;
import Facilities.DistrubutionCenter;
import Facilities.Factory;
import Facilities.Owner;
import Facilities.Warehouse;
import Items.*;

public class ARCargoTrack implements IARCargoTrack{
	public ARCargoTrack() {
		System.out.println("Your cargo will go out in 5 minutes!!");}
	
	public void toTheFactory(Warehouse<Ingredient> warehouse, Factory<Ingredient,Product> factory, Ingredient ingd, Owner sahip) {
		if(sahip.getWallet().getMoney()>=100) {
		if(ingd.getName().equals("Milk")  && !warehouse.getMilk().isEmpty()) {
			Milk tempMilk = new Milk(ingd.getName(),ingd.getAmount());
			if(10000-warehouse.getMilk().getCapacity()-tempMilk.getAmount()>=0) {
				if(tempMilk.getAmount()<=1000) {
					ARCargo.tankIngredient.addItem(tempMilk);
					ARCargo.tankIngredient.removCap(tempMilk.getAmount());
					if(factory.getMilk().lookFor(tempMilk.getAmount())>=0) {
						factory.getMilk().removCap(tempMilk.getAmount());
						warehouse.getMilk().addCap(tempMilk.getAmount());
						sahip.getWallet().giveMoney(100);
						System.out.println("Your cargo has arrived!!");
						ARCargo.tankIngredient.empty();}
					else {
						System.out.println("We do not have enough space in Factory Milk Deposit!!");
						ARCargo.tankIngredient.empty();}}
				else {
					System.out.println("We do not enough space for our cargo container!!");}}
			else {
				System.out.println("We do not have that amount milk!!");}}
		else if(ingd.getName().equals("Yeast") && !warehouse.getYeast().isEmpty()) {
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
		else if(ingd.getName().equals("Cacao") && !warehouse.getCacao().isEmpty()) {
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
		else if(ingd.getName().equals("Cream") && !warehouse.getCream().isEmpty()) {
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
			System.out.println("We do not have this Ingredient that you are looking for");}}
		else{
			System.out.println("You do not have enough money for shipment!!");}}
	
	public void toTheDistCenter(DistrubutionCenter<Product> distcen, Factory<Ingredient,Product> factory, Object[] prd, Owner sahip) {
		if(sahip.getWallet().getMoney()>=100) {
			double temp = 0;
			int index=prd.length-1;
			for(int i=0;i<prd.length;i++) {
				Product prdct = (Product) prd[i];
				temp+=prdct.getAmount();
				if(temp>1000) {
					temp-=prdct.getAmount();
					index=i;
					System.out.println("Because we have 1000 LT space we are going to ship that amount cargo!!");
					break;}}
			for(Object pr:prd) {
				Product pr1 = (Product) pr;
				if((prd.length-1==0 && pr1.getAmount()<=1000)|| !pr.equals(prd[index])) {
					switch(pr1.getName()) {
					case "Boxed Milk":
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
							ARCargo.productCon.addCap(bxm.getAmount());}}
						else {
							System.out.println("We do not have enough Boxed Milk to ship in Factory");}
						break;						
					case "Yogurt":
						Yogurt yrt = new Yogurt(pr1.getName(),pr1.getAmount()*2);
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
								ARCargo.productCon.addCap(yrt.getAmount());}}
							else {
								System.out.println("We do not have enough Yogurt to ship in Factory");}
						break;
					case "Chocolate":
						Chocolate chlt = new Chocolate(pr1.getName(),pr1.getAmount()*0.5);
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
								ARCargo.productCon.addCap(chlt.getAmount());}}
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


