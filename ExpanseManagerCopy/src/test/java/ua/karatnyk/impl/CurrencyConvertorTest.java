package ua.karatnyk.impl;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;



class CurrencyConvertorTest {

	
		private Double amount;
		private String from;
		private String to;
		private static CurrencyConversion conversion;
		public Class<Exception> exceptionClass = Exception.class; //Pour les exceptions
		
		
		@BeforeAll
		public static void setup() {
			
			conversion = new CurrencyConversion(); //la conversion entre les devises
		}
		
		@BeforeEach
		public void init() {
			
			amount = 1.00;  // valeurs par default
			from = "CAD";
			to = "USD";
			
		}
		
		//si la premiere devise n'existe pas
		@Test
	    public void isNotConvertingFrom() {
	        String wrongFrom = "ETH";
	        String expectedOutcome = "Not correct format currency";

	        assertThrows(exceptionClass,
	                () -> CurrencyConvertor.convert(amount, wrongFrom, to, conversion), expectedOutcome);
	    }
		
		//si la deuxieme devise n'existe pas 
		@Test
	    public void isNotConvertingTo() {
	        String wrongTo = "ETH";
	        String expectedOutcome = "Not correct format currency";

	        assertThrows(exceptionClass,
	                () -> CurrencyConvertor.convert(amount, from, wrongTo, conversion), expectedOutcome);
	    }
		
		//si ca donne le bon resultat lorsque ca fonctionne
		@Test
		public void isConvertingValidCurrency() throws ParseException {
			
			double curencyFrom = conversion.getRates().get(from);
			double curencyTo = conversion.getRates().get(to);
			double results = amount*(curencyFrom/curencyTo);
			
			assertTrue(results == CurrencyConvertor.convert(amount, from, to, conversion)); // to find if it's a double
		}
		
		//si ca fonctionne lorsque la premiere et deuxieme devise sont pareil
		@Test
	    void isSupportingSameCurrency() throws ParseException {
	        // USD, CAD, GBP, EUR, CHF, INR, AUD
	        double valeurParDefault = 10.00;

	        double USDaUSD = CurrencyConvertor.convert(valeurParDefault,"USD","USD", conversion);
	        double CADaCAD = CurrencyConvertor.convert(valeurParDefault,"CAD","CAD", conversion);
	        double GBPaGBP = CurrencyConvertor.convert(valeurParDefault,"GBP","GBP", conversion);
	        double EURaEUR = CurrencyConvertor.convert(valeurParDefault,"EUR","EUR", conversion);
	        double CHFaCHF = CurrencyConvertor.convert(valeurParDefault,"CHF","CHF", conversion);
	        double INRaINR = CurrencyConvertor.convert(valeurParDefault,"INR","INR", conversion);
	        double AUDaAUD = CurrencyConvertor.convert(valeurParDefault,"AUD","AUD", conversion);

	        assertNotNull(USDaUSD, "Devrait supporter USD");
	        assertNotNull(CADaCAD, "Devrait supporter CAD");
	        assertNotNull(GBPaGBP, "Devrait supporter GBP");
	        assertNotNull(EURaEUR, "Devrait supporter EUR");
	        assertNotNull(CHFaCHF, "Devrait supporter CHF");
	        assertNotNull(INRaINR, "Devrait supporter INR");
	        assertNotNull(AUDaAUD, "Devrait supporter AUD");
	        

	    }
		
		//si ca ne fonctionne pas lorsque le montant est trop grand
		@Test
	    public void isNotConvertingAmountMoreThan10k() {
	        Double superiorAmount = 99999.00d;
	        String expectedOutcome = "Amount not in the possible range";
	        
	        assertThrows(exceptionClass,
	                () -> CurrencyConvertor.convert(superiorAmount, from, to, conversion), expectedOutcome);
	    }
		
		//si ca ne fonctionne pas lorsque le montant est trop petit
		@Test
	    public void isNotConvertingAmountLessThan0() {
	        Double inferiorAmount = -5.00d;
	        String expectedOutcome = "Amount not in the possible range";
	        
	        assertThrows(exceptionClass,
	        		() -> CurrencyConvertor.convert(inferiorAmount, from, to, conversion), expectedOutcome);
	    }
		
		//si ca fonctionne lorsque le montant est maximal
		@Test
	    public void isConvertingAmount10k() {
	        Double goodAmount = 10000.00d;
	        String expectedOutcome = "Amount in the possible range";
	        
	        assertThrows(exceptionClass,
	        		() -> CurrencyConvertor.convert(goodAmount, from, to, conversion), expectedOutcome);
	    }
		
		//si ca fonctionne lorsque le montant est medium
		@Test
	    public void isConvertingAmount500() {
	        Double goodAmount = 500.00d;
	        String expectedOutcome = "Amount in the possible range";
	        
	        assertThrows(exceptionClass,
	        		() -> CurrencyConvertor.convert(goodAmount, from, to, conversion), expectedOutcome);
	    }
		
		//si ca fonctionne lorsque le montant est minimal
		@Test
	    public void isConvertingAmount0() {
	        Double goodAmount = 0.00d;
	        String expectedOutcome = "Amount in the possible range";
	        
	        assertThrows(exceptionClass,
	        		() -> CurrencyConvertor.convert(goodAmount, from, to, conversion), expectedOutcome);
	    }
		//White box testing coverage
		// si ca fonctionne pour les petits amouts 
	    public void isConvertingVerySmallAmount10k() {
	        Double goodAmount = 0000.01d;
	        String expectedOutcome = "Amount in the possible range";
	        
	        assertThrows(exceptionClass,
	        		() -> CurrencyConvertor.convert(goodAmount, from, to, conversion), expectedOutcome);
	    }
		
		
}
