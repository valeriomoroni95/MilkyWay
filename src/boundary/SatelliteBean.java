package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.SatelliteController;
import entity.Satellite;

public class SatelliteBean {
	
	private Vector<Satellite> satellites = new Vector<Satellite>();
	private String name;
	private int id;
	private String start;
	private String end;
	private String duration;
	private Vector<String> tools;
	private Vector<String> agencies;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public void setTools(Vector<String> tools) {
		this.tools = tools;
	}
	
	public void setAgencies(Vector<String> agencies) {
		this.agencies = agencies;
	}
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getStart() {
		return this.start;
	}
	
	public String getDuration() {
		return this.duration;
	}
	
	public String getEnd(){
		return this.end;
	}
	
	public void setEnd(String end){
		this.end = end;
	}
	
	public Vector<String> getTools(){
		return this.tools;
	}
	
	public Vector<String> getAgencies(){
		return this.agencies;
	}
	
	public void getSat() throws SQLException {
		SatelliteController controller = SatelliteController.getInstance(this);
		this.satellites = controller.getSatellitesList();
	}
	
	public boolean validate(){
		
		SatelliteController controller = SatelliteController.getInstance(this);
		if( !controller.InsertSatellites(this.name, this.start, this.end, this.tools, this.agencies)){
			return false;
		}
		return true;
		
	}
	
}