package free2;

import java.util.*;

public class lasNotasMiHermano {

    public static void tangamandapio(double[] notas, String[] nombres, String[] apellidos) {
        double max = obtenerMaximo(notas);  
        for (int exp = 1; max / exp > 0; exp *= 10) {
            mondongo(notas, nombres, apellidos, exp);
        }
    }

    private static double obtenerMaximo(double[] notas) {
        double max = notas[0];  
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];  
            }
        }
        return max;
    }

    private static void mondongo(double[] notas, String[] nombres, String[] apellidos, int exp) {
        int n = notas.length;
        String[] tempNombres = new String[n];
        String[] tempApellidos = new String[n];
        double[] tempNotas = new double[n];

        int[] count = new int[10]; 

        for (int i = 0; i < n; i++) {
            int digit = (int) (notas[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (int) (notas[i] / exp) % 10;
            tempNombres[count[digit] - 1] = nombres[i];
            tempApellidos[count[digit] - 1] = apellidos[i];
            tempNotas[count[digit] - 1] = notas[i];
            count[digit]--;
        }

        System.arraycopy(tempNombres, 0, nombres, 0, n);
        System.arraycopy(tempApellidos, 0, apellidos, 0, n);
        System.arraycopy(tempNotas, 0, notas, 0, n);
    }

    public static void mostrarListaOrdenada(double[] notas, String[] nombres, String[] apellidos) {
        tangamandapio(notas, nombres, apellidos);
        for (int i = notas.length - 1; i >= 0; i--) {
            System.out.println("Nombre: " + nombres[i] + " " + apellidos[i] + " | Nota: " + notas[i]);
        }
    }

    public static void buscarPorNota(double[] notas, String[] nombres, String[] apellidos, double notaBuscada) {
        boolean encontrado = false;
        System.out.println("Estudiantes con la nota " + notaBuscada + ":");
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] == notaBuscada) {
                System.out.println("Nombre: " + nombres[i] + " " + apellidos[i] + " | Nota: " + notas[i]);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ningún estudiante con esa nota.");
        }
    }

    public static void generarDatosAleatorios(int numEstudiantes) {
        Random random = new Random();
        String[] nombres = {"Melo", "Ana", "Sara", "Maria", "Pedro"};
        String[] apellidos = {"Cruz", "Pascal", "Como", "Rodriguez", "Stacio"};
        
        String[] nombresEstudiantes = new String[numEstudiantes];
        String[] apellidosEstudiantes = new String[numEstudiantes];
        double[] notasEstudiantes = new double[numEstudiantes];

        for (int i = 0; i < numEstudiantes; i++) {
            nombresEstudiantes[i] = nombres[random.nextInt(nombres.length)];
            apellidosEstudiantes[i] = apellidos[random.nextInt(apellidos.length)];
            notasEstudiantes[i] = 4.0 + (10.0 - 4.0) * random.nextDouble();
        }

        mostrarListaOrdenada(notasEstudiantes, nombresEstudiantes, apellidosEstudiantes);
        
        double notaBuscada = 7.5;  
        buscarPorNota(notasEstudiantes, nombresEstudiantes, apellidosEstudiantes, notaBuscada);
    }

    public static void main(String[] args) {
        generarDatosAleatorios(5);
    }
}
