package com.dev.metube.type;

public enum EnumYn {
	N,
	Y;
	
	public static EnumYn fromInteger(Integer val) {
		for (EnumYn yn : EnumYn.values()) {
	        if(yn.ordinal() == val) {
	        	return yn;
	        }
	    }
		return null;
	}
}
