/**
 * 
 */
package fab.life;

import java.util.Random;

/**
 * @author Fabrizio
 *
 */
public class Life {

    private boolean[][] lattice;
    private boolean[][] backbuffer;
    private int x;
    private int y;
    //private int maxCycles; //not yet implemented
    
    
    Life()
    {
        init(32,32,200);
    }
    
    Life(int x, int y)
    {
        init(x,y,200);
    }
    
    private void init(int x, int y, int cycles)
    {
        this.lattice = new boolean[x][y];
        this.backbuffer = new boolean[x][y];
        this.x = x;
        this.y = y;
        //this.maxCycles = cycles; //not yet implemented
        
        
        //Initialisation of the lattice
        Random r = new Random();
        for(int i=0 ; i<x ; ++i)
        {
            for(int j=0 ; j<y ; ++j)
            {
                lattice[i][j] = r.nextBoolean() ; 
            }
        }
    }
    
    public void play()
    {
        
        for(;;)
        {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
          }
          update();
          print();
        }
        
    }
    
    private void print()
    {
        //console clearing
        for (int i = 0; i < 50; ++i) System.out.println();
        
        for(int i=0 ; i<x ; ++i)
        {
            for(int j=0 ; j<y ; ++j)
            {
                System.out.print( lattice[i][j] ? "o " : "x " ); 
            }
            System.out.println();
        }
        
    }
    
    private void update()
    {
        
        for(int i=0 ; i<x ; ++i)
        {
            for(int j=0 ; j<y ; ++j)
            {
                backbuffer[i][j] = neighbours(i, j); 
            }
        }        
        lattice = backbuffer;
    }
    
    private boolean neighbours(int x, int y)
    {
        //VonNeumann neighborhood (not used)
        
        //          x, y-1 
        // x-1, y   x, y    x+1, y
        //          x, y+1
        
        //Moore neighborhood (not used)
        
        // x-1, y-1  x, y-1    x+1, y-1
        // x-1, y    x, y      x+1, y
        // x-1, y+1  x, y+1    x+1, y+1
        
        
        //WORST FIRST SOLUTION EVER as for code elegance (laziness + short on time)
        short num = 0;
        if(lattice[x][y-1])
        {
            num++;
        }
        if(lattice[x][y+1])
        {
            num++;
        }
        if(lattice[x-1][y])
        {
            num++;
        }
        if(lattice[x+1][y])
        {
            num++;
        }
        
        if(lattice[x][y-1])
        {
            num++;
        }
        if(lattice[x][y+1])
        {
            num++;
        }
        if(lattice[x-1][y])
        {
            num++;
        }
        if(lattice[x+1][y])
        {
            num++;
        }
        
        if(num == 2 || num == 3)
            return true;
        
        return false;
    }
    
    
}
