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

public class FileParser extends UntypedActor {

  ActorRef aggregator = this.getContext().actorOf(new Props(Aggregator.class), "aggregator");

  public void onReceive(Object message) {
    
    if (message instanceof Parse) {

    	try {
        aggregator.tell("start-of-file");
        Parse messageObject = (Parse) message;
        System.out.println("Reading file " + messageObject.getFile());
        Files.lines(Paths.get(messageObject.getFile()))
                                .forEach(line -> {
                        aggregator.tell("line");
                      });
      } catch (IOException e) {
        System.out.println("Error in reading file. Shutting down all actors..");
        e.printStackTrace();
      }
      aggregator.tell("end-of-file");
    } else {
      unhandled(message);
    }
  }
}