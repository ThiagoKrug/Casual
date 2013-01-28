package instrumentation;

import com.javamex.classmexer.MemoryUtil;
import control.Carla;
import control.HashMapData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class Instrumentation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMapData hmd = null;
        try {
            hmd = new HashMapData(new Carla(2, 0.1, 0.9), 1);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }

        while (true) {
            System.out.println("Digite 1 para obter o tamanho em memória, ou 2 para acionar o GarbageCollector");
            Scanner sc = new Scanner(System.in);
            int op = sc.nextInt();

            if (op == 1) {
                long noBytes = 0;
                noBytes = MemoryUtil.deepMemoryUsageOf(hmd);
                System.out.println(noBytes);
            } else if(op == 2) {
                System.gc();
                System.out.println("GarbageCollector acionado");
            }
        }
    }
}
