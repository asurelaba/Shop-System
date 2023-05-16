package entities.reflectionops;

import entities.enums.Role;
import entities.people.Employee;
import entities.people.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ObjectStreamException;
import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectShop {
    private static final Logger LOGGER = LogManager.getLogger(ReflectShop.class);

    public void createEmployees() {
        try {
            Class<?> managerClass = Class.forName("entities.people.Manager");

            //display methods and properties
            System.out.println("DeclaredConstructors:: " + Arrays.toString(managerClass.getDeclaredConstructors()));
            System.out.println(" DeclaredMethod:: " + Arrays.toString(managerClass.getDeclaredMethods()));
            System.out.println("Methods:: " + Arrays.toString(managerClass.getMethods()));
            System.out.println("Fields:: " + Arrays.toString(managerClass.getFields()));
            System.out.println("DeclaredFields:: " + Arrays.toString(managerClass.getDeclaredFields()));
            System.out.println("permissions:: " + Modifier.toString(managerClass.getDeclaredField("permissions").getModifiers()));

            //creating objects
            Constructor<?> employeeConstructor = Class.forName("entities.people.Employee")
                    .getDeclaredConstructor(int.class, String.class, String.class, String.class, int.class, Role.class, float.class);
            Object employeeObject = employeeConstructor.newInstance(10, "Manager2", "abcd 1234", "2342342345", 10, Role.STORE_MANAGER, Role.STORE_MANAGER.getBaseSalary());

            Constructor<?> managerConstructor = managerClass.getDeclaredConstructor(Employee.class, String.class);
            Object managerObject = managerConstructor.newInstance((Employee) employeeObject, "this is another store manager");

            //get the method printDetails and get permissions
            Method printDetalsMethod = managerClass.getDeclaredMethod("printDetails");
            if (printDetalsMethod.canAccess(managerObject)) {
                printDetalsMethod.invoke(managerObject);
            }
            Method getPermissionsMethod = managerClass.getDeclaredMethod("getPermissions");
            if (getPermissionsMethod.canAccess(managerObject)) {
                LOGGER.info("Permissions for this employee: " + getPermissionsMethod.invoke((Manager) managerObject));
            }

            //access fields
            Field permissionsField = managerClass.getDeclaredField("permissions");
            if (!permissionsField.canAccess(managerObject)) {
                permissionsField.setAccessible(true);
                LOGGER.info("Permissions is now accessible");
                permissionsField.set(managerObject, "Has been given new permissions by ReflectShop");
                LOGGER.info("Permissions field direct access:: " + permissionsField.get(managerObject));
            }
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException exception) {
            LOGGER.error(exception.getMessage());
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
