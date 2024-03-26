package pck.tfip.ssfrevision01.ssfrevision01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pck.tfip.ssfrevision01.ssfrevision01.services.JsonService;

@SpringBootApplication
public class Ssfrevision01Application implements CommandLineRunner{
	// json string
	static final Path path = Paths.get("src\\main\\resources\\static\\products.json");
	
	@Autowired
	JsonService jsonService;

	public static void main(String[] args) {
		SpringApplication.run(Ssfrevision01Application.class, args);
	}

	//to initialize the repo with items from json at the start of server
	@Override
	public void run(String... args) throws Exception {
		//read json string
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
			// build the json string
			StringBuilder strBuild = new StringBuilder();
			
			String lines = bufferedReader.readLine();
			while (lines != null) {
				strBuild.append(lines);
				lines = bufferedReader.readLine();
			}

			//calling service to store to repo
			jsonService.saveAllProductsFromArrayToRepo(strBuild.toString());

        } catch (IOException io) {
            io.printStackTrace();
        }
	}

}
