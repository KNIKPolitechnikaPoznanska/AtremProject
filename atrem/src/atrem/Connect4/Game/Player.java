package atrem.Connect4.Game;

/*
 * Klasa obsługująca dane graczy
 */
public class Player {
	protected String name;
	protected HoleState playerId;
	protected int slots;

	public Player() {
	};

	public Player(int slots, String name, HoleState playerId) {
		this.name = name;
		this.playerId = playerId;
		this.slots = slots;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HoleState getPlayerId() {
		return null;
	}

	public int getSlots() {
		return 0;
	}

}
