import static org.junit.Assert.*;

import org.junit.Test;

public class ScalarMultTests  {

	public static byte[] hexToBytes(String s) {
	    return javax.xml.bind.DatatypeConverter.parseHexBinary(s);
	}
	
	byte[] alicePrivateKey=hexToBytes(
			"77076d0a7318a57d"+
			"3c16c17251b26645"+
			"df4c2f87ebc0992a"+
			"b177fba51db92c2a");
	
	byte[] alicePublicKey=hexToBytes(
			"8520f0098930a754"+
			"748b7ddcb43ef75a"+
			"0dbf3a0d26381af4"+
			"eba4a98eaa9b4e6a");
	

	byte[] bobPrivateKey=hexToBytes(
			"5dab087e624a8a4b"+
			"79e17f8b83800ee6"+
			"6f3bb1292618b6fd"+
			"1c2f8b27ff88e0eb");

	byte[] bobPublicKey=hexToBytes(
			"de9edb7d7b7dc1b4"+
			"d35b61c2ece43537"+
			"3f8343c85b78674d"+
			"adfc7e146f882b4f");

	byte[] base=new byte[]{
			9,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0
			};
	
	byte[] aliceBobShared=hexToBytes(
			"4A5D9D5BA4CE2DE1"+
			"728E3BF480350F25"+
			"E07E21C947D19E33"+
			"76F09B3C1E161742");
	
	@Test
	public void scalarMultBaseWithAlicePrivate() {		
		byte[] output=new byte[32];
		MontgomeryOperations.scalarmult(output, 0, alicePrivateKey, 0, base, 0);
		assertArrayEquals(alicePublicKey, output);
	}
	
	@Test
	public void scalarMultBaseWithBobPrivate() {		
		byte[] output=new byte[32];
		MontgomeryOperations.scalarmult(output, 0, bobPrivateKey, 0, base, 0);
		assertArrayEquals(bobPublicKey, output);
	}

	@Test
	public void scalarMultAlicePublicWithBobPrivate() {		
		byte[] output=new byte[32];
		MontgomeryOperations.scalarmult(output, 0, bobPrivateKey, 0, alicePublicKey, 0);
		assertArrayEquals(aliceBobShared, output);
	}
	
	@Test
	public void scalarMultBobPublicWithAlicePrivate() {		
		byte[] output=new byte[32];
		MontgomeryOperations.scalarmult(output, 0, alicePrivateKey, 0, bobPublicKey, 0);
		assertArrayEquals(aliceBobShared, output);
	}
	
	@Test
	public void fromBytesTest()
	{
		FieldElement fe = new FieldElement();
		byte[] actualBobPublicKey = new byte[32];
		FieldOperations.fe_frombytes(fe, bobPublicKey, 0);
		FieldOperations.fe_tobytes(actualBobPublicKey, 0, fe);
		assertArrayEquals(bobPublicKey, actualBobPublicKey);
	}
}
