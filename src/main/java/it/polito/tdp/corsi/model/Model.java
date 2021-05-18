package it.polito.tdp.corsi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.corsi.DB.CorsoDAO;

public class Model {
	
	private CorsoDAO corsoDAO;
	
	public Model(){
		corsoDAO = new CorsoDAO();
	}
	
	public List<Corso> GetCorsiList(Integer i){
		
		List<Corso> ArrayCorso = new ArrayList<Corso>();
		ArrayCorso = corsoDAO.GetCorso(i);
		
		return ArrayCorso;
	}
	
	public String GetCorsibyPeriodo(Integer i){
		
		List<Corso> ArrayCorso = new ArrayList<Corso>();
		ArrayCorso = corsoDAO.GetCorso(i);
		String aux = "";
		
		for(Corso c: ArrayCorso) {
			aux += c.toString()+"\n";
		}
		
		return aux.trim();
	}
	
	public String GetTotIscrittiperCorso(List<Corso> p) {
		
		List<Iscrizione> ArrayIscrizione = new ArrayList<Iscrizione>();
		ArrayIscrizione = corsoDAO.GetnumeroIscrizioni();
		List<Iscrizione> ArrayIscrizionedef = new ArrayList<Iscrizione>();
		String aux = "";
		
		for(Iscrizione i: ArrayIscrizione) {
			for(Corso c: p) {
				if(i.getCodins().equals(c.getCodins())) {
					ArrayIscrizionedef.add(i);
				}
			}
		}
		
		for(Iscrizione c: ArrayIscrizionedef) {
			aux += c.toString()+"\n";
		}
		
		return aux.trim();
		
	}
	
	public boolean PeriodoControl(String p) {
		
		boolean check = false;
		int o = 0;
		
		if(p == null || p.equals("")) {
			return check;
		}
		
		try{
			o = Integer.parseInt(p);
		} catch(NumberFormatException e) {
			throw e;
		}
		if(o < 1 || o > 2 ) {
			return check;
		}
		
		check = true;
		
		return check;
		
	}
	
	public int PeriodoConverter(String p) {
		
		boolean check = false;
		int o = 0;

		try{
			o = Integer.parseInt(p);
		} catch(NumberFormatException e) {
			throw e;
		}
			
		return o;
	}
	
	public String ElencoStudenti(String codicecorso) {
		List<Studente> ArrayStudente = corsoDAO.getStudentidiCorso(codicecorso);
		String aux = "";
		for(int i = 0 ; i < ArrayStudente.size() ; i++) {
			aux += ArrayStudente.get(i).toString()+"\n";
		}
		return aux.trim();
	}
	
	public List<Studente> ElencoperCDS(String codicecorso){
		List<Studente> ArrayStudente = corsoDAO.getStudentidiCorso(codicecorso);
		return ArrayStudente;
	}
	
	public String ElencoCDS(String cds) {
		List<Studente> ArrayStudente = ElencoperCDS(cds);
		List<String> Arraycds = new ArrayList();
		Map<String,Integer> Mapcds = new TreeMap<String,Integer>();
		for(Studente s: ArrayStudente) {
			if(Mapcds.get(s.getCDS())==null) {
				Mapcds.put(s.getCDS(),1);
				Arraycds.add(s.getCDS());
			}
			else {
				Mapcds.put(s.getCDS(),Mapcds.get(s.getCDS())+1);
			}
		}
		String aux = "";
		for(int i = 0; i < Arraycds.size() ; i++) {
			aux += Arraycds.get(i)+" "+Mapcds.get(Arraycds.get(i))+"\n";
		}
		
		
		return aux.trim();
	}
}
