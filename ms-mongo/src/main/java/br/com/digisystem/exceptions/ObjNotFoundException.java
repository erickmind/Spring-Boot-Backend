package br.com.digisystem.exceptions;

public class ObjNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ObjNotFoundException(String msg){
		
		super(msg);	
	}
}
