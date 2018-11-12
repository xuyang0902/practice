package com.tongbanjie.yuren.jdk.socket.rpc;
 
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
 
public class RpcFramework {
 
	public static void export(final Object service, int port) throws IOException {
		if (service == null)
			throw new IllegalArgumentException("service instance == null");
 
		if (port <= 0 || port > 65535) {
			throw new IllegalArgumentException("Invalid port " + port);
		}
 
		System.out.println("Export service " + service.getClass().getName() + " on port " + port);
 
		ServerSocket server = new ServerSocket(port);
		for (;;) {
			try {
				final Socket socket = server.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
							String methodName = input.readUTF();

							Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
							Object[] arguments = (Object[]) input.readObject();
 
							ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
 
							Method method = service.getClass().getMethod(methodName, parameterTypes);
							Object result = method.invoke(service, arguments);
							output.writeObject(result);
 
							output.close();
							input.close();
 
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								socket.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
 
					}
				}).start();
 
			} catch (Exception e) {
 
			}
		}
 
	}
 
	public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) {
		if (interfaceClass == null)
			throw new IllegalArgumentException("Interface class == null");
		if (!interfaceClass.isInterface())
			throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
		if (host == null || host.length() == 0)
			throw new IllegalArgumentException("Host == null!");
		if (port <= 0 || port > 65535)
			throw new IllegalArgumentException("Invalid port " + port);
 
		T proxy = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass }, new RpcReferHandler(host, 1234));
 
		return proxy;
	}
}