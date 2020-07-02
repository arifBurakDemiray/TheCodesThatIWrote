package Facilities;
import StorageElements.IStorager;
import StorageElements.StorageTank;

public class Warehouse<T>{
	private IStorager<T> milk = new StorageTank<T>(10000);
	private IStorager<T> cacao = new StorageTank<T>(10000);
	private IStorager<T> yeast = new StorageTank<T>(10000);
	private IStorager<T> cream = new StorageTank<T>(10000);
	public Warehouse() {}
	public IStorager<T> getMilk() {
		return milk;
	}
	public void setMilk(IStorager<T> milk) {
		this.milk = milk;
	}
	public IStorager<T> getCacao() {
		return cacao;
	}
	public void setCacao(IStorager<T> cacao) {
		this.cacao = cacao;
	}
	public IStorager<T> getYeast() {
		return yeast;
	}
	public void setYeast(IStorager<T> yeast) {
		this.yeast = yeast;
	}
	public IStorager<T> getCream() {
		return cream;
	}
	public void setCream(IStorager<T> cream) {
		this.cream = cream;
	}

}
