package cz.kojotak.icpc2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SafeBet {

	static final int MAX_SIZE = 1000000;
	static final int MAX_MIRRORS_OF_GIVEN_TYPE = 200000;

	public static enum Type {
		SLASH('/'), BACKSLASH('\\');

		public final char sign;

		private Type(char sign) {
			this.sign = sign;
		}

	}

	public static class Mirror {
		public final int row, column;
		public final Type type;

		public Mirror(int row, int column, Type type) {
			if (type == null) {
				throw new IllegalArgumentException("Cannot create new mirror without valid type!");
			}
			if (row < 0 || column < 0 || row > MAX_SIZE || column > MAX_SIZE) {
				throw new IllegalArgumentException("Cannnot create new mirror with invalid coordinate!");
			}
			this.row = row;
			this.column = column;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Mirror(row=" + row + ",column=" + column + ",type=" + type.sign + ")";
		}

	}

	public static class Cfg {
		public final int rows, columns;
		public final List<Mirror> mirrors = new ArrayList<Mirror>();

		public Cfg(int rows, int columns) {
			if (rows < 0 || columns < 0 || rows > MAX_SIZE || columns > MAX_SIZE) {
				throw new IllegalAccessError("Cannnot create new mirror with invalid coordinate!");
			}
			this.rows = rows;
			this.columns = columns;
		}

		@Override
		public String toString() {
			return "Configuration(rows=" + rows + ", columns=" + columns + ", mirrors=" + mirrors + ")";
		}
	}

	static class CfgMirrorFormat {
		public final int lines;
		public final Type type;

		public CfgMirrorFormat(int lines, Type type) {
			this.lines = lines;
			this.type = type;
		}
	}

	final List<Cfg> configurations;

	public SafeBet() {
		this("SafeBet.input");
	}

	public SafeBet(Cfg cfg) {
		if (cfg == null) {
			throw new IllegalArgumentException("Cannot create safe bet without proper configuration!");
		}
		configurations = Collections.singletonList(cfg);
	}

	private SafeBet(String file) {
		try {
			configurations = loadCfg(file);
		} catch (IOException e) {
			throw new RuntimeException("Cannot load configuration from " + file, e);
		}
	}

	private List<Cfg> loadCfg(String file) throws IOException {
		URL resource = getClass().getResource(file);
		BufferedReader reader = new BufferedReader(new FileReader(resource.getFile()));
		String line = null;

		List<Cfg> cfgs = new ArrayList<SafeBet.Cfg>();
		while ((line = reader.readLine()) != null) {
			String[] params = line.split(" ");
			if (params == null || params.length != 4) {
				throw new IllegalArgumentException("Bad configuration: there has to be 4 parameters: r,c,m,n!");
			}
			int r = Integer.parseInt(params[0]), c = Integer.parseInt(params[1]), m = Integer.parseInt(params[2]), n = Integer
					.parseInt(params[3]);
			Cfg cfg = new Cfg(r, c);
			for (CfgMirrorFormat cmf : new CfgMirrorFormat[] { new CfgMirrorFormat(m, Type.SLASH),
					new CfgMirrorFormat(n, Type.BACKSLASH) }) {
				for (int i = 0; i < cmf.lines; i++) {
					String mirrorLine = reader.readLine();
					if (mirrorLine == null) {
						throw new IllegalArgumentException("Bad configuration: cannot read " + i
								+ "th configuration for " + cmf.type + " mirror!");
					}
					String[] positions = mirrorLine.split(" ");
					if (positions == null || positions.length != 2) {
						throw new IllegalArgumentException("Bad configuration: " + i + "th configuration for "
								+ cmf.type + " mirror doesn't contain valid coordinates!");
					}
					int ri = Integer.parseInt(positions[0]), ci = Integer.parseInt(positions[1]);
					Mirror mirror = new Mirror(ri, ci, cmf.type);
					cfg.mirrors.add(mirror);
				}
			}
			cfgs.add(cfg);
		}

		return cfgs;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(new SafeBet().resolveAll());
	}

	public String resolveAll() {
		StringBuilder builder = new StringBuilder();
		for (Cfg cfg : configurations) {
			String one = resolve(cfg);
			builder.append(one);
		}
		return builder.toString();
	}

	/** resolve one configuration */
	private String resolve(Cfg cfg) {
		return cfg.toString() + "\n";
	}

}
