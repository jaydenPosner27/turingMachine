import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class State{
    String name;
    boolean start = false;
    boolean accept = false;
    boolean reject = false;
    List<Transition> transList;
    public State(String name){
        this.name = name;
    }
}
class Transition {
    char readChar;
    int newState;
    char change;
    char direction;
    public Transition(char readChar,int newState,char change,char direction){
        this.readChar = readChar;
        this.newState = newState;
        this.change = change;
        this.direction = direction;
    }
}
public class posner_p1{
    public static void main(String [] args){
Scanner scan = new Scanner(System.in);
List<State> stateList = new ArrayList<State>();
String parts[];
State start;

String line = scan.nextLine();

while(line!=null){
    parts=line.split("\\s+");
    if(parts[0].equals("state")){
        State newState = new State(parts[1]);
        if(parts[2]!=null&&parts[2].equals("start")){
            newState.start = true;
        }
            stateList.add(new State(parts[1]));    
    }
    
    line=scan.nextLine();
}



scan.close();



    }
}