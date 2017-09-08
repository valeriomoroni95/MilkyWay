package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class FileImportDao {
	
public boolean importSatellite(String filename) {
    	
    	Connection connection = null;
		PreparedStatement statement = null;
		
		String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while ((line = br.readLine()) != null) {

                String[] vect = line.split(csvSplitBy);

                System.out.println(vect[0]);

            }
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return true;
    }

public static void main(String args[]) {
	FileImportDao dao = new FileImportDao();
	dao.importSatellite("/Users/Valerio/Desktop/Basi di Dati e Conoscenza/PROG_DB_2016_2017higal.csv");
		
}

}
    