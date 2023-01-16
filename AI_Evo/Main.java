import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

//makes random choices from the diffrent options in items array
//reuse the top 50% and copys them + mutates them and then uses top 50% again 

public class Main{
    //static variables
    private static data[] items;
    private static dna[] gen;
    private static dna[] BestDna;
    private static int best = 0;
    private static int dnaAmount = 100;
    private static int genAmount = 100;

    public static void main(String[] args) {
        gen = new dna[dnaAmount];
        BestDna = new dna[genAmount];
        
        //initialize dna
        for (int i = 0; i < gen.length;i++){
            gen[i] = new dna();
            System.out.println("");
        }

        //get data from data.txt
        items = new data[24];
        readfile();

        for(int i = 0; i < genAmount; i++){
            System.out.println("\nnew gen!");
            for (dna n : gen) {
                n.fitness(items);
                //System.out.println(n.fitness + " " + n.gram_total);
            }

            //sorts the array based on fitness
            Arrays.sort(gen, new Comparator<dna>() {
               public int compare(dna d1,dna d2){
                return Integer.compare(d1.fitness, d2.fitness);
               }
            });

            //add the best from each gen
            BestDna[best] = gen[gen.length-1].clone();
            best++;

            System.out.println("\nsorted!");

            for (dna n : gen) {
                n.fitness(items);
            }

            //copys the best from each gen
            int j=gen.length-1;
            for(int k=0;k<gen.length/2;k++){
                    gen[k] = gen[j].clone();
                j--;
            }

            //mutates half from prev gen
            for(int l=0;l<gen.length/2;l++){
                gen[l].mutate();
            }
        }

        //prints values from best each gen
        for (int a = 0 ; a < genAmount ; a++){
            BestDna[a].fitness(items);
            System.out.println("gen"+a+" value: "+BestDna[a].fitness +" grams:"+BestDna[a].gram_total);
        } 
        window w = new window(BestDna);
        w.setVisible(true);
    }

    //readsfile
    private static void readfile(){
        try {
            File file = new File("data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int increment = 0;
            while((line = br.readLine()) != null){
                String[] temp = line.split(" ");
                int gram,value;
                gram = Integer.parseInt(temp[1]);
                value = Integer.parseInt(temp[2]);

                items[increment ]= new data(temp[0], gram, value);
                increment++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("file reader not working");
        }
    }
}