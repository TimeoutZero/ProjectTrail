package io.redspark.ireadme.exceptions;

public class IReadmeExceptionMessage {

	private static final String NOT_FOUND = "%s.not.found";
	private static final String ALREADY_EXIST = "%s.already.exist";

	public static String notFound(String entity) {
		return String.format(NOT_FOUND, entity);
	}
	
	public static String alreadyExist(String entity){
		return String.format(ALREADY_EXIST, entity);

	}
}
