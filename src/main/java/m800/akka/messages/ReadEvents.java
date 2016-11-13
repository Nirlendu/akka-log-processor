/**
 * Nirlendu Saha
 */

package m800.akka.messages;

public class ReadEvents{
	
	private String start = "start-of-file";
	private String line = "line";
	private String end = "end-of-file";

	public String getStart(){
		return this.start;
	}

	public String getLine(){
		return this.line;
	}

	public String getEnd(){
		return this.end;
	}
}