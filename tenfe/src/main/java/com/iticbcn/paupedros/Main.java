package com.iticbcn.paupedros;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.iticbcn.paupedros.model.Companyia;
import com.iticbcn.paupedros.model.Estacio;
import com.iticbcn.paupedros.model.Trajecte;
import com.iticbcn.paupedros.model.Tren;
import com.iticbcn.paupedros.model.dao.CompanyiaDAO;
import com.iticbcn.paupedros.model.dao.EstacioDAO;
import com.iticbcn.paupedros.model.dao.TrajecteDAO;
import com.iticbcn.paupedros.model.dao.TrenDAO;

public class Main {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Selecciona l'entitat amb la que vols treballar:");
            System.out.println("1. Companyia");
            System.out.println("2. Trajecte");
            System.out.println("3. Estacio");
            System.out.println("4. Tren");
            System.out.println("0. Sortir");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> gestionarCompanyia(scanner);
                case 2 -> gestionarTrajecte(scanner);
                case 3 -> gestionarEstacio(scanner);
                case 4 -> gestionarTren(scanner);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }

        sessionFactory.close();
    }

    private static void gestionarCompanyia(Scanner scanner) {
        CompanyiaDAO companyiaDAO = new CompanyiaDAO(sessionFactory);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió de Companyies:");
            System.out.println("1. Afegir Companyia");
            System.out.println("2. Mostrar Companyia per ID");
            System.out.println("3. Actualitzar Companyia");
            System.out.println("4. Esborrar Companyia");
            System.out.println("0. Tornar");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> afegirCompanyia(scanner, companyiaDAO);
                case 2 -> mostrarCompanyiaPerID(scanner, companyiaDAO);
                case 3 -> actualitzarCompanyia(scanner, companyiaDAO);
                case 4 -> esborrarCompanyia(scanner, companyiaDAO);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }
    }

    private static void gestionarTrajecte(Scanner scanner) {
        TrajecteDAO trajecteDAO = new TrajecteDAO(sessionFactory);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió de Trajectes:");
            System.out.println("1. Afegir Trajecte");
            System.out.println("2. Mostrar Trajecte per ID");
            System.out.println("3. Actualitzar Trajecte");
            System.out.println("4. Esborrar Trajecte");
            System.out.println("5. Mostrar Tots els Trajectes");
            System.out.println("6. Comptar Trajectes per Tren");
            System.out.println("0. Tornar");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 ->
                    afegirTrajecte(scanner, trajecteDAO, new TrenDAO(sessionFactory), new EstacioDAO(sessionFactory));
                case 2 -> mostrarTrajectePerID(scanner, trajecteDAO);
                case 3 -> actualitzarTrajecte(scanner, trajecteDAO);
                case 4 -> esborrarTrajecte(scanner, trajecteDAO);
                case 5 -> mostrarTotsElsTrajectes(trajecteDAO);
                case 6 -> mostrarComptadorTrajectesPerTren(trajecteDAO);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }
    }

    private static void gestionarEstacio(Scanner scanner) {
        EstacioDAO estacioDAO = new EstacioDAO(sessionFactory);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió d'Estacions:");
            System.out.println("1. Afegir Estació");
            System.out.println("2. Mostrar Estació per ID");
            System.out.println("3. Actualitzar Estació");
            System.out.println("4. Esborrar Estació");
            System.out.println("0. Tornar");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> afegirEstacio(scanner, estacioDAO);
                case 2 -> mostrarEstacioPerID(scanner, estacioDAO);
                case 3 -> actualitzarEstacio(scanner, estacioDAO);
                case 4 -> esborrarEstacio(scanner, estacioDAO);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }
    }

    private static void gestionarTren(Scanner scanner) {
        TrenDAO trenDAO = new TrenDAO(sessionFactory);
        boolean sortir = false;

        while (!sortir) {
            System.out.println("Gestió de Trens:");
            System.out.println("1. Afegir Tren");
            System.out.println("2. Mostrar Tren per ID");
            System.out.println("3. Actualitzar Tren");
            System.out.println("4. Esborrar Tren");
            System.out.println("0. Tornar");

            int opcio = scanner.nextInt();
            scanner.nextLine(); // Consumim la línia

            switch (opcio) {
                case 1 -> afegirTren(scanner, trenDAO);
                case 2 -> mostrarTrenPerID(scanner, trenDAO);
                case 3 -> actualitzarTren(scanner, trenDAO);
                case 4 -> esborrarTren(scanner, trenDAO);
                case 0 -> sortir = true;
                default -> System.out.println("Opció invàlida. Siusplau, torna a provar.");
            }
        }
    }

    // Implementació dels mètodes per afegir, mostrar, actualitzar i esborrar
    private static void afegirCompanyia(Scanner scanner, CompanyiaDAO companyiaDAO) {
        System.out.println("Introdueix el nom de la companyia:");
        String nom = scanner.nextLine();
        Companyia companyia = new Companyia(nom);
        companyiaDAO.crearCompanyia(companyia);
        System.out.println("Companyia afegida.");

        System.out.println("Vols afegir tren? (s/n)");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            TrenDAO tdao = new TrenDAO(sessionFactory);
            Tren tren = afegirTrenToCompanyia(scanner, tdao, companyia.getId());
            companyia.addTren(tren);
        }
        System.err.println(companyia);
    }

    private static void mostrarCompanyiaPerID(Scanner scanner, CompanyiaDAO companyiaDAO) {
        System.out.println("Introduce el ID de la compañía:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salto de línea
        Companyia companyia = companyiaDAO.obtenirCompanyia(id);
        if (companyia != null) {
            System.out.println("Compañía encontrada: " + companyia.getNom());
        } else {
            System.out.println("No se ha encontrado ninguna compañía con el ID proporcionado.");
        }
    }

    private static void actualitzarCompanyia(Scanner scanner, CompanyiaDAO companyiaDAO) {
        System.out.println("Introdueix l'ID de la companyia a actualitzar:");
        Long id = scanner.nextLong();
        Companyia companyia = companyiaDAO.obtenirCompanyia(id);
        if (companyia != null) {
            System.out.println("Introdueix el nou nom per a la companyia:");
            scanner.nextLine();
            String nouNom = scanner.nextLine();
            companyia.setNom(nouNom);
            companyiaDAO.actualitzarCompanyia(companyia);
            System.out.println("Companyia actualitzada amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap companyia amb l'ID proporcionat.");
        }
    }

    private static void esborrarCompanyia(Scanner scanner, CompanyiaDAO companyiaDAO) {
        System.out.println("Introdueix l'ID de la companyia a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salt de línia
        Companyia companyia = companyiaDAO.obtenirCompanyia(id);
        if (companyia != null) {
            companyiaDAO.eliminarCompanyia(companyia);
            System.out.println("Companyia eliminada amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap companyia amb l'ID proporcionat.");
        }
    }

    private static void afegirTrajecte(Scanner scanner, TrajecteDAO trajecteDAO, TrenDAO trenDAO,
            EstacioDAO estacioDAO) {
        System.out.println("Introdueix l'origen del trajecte:");
        String origen = scanner.nextLine();

        System.out.println("Introdueix el destí del trajecte:");
        String desti = scanner.nextLine();

        // Creem el trajecte bàsic
        Trajecte trajecte = new Trajecte(origen, desti);

        // Preguntem si es vol associar un tren al trajecte
        System.out.println("Vols associar un tren al trajecte? (s/n):");
        String respostaTren = scanner.nextLine();
        if (respostaTren.equalsIgnoreCase("s")) {
            System.out.println("Introdueix l'ID del tren a associar (o 0 per crear un nou tren):");
            Long trenId = scanner.nextLong();
            scanner.nextLine(); // Consumir salt de línia

            Tren tren;
            if (trenId == 0) {
                // Crear un nou tren
                tren = afegirTren(scanner, trenDAO);
            } else {
                // Obtenir un tren existent
                tren = trenDAO.obtenirTren(trenId);
                if (tren == null) {
                    System.out.println("No s'ha trobat cap tren amb l'ID proporcionat.");
                    return;
                }
            }

            // Associem el tren al trajecte
            trajecte.setTren(tren);
        }

        // Preguntem si es volen afegir estacions al trajecte
        System.out.println("Vols afegir estacions al trajecte? (s/n):");
        String respostaEstacions = scanner.nextLine();
        if (respostaEstacions.equalsIgnoreCase("s")) {
            boolean afegirMesEstacions = true;
            while (afegirMesEstacions) {
                System.out.println("Introdueix l'ID de l'estació a afegir (o 0 per crear una nova estació):");
                Long estacioId = scanner.nextLong();
                scanner.nextLine(); // Consumir salt de línia

                Estacio estacio;
                if (estacioId == 0) {
                    // Crear una nova estació
                    estacio = afegirEstacio(scanner, estacioDAO);
                } else {
                    // Obtenir una estació existent
                    estacio = estacioDAO.obtenirEstacio(estacioId);
                    if (estacio == null) {
                        System.out.println("No s'ha trobat cap estació amb l'ID proporcionat.");
                        continue; // Tornem a preguntar per una estació vàlida
                    }
                }

                // Afegim l'estació al trajecte
                trajecte.getEstacions().add(estacio);

                // Preguntem si es vol afegir una altra estació
                System.out.println("Vols afegir una altra estació? (s/n):");
                String continuar = scanner.nextLine();
                if (!continuar.equalsIgnoreCase("s")) {
                    afegirMesEstacions = false;
                }
            }
        }

        // Guardem el trajecte a la base de dades
        trajecteDAO.crearTrajecte(trajecte);
        System.out.println("Trajecte creat amb èxit.");
    }

    private static void mostrarTrajectePerID(Scanner scanner, TrajecteDAO trajecteDAO) {
        System.out.println("Introdueix l'ID del trajecte:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salt de línia

        Trajecte trajecte = trajecteDAO.obtenirTrajecte(id);
        if (trajecte != null) {
            System.out.println("Trajecte trobat:");
            System.out.println("ID: " + trajecte.getId());
            System.out.println("Origen: " + trajecte.getOrigen());
            System.out.println("Destí: " + trajecte.getDesti());

            // Mostrar el tren associat (si n'hi ha)
            if (trajecte.getTren() != null) {
                System.out.println("Tren associat: " + trajecte.getTren().getModel());
            } else {
                System.out.println("Tren associat: No hi ha tren associat.");
            }

            // Mostrar les estacions associades
            System.out.println("Estacions associades:");
            for (Estacio estacio : trajecte.getEstacions()) {
                System.out.println("- " + estacio.getNom());
            }
        } else {
            System.out.println("No s'ha trobat cap trajecte amb l'ID proporcionat.");
        }
    }

    private static void mostrarTotsElsTrajectes(TrajecteDAO trajecteDAO) {
        List<Trajecte> trajectes = trajecteDAO.obtenirTotsElsTrajectes();
        if (trajectes.isEmpty()) {
            System.out.println("No hi ha cap trajecte disponible.");
        } else {
            System.out.println("Llista de trajectes:");
            for (Trajecte trajecte : trajectes) {
                System.out.println("- " + trajecte);
            }
        }
    }

    private static void mostrarComptadorTrajectesPerTren(TrajecteDAO trajecteDAO) {
        Map<String, Long> comptador = trajecteDAO.countTrajectesPerTren();
        if (comptador.isEmpty()) {
            System.out.println("No hi ha dades disponibles.");
        } else {
            System.out.println("Nombre de trajectes per tren:");
            for (Map.Entry<String, Long> entry : comptador.entrySet()) {
                System.out.println("- Tren: " + entry.getKey() + ", Trajectes: " + entry.getValue());
            }
        }
    }

    private static void actualitzarTrajecte(Scanner scanner, TrajecteDAO trajecteDAO) {
        System.out.println("Introdueix l'ID del trajecte a actualitzar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salt de línia

        Trajecte trajecte = trajecteDAO.obtenirTrajecte(id);
        if (trajecte != null) {
            System.out.println("Introdueix el nou origen del trajecte:");
            String nouOrigen = scanner.nextLine();

            System.out.println("Introdueix el nou destí del trajecte:");
            String nouDesti = scanner.nextLine();

            trajecte.setOrigen(nouOrigen);
            trajecte.setDesti(nouDesti);

            trajecteDAO.actualitzarTrajecte(trajecte);
            System.out.println("Trajecte actualitzat amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap trajecte amb l'ID proporcionat.");
        }
    }

    private static void esborrarTrajecte(Scanner scanner, TrajecteDAO trajecteDAO) {
        System.out.println("Introdueix l'ID del trajecte a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salt de línia

        Trajecte trajecte = trajecteDAO.obtenirTrajecte(id);
        if (trajecte != null) {
            trajecteDAO.eliminarTrajecte(trajecte);
            System.out.println("Trajecte eliminat amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap trajecte amb l'ID proporcionat.");
        }
    }

    private static Estacio afegirEstacio(Scanner scanner, EstacioDAO estacioDAO) {
        System.out.println("Introdueix el nom de l'estació:");
        String nom = scanner.nextLine();

        Estacio estacio = new Estacio(nom);
        estacioDAO.crearEstacio(estacio);
        System.out.println("Estació afegida amb èxit.");
        return estacio;
    }

    private static void mostrarEstacioPerID(Scanner scanner, EstacioDAO estacioDAO) {
        System.out.println("Introdueix l'ID de l'estació:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salt de línia

        Estacio estacio = estacioDAO.obtenirEstacio(id);
        if (estacio != null) {
            System.out.println("Estació trobada: " + estacio.getNom());
        } else {
            System.out.println("No s'ha trobat cap estació amb l'ID proporcionat.");
        }
    }

    private static void actualitzarEstacio(Scanner scanner, EstacioDAO estacioDAO) {
        System.out.println("Introdueix l'ID de l'estació a actualitzar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salt de línia

        Estacio estacio = estacioDAO.obtenirEstacio(id);
        if (estacio != null) {
            System.out.println("Introdueix el nou nom per a l'estació:");
            String nouNom = scanner.nextLine();

            estacio.setNom(nouNom);
            estacioDAO.actualitzarEstacio(estacio);
            System.out.println("Estació actualitzada amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap estació amb l'ID proporcionat.");
        }
    }

    private static void esborrarEstacio(Scanner scanner, EstacioDAO estacioDAO) {
        System.out.println("Introdueix l'ID de l'estació a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consumir salt de línia

        Estacio estacio = estacioDAO.obtenirEstacio(id);
        if (estacio != null) {
            estacioDAO.eliminarEstacio(estacio);
            System.out.println("Estació eliminada amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap estació amb l'ID proporcionat.");
        }
    }

    private static Tren afegirTrenToCompanyia(Scanner scanner, TrenDAO trenDAO, Long id) {
        System.out.println("Introduix el model:");
        String model = scanner.nextLine();

        CompanyiaDAO cdao = new CompanyiaDAO(sessionFactory);
        Companyia comp;

        if (id == null) {
            System.out.println("ID Companyia:");
            id = scanner.nextLong();
            scanner.nextLine();
        }

        comp = cdao.obtenirCompanyia(id);

        Tren tren = new Tren(model, comp);
        trenDAO.crearTren(tren);
        System.out.println("Tren añadido con éxito.");
        return tren;
    }

    private static Tren afegirTren(Scanner scanner, TrenDAO trenDAO) {
        // Cridem a afegirTrenToCompanyia amb id null per reutilitzar codi, Polimorfisme
        return afegirTrenToCompanyia(scanner, trenDAO, null);
    }

    private static void mostrarTrenPerID(Scanner scanner, TrenDAO trenDAO) {
        System.out.println("Introduce el ID del tren:");
        Long id = scanner.nextLong();
        Tren tren = trenDAO.obtenirTren(id);
        if (tren != null) {
            System.out.printf("Tren encontrado: %s\n", tren);
        } else {
            System.out.println("No se ha encontrado ningún tren con el ID proporcionado.");
        }
    }

    private static void actualitzarTren(Scanner scanner, TrenDAO trenDAO) {
        System.out.println("Introdueix l'ID del tren a actualitzar:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Tren tren = trenDAO.obtenirTren(id);
        if (tren != null) {
            System.out.println("Introdueix el nou model per al tren:");
            String nouModel = scanner.nextLine();
            tren.setModel(nouModel);
            trenDAO.actualitzarTren(tren);
            System.out.println("Tren actualitzat amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap tren amb l'ID proporcionat.");
        }
    }

    private static void esborrarTren(Scanner scanner, TrenDAO trenDAO) {
        System.out.println("Introdueix l'ID del tren a eliminar:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Tren tren = trenDAO.obtenirTren(id);
        if (tren != null) {
            trenDAO.eliminarTren(tren);
            System.out.println("Tren eliminat amb èxit.");
        } else {
            System.out.println("No s'ha trobat cap tren amb l'ID proporcionat.");
        }
    }
}