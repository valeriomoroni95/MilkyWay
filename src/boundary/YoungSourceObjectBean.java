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
	public static void main(String[] args) {
		YoungSourceObjectBean yso = new YoungSourceObjectBean();
		yso.setClumpId(182182);
		yso.importYoungSourceObjectBean();
		int i = 0;
		Vector<String[]> sources = yso.getYoungSources();
        for (String[] s : sources) {
            for (String k : s)
                System.out.print(k + "    ");
            System.out.println(i);
            i++;
        }
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
