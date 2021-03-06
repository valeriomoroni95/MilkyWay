package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.ShowObjectController;

public class ShowObjectBean {
	
	// vettori per la ricerca di oggetti in una mappa. i dati sono tutti castati a String, vanno solo stampati:
	
	private Vector<String[]> clumps; //formato: [clump_id, valoreflusso, erroreflusso, latitudine, longitudine]
	
	private Vector<String[]> sources; //formato: [source_codemap, valoreflusso, erroreflusso, latitudine, longitudine]
	
	private Vector<String> mapNames; //nomi delle mappe caricati dal db, da far scegliere per la ricerca
	Double band; // deve essere un float per essere confrontato a null nel DAO, poi viene sistemato
	private String mapName; //la query viene fatta col nome, no prob
	
	public Vector<String[]> getClumps() {
		return clumps;
	}
	public void setClumps(Vector<String[]> clumps) {
		this.clumps = clumps;
	}
	public Vector<String[]> getSources() {
		return sources;
	}
	
	public void setSources(Vector<String[]> sources) {
		this.sources = sources;
	}
	public Double getBand() {
		return band;
	}
	public void setBand(Double band) {
		this.band = band;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	
	public Vector<String> getMapNames() {
		return mapNames;
	}
	public void setMapNames(Vector<String> mapNames) {
		this.mapNames = mapNames;
	}
	
	public void importMapNames() throws SQLException {
		ShowObjectController controller = ShowObjectController.getInstance(this);
		this.mapNames = controller.getMapNames();
	}
	
	
	public void importClumpDatas() throws SQLException {
		
		ShowObjectController controller = ShowObjectController.getInstance(this);
		this.clumps = controller.getClumps(this.mapName, this.band);

	}
	public void importSourceDatas() throws SQLException {
		
		ShowObjectController controller = ShowObjectController.getInstance(this);
		this.sources = controller.getSources(this.mapName, this.band);
		
	}
	
	public static void main(String args[]) throws SQLException {
		ShowObjectBean bean = new ShowObjectBean();
		bean.setMapName("HIGAL");
		bean.setBand(0.0);
		bean.importClumpDatas();
		bean.importSourceDatas();
		Vector<String[]> z = new Vector<String[]>();
		
		z = bean.getClumps();
		System.out.println(z);
		
		
		for(int i = 0; i< z.size(); i++ ) {
			String[] v = z.get(i);
			for(String k : v)
				System.out.print(k  + " ");
			System.out.println("         i:" + i);	
		}
	}
}
