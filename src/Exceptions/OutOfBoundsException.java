package Exceptions;

public class OutOfBoundsException extends Exception {
	public OutOfBoundsException(String additionalInfos) {
		super("The given coordinate(s) are out of bounds of the game field\nAdditionalInfos: " + additionalInfos);
	}
}
