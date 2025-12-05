
public class RangeList extends IntegerList{
	//constructors
	RangeList() {
		super();
	}
	RangeList(int lowerBound, int upperBound) {
		super();
		if(lowerBound > upperBound) {
			throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
		}
		for (int i = lowerBound; i <= upperBound; i++) {
			super.insert(super.size(), i);
		}
	}
	
	@Override
	public void insert(int index, int integer) {
		throw new UnsupportedOperationException();
	}
	@Override
	public void add(int integer) {
		throw new UnsupportedOperationException();
	}
	
	//methods 
	public void add(int lowerBound, int upperBound) {
		//1 makes sure the bounds are valid
		if(lowerBound > upperBound) {
			throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
		}
		//2 creates a list of the specified range if list is empty
		if(super.size() == 0) {
			for(int i = lowerBound; i <= upperBound; i++) {
				super.insert(super.size(), i);
			}
			return;
		}
		//3 if lower and upper bound extend past current bounds the list is extended in either direction
		int currentLower = super.get(0);
		int currentUpper = super.get(super.size()-1);
		
		if(lowerBound < currentLower) {
			for(int i = currentLower -1; i >= lowerBound; i--) {
				super.insert(0, i);
			}
		}
		
		if(upperBound > currentUpper) {
			for(int i = currentUpper +1; i <= upperBound; i++) {
				super.insert(super.size(), i);
			}
		}
	}
	public void remove(int lowerBound, int upperBound) {
		//1 makes sure the bounds are valid
		if(lowerBound > upperBound) {
			throw new IllegalArgumentException("The upper bound must be greater than or equal to the lower bound.");
		}
		//2 throws error message if the list is empty
		if(super.size() == 0) {
			throw new UnsupportedOperationException("Cannot remove range from an empty list.");
		}
		//3 throws error message if the bounds are outside the current bounds
		int currentLower = super.get(0);
		int currentUpper = super.get(super.size()-1);
		
		if(lowerBound < currentLower || upperBound > currentUpper) {
			throw new IllegalArgumentException("Lower and/or upper bounds are out of the current list range.");
		}
		
		if(lowerBound > currentLower && upperBound < currentUpper) {
			throw new IllegalArgumentException("Cannot remove non-terminal ranges.");
		}
		
		//remove the elements
		  if (lowerBound == currentLower && upperBound == currentUpper) {
	            while (super.size() > 0) {
	                super.remove(0);
	            }
	            return;
	        }
		
		if(lowerBound == currentLower) {
			int count = upperBound - currentLower + 1;
			for(int i = 0; i < count; i++) {
				super.remove(0);
			}
			return;
		}
		
		int count = currentUpper - lowerBound + 1;
		for(int i = 0; i < count; i++) {
			super.remove(super.size() -1);
		}
	}
	
	
//	public void insert(int index, int integer) {
//		if(super.get(index) == integer || index < 0 || index >= super.size()) {
//			throw new UnsupportedOperationException();
//		}
//	
//		super.insert(index, integer);
//	}
//	public void add(int integer) {
//		for(int i = 0; i < super.size(); i++) {
//			if(super.get(i) == integer) {
//				throw new UnsupportedOperationException();
//			}
//		}
//		super.add(integer);
//	}
	

}
