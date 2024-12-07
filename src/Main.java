import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // List array gaji berdasarkan employeeGroup (Golongan Karyawan)
        int[] salaryByGroups = {5000000, 6500000, 9500000};

        // List Array Persentase Lembur
        int[] percentageOvertimeList = {30, 32, 34, 36, 38};

        // Scanner input
        Scanner scanner = new Scanner(System.in);

        // Input Golongan Karyawan
        System.out.print("Masukkan Golongan Karyawan (A/B/C) : ");
        String employeeGroup = scanner.nextLine().toLowerCase();

        // Variabel untuk menyimpan index employeeGroup (golongan karyawan)
        int indexGroups = -1;

        // Menentukan index employeeGroup (golongan karyawan) berdasarkan input
        switch (employeeGroup.toLowerCase()) {
            case "a":
                indexGroups = 0;
                break;
            case "b":
                indexGroups = 1;
                break;
            case "c":
                indexGroups = 2;
                break;
            default:
                System.out.println("Golongan tidak valid!");
                System.exit(0);
        }

        // Input Jam Lembur
        System.out.print("Masukkan Jam Lembur: ");
        int jamLembur = 0;

        try {
            String inputOvertime = scanner.nextLine(); // Baca input sebagai String;
            if (inputOvertime.isBlank()) {
                System.out.println("Jumlah jam lembur tidak boleh kosong!");
                System.exit(0);
            }
            jamLembur = Integer.parseInt(inputOvertime); // Konversi ke integer
            if (jamLembur < 0) {
                System.out.println("Jumlah jam lembur tidak boleh negatif!");
                System.exit(0);
            }
        } catch (NumberFormatException e) {
            System.out.println("Jumlah jam lembur tidak valid!");
            throw new RuntimeException(e);
        }

        // Menghitung gaji lembur
        int basicSalary = salaryByGroups[indexGroups];
        int percentageOvertime = percentageOvertimeList[Math.min(jamLembur, percentageOvertimeList.length) - 1];
        int overtimeSalary = (int)(basicSalary * (percentageOvertime / 100.0));

        // Menghitung total penghasilan
        int totalSalary = basicSalary + overtimeSalary;
        Locale localeID = new Locale("id", "ID");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(localeID);

        // Menampilkan output
        System.out.println("\n--- Rincian Gaji ---");
        System.out.println("Golongan Karyawan: " + (employeeGroup));
        System.out.println("Gaji Pokok: Rp. " + currencyFormat.format(basicSalary));
        System.out.println("Jam Lembur: " + jamLembur + " Jam");
        System.out.println("Rumus Hitungan : (" + currencyFormat.format(basicSalary) + " x (" + percentageOvertime + " / " + "100))");
        System.out.println("Gaji Lembur: Rp. " + currencyFormat.format(overtimeSalary));
        System.out.println("Total Penghasilan: Rp. " + currencyFormat.format(totalSalary));
        scanner.close();
    }
}