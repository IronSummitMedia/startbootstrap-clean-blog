package tech.daniellas.p1;

import static tech.daniellas.p1.SideEffectsPure.DANGEROUS_NAMES;

import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

import tech.daniellas.p1.SideEffectsPure.Player;

public class SideEffectsPure2 {
	// This is pure version accepting 2 side effects handlers and returning one of
	// them depending on internal logic
	static Consumer<Player> verifyPlayer(
	    Consumer<Player> message,
	    Consumer<Player> warning,
	    List<String> dangerousNames,
	    Player player) {
		if (dangerousNames.contains(player.name)) {
			return warning;
		}

		return message;
	}

	@Test
	public void shouldPrintWarning() {
		Player player = new Player("John Rambo");

		// Prints warning
		verifyPlayer(
		    SideEffectsPure::printMessage,
		    SideEffectsPure::printWarning,
		    DANGEROUS_NAMES,
		    player)
		        .accept(player);
	}
}
