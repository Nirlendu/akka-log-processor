/**
 * Nirlendu Saha
 */
 
package m800.akka;
 
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import m800.akka.messages.Scan;

import m800.akka.actors.FileScanner;
 
public class LogFileScanner {

  private ActorSystem system;
  private ActorRef fileScanner;
 
  public static void main(String[] args) {
    LogFileScanner scanner = new LogFileScanner();
    scanner.initSystem();
  }
 
  public void initSystem() {
    // Create an Akka system
    system = ActorSystem.create("LogFileScannerSytem");
 
    // create the actor
    fileScanner = system.actorOf(new Props(FileScanner.class), "fileScanner");

    Scan scan = new Scan();
    scan.setDirectory("./logs");

    // invoke the actor
    fileScanner.tell(scan);
  }
}