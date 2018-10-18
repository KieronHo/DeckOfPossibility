import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
	
/**
 * 
 * @author Kieron Ho
 *
 */
public class DeckOfPossibility{
	private HashMap<Card, Integer> deck;
	private int age;
	
	public DeckOfPossibility() {
		deck = new HashMap<Card, Integer>(fullDeck());
		age = 0;
	}
	
	public DeckOfPossibility(DeckOfPossibility oldDeck) {
		deck = new HashMap<Card, Integer>(oldDeck.getPossibleDeck());
		this.age = oldDeck.getAge();
	}
	
	public static HashMap<Card, Integer> fullDeck(){
		HashMap<Card, Integer> fullDeck = new HashMap<Card, Integer>();
		Card[] cardList = Card.getDeck();
		for(Card card : cardList) {
			if(!fullDeck.isEmpty()) {
				boolean cardFound = false;
			Iterator<Card> fullDeckIterator = fullDeck.keySet().iterator();
			while(fullDeckIterator.hasNext()) {
				Card existingCard = fullDeckIterator.next();
				if(existingCard.equals(card)) {
					fullDeck.put(existingCard, fullDeck.get(existingCard) + 1);
					cardFound = true;
				}
			}
			if(!cardFound) {
				fullDeck.put(card, 1);
			}
		} else {
				fullDeck.put(card, 1);
			}
		}
		return new HashMap<Card, Integer>(fullDeck);
	}
	
	public HashMap<Card, Integer> getPossibleDeck(){
		return deck;
	}
	
	public void reset() {
		deck = new HashMap<Card, Integer>(fullDeck());
		age = 0;
	}
	
	public void increaseAge() {
		age++;
	}

	public int getAge() {
		return age;
	}
	
	public boolean containsCard(Card checkCard) {
		HashMap<Card, Integer> checkDeck = new HashMap<Card, Integer>(deck);
		boolean contains = false;
		Iterator<Card> deckIterator = checkDeck.keySet().iterator();
		while(deckIterator.hasNext()) {
			if(deckIterator.next().equals(checkCard)){
				contains = true;
				break;
			}
		}
		return contains;
	}
	
	public void updateCardCount(Card card, int modification) {
		if(!containsCard(card) && modification > 0) {//if you are adding a new card
			deck.put(card, modification);
		}else if(cardCount(card) >= 1 || modification > 0){//if you are increasing the number of a card
		Iterator<Card> deckIterator = deck.keySet().iterator();
		Card storedCard;
		storedCard = deckIterator.next();
		while(deckIterator.hasNext()) {
			if(storedCard.equals(card)) {
				break;
			}
			storedCard = deckIterator.next();
		}
		Integer oldValue = deck.get(storedCard);
		deck.put(storedCard, oldValue + modification);
		} else if(cardCount(card) == 1 || modification < 0){//if you are decreasing the amount of cards to zero
			Iterator<Card> deckIterator = deck.keySet().iterator();
			Card storedCard;
			storedCard = deckIterator.next();
			while(deckIterator.hasNext()) {
				if(storedCard.equals(card)) {
					break;
				}
				storedCard = deckIterator.next();
			}
			deck.remove(storedCard);
		}
	}
	
	public int cardCount(Card card) {
		HashMap<Card, Integer> tempDeck = new HashMap<Card, Integer>(deck);
		Card actualCard = null;
		Iterator<Card> deckIterator = tempDeck.keySet().iterator();

		while(deckIterator.hasNext()) {
			actualCard = deckIterator.next();
			if(actualCard.equals(card)) {
				break;
			} else actualCard = null;
		}
		return (actualCard == null?0:tempDeck.get(actualCard));
	}
	
	public void removeColour(Colour removeColour) {
		ArrayList<Card> cardsToRemove = new ArrayList<Card>();
		Iterator<Card> deckIterator = deck.keySet().iterator();
		Card card;
		while(deckIterator.hasNext()) {
			card = deckIterator.next();
			if(card.getColour().equals(removeColour)){
				cardsToRemove.add(card);
			}
		}
		if(!cardsToRemove.isEmpty()) {
		for(Card removeCard:cardsToRemove) {
			deck.remove(removeCard);
		}
		}
	}
	
	public void removeNumber(int removeNumber) {
		ArrayList<Card> cardsToRemove = new ArrayList<Card>();
		Iterator<Card> deckIterator = deck.keySet().iterator();
		Card card;
		while(deckIterator.hasNext()) {
			card = deckIterator.next();
			if(card.getValue() == removeNumber) {
				cardsToRemove.add(card);
			}
		}
		if(!cardsToRemove.isEmpty()) {
		for(Card removeCard:cardsToRemove) {
			deck.remove(removeCard);
		}
		}
	}
	
	public int totalCardsInDeck() {
		HashMap<Card, Integer> tempDeck = new HashMap<Card, Integer>(deck);
		int cardTotal = 0;
		for(Integer count:tempDeck.values()) {
			cardTotal += count;
		}
		return cardTotal;
	}
	
	public boolean containsColour(Colour colour) {
		Iterator<Card> deckIterator = deck.keySet().iterator();
		while(deckIterator.hasNext()) {
			if(deckIterator.next().getColour().equals(colour)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsValue(Integer value) {
		Iterator<Card> deckIterator = deck.keySet().iterator();
		while(deckIterator.hasNext()) {
			if(deckIterator.next().getValue() == value) {
				return true;
			}
		}
		return false;
	}
	
	public Card getLastCard() {
		if(totalCardsInDeck() == 1) {
			return deck.keySet().iterator().next();
		}else return null;
	}
	
	public Colour remainingColour() {
		if(totalCardsInDeck() == 1) {
			return deck.keySet().iterator().next().getColour();
		} else return null;
	}
	
	public Integer remainingValue() {
		if(totalCardsInDeck() == 1) {
			return deck.keySet().iterator().next().getValue();
		} else return null;
		
	}
	
	//////////////////////////////////////////////////////////////////////////////
	/*
	 * These are methods that can be placed in the surface class.
	 */
	
	public static DeckOfPossibility[] newPossibilityArray(int numberOfCards) {
		DeckOfPossibility[] possDeck = new DeckOfPossibility[numberOfCards];
		for(int i = 0 ; i < numberOfCards ; i++) {
			possDeck[i] = new DeckOfPossibility();
		}
		return possDeck;
	}
	
	public static Colour[] allRemainingColours(Colour colour) {
		Colour[] allColours = Colour.values();
		ArrayList<Colour> remainingList = new ArrayList<Colour>();
		for(Colour c : allColours) {
			if(!c.equals(colour)) {
				remainingList.add(c);
			}
		}
		Colour[] remainingArray = new Colour[allColours.length - 1];
		for(int i = 0 ; i < remainingArray.length ; i++) {
			remainingArray[i] = remainingList.remove(0);
		}
		return remainingArray;
	}
	
	public static Integer[] allRemainingValues(Integer value) {
		Integer[] allValues = {1, 2, 3, 4, 5};
		ArrayList<Integer> remainingList = new ArrayList<Integer>();
		for(Integer v : allValues) {
			if(!v.equals(value)) {
				remainingList.add(v);
			}
		}
		Integer[] remainingArray = new Integer[allValues.length - 1];
		for(int i = 0 ; i < remainingArray.length ; i++) {
			remainingArray[i] = remainingList.remove(0);
		}
		return remainingArray;
	}
	
	public static Card certainCard(DeckOfPossibility singleCardDeck) {
		DeckOfPossibility deck = new DeckOfPossibility(singleCardDeck);
		if(deck.totalCardsInDeck() == 1) {
			Iterator<Card> lastCardIterator = deck.getPossibleDeck().keySet().iterator();
			return lastCardIterator.next();
		}
		return null;
	}
	
}