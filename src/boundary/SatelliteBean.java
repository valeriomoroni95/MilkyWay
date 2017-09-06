package boundary;

import java.util.List;
import java.util.Vector;

import entity.Satellite;

public class SatelliteBean {
	
	private Vector<Satellite> satellite = new Vector<Satellite>();
	/*private String name;
	private int id;
	private String start;
	private String duration;
	private List<String> tools;*/
	
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
	
	public void setTools(List<String> tools) {
		this.tools = tools;
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
	
	public List<String> getTools(){
		return this.tools;
	}
	
	public void getSat() {
		SatelliteController controller = SatelliteController.getInstance();
		this.satellite = controller.getSatellitesList();
	}
	
}


