/**
 * Nirlendu Saha
 */

package m800.akka;
 
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class FileScanner extends UntypedActor {

  // private final ActorRef fileParser;

  public void onReceive(Object message) {
  //   fileParser = this.getContext().actorOf(Props.create(FileParser.class), "fileParser");
    
  //   if (message == "scan") {
  //     fileParser.tell("parse");
  //   } else {
  //     unhandled(message);
  //   }
    System.out.println("HERE");
  }
}