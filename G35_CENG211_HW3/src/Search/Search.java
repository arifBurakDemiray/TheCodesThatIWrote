package Search;

public class Search<T> implements ISearch<T>{
	public Search() {};
	public boolean searchsForEqualClasses(T list, Object object){
		try {
		if(list instanceof java.lang.Iterable) {
			 java.lang.Iterable<?> list1 = (java.lang.Iterable<?>) list;
			for(Object item: list1) {
				if(item.getClass().equals(object.getClass())) {
					continue;}
				else
					return false;}
			return true;}
		else {
			throw new Exception("The object that you wrote to the method is not Iterable");}}
		catch(Exception e) {
			System.out.println(e.getMessage());}
		return false; }}
