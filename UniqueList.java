
public class UniqueList extends IntegerList{
	//constructors
	UniqueList() {
		super();
	}
	UniqueList(int capacity) {
		super(capacity);
	}
	
	public void add(int integer) {
		for(int i = 0; i < super.size(); i++) {
			if(super.get(i) == integer) {
				throw new IllegalArgumentException("The integer " + 
						integer + " is already in the list.");
			}
		}
		super.add(integer);
	}
	public void insert(int index, int integer) {
//		if(index < 0 || index >= super.size()) {
//			throw new IndexOutOfBoundsException("The index is outside the rang [0, "
//									+ (super.size()-1) + "].");
//		}
		for(int i = 0; i < super.size(); i++) {
			if(super.get(i) == integer) {
				throw new IllegalArgumentException("The integer " + 
						integer + " is already in the list.");
			}
		}
		super.insert(index, integer);
	}
}
