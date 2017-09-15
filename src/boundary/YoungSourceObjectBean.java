package boundary;

import controller.SourceController;

public class YoungSourceObjectBean {
	
	int clumpId;
	
	public void importYoungSourceObjectBean() {
		SourceController controller = SourceController.getInstance(this);
		controller.getYoungSourceObject(this.getClumpId());
		this.setClumpId(clumpId);
	}

	public int getClumpId() {
		return clumpId;
	}

	public void setClumpId(int clumpId) {
		this.clumpId = clumpId;
	}

}
