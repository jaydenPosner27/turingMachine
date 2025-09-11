import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
class State{
    String name;
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
boolean validTransition = false;
String parts[];
State start = new State("-1");
String textFile = args[0];
List<String> tape = new ArrayList<>();
String [] tape1 = args[1].split("");
tape.addAll(Arrays.asList(tape1));
tape.add("_");

Scanner scan = new Scanner(new File(textFile));
int tot=0;

while(scan.hasNextLine()){
    String line = scan.nextLine();
    parts=line.split("\\s+");
    if(parts[0].equals("state")){
        State newState = new State(parts[1]);
        if(parts.length>2){
            if(parts[2].equals("start")){
                start = newState;
            }
            if(parts[2].equals("reject")){
                newState.reject=true;
                
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
        //System.out.println(index);
        
        if(tran.readChar.equals(tape.get(index))){
            tot++;
            validTransition=true;
            if(tape.get(index).equals("_")){tape.add("_");}
            tape.set(index,tran.change);
            if(tran.direction.equals("L")){
                index--;
                //System.out.println("AAA");
            }else if(tran.direction.equals("R")){
                //System.out.println("BBBB");
                index++;
            }
            for(State changeState : stateList){
                if(changeState.name.equals(tran.newState)){
                    start = changeState;
                    break;
                }
            }
            break;
            
        }     
  }
   if(start.accept==true){
            while(tape.get(index).equals("_")!=true){
            System.out.print(tape.get(index));
            index++;
            }
            System.out.println(" accept");
            break;
        }
        if(start.reject==true || validTransition==false){
            while(tape.get(index).equals("_")!=true){
            //System.out.println(tape.get(index).equals("_"));
            System.out.print(tape.get(index));
            index++;
            }
            System.out.println(" reject");
            break;
        }
        if(tot>=Integer.parseInt(args[2])){
            while(tape.get(index).equals("_")!=true){
            System.out.print(tape.get(index));
            index++;
            }
            System.out.println(" quit");
            break;
        }
        if(index<0){
            index=0;
            while(tape.get(index).equals("_")!=true){
            System.out.print(tape.get(index));
            index++;
            }
            System.out.println(" crash");
            break;
        } 
        validTransition = false;
}



scan.close();
    }
}