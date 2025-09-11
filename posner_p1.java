import java.io.*;
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
        transList = new ArrayList<>();
    }
}
class Transition {
    String readChar;
    String newState;
    String change;
    String direction;
    public Transition(String readChar,String newState,String change,String direction){
        this.readChar = readChar;
        this.newState = newState;
        this.change = change;
        this.direction = direction;
    }
}
public class posner_p1{
    public static void main(String [] args) throws FileNotFoundException{
List<State> stateList = new ArrayList<>();
String parts[];
State start = new State("-1");
String textFile = args[0];
String [] tape = args[1].split("");
Scanner scan = new Scanner(new File(textFile));
int tot=0;

while(scan.hasNextLine()){
    String line = scan.nextLine();
    parts=line.split("\\s+");
    if(parts[0].equals("state")){
        State newState = new State(parts[1]);
        if(parts.length>2){
            if(parts[2].equals("start")){
                newState.start = true;
            }
            if(parts[2].equals("reject")){
                newState.reject=true;
                start = newState;
            }
            if(parts[2].equals("accept")){
                newState.accept=true;
            }
        }
            stateList.add(newState);    
    }else if(parts[0].equals("transition")){
        for(State state:stateList){
            if(state.name.equals(parts[1])){
                state.transList.add(new Transition(parts[2],parts[3],parts[4],parts[5]));
            }
}
    }
}

int index = 0;


while(true){
    for(Transition tran:start.transList){
        if(tran.readChar.equals(tape[index])){
            tot++;
            tape[index]=tran.change;
            index = switch(tran.direction){
                case "L"->index--;
                case "R"->index++;
                default->index;
            };
            for(State changeState : stateList){
                if(changeState.name.equals(tran.newState)){
                    start = changeState;
                }
            }
        }     
  }
   if(start.accept=true){
            for(String tapeIndex:tape){
            System.out.print(tapeIndex);
            }
            System.out.println(" accept");
            break;
        }
        if(start.reject=true){
            for(String tapeIndex:tape){
            System.out.print(tapeIndex);
            }
            System.out.println(" reject");
            break;
        }
        if(tot>Integer.parseInt(args[2])){
            for(String tapeIndex:tape){
            System.out.print(tapeIndex);
            }
            System.out.println(" quit");
        }
        if(index<0){
            for(String tapeIndex:tape){
            System.out.print(tapeIndex);
            }
            System.out.println(" crash");
        } 

}



scan.close();
    }
}