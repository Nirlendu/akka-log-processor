/**
 * Nirlendu Saha
 */

package m800.akka;
 
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.*;

public class FileScanner extends UntypedActor {

  public void onReceive(Object message) {
    ActorRef fileParser = this.getContext().actorOf(new Props(FileParser.class), "fileParser");
    
    if (message == "scan") {
    	try{
    		Files.walk(Paths.get("/Users/nirlendu/Documents/Codes/Assignment/m800/logs"))
                                .filter(Files::isRegularFile)
                                .collect(Collectors.toList())
                                .forEach(filePath -> { 
					            	fileParser.tell("parse");
					            });
		 }catch(Exception x){
		 	System.out.println("Error in reading directory. Shutting down all actors..");
		 	this.getContext().system().shutdown();
		 }
		this.getContext().system().shutdown();
    }else {
      unhandled(message);
    }
  }
}