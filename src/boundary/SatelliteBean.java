package boundary;

import java.sql.SQLException;
import java.util.Vector;

import controller.SatelliteController;
import entity.Agency;
import entity.Satellite;

public class SatelliteBean {
	
	private Vector<Satellite> satellites;
	private String name;
	private int id;
	private String start;
	private String end;
	private String duration;
	private Vector<String> tools;
	private Vector<String> allTools;
	private Vector<String> agencyNames;
	private Vector<Agency> agencies;
	
	
	public Vector<Satellite> getSatellites() {
		return satellites;
	}

	public void setSatellites(Vector<Satellite> satellites) {
		this.satellites = satellites;
	}

	public void setAgencyNames(Vector<String> agencyNames) {
		this.agencyNames = agencyNames;
	}

	public Vector<String> getAllTools() {
		return allTools;
	}

	public void setAllTools(Vector<String> allTools) {
		this.allTools = allTools;
	}

	public Vector<Agency> getAgencies() {
		return agencies;
	}

	public void setAgencies(Vector<Agency> agencies) {
		this.agencies = agencies;
	}

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
	
	public void setAgenciesNames(Vector<String> agencies) {
		this.agencyNames = agencies;
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
	
	public Vector<String> getAgencyNames(){
		return this.agencyNames;
	}
	
	public void importSatellitesList() throws SQLException {
		SatelliteController controller = SatelliteController.getInstance(this);
		this.satellites = controller.getSatellitesList();
	}
	
	public void importAgenciesList() throws SQLException {
		SatelliteController controller = SatelliteController.getInstance(this);
		this.agencies = controller.getAgencies();
	}
	
	public void importToolsList() throws SQLException {
		SatelliteController controller = SatelliteController.getInstance(this);
		this.allTools = controller.getTools();
	}
	
	public boolean validate(){
		System.out.println("SatelliteBean.java: validate(): " + name + start + end + tools + agencyNames);
		SatelliteController controller = SatelliteController.getInstance(this);
		System.out.println("SatelliteBean.java: validate() controller: " + controller);
		if( !controller.InsertSatellites(this.name, this.start, this.end, this.tools, this.agencyNames)){
			System.out.println("SatelliteBean.java: validate() !controller.InsertSatellites FALSE");
			return false;
		}
		System.out.println("SatelliteBean.java: validate() !controller.InsertSatellites TRUE");
		return true;
		
	}
	
}