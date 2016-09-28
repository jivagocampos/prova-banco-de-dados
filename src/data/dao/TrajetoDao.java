package data.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import data.util.Conexao;
import data.model.Trajeto;
public class TrajetoDao {


	private static TrajetoDao make(Conexao connection) throws SQLException {
		return new TrajetoDao();
	}

	public static void insertTrajeto(Trajeto t, Conexao connection) throws SQLException {
		connection.setQuery("insert into trajeto (data, taxi_id, longitude, latitude) values (?, ?, ?, ?) ");
		connection.setDate(1, t.getData());
		connection.setInt(2, t.getIdveiculo());
		connection.setFloat(3, t.getLongitude());
		connection.setFloat(4, t.getLatitude());
		connection.executeUpdate();
	}

	public static void insertTrajetoIndice(Trajeto t, Conexao connection) throws SQLException {
		connection.setQuery("insert into trajeto_indice (data, taxi_id, longitude, latitude) values (?, ?, ?, ?) ");
		connection.setDate(1, t.getData());
		connection.setInt(2, t.getIdveiculo());
		connection.setFloat(3, t.getLongitude());
		connection.setFloat(4, t.getLatitude());
		connection.executeUpdate();
	}

	public static <Trajeto> ArrayList<Trajeto> listTrajeto(Conexao connection, float longitude, float latitude)
			throws SQLException, ParseException {
		ArrayList<Trajeto> r = new ArrayList<>();
		String sql = "select id, taxi_id, data, taxi_id, longitude, latitude, from trajeto, where longitude = ?, and latitude = ? ";
		connection.setQuery(sql);
		connection.setFloat(1, longitude);
		connection.setFloat(2, latitude);
		connection.executeQuery();
		while (connection.next()) {
			r.add((Trajeto) make(connection));
		}
		System.out.println("Records Found: " + r.stream().count());
		return r;
	}

	public static <Trajeto> ArrayList<Trajeto> listTrajetoIndice(Conexao connection, float longitude, float latitude)
			throws SQLException, ParseException {
		ArrayList<Trajeto> r = new ArrayList<>();
		String sql = "select id, taxi_id, data, taxi_id, longitude, latitude, from trajeto_indice, where longitude = ?, and latitude = ? ";
		connection.setQuery(sql);
		connection.setFloat(1, longitude);
		connection.setFloat(2, latitude);
		connection.executeQuery();
		while (connection.next()) {
			r.add((Trajeto) make(connection));
		}
		System.out.println("Records Found: " + r.stream().count());
		return r;
	}

	public static void listTrajeto(Conexao connection, String longitude, String latitude) {	
	}

	public static void listTrajetoIndice(Conexao connection, String longitude, String latitude) {	
	}
}
