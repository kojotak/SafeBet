package cz.kojotak.icpc2012;

import org.junit.Assert;
import org.junit.Test;

import cz.kojotak.icpc2012.SafeBet.Type;

public class SafeBetTest {

	@Test
	public void correctOutput() {
		String output = new SafeBet().resolveAll();
		Assert.assertArrayEquals(new String[] { "Case 1: 2 4 3", "Case 2: 0", " Case 3: impossible" },
				output.split("\n"));
	}

	@Test
	public void correctInput() {
		SafeBet safeBet = new SafeBet();
		Assert.assertEquals("there are 3 input configurations", 3, safeBet.configurations.size());
		Assert.assertEquals("there are 5 mirros in 1st configuration", 5, safeBet.configurations.get(0).mirrors.size());
		Assert.assertEquals("there are 2 mirros in 2nd configuration", 2, safeBet.configurations.get(1).mirrors.size());
		Assert.assertEquals("there are no mirros in 3rd configuration", 0, safeBet.configurations.get(2).mirrors.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeMirrorCoordinate() {
		new SafeBet.Mirror(-1, 1, Type.SLASH);
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooBigMirrorCoordinate() {
		new SafeBet.Mirror(1000001, 1000001, Type.BACKSLASH);
	}

}
