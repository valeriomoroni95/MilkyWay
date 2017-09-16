package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Vector;

import entity.DistanceComparator;

public class SourceDao {


    public Vector<String[]> findSourcesInMap(String map, Double band) {
        Vector<String[]> v = null;
        Connection connection = null;
        ResultSet result = null;
        v = new Vector<String[]>();
        PreparedStatement pStatement = null;
        Statement statement = null;
        String query = "SELECT distinct s.source_id, fs.value, fs.error, s.source_mapcode, s.latitude, s.longitude, fs.band_resolution FROM source s join flux_source fs on" +
                " s.source_id = fs.source_id join map m on m.map_id = s.map_id WHERE m.name = '" + map + "' ";

        if (band != 0.0)
            query = query + "AND fs.band_resolution = ?"; //da inserire, è un double

        query = query + "order by s.source_id;";
        System.out.println(query);
        try { //TODO check || NB: è per il requisito 5

            DataSource d = new DataSource();
            connection = d.getConnection();
            if (band != 0.0) {
                pStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                pStatement.setDouble(1, band);
                result = pStatement.executeQuery();
            } else {
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                System.out.println("vivo prima execute");
                result = statement.executeQuery(query);
                System.out.println("vivo dopo execute");

            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("ToolDao.java: catch after try");
        }

        String[] s = new String[6];
        try {
            String currSource = "";
            String tempS = null;
            if (result.first()) {
                do {

                    if (band != 0.0) {
                        s = new String[6];

                        s[0] = result.getString("source_mapcode");
                        s[1] = Double.toString(result.getDouble("latitude"));
                        s[2] = Double.toString(result.getDouble("longitude"));
                        s[3] = Double.toString(band);
                        s[4] = Double.toString(result.getDouble("value")); //si fa col toString?
                        s[5] = Double.toString(result.getDouble("error"));
                        v.add(s);
                    } else {

                        tempS = result.getString("source_mapcode");
                        if (!currSource.equals(tempS)) {
                            s = new String[6];
                            s[0] = tempS;
                            s[1] = Double.toString(result.getDouble("latitude"));
                            s[2] = Double.toString(result.getDouble("longitude"));
                            s[3] = Double.toString(result.getDouble("band_resolution"));
                            s[4] = Double.toString(result.getDouble("value")); //si fa col toString?
                            s[5] = Double.toString(result.getDouble("error"));
                            v.add(s);
                            currSource = tempS;

                        } else {
                            s = new String[6];

                            s[0] = " ";
                            s[1] = " ";
                            s[2] = " ";
                            s[3] = Double.toString(result.getDouble("band_resolution"));
                            s[4] = Double.toString(result.getDouble("value")); //si fa col toString?
                            s[5] = Double.toString(result.getDouble("error"));
                            v.add(s);
                        }
                    }
                } while (result.next());
            }


        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return v;

    }
    

    public Vector<String[]> showSourcesInArea(Double latitude, Double longitude, Double lenght, boolean isCircle) {

        //permette di cercare i clump che si trovano in un cerchio o un quadrato centrato in latitude, longitude
        // e con raggio/cateto di lunghezza lenght. Se cerco in un cerchio isCircle = true, rettangolo false
        // l'array di stringhe ritornato nel vettore ha il formato [id, latitudine, longitudine, distanza]

        Vector<String[]> data = new Vector<String[]>();
        String query, condition;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet result;

        try {

            query = "SELECT s.source_mapcode, s.latitude, s.longitude " +
                    "FROM source s;";

            DataSource d = new DataSource();
            connection = d.getConnection();
            pStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = pStatement.executeQuery();
            
            System.out.println("SourceDao.java: result " + result);
            
            String mapCode;
            Double lat, lon, distance;
            if (result == null) {
                return data;
            }
            if (result.first()) {

                do {

                    mapCode = result.getString("source_mapcode");
                    lat = result.getDouble("latitude");
                    lon = result.getDouble("longitude");

                    if (isCircle) {
                        distance = Math.sqrt(Math.pow(latitude - lat, 2) + Math.pow(longitude - lon, 2));
                        if (distance < lenght) {
                            String toPass[] = {mapCode,
                                    Double.toString(lat),
                                    Double.toString(lon),
                                    Double.toString(distance)
                            };
                            data.add(toPass);
                        }
                    } else {
                        if (lat < latitude + lenght / 2 && lat > latitude - lenght / 2 &&
                                lon < longitude + lenght / 2 && lon > longitude - lenght / 2) {
                            distance = Math.sqrt(Math.pow(latitude - lat, 2) + Math.pow(longitude - lon, 2));
                            String toPass[] = {mapCode,
                                    Double.toString(lat),
                                    Double.toString(lon),
                                    Double.toString(distance)
                            };
                            data.add(toPass);

                        }
                    }
                } while (result.next());
            }

        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(data, new DistanceComparator());
        return data;
    }

    public boolean isPresent(String source_mapcode) {
        Connection connection = null;
        PreparedStatement statement = null;

        ResultSet result = null;


        try {

            System.out.println("SourceDao: isPresent.java: sto dopo il try");
            final String query = "SELECT \"source_mapcode\" FROM \"source\" WHERE \"source_mapcode\" = ?;";
            DataSource d = new DataSource();
            System.out.println("SourceDao: isPresent.java: sopravvissuto al Datasource");

            connection = d.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pStatement.setString(1, source_mapcode);
            result = pStatement.executeQuery();
            System.out.println("SourceDao: isPresent.java: ho eseguito la query");


            if (result.first())
                return true;

            result.close();
            pStatement.close();
            connection.close();
            return false;
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return true;
    }

	/* public static void main(String[] args) {
			SourceDao sourceD = new SourceDao();
			Vector<String[]> results = new Vector<String[]>();
			results = sourceD.showSourcesInArea(0.0,42.7,2.0, false);
			for(String[] v : results) {
				for(String k : v)
					System.out.println(k);
			}
	 
	 }*/

/*Trovare tra le sorgenti all’interno della mappa MIPSGAL, 
 * tutte quelle che sono all’interno di uno specifico clump e per una specifica banda. 
 * Un oggetto si dice che appartiene a un clump se la distanza tra la posizione della sorgente  
 * e quella del clump è minore dell’asse maggiore dell’ellisse per la banda selezionata. */

    public Vector<String[]> findSourcesInClump(int clumpId, Double bandRes) {

        Vector<String[]> sources = new Vector<String[]>();
        String query = "SELECT c.g_lat, c.g_lon, e.x_axis, e.y_axis FROM clump c join ellipse e on c.clump_id " +
                "=e.clump_id AND e.band_resolution = ?";
        String query2 = "SELECT s.source_mapcode, s.latitude, s.longitude FROM source s join map m on s.map_id " +
                "= m.map_id where m.name = 'MIPS-GAL' OR m.name = 'MIPSGAL' OR m.name = 'mipsgal' OR m.name = 'mips-gal'" +
                "OR m.name = 'Mipsgal';";
        Double cLatitude = 0.0;
        Double cLongitude = 0.0;
        Double x = 0.0;
        Double y = 0.0;
        Connection connection = null;
        ResultSet result = null;
        PreparedStatement pStatement = null;
        Connection connection2 = null;
        ResultSet result2 = null;
        Statement statement2 = null;
        DataSource d = new DataSource();
        String sourceCode;
        Double sLatitude;
        Double sLongitude;
        String[] toPass = new String[3];

        try {

            connection = d.getConnection();
            pStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pStatement.setDouble(1, bandRes);
            result = pStatement.executeQuery();

            if (result.first()) {
                cLatitude = result.getDouble("g_lat");
                cLongitude = result.getDouble("g_lon");
                x = result.getDouble("x_axis");
                y = result.getDouble("y_axis");
            }
            connection2 = d.getConnection();
            statement2 = connection2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result2 = statement2.executeQuery(query2);
            if (result2.first()) {
                do {
                    sourceCode = result2.getString("source_mapcode");
                    sLatitude = result2.getDouble("latitude");
                    sLongitude = result2.getDouble("longitude");
                    System.out.println("Sono arrivato dentro al do");
                    if (Math.sqrt(Math.pow(sLatitude - cLatitude, 2) +
                            Math.pow(sLongitude - cLongitude, 2)) < Math.max(2 * x, 2 * y)) {
                    	System.out.println("Sono nell'if!");
                        toPass = new String[3];
                        toPass[0] = sourceCode;
                        toPass[1] = Double.toString(sLatitude);
                        toPass[2] = Double.toString(sLongitude);
                        sources.add(toPass);
                    }
                } while (result2.next());
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sources;
    }

    public boolean isInClump(int clumpId, int sourceId, Double bandRes) {

        Vector<String[]> sources = new Vector<String[]>();
        String query = "SELECT c.g_lat, c.g_lon, e.x_axis, e.y_axis FROM clump c join ellipse e on c.clump_id " +
                "=e.clump_id AND e.band_resolution = ?";
        String query2 = "SELECT s.source_mapcode, s.latitude, s.longitude FROM source s join map m on s.map_id " +
                "= m.map_id where m.name = 'MIPS-GAL' OR m.name = 'MIPSGAL' OR m.name = 'mipsgal' OR m.name = 'mips-gal'" +
                "OR m.name = 'Mipsgal' AND s.source_id = " + sourceId + ";";
        Double cLatitude = 0.0;
        Double cLongitude = 0.0;
        Double x = 0.0;
        Double y = 0.0;
        Connection connection = null;
        ResultSet result = null;
        PreparedStatement pStatement = null;
        Connection connection2 = null;
        ResultSet result2 = null;
        Statement statement2 = null;
        DataSource d = new DataSource();
        Double sLatitude;
        Double sLongitude;
        boolean check = false;

        try {

            connection = d.getConnection();
            pStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pStatement.setDouble(1, bandRes);
            result = pStatement.executeQuery();

            if (result.first()) {
                cLatitude = result.getDouble("g_lat");
                cLongitude = result.getDouble("g_lon");
                x = result.getDouble("x_axis");
                y = result.getDouble("y_axis");
            }
            connection2 = d.getConnection();
            statement2 = connection2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result2 = statement2.executeQuery(query2);
            if (result2.first()) {
                sLatitude = result2.getDouble("latitude");
                sLongitude = result2.getDouble("longitude");
                
                if (Math.sqrt(Math.pow(sLatitude - cLatitude, 2) +
                        Math.pow(sLongitude - cLongitude, 2)) < Math.max(2 * x, 2 * y)) {
                    check = true;
                }
                connection.close();

            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean isFluxPresent(int sourceId, Double band) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {

            final String query = "SELECT s.source_id, fs.band_resolution FROM source s join flux_source fs on s.source_id = fs.source_id WHERE s.source_id = ? and fs.band_resolution = ?;";
            DataSource d = new DataSource();
            System.out.println("SourceDao: isFluxPresent.java: sopravvissuto al Datasource");

            connection = d.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pStatement.setInt(1, sourceId);
            pStatement.setDouble(2, band);
            result = pStatement.executeQuery();
            System.out.println("ClumpDao: isPresent.java: ho eseguito la query");


            if (result.first())
                return true;

            result.close();
            pStatement.close();
            connection.close();
            return false;
        } catch (SQLException se) {
            // Errore durante l'apertura della connessione
            se.printStackTrace();
        } catch (Exception e) {
            // Errore nel loading del driver
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return true;
    }

    public Vector<String[]> findYoungSourceObject(int clumpId) {

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        //	 Double[] bands = {70.0, 160.0, 250.0, 350.0, 500.0};
        SourceDao sd = new SourceDao();
        //Vector<String[]> sources = new Vector<String[]>();
        Vector<String[]> yso = new Vector<String[]>();

		/* for(Double b : bands) {
			 Vector<String[]> s1 = new Vector<String[]>();
			 s1 = sd.findSourcesInClump(clumpId, b);
			 for(String[] s : s1) {
				 if(!sources.contains(s)) {
					 sources.add(s);
				 }
			 }
		 }*/

        //ATTENZIONE: LE MAPPE IN MIPSGAL NON HANNO MISURAZIONI A QUELLA BANDA, devo prendere quelle collegate

        try {
            DataSource d = new DataSource();
            connection = d.getConnection();
            statement = connection.createStatement();
            String view45 = "CREATE OR REPLACE VIEW f4_5 AS "
                    + "SELECT s1.source_mapcode, s1.source_id, fs.value from source s1 join source s2 "
                    + "on s2.source_mapcode = s1.source_x join flux_source fs "
                    + "on s2.source_id = fs.source_id "
                    + "WHERE fs.band_resolution = 4.5;";
            statement.executeUpdate(view45);
            String view58 = "CREATE OR REPLACE VIEW f5_8 AS "
                    + "SELECT s1.source_mapcode, s1.source_id, fs.value from source s1 join source s2 "
                    + "on s2.source_mapcode = s1.source_x join flux_source fs "
                    + "on s2.source_id = fs.source_id "
                    + "WHERE fs.band_resolution = 5.8;";
            statement.executeUpdate(view58);
            String view36 = "CREATE OR REPLACE VIEW f3_6 AS "
                    + "SELECT s1.source_mapcode, s1.source_id, fs.value from source s1 join source s2 "
                    + "on s2.source_mapcode = s1.source_x join flux_source fs "
                    + "on s2.source_id = fs.source_id "
                    + "WHERE fs.band_resolution = 3.6;";

            statement.executeUpdate(view36);

            String query = "SELECT s.source_id, s.source_mapcode, s.latitude, s.longitude FROM "
                    + "source s join f3_6 as f1 on s.source_id = f1.source_id join f4_5 as f2 on f1.source_id = f2.source_id "
                    + "join f5_8 as f3 on f3.source_id = f1.source_id "
                    + "WHERE (f2.value - f3.value) > 0.7 AND (f1.value - f2.value) > 0.7 "
                    + "AND (f1.value - f2.value) > (1.4*(f2.value - f3.value - 0.7) + 0.15);";


            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = statement.executeQuery(query);
            if (result.first()) {
                do {
                    int sourceId = result.getInt("source_id");
                    String[] srcs = new String[3];
                    if (sd.isInClump(clumpId, sourceId, 70.0) ||
                            sd.isInClump(clumpId, sourceId, 160.0) ||
                            sd.isInClump(clumpId, sourceId, 250.0) ||
                            sd.isInClump(clumpId, sourceId, 350.0) ||
                            sd.isInClump(clumpId, sourceId, 500.0)) {

                        srcs[0] = result.getString("source_mapcode");
                        srcs[1] = Double.toString(result.getDouble("latitude"));
                        srcs[2] = Double.toString(result.getDouble("longitude"));

                        yso.add(srcs);
                    }

                } while (result.next());
            }

            //cambio strategia, prendo queste sorgentie se sono in un clump a una banda le aggiungo.

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {


        }
        return yso;
    }


    public static void main(String[] args) {
        SourceDao sd = new SourceDao();

        Vector<String[]> sorc = sd.findSourcesInClump(182182,350.0);
        int i = 0;
        for (String[] s : sorc) {
            for (String k : s)
                //System.out.print(k + "    ");
           // System.out.println(i);
            i++;
        }
    }
}

