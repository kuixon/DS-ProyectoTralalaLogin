package login;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServicioAutorizacion extends UnicastRemoteObject implements IServicioAutorizacion {
	
	private HashMap<String, String> userPass = new HashMap<>();
	
	protected ServicioAutorizacion() throws RemoteException {
		userPass.put("yo", "123");
		userPass.put("tu", "456");
		userPass.put("el", "789");
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IServicioAutorizacion saServer = new ServicioAutorizacion();
			Naming.rebind(name, saServer);

			System.out.println("- Servicio autorizacion server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("$ Servicio autorizacion server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean iniciarSesion(String username, String password) {
		if(userPass.containsKey(username)) {
			if(userPass.get(username).equals(password)) {
				System.out.println(username + " conectado con exito!!!");
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
}
