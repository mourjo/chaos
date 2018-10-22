package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Colors {
	// https://en.wikipedia.org/wiki/ANSI_escape_code

	public static final String ANSI_RESET = "\u001B[0m";

	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BRIGHT_RED = "\u001B[91m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BRIGHT_GREEN = "\u001B[92m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BRIGHT_YELLOW = "\u001B[93m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_BRIGHT_BLUE = "\u001B[94m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_MAGENTA = "\u001B[95m";
	public static final String ANSI_BRIGHT_MAGENTA = "\u001B[95m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_BRIGHT_CYAN = "\u001B[96m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BRIGHT_WHITE = "\u001B[97m";

	private static List<String> colorPool = Arrays.asList(new String[] { ANSI_BRIGHT_BLACK, ANSI_RED, ANSI_BRIGHT_RED,
			ANSI_GREEN, ANSI_BRIGHT_GREEN, ANSI_YELLOW, ANSI_BRIGHT_YELLOW, ANSI_BLUE, ANSI_BRIGHT_BLUE, ANSI_PURPLE,
			ANSI_MAGENTA, ANSI_BRIGHT_MAGENTA, ANSI_CYAN, ANSI_BRIGHT_CYAN, ANSI_WHITE });
	private static int currentColorId = 0;

	static {
		Collections.shuffle(colorPool);
	}

	public static synchronized String nextColor() {
		return colorPool.get(Math.abs(currentColorId++) % colorPool.size());
	}
}
