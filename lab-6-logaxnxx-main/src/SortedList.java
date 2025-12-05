public class SortedList extends IntegerList {
	//constructors
	SortedList() {
		super();
	}
	SortedList(int capacity) {
		super(capacity);
	}
	
	public void add(int integer) {
		int i = 0;
		while(i < super.size() && super.get(i) < integer) {
			i++;
		}
		super.insert(i, integer);
	}
	public void insert(int index, int integer) {
		//add(integer);
//		for(int i = 0; i < super.size(); i++) {
//			if(super.get(i) == integer || index < 0 || index >= super.size()) {
//				throw new UnsupportedOperationException();
//			}
//		}
//		super.insert(index, integer);
		throw new UnsupportedOperationException();
	}
}
