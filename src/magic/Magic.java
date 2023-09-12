package magic;

import java.util.Random;
import java.util.Scanner;

public class Magic
{
    private int[] pakli;
    
    private final Scanner sc = new Scanner(System.in);
    private final Random rnd = new Random();
    
    public static void main(String[] args)
    {
        new Magic().program();
    }
    
    private void program()
    {
        paklitFeltolt();
        paklitKever();
        jatekMenet();
    }
    
    private void paklitFeltolt()
    {
        pakli = new int[21];
        for (int i = 0; i < pakli.length; i++)
        {
            pakli[i] = i;
        }
    }
    
    private void paklitKever()
    {
        for (int i = pakli.length - 1; i >= 0; i--)
        {
            final int R = rnd.nextInt(i + 1);
            int tmp = pakli[i];
            pakli[i] = pakli[R];
            pakli[R] = tmp;
        }
    }
    
    private void jatekMenet()
    {
        for (int i = 0; i < 3; i++)
        {
            System.out.println(korKiiras());
            paklitUjrarendez(valasztas() - 1);
        }
        System.out.println("Erre a kártyára gondoltál: " + pakli[10]);
    }
    
    private void paklitUjrarendez(int valasztottOszlopIndexe)
    {
        final int[][] oszlopTombok = new int[3][7];
        int i = 0;
        for (int j = 0; j < 7; j++)
        {
            for (int k = 0; k < 3; k++)
            {
                oszlopTombok[k][j] = pakli[i++];
            }
        }
        int[] oszlopTomb = oszlopTombok[(valasztottOszlopIndexe + 1) % 3];
        System.arraycopy(oszlopTomb, 0, pakli, 0, oszlopTomb.length);
        oszlopTomb = oszlopTombok[valasztottOszlopIndexe];
        System.arraycopy(oszlopTomb, 0, pakli, oszlopTomb.length, oszlopTomb.length);
        oszlopTomb = oszlopTombok[(valasztottOszlopIndexe + 2) % 3];
        System.arraycopy(oszlopTomb, 0, pakli, oszlopTomb.length * 2, oszlopTomb.length);
    }
    
    private void hibaUzenet(String uzenet)
    {
        System.out.println("Hiba! " + uzenet);
    }
    
    private String korKiiras()
    {
        String s = "";
        for (int i = 0; i < 7; i++)
        {
            final int iHaromszorosa = i * 3;
            s += String.format("%2d %2d %2d\n", pakli[iHaromszorosa], pakli[iHaromszorosa + 1], pakli[iHaromszorosa + 2]);
        }
        return s;
    }
    
    private int valasztas()
    {
        int oszlopSzam = 0;
        boolean helyesErtek = false;
        while (!helyesErtek)
        {
            try
            {
                System.out.print("Hanyas számú oszlopban van a kártya: ");
                oszlopSzam = Integer.parseInt(sc.nextLine());
                if (oszlopSzam >= 1 && oszlopSzam <= 3)
                {
                    helyesErtek = true;
                }
                else
                {
                    hibaUzenet("A számnak 1 és 3 között kell lennie.");
                }
            }
            catch (NumberFormatException e)
            {
                hibaUzenet("Számot kell megadni.");
            }
        }
        return oszlopSzam;
    }
}
