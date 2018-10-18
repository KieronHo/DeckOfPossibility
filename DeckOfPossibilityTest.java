import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Kieron Ho
 *
 */
class DeckOfPossibilityTest {
	
	@Test
	void testDeckOfPossibility() {
		DeckOfPossibility testDeck = new DeckOfPossibility();
		assertTrue(testDeck != null);
	}

	@Test
	void testFullDeck() {
		assertTrue(DeckOfPossibility.fullDeck().size() == 25);
	}

	@Test
	void testGetPossibleDeck() {
		String fullDeck = DeckOfPossibility.fullDeck().keySet().toString();
		DeckOfPossibility testDeck = new DeckOfPossibility();
		String testDeckString = testDeck.getPossibleDeck().keySet().toString();
		assertTrue(fullDeck.equals(testDeckString));
	}

	@Test
	void testContainsCard() {
		DeckOfPossibility testDeck = new DeckOfPossibility();
		Card card1 = new Card(Colour.YELLOW, 5);
		Card card2 = new Card(Colour.RED, 2);
		assertTrue(testDeck.containsCard(card1));
		assertTrue(testDeck.containsCard(card2));
	}

	@Test
	void testUpdateCardCount() {
		DeckOfPossibility testDeck = new DeckOfPossibility();
		Card card1 = new Card(Colour.YELLOW, 4);
		assertTrue((int) 2 == (int) testDeck.cardCount(card1));
		testDeck.updateCardCount(card1, -1);
		
	}

	@Test
	void testCardCount() {
		DeckOfPossibility testDeck = new DeckOfPossibility();
		Card card1 = new Card(Colour.BLUE, 1);
		Card card2 = new Card(Colour.BLUE, 5);
		assertTrue(3 == testDeck.cardCount(card1));
		assertTrue(1 == testDeck.cardCount(card2));
	}

	@Test
	void testRemoveColour() {
		DeckOfPossibility testDeck = new DeckOfPossibility();
		assertTrue(50 == testDeck.totalCardsInDeck());
		testDeck.removeColour(Colour.GREEN);
		assertTrue(40 == testDeck.totalCardsInDeck());
		testDeck.removeColour(Colour.BLUE);
		assertTrue(30 == testDeck.totalCardsInDeck());
	}

	@Test
	void testRemoveNumber() {
		DeckOfPossibility testDeck = new DeckOfPossibility();
		assertTrue(50 == testDeck.totalCardsInDeck());
		testDeck.removeNumber(1);
		assertTrue(35 == testDeck.totalCardsInDeck());
	}

	@Test
	void testTotalCardsInDeck() {
		DeckOfPossibility testDeck = new DeckOfPossibility();
		assertTrue(50 == testDeck.totalCardsInDeck());
		Colour[] allColours = Colour.values();
		for(Colour c : allColours) {
			testDeck.removeColour(c);
		}
		assertEquals(0, testDeck.totalCardsInDeck());
		testDeck.reset();
		assertEquals(50, testDeck.totalCardsInDeck());
		testDeck.removeNumber(5);
		assertEquals(45, testDeck.totalCardsInDeck());
	}



}
