package Facilities;
import StorageElements.IStorager;
import StorageElements.StorageArea;
import StorageElements.StorageTank;

public class Factory<T1,T2>{
	private IStorager<T1> milk = new StorageTank<T1>(10000);
	private IStorager<T1> cacao = new StorageTank<T1>(10000);
	private IStorager<T1> yeast = new StorageTank<T1>(10000);
	private IStorager<T1> cream = new StorageTank<T1>(10000);
	private IStorager<T2> boxedMilk = new StorageArea<T2>(10000);
	private IStorager<T2> chocolate = new StorageArea<T2>(10000);
	private IStorager<T2> yogurt = new StorageArea<T2>(10000);
	public Factory() {
	}
	public IStorager<T1> getMilk() {
		return milk;
	}
	public void setMilk(IStorager<T1> milk) {
		this.milk = milk;
	}
	public IStorager<T1> getCacao() {
		return cacao;
	}
	public void setCacao(IStorager<T1> cacao) {
		this.cacao = cacao;
	}
	public IStorager<T1> getYeast() {
		return yeast;
	}
	public void setYeast(IStorager<T1> yeast) {
		this.yeast = yeast;
	}
	public IStorager<T1> getCream() {
		return cream;
	}
	public void setCream(IStorager<T1> cream) {
		this.cream = cream;
	}
	public IStorager<T2> getBoxedMilk() {
		return boxedMilk;
	}
	public void setBoxedMilk(IStorager<T2> boxedMilk) {
		this.boxedMilk = boxedMilk;
	}
	public IStorager<T2> getChocolate() {
		return chocolate;
	}
	public void setChocolate(IStorager<T2> chocolate) {
		this.chocolate = chocolate;
	}
	public IStorager<T2> getYogurt() {
		return yogurt;
	}
	public void setYogurt(IStorager<T2> yogurt) {
		this.yogurt = yogurt;
	}
	
}