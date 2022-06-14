//creating the driver
public class fageneralparser {
    public static void main(String[] args) {
        int[][] transitionTable = {{1, 3}, {2, 5}, {1, 3}, {1, 4}, {5, 3}, {5, 5}};
        char[] inputChars = {'a', 'b'};
        int[] finalStates = {0, 2, 3};
        finiteautomata fa = new finiteautomata(0, finalStates, transitionTable , inputChars, 6);
        System.out.println(fa.validate("aabbb"));
    }
}

class finiteautomata{
    // fa requires the following stuff
    int initialState;
    int[] finalStates;
    int[][] trTable;
    char[] inputChar;
    int noOfStates;

    // constructor
    finiteautomata(int initialState, int[] finalStates, int[][] trTable, char[] inputChar, int noOfStates){
        this.initialState = initialState;
        this.finalStates = finalStates;
        this.trTable = trTable;
        this.inputChar = inputChar;
        this.noOfStates = noOfStates;
    } 

    // creating the transition function
    // currstate and stateinp parameters honge
    // stateinp agar inputchar array (jo ke allowed chars hain, Alphabets) usske pehle index ke char se
    // match hua toh trtable ke [currstate] index ke pehle element ko uthalega aur return karadega
    private int transition(int currState, char stateInp){
        int i = 0;
        for (; i<inputChar.length; i++){
            if(stateInp == inputChar[i]){
                break; //stateinput inputChar ki array main se kisise match hojaye toh wahin break krdo and woh wala index save krlo
            }
        }
        return trTable[currState][i];
    }

    // validate ka function, aik string input lega
    // uss string pe loop chalega and hrr character ko transition function se guzarajayega
    // function main currState ke naam se aik local variable hoga(initial state ke barabar) jo ke hr transition ke saath
    // update hota rahega. Loop khtm hone ke baad uss agar currState ki value belongs to 
    // finalstates toh vaidate returns true otherwise false
    
    boolean validate(String inpWord){
        int currState = initialState;
        for (int i = 0; i<inpWord.length(); i++){
            currState = transition(currState, inpWord.charAt(i));
        }
        for (int fs: finalStates){
            if (currState==fs){
                return true;
            }
        }
        return false;
    }
}