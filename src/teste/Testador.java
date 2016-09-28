package teste;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import data.dao.TrajetoDao;
import data.model.Leitura;
import data.model.Trajeto;
import data.util.Conexao;

public class Testador {
    private static Date end;
	private static Date start;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Conexao connection = new Conexao();
        System.out.println("10 mil registros");
        Leitura.loadResourceToDataBase(connection, 10000);
        String longitude = "116.18613";
        String latitude = "39.914";
          
        
        System.out.println(String.format("Sem indice longitude: %s  latitude: %s  ", longitude, latitude));
        Date start = new Date();
        TrajetoDao.listTrajeto(connection, longitude, latitude);
        Date end = new Date();
        System.out.println(String.format("Total tempo %s milliseconds", end.getTime() - start.getTime()));
        System.out.println(String.format("Com indice longitude: %s  latitude: %s  ", longitude, latitude));
        start = new Date();
        Trajeto.listTrajetoIndice(connection, longitude, latitude);
        end = new Date();
        System.out.println(String.format("Total tempo %s milliseconds", end.getTime() - start.getTime()));
       

        
        System.out.println("\n");
        
        System.out.println("100 mil registros");
        Leitura.loadResourceToDataBase(connection, 100000); 
        System.out.println(String.format("sem indice longitude: %s - latitude: %s ", longitude, latitude));
        start = new Date();
        TrajetoDao.listTrajeto(connection, longitude, latitude);
        end = new Date();
        System.out.println(String.format("total tempo %s milliseconds", end.getTime() - start.getTime()));
        System.out.println(String.format("com indice longitude: %s - latitude: %s  ", longitude, latitude));
        start = new Date();
        TrajetoDao.listTrajetoIndice(connection, longitude, latitude);
        end = new Date();
        System.out.println(String.format("Total tempo %s milliseconds", end.getTime() - start.getTime()));
        System.out.println("");
        System.out.println("\n");
        
       
    }
}
