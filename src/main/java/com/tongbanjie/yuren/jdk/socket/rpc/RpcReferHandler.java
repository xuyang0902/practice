package com.tongbanjie.yuren.jdk.socket.rpc;
 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;
 
public class RpcReferHandler implements InvocationHandler {
 
	private String host;
	private int port;
 
	public RpcReferHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}
 
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Socket socket = new Socket(host, port);
		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream input = null;
		try {
			output.writeUTF(method.getName());

			output.writeObject(method.getParameterTypes());
 
			output.writeObject(args);
 
			input = new ObjectInputStream(socket.getInputStream());
 
			Object result = input.readObject();
			if (result instanceof Throwable)
				throw (Throwable) result;
 
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			input.close();
			output.close();
			socket.close();
		}
		return null;
	}
 
}