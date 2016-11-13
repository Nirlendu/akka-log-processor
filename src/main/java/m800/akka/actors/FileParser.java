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

    // Actor to invoke
    ActorRef aggregator = this.getContext().actorOf(new Props(Aggregator.class), "aggregator");

    // Instantiaing ReadEvents class
    ReadEvents fileReadEvents = new ReadEvents();

    public void onReceive(Object message) {

        // if the message is instance of class Parse
        if (message instanceof Parse) {
            try {
                // Invoking the actor on starting the read operation
                aggregator.tell(fileReadEvents.getStart());
                // Typecasting
                Parse messageObject = (Parse) message;
                System.out.println("File Present : " + messageObject.getFile());
                // Invoking the actor on each line read
                Files.lines(Paths.get(messageObject.getFile()))
                .forEach(line -> {
                    aggregator.tell(fileReadEvents.getLine());
                });
            } catch (IOException e) {
                System.out.println("Error in reading file. Shutting down all actors..");
                e.printStackTrace();
            }
            // Invoking the actor on ending the read operation
            aggregator.tell(fileReadEvents.getEnd());
        } else {
            unhandled(message);
        }
    }
}