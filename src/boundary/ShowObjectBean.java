package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.ShowObjectController;

public class ShowObjectBean {
	
	// vettori per la ricerca di oggetti in una mappa. i dati sono tutti castati a String, vanno solo stampati:
	
	private Vector<String[]> clumps; //formato: [clump_id, valoreflusso, erroreflusso, latitudine, longitudine]
	
	private Vector<String[]> sources; //formato: [source_codemap, valoreflusso, erroreflusso, latitudine, longitudine]
	
	private Vector<String> mapNames; //nomi delle mappe caricati dal db, da far scegliere per la ricerca
	float band; // deve essere un float per essere confrontato a null nel DAO, poi viene sistemato
	private String mapName; //la query viene fatta col nome, no prob
	/* NOT NEEDED, riciclare per REQ. 8
	 * private boolean isClump; //l'utente deve scegliere tra ricerca di clump e ricerca di sorgenti nella mappa
	 */
	
	public Vector<String[]> getClumps() {
		return clumps;
	}
	public void setClumps(Vector<String[]> clumps) {
		this.clumps = clumps;
	}
	public Vector<String[]> getSources() {
		return sources;
	}
	
	/*public boolean isClump() {
		return isClump;
	}
	public void setClump(boolean isClump) {
		this.isClump = isClump;
	}*/
	public void setSources(Vector<String[]> sources) {
		this.sources = sources;
	}
	public float getBand() {
		return band;
	}
	public void setBand(float band) {
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
	
	public void importMapNames() throws SQLException { //serve per far scegliere tra i nomi delle varie mappe
		ShowObjectController controller = ShowObjectController.getInstance(this);
		this.mapNames = controller.getMapNames();
	}
	
	
	public void importClumpDatas() throws SQLException {
		
		ShowObjectController controller = ShowObjectController.getInstance(this);
		this.clumps = controller.getClumps(mapName, band);

	}
	public void importSourceDatas() throws SQLException {
		
		ShowObjectController controller = ShowObjectController.getInstance(this);
		this.sources = controller.getSources(mapName, band);

	}
	
	
	public static void main(String args[]) throws SQLException {
		ShowObjectBean bean = new ShowObjectBean();
		bean.importMapNames();
		Vector<String> mapNames = bean.getMapNames();
		System.out.println(mapNames);
				
	
	}
	
	
	
	
	
	
	
	
}
