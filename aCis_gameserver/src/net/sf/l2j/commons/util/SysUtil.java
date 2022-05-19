package net.sf.l2j.commons.util;

import java.io.Closeable;
import java.sql.Connection;

import net.sf.l2j.commons.logging.CLogger;

/**
 * A class holding system oriented methods.
 */
public class SysUtil {

	private final static CLogger LOGGER = new CLogger(SysUtil.class.getName());

	private static final int MEBIOCTET = 1024 * 1024;

	/**
	 * @return the used amount of memory the JVM is using.
	 */
	public static long getUsedMemory() {
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / MEBIOCTET;
	}

	/**
	 * @return the maximum amount of memory the JVM can use.
	 */
	public static long getMaxMemory() {
		return Runtime.getRuntime().maxMemory() / MEBIOCTET;
	}

	public static void close(Connection con) {
		if (con != null)
			try {
				con.close();
				con = null;
			} catch (final Throwable e) {
				LOGGER.error("O correu uma falha ao tentar fechar a conexão com o CloseUtil", e);
			}
	}

	public static void close(final Closeable closeable) {
		if (closeable != null)
			try {
				closeable.close();
			} catch (final Throwable e) {
				LOGGER.error(e);
			}
	}

	public static boolean isDigit(String text) {
		if (text == null)
			return false;

		return text.matches("[0-9]+");
	}

}
