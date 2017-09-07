package entity;

public class Clump {
	
	private int clump_id;
	private double g_lon;
	private double g_lat;
	private double k_temp;
	private double ratio;
	private double surf_dens;
	private int c_type;
	
	public int getClump_id() {
		return clump_id;
	}
	public void setClump_id(int clump_id) {
		this.clump_id = clump_id;
	}
	public double getG_lon() {
		return g_lon;
	}
	public void setG_lon(double g_lon) {
		this.g_lon = g_lon;
	}
	public double getG_lat() {
		return g_lat;
	}
	public void setG_lat(double g_lat) {
		this.g_lat = g_lat;
	}
	public double getK_temp() {
		return k_temp;
	}
	public void setK_temp(double k_temp) {
		this.k_temp = k_temp;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public double getSurf_dens() {
		return surf_dens;
	}
	public void setSurf_dens(double surf_dens) {
		this.surf_dens = surf_dens;
	}
	public int getC_type() {
		return c_type;
	}
	public void setC_type(int c_type) {
		this.c_type = c_type;
	}
	
	
}
