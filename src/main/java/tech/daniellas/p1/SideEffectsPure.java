package tech.daniellas.p1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;

public class SideEffectsPure {

	// This is our player class
	static class Player {
		final String name;

		Player(String name) {
			this.name = name;
		}
	}

	// This is our dangerous players list
	static List<String> DANGEROUS_NAMES = Arrays.asList("John Rambo", "Chuck Norris");

	// This is our warning printing impure function
	static void printWarning(Player player) {
		System.out.println("WARNING !!! " + player.name + " is attempting to enter");
	}

	// This is our message printing impure function
	static void printMessage(Player player) {
		System.out.println(player.name + " is attempting to enter");
	}

	// This is our verification function. Instead of performing side effect it
	// returns Consumer<Player> than can be applied to player.
	static Consumer<Player> verifyPlayer(List<String> dangerousNames, Player player) {
		if (dangerousNames.contains(player.name)) {
			return SideEffectsPure::printWarning;
		}

		return SideEffectsPure::printMessage;
	}

	// This is how we use our verification function, we apply it to given player and
	// call accept method with same player
	@Test
	public void shouldPrintWarning() {
		Player player = new Player("John Rambo");

		// Prints warning
		verifyPlayer(DANGEROUS_NAMES, player).accept(player);
	}

	@Test
	public void shouldPrintMessage() {
		Player player = new Player("John Doe");

		// Prints message
		verifyPlayer(DANGEROUS_NAMES, player).accept(player);
	}
}
