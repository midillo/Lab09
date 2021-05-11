package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void getCountry(Map<Integer, Country> idMap, int annoUtente) {

		String sql = "SELECT c.StateAbb, c.CCode, c.StateNme FROM country c, contiguity b WHERE (c.CCode=b.state1no OR c.CCode=b.state2no) " 
				+ "AND b.conttype=1 AND b.state1no<b.state2no AND b.year<=? GROUP BY c.CCode";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, annoUtente);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(!idMap.containsKey(rs.getInt("c.CCode"))){
					Country c = new Country(rs.getString("c.StateAbb"), rs.getInt("c.CCode"), rs.getString("c.StateNme"));
					idMap.put(c.getCodiceNazione(), c);
				}
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Country> loadAllCountries() {

		String sql = "SELECT * FROM country c";
		List<Country> result = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getString("c.StateAbb"), rs.getInt("c.CCode"), rs.getString("c.StateNme"));
				result.add(c);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Confinanti> getArchi() {

		String sql = "SELECT state1no, state2no FROM contiguity WHERE conttype=1";
		List<Confinanti> result = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Confinanti c = new Confinanti(rs.getInt("state1no"), rs.getInt("state2no"));
				result.add(c);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
}
