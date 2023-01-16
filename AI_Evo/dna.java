import java.util.Random;

//dna objekt aka each backpack
public class dna {
    public int fitness,gram_total,value_total;
    public int[] items;
    private Random r;
    
    public dna(){
        r = new Random();
        items = new int[24];
        for (int i = 0;i < items.length-1;i++) {
            if(r.nextInt(10)==1){
                items[i] = 1;
            }
            //System.out.print(items[i]);
        }
    }
    public dna(int[] items){
        r = new Random();
        this.items = items;
    }

    //calculate fitness for next generation
    public void fitness(data[] datas){
        int count=0;
        gram_total=0;
        value_total=0;
        for (int i : items) {
            if(i == 1){
                gram_total += datas[count].gram;
                value_total += datas[count].value;
            }
            count++;
        }
        if(gram_total > 5000){
            fitness = 0;
        } else {
            fitness = value_total;
        }
    }

    public dna clone(){
        return new dna(items.clone());
    }

    //20% chance of mutation and 50% chance of change... 
    public void mutate(){
        for(int i=0;i<items.length;i++){
            if(r.nextInt( 4) == 0){
                if(r.nextBoolean())
                this.items[i] = r.nextInt(2);
            }
        }
    }
}
