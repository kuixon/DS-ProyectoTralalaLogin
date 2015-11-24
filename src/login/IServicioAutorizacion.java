package login;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServicioAutorizacion extends Remote {
	public boolean iniciarSesion(String username, String password) throws RemoteException;
}
