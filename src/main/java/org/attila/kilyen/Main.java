package org.attila.kilyen;

import org.attila.kilyen.service.FileReaderService;
import org.attila.kilyen.service.SolutionService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input file: ");
        if (args[0] == null || args[0].trim().isEmpty()) {
            System.out.println("You need to specify a path!");
        } else {
            SolutionService solutionService = new SolutionService(new FileReaderService());
            solutionService.getSolution(args[0]);
        }
    }
}