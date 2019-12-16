package Facilities;
import StorageElements.IStorager;
import StorageElements.StorageArea;
//it has existing product's storage in it.
public class DistrubutionCenter<T>{
	private IStorager<T> boxedMilk = new StorageArea<T>(10000);
	private IStorager<T> chocolate = new StorageArea<T>(10000);
	private IStorager<T> yogurt = new StorageArea<T>(10000);
	public DistrubutionCenter() {}
	public IStorager<T> getBoxedMilk() {
		return boxedMilk;
	}
	public void setBoxedMilk(IStorager<T> boxedMilk) {
		this.boxedMilk = boxedMilk;
	}
	public IStorager<T> getChocolate() {
		return chocolate;
	}
	public void setChocolate(IStorager<T> chocolate) {
		this.chocolate = chocolate;
	}
	public IStorager<T> getYogurt() {
		return yogurt;
	}
	public void setYogurt(IStorager<T> yogurt) {
		this.yogurt = yogurt;
	}
}

