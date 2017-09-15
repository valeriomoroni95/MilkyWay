package boundary;

import java.util.Vector;

import controller.SourceController;

public class YoungSourceObjectBean {
	
	private int clumpId;
	private Vector<String[]> yso;
	
	public void importYoungSourceObjectBean() {
		SourceController controller = SourceController.getInstance(this);
		this.yso = controller.getYoungSourceObject(this.getClumpId());
	}

	public int getClumpId() {
		return clumpId;
	}

	public void setClumpId(int clumpId) {
		this.clumpId = clumpId;
	}
	
	public Vector<String[]> getYoungSources(){
		return yso;
	}

}
