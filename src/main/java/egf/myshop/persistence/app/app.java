package egf.myshop.persistence.app;

import egf.myshop.persistence.entity.Category;
import egf.myshop.persistence.entity.Client;
import egf.myshop.persistence.exception.ShopException;
import egf.myshop.persistence.service.ArticleDataService;
import egf.myshop.persistence.service.PersonalDataService;
import egf.myshop.persistence.util.HibernateUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author espeg
 */
public class app {
    static Scanner sc = new Scanner(System.in);
    //DataService
    static PersonalDataService personalDataService = new PersonalDataService();
    static ArticleDataService articleDataService = new ArticleDataService();

    public static void main(String[] args) {
        //Comprobar conexión
        try {
            HibernateUtil.tryConexion();
        } catch (ShopException e) {
            System.out.println("Error: " +e.getCode()+": "+e.getMessage());
            System.exit(0);
        }

        menu();
    }
    //Menu principal(te va desviando hacia las tablas con las que quieres interactuar)
    private static void menu(){
        boolean salir = false;
        while (!salir){
            System.out.println("***************************************");
            System.out.println("Menu Principal");
            System.out.println("1 Menu clientes");
            System.out.println("2 Menu clientesAmp");
            System.out.println("3 Menu categorias");
            System.out.println("4 Menu articulos");
            System.out.println("***************************************");
            System.out.println();
            System.out.print("Que desea hacer: ");
            try {
                int opcion = sc.nextInt();
                System.out.println();
                switch (opcion) {
                    case 1:
                        menuClientes();
                        break;
                    case 2:
                        menuClientesAmp();
                        break;
                    case 3:
                        menuCategorias();
                        break;
                    case 4:
                        menuArticulos();
                        break;
                    case 5:
                        salir = true;
                        HibernateUtil.getSessionFactory().close();
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }
            }catch (InputMismatchException e){
                System.err.println("Caracter no valido");
                System.exit(0);
            }
        }
    }
    //Menu de la tabla clientes
    private static void menuClientes(){
        System.out.println("***************************************");
        System.out.println("Menu consultas clientes");
        System.out.println("1 Crear cliente");
        System.out.println("2 Eliminar cliente");
        System.out.println("3 Mostrar todos los clientes");
        System.out.println("4 Mostrar un cliente");
        System.out.println("5 Mostrar clientes con información ampliada");
        System.out.println("6 Lista de articulos comprados por un cliente");
        System.out.println("***************************************");
        System.out.print("Que desea hacer: ");
        try {
            int opcion = sc.nextInt();
            switch (opcion){
                case 1: crearC();
                    break;
                case 2: borrarC();
                    break;
                case 3:mostrarClientes();
                    break;
                case 4:mostrarCliente();
                    break;
                case 5:mostrarClientesAmp();
                    break;
                case 6:mostrarArticulosCliente();//no va
                    break;
                default:
                    break;
            }
        }catch (InputMismatchException e){
            System.err.println("Caracter no valido");
            System.exit(0);
        }
    }

    //Menu de la tabla clientesAmp
    private static void menuClientesAmp(){
        System.out.println("***************************************");
        System.out.println("Menu consultas clientesAmp");
        System.out.println("1 Crear clienteAmp no  va");
        System.out.println("2 Eliminar clienteAmp no va");
        System.out.println("3 Mostrar todos los clientesAmp");
        System.out.println("4 Mostrar un clienteAmp");
        System.out.println("***************************************");
        System.out.print("Que desea hacer: ");
        try {
            int opcion = sc.nextInt();
            switch (opcion){
                case 1: crearC();
                    break;
                case 2: borrarClientAmp();
                    break;
                case 3:mostrarClientesAm();
                    break;
                case 4:mostrarClienteA();
                    break;
                default:
                    break;
            }
        }catch (InputMismatchException e){
            System.err.println("Caracter no valido");
            System.exit(0);
        }
    }

    //Menu de la tabla categorias
    private static void menuCategorias(){
        Scanner sc = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("Menu consultas categorias");
        System.out.println("1 Crear categoria");
        System.out.println("3 Eliminar categoria(Esta opción borra los articulos que pertenecen a esa categoria)");
        System.out.println("4 Editar categoria");
        System.out.println("6 Ver todas las categorias");
        System.out.println("7 Lista de articulos que pertenecen a esa categoria");
        System.out.print("Que desea hacer: ");
        int opcion = sc.nextInt();
        switch (opcion){
            case 1: crearCat();
                break;
            case 2: borrarCat();
                break;
            case 3:
                System.out.println();;
                break;
            case 4:
                System.out.println();;
                break;
            default:
                break;
        }
    }

    //Menu de la tabla articulos
    private static void menuArticulos(){
        Scanner sc = new Scanner(System.in);
        System.out.println("***************************************");
        System.out.println("Menu consultas articulos");
        System.out.println("1 Crear articulo");
        System.out.println("2 Eliminar articulo");
        System.out.println("4 Editar articulo");
        System.out.println("5 Guardar articulo");
        System.out.println("6 Ver todos los articulos");
        System.out.println("7 Lista de clientes que han comprado ese articulo");
        System.out.print("Que desea hacer: ");
        int opcion = sc.nextInt();
       /* switch (opcion){
            case 1: crearT();
                break;
            case 2: borrarT();
                break;
            case 3: mostratT();
                break;
            case 4: cambiarC(user);
                break;
            default:
                break;
        }*/
    }

    //metodos de la clase categorias
    static void crearCat(){
        sc.nextLine();
        System.out.print("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Escribe una descripcion: ");
        String descrip = sc.nextLine();
        Category category = new Category(nombre,descrip);
        try {
            if (articleDataService.findByName(nombre)==null){
                articleDataService.crearCat(category);
            }
        } catch (ShopException e) {
            System.out.println(e.getCode()+": "+e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void borrarCat(){
        System.out.print("Inserte el id de la categoria que desea eliminar: ");
        try {
            long id = sc.nextLong();
            System.out.println();
            try {
                articleDataService.eliminarC(id);
            } catch (ShopException e) {
                System.out.println("Error: " +e.getCode()+": "+e.getMessage());
            }
        }catch (Exception e){
            System.err.println("Ha introducido un caracter no valido");
        }
    }

    //metodos de la clase clientes
    static void crearC(){
        sc.nextLine();
        System.out.print("Escribe el nombre: ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()){
            Client client = new Client();
            client.setName(nombre);
            System.out.println(nombre);
            try {
                personalDataService.findByName(nombre);
                personalDataService.crearC(client);
            } catch (ShopException e) {
                System.out.println(e.getCode()+": "+e.getMessage());
            }
        }else {
            System.err.println("Nombre no introducido");
        }
    }

    static void borrarC(){
        sc.nextLine();
        System.out.print("Inserte el id del cliente que desea eliminar: ");
        try {
            long id = sc.nextLong();
            System.out.println();
            try {
                personalDataService.eliminarC(id);
            } catch (ShopException e) {
                System.out.println("Error: " +e.getCode()+": "+e.getMessage());
            }
        }catch (Exception e){
            System.err.println("Ha introducido un caracter no valido");
        }
    }

    static void mostrarCliente(){
        sc.nextLine();
        System.out.print("Inserte el id del cliente que desea ver: ");
        long id = sc.nextLong();
        System.out.println(personalDataService.findByIdClt(id));
    }
    static void mostrarClientes(){
        System.out.println("Lista de clientes:");
        System.out.println("*************************************");
        personalDataService.mostrarClientes();
    }
     static void mostrarClientesAmp(){
         sc.nextLine();
         System.out.print("Inserte el id del cliente que desea visualizar: ");
             long id = sc.nextLong();
             System.out.println();
        System.out.print("El cliente: ");
        System.out.println(personalDataService.findByIdClt(id));
        personalDataService.mostrarClienteAmp(id);
    }

    static void mostrarArticulosCliente(){
        sc.nextLine();
        System.out.print("Inserte el id del cliente qdel que desea visualizar los articulos: ");
        long id = sc.nextLong();
        System.out.println();
        personalDataService.mostrarArticulosDeUnCliente(id);
    }

    //metodos de la clase clientes
  /*  static void crearClientAmp(){
        sc.nextLine();
        System.out.print("Escribe la direccion: ");
        String dir = sc.nextLine();
        System.out.print("Escribe el telefono: ");
        String tlf = sc.nextLine();
        System.out.print("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Escribe el id del cliente: ");
        Long id = sc.nextLong();
        try {
           Client c = (Client) personalDataService.findById(id);
            ClientAmp client = new ClientAmp(dir,tlf,nombre,c);
        }catch (Exception e){
            System.err.println("No existe el cliente con esa id");
        }
        try {
            personalDataService.findByName(nombre);
            personalDataService.crearClientAmp(client);
        } catch (ShopException e) {
            System.out.println(e.getCode()+": "+e.getMenssage());
        }
    }*/

    static void borrarClientAmp(){
        sc.nextLine();
        System.out.print("Inserte el id del clienteAMp que desea eliminar: ");
        try {
            long id = sc.nextLong();
            System.out.println();
            try {
                personalDataService.eliminarClientAmp(id);
            } catch (ShopException e) {
                System.out.println("Error: " +e.getCode()+": "+e.getMessage());
            }
        }catch (Exception e){
            System.err.println("Ha introducido un caracter no valido");
        }
    }
    static void mostrarClienteA(){
        sc.nextLine();
        System.out.print("Inserte el id del cliente que desea ver: ");
        long id = sc.nextLong();
        System.out.println(personalDataService.findByIdCltA(id));
    }
    static void mostrarClientesAm(){
        personalDataService.mostrarClientesAmp();
    }


}
