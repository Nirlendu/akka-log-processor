/**
 * Nirlendu Saha
 */

package m800.akka.actors;
 
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.*;
import java.io.IOException;

import m800.akka.messages.*;

public class FileScanner extends UntypedActor {

    // The actor to invoke
    ActorRef fileParser = this.getContext().actorOf(new Props(FileParser.class), "fileParser");

    public void onReceive(Object message) {

        //if the message is an intance of class Scan
        if (message instanceof Scan) {
            try{
                // Typecasting the object
                Scan messageObject = (Scan) message;
                // Read the complete directory and invoke file parser for each file
                Files.walk(Paths.get(messageObject.getDirectory()))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList())
                .forEach(filePath -> { 
                Parse parse = new Parse();
                    parse.setFile(filePath.toString());
                    fileParser.tell(parse);
                });
            }catch(IOException e){
                System.out.println("Error in reading directory. Shutting down all actors..");
                e.printStackTrace();
            }
            // Shutting down all actors
            this.getContext().system().shutdown();
        }else {
            unhandled(message);
        }
    }
}