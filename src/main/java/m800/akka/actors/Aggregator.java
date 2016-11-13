/**
 * Nirlendu Saha
 */

package m800.akka.actors;
 
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class Aggregator extends UntypedActor {

  int count=0;

  public void onReceive(Object message) {
    if (message == "line") {
      this.count = this.count + 1;
    }
    else if (message == "end-of-file"){
      System.out.println("No. of lines : " + Integer.toString(this.count));
    }
    else if (message == "start-of-file"){
      this.count = 0;
    } else {
      unhandled(message);
    }
  }
}