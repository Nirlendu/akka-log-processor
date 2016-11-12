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
import java.util.stream.Stream;
import java.io.IOException;

public class FileParser extends UntypedActor {

  public void onReceive(Object message) {
    
    if (message == "parse") {
      ActorRef aggregator = this.getContext().actorOf(new Props(Aggregator.class), "aggregator");
    	try {
        Files.lines(Paths.get("/Users/nirlendu/Documents/Codes/Assignment/m800/logs/app.log"))
                                .forEach(line -> {
                        aggregator.tell("line");
                      });
      } catch (IOException e) {
        e.printStackTrace();
      }
      aggregator.tell("end-of-file");
    } else {
      unhandled(message);
    }
  }
}