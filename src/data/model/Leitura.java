package data.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import data.util.Conexao;

public class Leitura {
    private static  File ARQUIVOS = new File("arquivos");
	private static Object connection;
    
    public static void loadResourceToDataBase(Conexao connection, int numeroRegistros) throws ClassNotFoundException, SQLException {
        ArrayList<File> scriptFiles = new ArrayList<>();
        blowFileStructure(ARQUIVOS.listFiles(), scriptFiles);  
        final ArrayList<Trajeto> trajetoT = new ArrayList<>();
        scriptFiles.forEach(o -> {
            
            try {
                trajetoT.addAll(listTrajeto(o));
            } catch (IOException | ParseException ex) {
                Logger.getLogger(Leitura.class.getName()).log(Level.SEVERE, null, ex);
            }
           }); 
        
       //Insert trajeto e trajeto_indice
        Date startDate = new Date();
        trajetoT.stream().limit(numeroRegistros).forEach(t -> {
            Trajeto.insertTrajeto(t, connection);
			
        });
        Date endDate = new Date();
        System.out.println(String.format("Registros inseridos %s", trajetoT.stream().limit(numeroRegistros).count()));
        System.out.println(String.format("Inicio: %s", startDate, "Termino: %s", endDate));
       
        System.out.println(String.format("Tempo: %s milli sec.", (endDate.getTime() - startDate.getTime()) ));
       }
   
    private static void blowFileStructure(File[] files, ArrayList<File> scriptFiles) {
        for(File file : files) {
            if(file.isDirectory())
                blowFileStructure(file.listFiles(), scriptFiles);
            else 
                scriptFiles.add(file);
        }
    }
    private static ArrayList<Trajeto> listTrajeto(File f) throws FileNotFoundException, IOException, ParseException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<Trajeto> result = new ArrayList<>();
        String line;
        while( ( line = br.readLine()) != null) {
            result.add(Trajeto.parser(line));
        }
        return result;
    }   
}
