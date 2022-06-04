package net.sf.l2j.gsregistering;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sf.l2j.Config;
import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.LoginServerThread;
import net.sf.l2j.loginserver.GameServerManager;
import net.sf.l2j.loginserver.model.GameServerInfo;

public class GameServerRegisterDocker {

	private static final String COUNT = "select count(*) as count from gameservers";

	public static void main(String[] args) {
		Config.loadGameServerRegistration();

		System.out.println();
		System.out.println();
		System.out.println("                  aCis gameserver registering docker");
		System.out.println("                        ____________________________");

		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(COUNT)) {

			ResultSet rs = ps.executeQuery();

			int id = 0;
			while (rs.next()) {
				id = (Integer.parseInt(rs.getString("count")) + 1);

				byte[] hexId = LoginServerThread.generateHex(16);

				GameServerManager.getInstance().getRegisteredGameServers().put(id, new GameServerInfo(id, hexId));
				GameServerManager.getInstance().registerServerOnDB(hexId, id, "");
				Config.saveHexid(id, new BigInteger(hexId).toString(16), "hexid.txt");

				System.out.println("Server registered under (server " + id + ") hexid.txt.");
				System.out.println("Put this file in /config gameserver folder and rename it 'hexid.txt'.");

			}
		} catch (Exception e) {
			System.out.println("SQL error while cleaning registered server: " + e);
		}
	}

}