/**
 * Nirlendu Saha
 */

package m800.akka.actors;
 
import akka.actor.UntypedActor;

import m800.akka.messages.ReadEvents;

public class Aggregator extends UntypedActor {

    int count=0;
    ReadEvents fileReadEvents = new ReadEvents();

    public void onReceive(Object message) {
        // if start read operation, count set to zero
        if (message.equals(fileReadEvents.getStart())) {
            this.count = 0;
        } 
        // if line read, increment count by one for each line
        else if (message.equals(fileReadEvents.getLine())) {
            this.count = this.count + 1;
        }
        // if ending file read, print the number of lines
        else if (message.equals(fileReadEvents.getEnd())) {
            System.out.println("No. of lines : " + Integer.toString(this.count));
        }
        else {
            unhandled(message);
        }
    }
}