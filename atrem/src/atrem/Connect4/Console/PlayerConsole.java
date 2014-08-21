package atrem.Connect4.Console;

import atrem.Connect4.Game.HoleState;
import atrem.Connect4.Game.Player;
import atrem.Connect4.Game.PlayerController;

/*
 * Klasa obsługująca gracza konsolowego
 */
public class PlayerConsole extends Player implements PlayerController {

	private CKeyHandler keyHandler;

	public PlayerConsole(int slots, String name, HoleState playerId) {
		super(slots, name, playerId);
	}

	@Override
	public int getSlots(int slots) {
		slots = keyHandler.getSlot(maxSlots);
		return slots;
	}

	@Override
	public final void setName(String name) {
		super.setName(name);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HoleState getPlayerId() {
		// TODO Auto-generated method stub
		return null;
	}

}
