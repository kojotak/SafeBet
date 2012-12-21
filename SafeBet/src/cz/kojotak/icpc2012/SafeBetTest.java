package cz.kojotak.icpc2012;

import org.junit.Assert;
import org.junit.Test;

public class SafeBetTest {

	@Test
	public void output() {
		SafeBet safeBet = new SafeBet("SafeBet.input");
		Assert.assertEquals(3, safeBet.configurations.size());
		String output = safeBet.resolve();
		Assert.assertArrayEquals(new String[] { "Case 1: 2 4 3", "Case 2: 0", " Case 3: impossible" },
				output.split("\n"));
	}

}
