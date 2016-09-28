package data.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.util.Conexao;


public class Trajeto {
    private int id;    
    private int idveiculo;
    private Date data;
    private float longitude;
    private float latitude;

    public Trajeto(int id, int idveiculo, Date data, float longitude, float latitude) {
        this.id = id;
        this.idveiculo = idveiculo;
        this.data = data;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public int getId() {
        return id;
    }

    public int getIdveiculo() {
        return idveiculo;
    }

    public Date getData() {
        return data;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }
    
    private Trajeto(String idveiculo, String data, float longitude, float latitude) throws ParseException {
        this(0, Integer.parseInt(idveiculo), parseDate(data), longitude, latitude);
        
    }

    public Trajeto(String string, String string2, String string3, String string4) {

	}

	private static Date parseDate(String data1) throws ParseException {
        return new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(data1);
    }
    
    public static Trajeto parser(String linha) throws ParseException {
        String[] lineSplited = linha.split(",");
        return new Trajeto(lineSplited[0], lineSplited[1], lineSplited[2], lineSplited[3]);
    }
    
    @Override
    public String toString() {
        return idveiculo + "," + data + "," + longitude + "," + latitude;
    }

	public static void insertTrajeto(Trajeto t, Conexao cn) {
		
	}

	public static void listTrajetoIndice(Conexao connection, String longitude2, String latitude2) {
		
	}   
}
