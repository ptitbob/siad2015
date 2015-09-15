package fr.univ.tours.siad.util;

import fr.univ.tours.siad.util.data.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import javax.persistence.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
        initializeRegion();
        initializeDistrict();
        initializeCity();
    }

    private void initializeCity() {
        if (entityManager.createNamedQuery(City.COUNT, Long.class).getSingleResult() == 0) {
            LOGGER.info("Injection des villes");
            EntityTransaction entityTransaction = null;
            File file = new File(getClass().getClassLoader().getResource("correspondance-code-insee-code-postal.csv").getFile());
            Map<String, Region> regionMap = new HashMap<>();
            Map<String, District> districtMap = new HashMap<>();
            Map<String, CityStatus> cityStatusMap = new HashMap<>();
            boolean firstLine = true;
            try (Scanner scanner = new Scanner(file)) {
                entityTransaction = entityManager.getTransaction();
                entityTransaction.begin();
                while (scanner.hasNextLine()) {
                    String cityAsString = scanner.nextLine();
                    LOGGER.info(cityAsString);
                    if (firstLine) {
                        firstLine = !firstLine;
                        continue;
                    }
                    String[] cityAsStringArray = cityAsString.split(";");
                    Region region;
                    District district;
                    CityStatus cityStatus;
                    if ((region = regionMap.get(cityAsStringArray[16])) == null) {
                        try {
                            region = entityManager.createNamedQuery(Region.FIND_BY_INSEEID, Region.class).setParameter(Region.INSEEID, cityAsStringArray[16]).getSingleResult();
                        } catch (NoResultException noResultException) {
                            LOGGER.error("Region non trouvé " + cityAsStringArray[16]);
                            continue;
                        }
                        regionMap.put(region.getInseeId(), region);
                    }
                    if ((district = districtMap.get(cityAsStringArray[15])) == null) {
                        try {
                            district = entityManager.createNamedQuery(District.FIND_BY_INSEEID, District.class).setParameter(District.INSEEID, cityAsStringArray[15]).getSingleResult();
                            districtMap.put(district.getInseeId(), district);
                        } catch (NoResultException noResultException) {
                            LOGGER.error("Departement non trouvé : " + cityAsStringArray[15]);
                            continue;
                        }
                    }
                    if ((cityStatus = cityStatusMap.get(cityAsStringArray[5])) == null) {
                        cityStatus = new CityStatus(cityAsStringArray[5]);
                        entityManager.persist(cityStatus);
                        cityStatusMap.put(cityStatus.getLabel(), cityStatus);
                    }
                    if (region != null && district !=null) {
                        City city = new City(cityAsStringArray, region, district, cityStatus);
                        entityManager.persist(city);
                        for (String zipCode : cityAsStringArray[1].split("/")) {
                            if (zipCode.length() > 6) {
                                LOGGER.error("oups !!!");
                            }
                            entityManager.persist(new ZipCode(city, zipCode));
                        }
                    }
                }
                entityTransaction.commit();
            } catch (FileNotFoundException e) {
                if (entityTransaction != null) {
                    entityTransaction.rollback();
                }
                LOGGER.error("ERREUR : " + e.getMessage());
            }
        }
    }

    private void initializeDistrict() {
        if (entityManager.createNamedQuery(District.COUNT, Long.class).getSingleResult() == 0) {
            LOGGER.info("Injection des departement");
            EntityTransaction entityTransaction = null;
            File file = new File(getClass().getClassLoader().getResource("depts2015.txt").getFile());
            Map<String, Region> regionMap = new HashMap<>();
            boolean firstLine = true;
            try (Scanner scanner = new Scanner(file)) {
                entityTransaction = entityManager.getTransaction();
                entityTransaction.begin();
                while (scanner.hasNextLine()) {
                    String districtAsString = scanner.nextLine();
                    LOGGER.info(districtAsString);
                    if (firstLine) {
                        firstLine = !firstLine;
                        continue;
                    }
                    String[] districtAsStringArray = districtAsString.split("\t");
                    Region region;
                    if ((region = regionMap.get(districtAsStringArray[0])) == null) {
                        region = entityManager.createNamedQuery(Region.FIND_BY_INSEEID, Region.class).setParameter(Region.INSEEID, districtAsStringArray[0]).getSingleResult();
                        regionMap.put(region.getInseeId(), region);
                    }
                    if (region != null) {
                        entityManager.persist(new District(districtAsString.split("\t"), region));
                    }
                }
                entityTransaction.commit();
            } catch (FileNotFoundException e) {
                if (entityTransaction != null) {
                    entityTransaction.rollback();
                }
                LOGGER.error("ERREUR : " + e.getMessage());
            }
        }
    }

    private void initializeRegion() {
        if (entityManager.createNamedQuery(Region.COUNT, Long.class).getSingleResult() == 0) {
            LOGGER.info("Injection des régions");
            EntityTransaction entityTransaction = null;
            File file = new File(getClass().getClassLoader().getResource("reg2015.txt").getFile());
            boolean firstLine = true;
            try (Scanner scanner = new Scanner(file)) {
                entityTransaction = entityManager.getTransaction();
                entityTransaction.begin();
                while (scanner.hasNextLine()) {
                    String regionAsString = scanner.nextLine();
                    LOGGER.info(regionAsString);
                    if (firstLine) {
                        firstLine = !firstLine;
                        continue;
                    }
                    entityManager.persist(new Region(regionAsString.split("\t")));
                }
                entityTransaction.commit();
            } catch (FileNotFoundException e) {
                if (entityTransaction != null) {
                    entityTransaction.rollback();
                }
                LOGGER.error("ERREUR : " + e.getMessage());
            }
        }
    }

    private void intializeEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistanceUnitTarget);
        entityManager = entityManagerFactory.createEntityManager();
    }

}
