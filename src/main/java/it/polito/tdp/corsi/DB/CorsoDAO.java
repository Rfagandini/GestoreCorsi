package it.polito.tdp.corsi.DB;

import java.sql.*;
import java.util.*;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Iscrizione;

public class CorsoDAO {

	public List<Corso> GetCorso(Integer u){
		
		Connection conn;
		String p = "SELECT * FROM corso WHERE pd = ? "; 
		List<Corso> ArrayCorso = new ArrayList<Corso>();
		try {
			conn = DBConnect.getConnection();
			PreparedStatement ps = conn.prepareStatement(p);
			ps.setInt(1, u);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
				ArrayCorso.add(c);
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException("There was an error",e);
		} 
		return ArrayCorso;
	}
	
	public List<Iscrizione> GetnumeroIscrizioni() {
		
		Connection conn;
		String p = " SELECT codins, COUNT(*) AS tot FROM iscrizione GROUP BY codins ";


		List<Iscrizione> ArrayIscrizione = new ArrayList<Iscrizione>();
		try {
			conn = DBConnect.getConnection();
			PreparedStatement ps = conn.prepareStatement(p);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Iscrizione c = new Iscrizione(rs.getString("codins"),rs.getInt("tot"));
				ArrayIscrizione.add(c);
			}
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			throw new RuntimeException("There was an error",e);
		} 
		return ArrayIscrizione;
		
	}
	
}
