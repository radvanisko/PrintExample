import java.awt.*;
import java.awt.print.*;
import java.util.ArrayList;
import javax.print.*;
import javax.swing.*;

public class PrintArrayListToDefaultPrinter {
    public static void main(String[] args) throws PrinterException {

        PrintService[] printers = PrintServiceLookup.lookupPrintServices(null, null);
        // Print the names of all available printers
        System.out.println("Available printers:");
        for (PrintService printer : printers) {
            System.out.println(printer.getName());
        }
        // Get the default printer
        PrintService defaultPrinter = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("Default  tlaciaren je :" + defaultPrinter);

        // Create a printer job
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintService(defaultPrinter);


         job.printDialog();
         defaultPrinter=job.getPrintService();
        System.out.println("Vybrana tlaciaren je :" + defaultPrinter);


        // Set the printable content
        Printable printableContent = new ArrayListPrintable(getArrayList());
        job.setPrintable(printableContent);

        // Print the content
        try {
            job.print();
            System.out.println(" vytlačene");
            JOptionPane.showMessageDialog(null,"Vytlačené");
        } catch (PrinterException e) {
            System.out.println(" Tlač bola zrušena");
            JOptionPane.showMessageDialog(null,"Tlac bola zrušena");

        }
    }

    // A method that returns an example ArrayList
    public static ArrayList<String> getArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("How");
        list.add("Are");
        list.add("You");
        return list;
    }

    // A class that implements the Printable interface to print the ArrayList
    public static class ArrayListPrintable implements Printable {
        private ArrayList<String> list;

        public ArrayListPrintable(ArrayList<String> list)
        {
            this.list = list;
        }

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            if (pageIndex >= 1) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            int lineHeight = graphics.getFontMetrics().getHeight();
            int y = 20;

            for (String line : list) {
                y += lineHeight;
                graphics.drawString(line, 60, y);
            }

            return Printable.PAGE_EXISTS;
        }
    }
}
