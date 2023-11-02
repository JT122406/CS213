package edu.rutgers.chess.game.player;

/**
 * @author Joseph McQuigg
 * @author Milton Zarzuela
 * Enum for the color of the player
 */
public enum Color {
	/**
	 * White
	 */
	WHITE("white"),
	/**
	 * Black
	 */
	BLACK("black");

	private final String color;

	Color(String color) {
		this.color = color;
	}

	/**
	 * Returns the color of the player
	 * @return color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Returns first letter of the color
	 */
	public char getLetter() {
		return this.color.charAt(0);
	}
}
