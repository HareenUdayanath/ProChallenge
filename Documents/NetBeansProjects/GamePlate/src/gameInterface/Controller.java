package gameInterface;

import java.util.ArrayList;
import java.util.Random;


public class Controller {
    private Random randomCreater;
    private int playerFirstBut;
    
    public Controller(){
        randomCreater = new Random();
        playerFirstBut = -1;        
    }
    
    int easy(ArrayList<Integer> rest){
        return randomGiver(rest);
    }
   
    int impossible1(int ar[][],ArrayList<Integer> rest,int button,int count){  
       
        int sn = stoper(ar,2);
        int wn = Winnner(ar,2);
        if(wn>-1){            
            return wn;
        }else if(sn>-1){            
            return sn;
        }
        
        if(button==5&&count==3){            
            setPlayerFirstBut(5);
            int arr[] = {1,3,7,9};           
            return arr[randomCreater.nextInt(4)];
        }else if((button==1||button==9)&&count==5&&playerFirstBut==5){           
            int arr[] = {7,3};
            return arr[randomCreater.nextInt(2)];
        }else if((button==3||button==7)&&count==5&&playerFirstBut==5){                       
            int arr[] = {1,9};
            return arr[randomCreater.nextInt(2)];
        }else if(count==9&&playerFirstBut==5){
            return randomGiver(rest);
        }
        
        if((button==1||button==3||button==7||button==9)&&count==3){            
            setPlayerFirstBut(button);
            return 5;
        }else if((button==1||button==9)&&count==5&&(playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){            
            int arr[] = {4,8,2,6};            
            return arr[randomCreater.nextInt(4)];
        }else if((button==3||button==7)&&count==5&&(playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){            
            int arr[] = {4,2,8,6};
            return arr[randomCreater.nextInt(4)];
        }else if((button==2||button==8||button==4||button==6)&&count==5&&(playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){            
            int arr[] = {1,3,9,7};
            arr = reList(arr, rest, 4);            
            return arr[randomCreater.nextInt(3)];       
        }else if(count==7&&(playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){
            return randomGiver(rest);
        }else if((playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){
            return randomGiver(rest);
        }
        
        if((button==2||button==4||button==6||button==8)&&count==3){           
            setPlayerFirstBut(button);            
            return 5;
        }else if(((button==2||button==4||button==6||button==8)&&count==5)&&(playerFirstBut==2||playerFirstBut==4||playerFirstBut==6||playerFirstBut==8)){
            
            if((playerFirstBut==2&&button==6)||(playerFirstBut==6&&button==2)){
                int arr[] = {1,3,9};                        
                return arr[randomCreater.nextInt(3)];
            }else if((playerFirstBut==8&&button==6)||(playerFirstBut==6&&button==8)){
                int arr[] = {7,3,9};                        
                return arr[randomCreater.nextInt(3)];
            }else if((playerFirstBut==8&&button==4)||(playerFirstBut==4&&button==8)){
                int arr[] = {7,1,9};                        
                return arr[randomCreater.nextInt(3)];
            }else if((playerFirstBut==4&&button==2)||(playerFirstBut==2&&button==4)){
                int arr[] = {7,3,1};                        
                return arr[randomCreater.nextInt(3)];
            }else{
                int arr[] = {9,7,3,1};                        
                return arr[randomCreater.nextInt(4)];
            }
        }else if((playerFirstBut==2||playerFirstBut==4||playerFirstBut==6||playerFirstBut==8)){            
            return randomGiver(rest);
        }        
        return -1;
    }
    
    int impossible2(int ar[][],ArrayList<Integer> rest,int button,int count){  
       
        int sn = stoper(ar,2);
        int wn = Winnner(ar,2);
        if(wn>-1){            
            return wn;
        }else if(sn>-1){            
            return sn;
        }  
        
        if((button==1||button==3||button==7||button==9)&&count==4){     
            setPlayerFirstBut(button);
            if(button==1){
                return 9;
            }else if(button==3){
                return 7;
            }else if(button==9){
                return 1; 
            }else{
                return 3;
            }
        }else if((button==2||button==6||button==8||button==4)&&count==4){
            setPlayerFirstBut(button);
            int arr[] = {1,3,7,9};
            return arr[randomCreater.nextInt(4)];
        }else if((playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){
            return randomGiver(rest);
        }
        
        if((button==2||button==4||button==6||button==8)&&count==4){     
            setPlayerFirstBut(button);   
            int arr[] = {1,3,7,9};
            return arr[randomCreater.nextInt(4)];            
        }else if((playerFirstBut==2||playerFirstBut==4||playerFirstBut==6||playerFirstBut==8)&&count==6){
            if(!rest.contains(4)&&!rest.contains(9)||(!rest.contains(8)&&!rest.contains(1))){
                return 3;
            }else if((!rest.contains(4)&&!rest.contains(3))||(!rest.contains(2)&&!rest.contains(7))){
                return 9;
            }else if(!rest.contains(6)&&!rest.contains(7)||(!rest.contains(8)&&!rest.contains(3))){
                return 1;
            }else if(!rest.contains(6)&&!rest.contains(1)||(!rest.contains(2)&&!rest.contains(9))){
                return 7;
            }            
        }        
        return -1;
    }
    
    int hard1(int ar[][],ArrayList<Integer> rest,int button,int count){  
       
        int sn = stoper(ar,2);
        int wn = Winnner(ar,2);
        if(wn>-1){            
            return wn;
        }else if(sn>-1){            
            return sn;
        }
        
        if(button==5&&count==3){            
            setPlayerFirstBut(5);
            int arr[] = {1,3,7,9};           
            return arr[randomCreater.nextInt(4)];
        }else if((button==1||button==9)&&count==5&&playerFirstBut==5){            
            int arr[] = {7,3,9,1}; 
            arr = restList(arr,rest);
            return arr[randomCreater.nextInt(arr.length)];
        }/*else if((button==3||button==7)&&count==5&&playerFirstBut==5){
            int arr[] = {7,3,9,1};  
            arr = restList(arr,rest);
            return arr[randomCreater.nextInt(arr.length)];
        }else if(count==9&&playerFirstBut==5){
            return randomGiver(rest);
        */
        
        if((button==1||button==3||button==7||button==9)&&count==3){     
            setPlayerFirstBut(button);
            return 5;
        }else if((button==1||button==9)&&count==5&&(playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){
            int arr[] = {7,3,9,1}; 
            arr = restList(arr,rest);
            return arr[randomCreater.nextInt(arr.length)];
        }else if((button==3||button==7)&&count==5&&(playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){
            int arr[] = {7,3,9,1}; 
            arr = restList(arr,rest);
            return arr[randomCreater.nextInt(arr.length)];       
        }/*else if((playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){
            return randomGiver(rest);
        }*/
        
       /* if((button==2||button==4||button==6||button==8)&&count==3){     
            setPlayerFirstBut(button);            
            return 5;
        }else if((playerFirstBut==2||playerFirstBut==4||playerFirstBut==6||playerFirstBut==8)){
            return randomGiver(rest);
        } */       
        return randomGiver(rest);
    }
    
    int hard2(int ar[][],ArrayList<Integer> rest,int button,int count){  
       
        int sn = stoper(ar,2);
        int wn = Winnner(ar,2);
        if(wn>-1){            
            return wn;
        }else if(sn>-1){            
            return sn;
        }  
        //first paressed {1,3,9,7,5}
        if(button==5&&count==4){
            setPlayerFirstBut(button);
            int arr[] = {1,3,7,9};
            arr = reList(arr,rest,4);            
            return arr[randomCreater.nextInt(3)];     
        }
        
        if((button==1||button==3||button==7||button==9)&&count==4){     
            setPlayerFirstBut(button);
            if(rest.indexOf(5)!=-1){
                return 5;
            }else{
                int arr[] = {1,3,7,9};
                arr = reList(arr,rest,4);            
                return arr[randomCreater.nextInt(3)]; 
            }                     
        }else if((playerFirstBut==1||playerFirstBut==3||playerFirstBut==7||playerFirstBut==9)){
            return randomGiver(rest);
        }
        
        if((button==2||button==4||button==6||button==8)&&count==4){     
            setPlayerFirstBut(button);   
            int arr[] = {1,3,7,9};
            return arr[randomCreater.nextInt(4)];            
        }else if((playerFirstBut==2||playerFirstBut==4||playerFirstBut==6||playerFirstBut==8)&&count==6){
            if(!rest.contains(4)&&!rest.contains(9)||(!rest.contains(8)&&!rest.contains(1))){
                return 3;
            }else if((!rest.contains(4)&&!rest.contains(3))||(!rest.contains(2)&&!rest.contains(7))){
                return 9;
            }else if(!rest.contains(6)&&!rest.contains(7)||(!rest.contains(8)&&!rest.contains(3))){
                return 1;
            }else if(!rest.contains(6)&&!rest.contains(1)||(!rest.contains(2)&&!rest.contains(9))){
                return 7;
            }            
        }else{
            return randomGiver(rest);
        }       
        return randomGiver(rest);
    }
    
    int Winnner(int ar[][],int comColour){
        int count1;
        int count2;
        int button=-1;
        for(int i=0;i<3;i++){
            count1 = 0;
            count2 = 0;
            for(int j=0;j<3;j++){
                if((ar[i][j]==1)){
                    count1++;
                }else if((ar[i][j]==2)){
                    count2++;
                }else{
                    button = buttonNumberGiver(i,j);
                }
            }           
            if(comColour==1&&count1==2&&count2==0)
                return button;
            else if(comColour==2&&count1==0&&count2==2)
                return button;
        }
        for(int i=0;i<3;i++){
            count1 = 0;
            count2 = 0;
            for(int j=0;j<3;j++){
                if((ar[j][i]==1)){
                    count1++;
                }else if((ar[j][i]==2)){
                    count2++;
                }else{
                    button = buttonNumberGiver(j,i);
                }
            }            
            if(comColour==1&&count1==2&&count2==0)
                return button;
            else if(comColour==2&&count1==0&&count2==2)
                return button;
        }
        count1 = 0;
        count2 = 0; 
        for(int i=0;i<3;i++){               
            if((ar[i][i]==1)){
                count1++;
            }else if((ar[i][i]==2)){
                count2++;
            }else{
                button = buttonNumberGiver(i,i);
            }              
        }       
        if(comColour==1&&count1==2&&count2==0)
            return button;
        else if(comColour==2&&count1==0&&count2==2)
            return button;
        count1 = 0;
        count2 = 0; 
        for(int i=0,j=2;i<3&&j>=0;i++,j--){                
            if((ar[i][j]==1)){
                count1++;
            }else if((ar[i][j]==2)){
                count2++;
            }else{
                button = buttonNumberGiver(i,j);
            }           
        }
        if(comColour==1&&count1==2&&count2==0)
            return button;
        else if(comColour==2&&count1==0&&count2==2)
            return button;      
        return -1;
    }
    int stoper(int ar[][],int comColour){
        int count1;
        int count2;
        int button=-1;
        for(int i=0;i<3;i++){
            count1 = 0;
            count2 = 0;
            for(int j=0;j<3;j++){
                if((ar[i][j]==1)){
                    count1++;
                }else if((ar[i][j]==2)){
                    count2++;
                }else{
                    button = buttonNumberGiver(i,j);
                }
            }           
            if(comColour==2&&count1==2&&count2==0)
                return button;
            else if(comColour==1&&count1==0&&count2==2)
                return button;
        }
        for(int i=0;i<3;i++){
            count1 = 0;
            count2 = 0;
            for(int j=0;j<3;j++){
                if((ar[j][i]==1)){
                    count1++;
                }else if((ar[j][i]==2)){
                    count2++;
                }else{
                    button = buttonNumberGiver(j,i);
                }
            }            
            if(comColour==2&&count1==2&&count2==0)
                return button;
            else if(comColour==1&&count1==0&&count2==2)
                return button;
        }
        count1 = 0;
        count2 = 0; 
        for(int i=0;i<3;i++){               
            if((ar[i][i]==1)){
                count1++;
            }else if((ar[i][i]==2)){
                count2++;
            }else{
                button = buttonNumberGiver(i,i);
            }              
        }       
        if(comColour==2&&count1==2&&count2==0)
            return button;
        else if(comColour==1&&count1==0&&count2==2)
            return button;
        count1 = 0;
        count2 = 0; 
        for(int i=0,j=2;i<3&&j>=0;i++,j--){                
            if((ar[i][j]==1)){
                count1++;
            }else if((ar[i][j]==2)){
                count2++;
            }else{
                button = buttonNumberGiver(i,j);
            }           
        }
        if(comColour==2&&count1==2&&count2==0)
            return button;
        else if(comColour==1&&count1==0&&count2==2)
            return button;      
        return -1;
    }
    int checher(int ar[][]){
        int count1;
        int count2;
        for(int i=0;i<3;i++){
            count1 = 0;
            count2 = 0;
            for(int j=0;j<3;j++){
                if((ar[i][j]==1))
                    count1++;
                if((ar[i][j]==2))
                    count2++;
            }           
            if(count1==3)
                return 1;
            if(count2==3)
                return 2;
        }
        for(int i=0;i<3;i++){
            count1 = 0;
            count2 = 0;
            for(int j=0;j<3;j++){
                if((ar[j][i]==1))
                    count1++;
                if((ar[j][i]==2))
                    count2++;
            }
            if(count1==3)
                return 1;
            if(count2==3)
                return 2;
        }
        if(((ar[0][0]==1)&&(ar[1][1]==1)&&(ar[2][2]==1))||((ar[0][2]==1)&&(ar[1][1]==1)&&(ar[2][0]==1))){
               return 1;
        }
        if(((ar[0][0]==2)&&(ar[1][1]==2)&&(ar[2][2]==2))||((ar[0][2]==2)&&(ar[1][1]==2)&&(ar[2][0]==2))){
               return 2;
        }
        return -1;
    }
    int buttonNumberGiver(int r,int c){
        if(r==0&&c==0){
            return 1;
        }else if(r==0&&c==1){
            return 2;
        }else if(r==0&&c==2){
            return 3;
        }else if(r==1&&c==0){
            return 4;
        }else if(r==1&&c==1){
            return 5;
        }else if(r==1&&c==2){
            return 6;
        }else if(r==2&&c==0){
            return 7;
        }else if(r==2&&c==1){
            return 8;
        }else{
            return 9;
        }
    }
    int randomGiver(ArrayList<Integer> rest){
        return rest.get(randomCreater.nextInt(rest.size()));
    }
    
    int[] reList(int[] arr,ArrayList<Integer> rest,int len){
        int index = 0;
        int[] newL = new int[len-1];
        for(int i=0;i<len;i++){
            if(rest.indexOf(arr[i])!=-1){                
                newL[index++] = arr[i];
            }
        }       
        return newL;
    } 
    
    int[] restList(int[] arr,ArrayList<Integer> rest){
        int index = 0;
        for(int i=0;i<arr.length;i++){
            if(rest.indexOf(arr[i])!=-1){                
                index++;
            }
        }    
        int[] newL = new int[index];
        index = 0;
        for(int i=0;i<arr.length;i++){
            if(rest.indexOf(arr[i])!=-1){                
                newL[index++] = arr[i];
            }
        }       
        return newL;
    } 
    
    /*boolean isInRest(ArrayList<Integer> rest,int itm){
        for(int i=0;i<rest.size();i++){
            if(rest.indexOf(arr[i])!=-1){
                arr[index++] = arr[i];
            }
        }
    }*/

    /**
     * @param playerFirstBut the playerFirstBut to set
     */
    public void setPlayerFirstBut(int playerFirstBut) {
        this.playerFirstBut = playerFirstBut;
    }
}
