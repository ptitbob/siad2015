package fr.univ.tours.siad.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by robert-f on 11/09/2015.
 */
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    @Option(name = "-pu", usage = "alias du contexte de persistance", required = true)
    private String persistanceUnitTarget;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public static void main(String... arguments) {
        LOGGER.info("Debut de traitement de l'injection des données");
        Main main = new Main();
        CmdLineParser cmdLineParser = new CmdLineParser(main);
        try {
            cmdLineParser.parseArgument(arguments);
            try {
                main.intializeEntityManager();
                main.execute();
            } finally {
                main.releaseEntityManager();
            }
        } catch (CmdLineException e) {
            System.err.println("Erreur : " + e.getMessage());
            cmdLineParser.printUsage(System.err);
        }
    }

    private void releaseEntityManager() {
        if (entityManager != null) {
            entityManager.clear();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    private void execute() {

    }

    private void intializeEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnitTarget);
        entityManager = entityManagerFactory.createEntityManager();
    }

}
