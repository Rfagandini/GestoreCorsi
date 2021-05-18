package it.polito.tdp.corsi.DB;

import java.sql.*;
import java.util.*;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Iscrizione;
import it.polito.tdp.corsi.model.Studente;

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
	
	public List<Studente> getStudentidiCorso(String codicecorso){
		Connection conn;
		List <Studente> ArrayStudente = new ArrayList<Studente>();
		List <Integer> ArrayMatricola = new ArrayList<Integer>();
		String p = " SELECT * FROM iscrizione WHERE codins = ? ";
		
		try {
			conn = DBConnect.getConnection();
			PreparedStatement ps = conn.prepareStatement(p);
			ps.setString(1, codicecorso);
			ResultSet rs = ps.executeQuery();
		//	ps.setString(1, codicecorso);
			while(rs.next()) {
				Integer c = rs.getInt("matricola");
				ArrayMatricola.add(c);
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			
			throw new RuntimeException("There was an error",e);
		}
		
		String t = "SELECT * FROM studente ";		
		try {
			conn = DBConnect.getConnection();
			PreparedStatement ps = conn.prepareStatement(t);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(ArrayMatricola.contains(rs.getInt("matricola"))) {
					
					Studente s = new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
					ArrayStudente.add(s);
				}
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException e) {
			
			throw new RuntimeException("There was an error",e);
			}
		return ArrayStudente;
	}
	
}
