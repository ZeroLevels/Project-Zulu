package projectzulu.common.world.blockdataobjects;

import com.google.common.base.Optional;

public class Returnable<K> {
	
	private Optional<K> returnableObject = Optional.absent();
	
	/**
	 * Assign as Returnable Object if it is Empty. 
	 * @param returnableObject. Cannot hold null values.
	 * @return True is object is assigend. False otherwise.
	 */
	public boolean acceptIfEmpty(K returnableObject){
		if(!this.returnableObject.isPresent() && returnableObject != null){
			this.returnableObject = Optional.of(returnableObject);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Assign as Returnable Object even is present.
	 * @param returnableObject. Cannot hold null values.
	 * @return True is object is assigend. False otherwise.
	 */
	public boolean setReturnableObject(K returnableObject){
		if(returnableObject != null){
			this.returnableObject = Optional.of(returnableObject);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Returns the returnableObject if present. Accessor to Optional.get();
	 * @return
	 */
	public K getReturnableObject(){
		return returnableObject.get();
	}
	/**
	 * Returns the returnableObject if present. Accessor to Optional.isPresent
	 * @return
	 */
	public boolean isReturnablePresent(){
		return returnableObject.isPresent();
	}
}
